package com.example.calculator.calculateModule;

import com.example.calculator.Utils.calculatorUtils;

import java.util.ArrayList;

public class resultCalculate {
    private ArrayList<Double> nums = new ArrayList<>();
    private ArrayList<Character> operators = new ArrayList<>();

    public double normalCalculate(){
        if (operators.isEmpty()){
            return nums.get(0);
        }
        double sum = nums.get(0);
        for (int i = 0; i < operators.size(); i++){
            double num = nums.get(i + 1);
            char operator = operators.get(i);
            sum = calculatorUtils.identity(sum, num, operator);
        }
        return sum;
    }

    public double redicalCalculate(){
        double sum = normalCalculate();
        return Math.sqrt(sum);
    }

    public ArrayList<Double> getNums() {
        return nums;
    }

    public void setNums(ArrayList<Double> nums) {
        this.nums = nums;
    }

    public ArrayList<Character> getOperators() {
        return operators;
    }

    public void setOperators(ArrayList<Character> operators) {
        this.operators = operators;
    }
}
