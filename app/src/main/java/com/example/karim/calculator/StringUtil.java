package com.example.karim.calculator;

public class StringUtil {

    public static String remveLastIndexOfNumberString(String input) {
        if (input.length() > 1) {
            return input.substring(0, input.length() - 1);
        } else {
            return "0";
        }
    }

    public static String addNumberToRightOfNumber(String input, int number) {
        String addString = input + number;
        return (Double.parseDouble(addString) + ""); // for remove zero before number
    }

}
