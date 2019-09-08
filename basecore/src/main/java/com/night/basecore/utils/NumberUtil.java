package com.night.basecore.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberUtil {
    public static List<Integer> getRandomList(int startIndex, int endIndex, int number){
        if(startIndex>endIndex){
            int tempNumber=endIndex;
            endIndex=startIndex;
            startIndex=tempNumber;
        }
        Random random = new Random(System.currentTimeMillis());
        List<Integer> list = new ArrayList<>();
        while(list.size()<50){
            int tempNumber=random.nextInt(endIndex-startIndex)+startIndex;
            if(!list.contains(tempNumber)){
                list.add(tempNumber);
            }
        }
        return list;
    }
}
