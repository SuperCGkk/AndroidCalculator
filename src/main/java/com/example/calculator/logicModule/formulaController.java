package com.example.calculator.logicModule;

import com.example.calculator.R;
import com.example.calculator.entity.twoStr;

import java.util.ArrayList;
import java.util.HashMap;

public class formulaController {
    private boolean isNum = false;
    private boolean isSymbolPercent = false;

    /**
     * 将之前输入的所有数字整合成一个数字并添加到计算式中
     * @param formula 现有运算式
     * @param num 当前未结算数字
     * @return twoStr类，添加完数字后的formula作为第一个字符串，清空后的num作为第二个字符串
     */
    public twoStr joinNum(String formula, String num, ArrayList<Double> nums){
        formula += num;
        nums.add(Double.parseDouble(num));
        num = "";
        return new twoStr(formula, num);
    }

    /**
     * 将输入数字添加到当前未结算的数字内
     * @param num 当前未结算数字
     * @param id 控件id
     * @param hashMap 存储数字控件id的HashMap
     * @return 添加完数字后的未结算数字
     */
    public String addNum(String num, int id, HashMap<Integer, Character> hashMap){
        this.isNum = true;
        num += hashMap.get(id);
        return num;
    }

    /**
     * 添加运算符
     * @param formula 现有运算式
     * @param id 控件id
     * @param operators 运算符存储数组
     * @param hashMap 存储运算符控件id的HashMap
     * @return 添加运算符过后的运算式
     */
    public String addOperator(String formula, int id, ArrayList<Character> operators , HashMap<Integer, Character> hashMap){
        this.isSymbolPercent = false;
        this.isNum = false;
        Character operator = hashMap.get(id);
        operators.add(operator);
        formula += operator;
        return formula;
    }

    public twoStr addSymbol(String formula, String num, ArrayList<Double> nums){
        this.isSymbolPercent = true;
        nums.add(Double.parseDouble(num) * 0.01);
        formula += num + "%";
        num = "";
        return new twoStr(formula, num);
    }

    public String addSymbol(String formula, ArrayList<Double> nums){
        this.isSymbolPercent = true;
        Double num = nums.get(nums.size() - 1);
        num *= 0.01;
        nums.remove(nums.size() - 1);
        nums.add(num);
        return formula + '%';
    }
    public String addSymbol(String num){
        this.isNum = false;
        num += ".";
        return num;
    }
    public twoStr clear(String formula, String num, ArrayList<Double> nums, ArrayList<Character> operators){
        this.isNum = false;
        this.isSymbolPercent = false;
        nums.clear();
        operators.clear();
        formula = "";
        num = "";
        return new twoStr(formula, num);
    }

    /**
     * 删除已经存储过的数字
     * @param formula 现有运算式
     * @param nums 存储数字的数组
     * @return 删除最后一位数字过后的formula
     */
    public String delNum(String formula, ArrayList<Double> nums){
        String numb = nums.get(nums.size() - 1).toString();
        numb = numb.substring(0, (numb.length() - 1));
        if (numb.isEmpty()){
            nums.remove(nums.size() - 1);
            this.isNum = false;
        }
        else {
            nums.remove(nums.size() - 1);
            nums.add(Double.parseDouble(numb));
        }
        return formula.substring(0, (formula.length() - 1));
    }

    /**
     * 删除正在输入的数字
     * @param num 正在输入的数字
     * @return 删除最后一位数字过后的未结算数字
     */
    public String delNum(String num){
        num = num.substring(0, num.length() - 1);
        return num;
    }

    /**
     * 删除输入的运算符
     * @param formula 现有运算式
     * @param operators 存储运算符数组
     * @return 删除最后一位运算符过后的运算式
     */
    public String delOperator(String formula, ArrayList<Character> operators){
        operators.remove(operators.size() - 1);
        this.isNum = true;
        return formula.substring(0, (formula.length() - 1));
    }

    public String delSymbol(String formula, ArrayList<Double> nums){
        Double num = nums.get(nums.size() - 1);
        num *= 100;
        nums.remove(nums.size() - 1);
        nums.add(num);
        return formula.substring(0, formula.length() - 1);
    }

    public String delSymbol(String any){
        this.isNum = true;
        return any.substring(0, any.length() - 1);
    }

    public boolean isIsNum() {
        return isNum;
    }

    public void setIsNum(boolean isNum) {
        this.isNum = isNum;
    }

    public boolean isSymbolPercent() {
        return isSymbolPercent;
    }

    public void setSymbolPercent(boolean symbolPercent) {
        isSymbolPercent = symbolPercent;
    }
}
