package com.night.api.business.util;

import com.night.api.consts.UrlConsts;
import com.night.api.consts.enums.WordEnums;
import com.night.basecore.utils.DateUtil;
import com.night.basecore.utils.StringUtil;
import com.night.model.entity.WordEntity;
import com.night.model.wrapper.database.CurrentWrapper;
import com.night.model.wrapper.common.WordTranslationWrapper;
import com.night.model.wrapper.common.WordWrapper;
import com.night.model.wrapper.database.WordDataBaseWrapper;

import java.util.ArrayList;
import java.util.List;

public class WordUtil {

    /**
     * 当返回值为0或者-1时，说明该单词应该走向结束状态;0 第一次背记被确定；-1 该单词走完整个背记周期；当返回值不为0时，说明该单词应该走向背记状态
     * @param wrapper
     * @return
     */
    public static int getEexpectedNextDate(CurrentWrapper wrapper) {
        int firstDate = wrapper.getFirstDate();
        int nextDate = wrapper.getNextDate();
        // 预期中应该间隔的天数
        int expectedDayAfterNumber = 1;
        // 在间隔的天数下下一次应该显示该单词的时间
        int expectedNextDate;
        // 当nextDate为空时，表示当前单词为第一次背记
        // 记忆周期 1 2 4 7 15 30 60 100 180
        if (nextDate == 0) {
            if (wrapper.getState() == WordEnums.STATE_FIRST) {
                return 0;
            } else {
                expectedDayAfterNumber = 1;
            }
        } else {
            switch (nextDate - firstDate) {
            case 1:
                // 记忆周期2-4
                expectedDayAfterNumber = 3;
                break;
            case 3:
                // 记忆周期4-7
                expectedDayAfterNumber = 6;
                break;
            case 6:
                // 记忆周期7-15
                expectedDayAfterNumber = 14;
                break;
            case 14:
                // 记忆周期15-30
                expectedDayAfterNumber = 29;
                break;
            case 29:
                // 记忆周期30-60
                expectedDayAfterNumber = 59;
                break;
            case 59:
                // 记忆周期60-100
                expectedDayAfterNumber = 99;
                break;
            case 99:
                // 记忆周期100-180
                expectedDayAfterNumber = 179;
                break;
            case 179:
                // 记忆周期180-结束
                return -1;
            default:
                break;
            }
        }
        return Integer.valueOf(DateUtil.getDateAfter(firstDate, DateUtil.yyyy_MM_dd_number, expectedDayAfterNumber));
    }

    /**
     * 将WordEntity转换为WordWrapper
     * @param wordEntity
     * @return
     */
    public static WordWrapper changeEntityToWrapper(WordEntity wordEntity) {
        WordWrapper wordWrapper = new WordWrapper();
        if (wordEntity == null || StringUtil.isEmpty(wordEntity.getWord_name())) {
            return null;
        }

        String wordPhEnMp3 = wordEntity.getSymbols().get(0).getPh_en_mp3();
        String wordPhAmMp3 = wordEntity.getSymbols().get(0).getPh_am_mp3();
        if (!StringUtil.isEmpty(wordPhEnMp3)) {
            wordPhEnMp3 = UrlConsts.HTTPS + wordPhEnMp3.substring(7);
        }
        if (!StringUtil.isEmpty(wordPhAmMp3)) {
            wordPhAmMp3 = UrlConsts.HTTPS + wordPhAmMp3.substring(7);
        }
        wordWrapper.setWordName(wordEntity.getWord_name());
        wordWrapper.setWordPhEn(wordEntity.getSymbols().get(0).getPh_en());
        wordWrapper.setWordPhAm(wordEntity.getSymbols().get(0).getPh_am());
        wordWrapper.setWordPhEnMp3(wordPhEnMp3);
        wordWrapper.setWordPhAmMp3(wordPhAmMp3);

        List<WordTranslationWrapper> translationWrapperList = new ArrayList<>();
        List<WordEntity.SymbolsBean.PartsBean> wordTranslationList = wordEntity.getSymbols().get(0).getParts();
        for (int i = 0; i < wordTranslationList.size(); i++) {
            WordEntity.SymbolsBean.PartsBean partsBean = wordTranslationList.get(i);
            String part = partsBean.getPart();
            String means = "";
            for (int t = 0; t < partsBean.getMeans().size(); t++) {
                means += partsBean.getMeans().get(t) + "；";
            }
            translationWrapperList.add(new WordTranslationWrapper(part, means));
        }
        wordWrapper.setWordTranslationWrapperList(translationWrapperList);
        wordWrapper.setWordCollectState(WordEnums.NOT_COLLECTED);
        return wordWrapper;
    }

    /**
     * WordEntity转换为WordDataBaseWrapper
     * @param wordEntity
     * @return
     */
    public static WordDataBaseWrapper changeEntityToDataBaseWrapper(WordEntity wordEntity) {
        if (wordEntity == null || StringUtil.isEmpty(wordEntity.getWord_name())) {
            return null;
        }
        String wordPhEnMp3 = wordEntity.getSymbols().get(0).getPh_en_mp3();
        String wordPhAmMp3 = wordEntity.getSymbols().get(0).getPh_am_mp3();
        if (!StringUtil.isEmpty(wordPhEnMp3)) {
            wordPhEnMp3 = UrlConsts.HTTPS + wordPhEnMp3.substring(7);
        }
        if (!StringUtil.isEmpty(wordPhAmMp3)) {
            wordPhAmMp3 = UrlConsts.HTTPS + wordPhAmMp3.substring(7);
        }
        String wordParts = "";
        String wordMeans = "";
        List<WordEntity.SymbolsBean.PartsBean> wordTranslationList = wordEntity.getSymbols().get(0).getParts();
        for (int t = 0; t < wordTranslationList.size(); t++) {
            wordParts += wordTranslationList.get(t).getPart();
            List<String> meansList = wordTranslationList.get(t).getMeans();
            for (int q = 0; q < meansList.size(); q++) {
                wordMeans += (meansList.get(q) + "；");
            }
            wordParts += "#";
            wordMeans += "#";
        }
        return new WordDataBaseWrapper(wordEntity.getWord_name(), wordEntity.getSymbols().get(0).getPh_en(),
                wordPhEnMp3, wordEntity.getSymbols().get(0).getPh_am(), wordPhAmMp3, wordParts, wordMeans,
                WordEnums.NOT_COLLECTED);
    }

    /**
     * WordDataBaseWrapper转换为WordWrapper
     * @param wordDataBaseWrapper
     * @return
     */
    public static WordWrapper changeDataBaseWrapperToWrapper(WordDataBaseWrapper wordDataBaseWrapper) {
        WordWrapper wordWrapper = new WordWrapper();
        wordWrapper.setWordName(wordDataBaseWrapper.getWordName());
        wordWrapper.setWordPhEn(wordDataBaseWrapper.getWordPhEn());
        wordWrapper.setWordPhAm(wordDataBaseWrapper.getWordPhAm());
        wordWrapper.setWordPhEnMp3(wordDataBaseWrapper.getWordPhEnMp3());
        wordWrapper.setWordPhAmMp3(wordDataBaseWrapper.getWordPhAmMp3());
        wordWrapper.setWordCollectState(wordDataBaseWrapper.getWordCollectState());

        String[] parts = wordDataBaseWrapper.getWordParts().split("#");
        String[] means = wordDataBaseWrapper.getWordMeans().split("#");
        List<WordTranslationWrapper> wordTranslationWrapperList = new ArrayList<>();
        for (int t = 0; t < parts.length; t++) {
            WordTranslationWrapper wordTranslationWrapper = new WordTranslationWrapper(parts[t], means[t]);
            wordTranslationWrapperList.add(wordTranslationWrapper);
        }
        wordWrapper.setWordTranslationWrapperList(wordTranslationWrapperList);
        return wordWrapper;
    }

    public static List<String> getWordNameListFromEntity(List<WordEntity> list) {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            nameList.add(list.get(i).getWord_name());
        }
        return nameList;
    }

    public static List<String> getWordNameListFromWrapper(List<WordWrapper> list) {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            nameList.add(list.get(i).getWordName());
        }
        return nameList;
    }

    public static List<String> getWordNameListFromDataBaseWrapper(List<WordDataBaseWrapper> list) {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            nameList.add(list.get(i).getWordName());
        }
        return nameList;
    }

    public static List<String> getWordNameListFromCurrentWrapper(List<CurrentWrapper> list) {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            nameList.add(list.get(i).getWordName());
        }
        return nameList;
    }
}
