package com.night.app.consts;

import com.night.app.R;
import com.night.model.wrapper.baseui.CommonFunctionWrapper;

import java.util.ArrayList;
import java.util.List;

public class BusinessConsts {

    /**
     * @return 账户页功能wrapper
     */
    public static List<CommonFunctionWrapper> getAccountFunctionWrapperList() {
        int[] btnTextIdArr = {R.string.account_function_personal_info_letter,
                R.string.account_function_personal_plan_letter, R.string.account_function_safety_center_letter,
                R.string.account_function_help_center_letter};
        int[] textIdArr = {R.string.account_function_personal_info, R.string.account_function_personal_plan,
                R.string.account_function_safety_center, R.string.account_function_help_center};
        List<CommonFunctionWrapper> dataList = new ArrayList<>();
        for (int i = 0; i < btnTextIdArr.length; i++) {
            dataList.add(new CommonFunctionWrapper(btnTextIdArr[i], textIdArr[i]));
        }
        return dataList;
    }

    /**
     * @return 学习页功能wrapper
     */
    public static List<CommonFunctionWrapper> getStudyFunctionWrapperList() {
        int[] btnTextIdArr = {R.string.study_function_recite_letter, R.string.study_function_select_word_letter,
                R.string.study_function_query_word_letter, R.string.study_function_data_letter,
                R.string.study_function_test_letter, R.string.study_function_pk_letter,
                R.string.study_function_gamble_letter, R.string.study_function_my_word_book_letter};

        int[] textIdArr = {R.string.study_function_recite, R.string.study_function_select_word,
                R.string.study_function_query_word, R.string.study_function_data, R.string.study_function_test,
                R.string.study_function_pk, R.string.study_function_gamble, R.string.study_function_my_word_book};
        List<CommonFunctionWrapper> dataList = new ArrayList<>();
        for (int i = 0; i < btnTextIdArr.length; i++) {
            dataList.add(new CommonFunctionWrapper(btnTextIdArr[i], textIdArr[i]));
        }
        return dataList;
    }

    public static String BOOK_LIBRARY_NAME = "BOOK_LIBRARY_NAME";

    public static String BOOK_CHINESE_NAME = "BOOK_CHINESE_NAME";
}
