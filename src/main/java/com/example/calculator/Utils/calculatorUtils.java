package com.example.calculator.Utils;

public class calculatorUtils {
    public static double identity(double sum, double num, char operator){
        switch (operator){
            case '+':
                sum += num;
                break;
            case '-':
                sum -= num;
                break;
            case '×':
                sum *= num;
                break;
            case '÷':
                sum /= num;
                break;
        }
        return sum;
    }

    public static boolean isInteger(double num){
        return (int)num == num;
    }
}
