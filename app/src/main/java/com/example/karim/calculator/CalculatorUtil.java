package com.example.karim.calculator;

import android.util.SparseArray;
import android.util.SparseIntArray;

public class CalculatorUtil {
    public static SparseIntArray mapButtonToNumbers = new SparseIntArray();
    public static SparseArray<Character> mapButtonToOperator = new SparseArray<>();


    static {
        mapButtonToNumbers.put(R.id.number0, 0);
        mapButtonToNumbers.put(R.id.number1, 1);
        mapButtonToNumbers.put(R.id.number2, 2);
        mapButtonToNumbers.put(R.id.number3, 3);
        mapButtonToNumbers.put(R.id.number4, 4);
        mapButtonToNumbers.put(R.id.number5, 5);
        mapButtonToNumbers.put(R.id.number6, 6);
        mapButtonToNumbers.put(R.id.number7, 7);
        mapButtonToNumbers.put(R.id.number8, 8);
        mapButtonToNumbers.put(R.id.number9, 9);

        mapButtonToOperator.put(R.id.add_btn, '+');
        mapButtonToOperator.put(R.id.minus_btn, '-');
        mapButtonToOperator.put(R.id.multiply_btn, '*');
        mapButtonToOperator.put(R.id.division_btn, '/');


    }

    public static double calculate(double number1, double number2, char operator) {
        switch (operator) {
            case '+':
                return number1 + number2;
            case '-':
                return number1 - number2;
            case '/':
                return number1 / number2;
            case '*':
                return number1 * number2;
        }
        return 0;
    }
}
