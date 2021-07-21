package com.kpsec.test.utils;

public class StringUtils {
    public static String[] splitBySeparator(String original_txt, String separator){
        String[] result;
        if(original_txt.indexOf(",") > -1){
            String[] elements = original_txt.split(separator);
            result = new String[elements.length];
            for(int i = 0; i < elements.length; i++){
                result[i] = elements[i];
            }
        }else{
            result = new String[1];
            result[0] = original_txt;
        }
        return result;
    }
}
