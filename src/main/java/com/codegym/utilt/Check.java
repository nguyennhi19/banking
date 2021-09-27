package com.codegym.utilt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isDay(String email){
        String EMAIL_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isEmail(String email){
        String EMAIL_REGEX = "^[a-zA-Z]+[a-zA-Z0-9]*@{1}+[\\w+mail]|[outlook]+.com$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkPhone(String str) throws Exception {
        // Bieu thuc chinh quy mo ta dinh dang so dien thoai
        String reg = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{6}$";

        // Kiem tra dinh dang
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean checkPass(String str) throws Exception {
        // Bieu thuc chinh quy mo ta dinh dang so pass
        String reg = "((?=.*d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!.#$@_+,?-]).{8,50})";

        // Kiem tra dinh dang
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}
