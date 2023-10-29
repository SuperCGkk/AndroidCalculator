package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.calculator.Utils.calculatorUtils;
import com.example.calculator.calculateModule.resultCalculate;
import com.example.calculator.entity.twoStr;
import com.example.calculator.logicModule.formulaController;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 控件声明区域
     */
    private Button btClear;
    private Button btDivide;
    private Button btMultiply;
    private Button btDelete;
    private Button btSeven;
    private Button btEight;
    private Button btNine;
    private Button btMinus;
    private Button btFour;
    private Button btFive;
    private Button btSix;
    private Button btAdd;
    private Button btOne;
    private Button btTwo;
    private Button btThree;
    private ImageButton btGenhao;
    private Button btBaiFenHao;
    private Button btZero;
    private Button btDian;
    private Button btDengHao;
    private TextView tvFormula;
    /**
     * 自建变量和类区域
     */
    private String num = "";
    private String formula = "";
    private final String init = "0";
    private final formulaController controller = new formulaController();
    private final resultCalculate calculate = new resultCalculate();
    private static final HashMap<Integer, Character> operatorsId;
    private static final HashMap<Integer, Character> numbsId;
    private static final HashMap<Integer, Character> symbolsId;
    //哈希Map的值填充
    static {
        operatorsId = new HashMap<>();
        numbsId = new HashMap<>();
        symbolsId = new HashMap<>();
        operatorsId.put(R.id.btDivide, '÷');
        operatorsId.put(R.id.btAdd, '+');
        operatorsId.put(R.id.btMinus, '-');
        operatorsId.put(R.id.btMultiply, '×');
        numbsId.put(R.id.btZero, '0');
        numbsId.put(R.id.btOne, '1');
        numbsId.put(R.id.btTwo, '2');
        numbsId.put(R.id.btThree, '3');
        numbsId.put(R.id.btFour, '4');
        numbsId.put(R.id.btFive, '5');
        numbsId.put(R.id.btSix, '6');
        numbsId.put(R.id.btSeven, '7');
        numbsId.put(R.id.btEight, '8');
        numbsId.put(R.id.btNine, '9');
        symbolsId.put(R.id.btDian, '.');
        symbolsId.put(R.id.btBaiFenHao, '%');
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //应用加载区
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //控件指定区
        tvFormula = findViewById(R.id.tvFormula);

        btDivide = findViewById(R.id.btDivide);
        btClear = findViewById(R.id.btClear);
        btMultiply = findViewById(R.id.btMultiply);
        btDelete = findViewById(R.id.btDelete);
        btSeven = findViewById(R.id.btSeven);
        btEight = findViewById(R.id.btEight);
        btNine = findViewById(R.id.btNine);
        btMinus = findViewById(R.id.btMinus);
        btFour = findViewById(R.id.btFour);
        btFive = findViewById(R.id.btFive);
        btSix = findViewById(R.id.btSix);
        btAdd = findViewById(R.id.btAdd);
        btOne = findViewById(R.id.btOne);
        btTwo = findViewById(R.id.btTwo);
        btThree = findViewById(R.id.btThree);
        btGenhao = findViewById(R.id.btGenhao);
        btBaiFenHao = findViewById(R.id.btBaiFenHao);
        btZero = findViewById(R.id.btZero);
        btDian = findViewById(R.id.btDian);
        btDengHao = findViewById(R.id.btEqual);

        //控件监听添加区
        btDivide.setOnClickListener(this);
        btClear.setOnClickListener(this);
        btMultiply.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btSeven.setOnClickListener(this);
        btEight.setOnClickListener(this);
        btNine.setOnClickListener(this);
        btMinus.setOnClickListener(this);
        btFour.setOnClickListener(this);
        btFive.setOnClickListener(this);
        btSix.setOnClickListener(this);
        btAdd.setOnClickListener(this);
        btOne.setOnClickListener(this);
        btTwo.setOnClickListener(this);
        btThree.setOnClickListener(this);
        btGenhao.setOnClickListener(this);
        btBaiFenHao.setOnClickListener(this);
        btZero.setOnClickListener(this);
        btDian.setOnClickListener(this);
        btDengHao.setOnClickListener(this);

        //控件初始化区
        tvFormula.setText(init);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        //运算符相应设置
        //判断当前控件id是否为运算符控件
        if (operatorsId.containsKey(id)){
            if (controller.isIsNum()) {
                if (!num.isEmpty()){
                    twoStr str = controller.joinNum(formula, num, calculate.getNums());
                    formula = str.getFirst();
                    num = str.getSecond();
                }
                formula = controller.addOperator(formula, id, calculate.getOperators(), operatorsId);
                tvFormula.setText(formula);
            }
        }
        //操作响应设置区
        else if (id == R.id.btClear){
            twoStr str = controller.clear(formula, num, calculate.getNums(), calculate.getOperators());
            formula = str.getFirst();
            num = str.getSecond();
            tvFormula.setText(init);
        }
        else if (id == R.id.btDelete){
            //判断当前的formula+num是否为空（有可能num还未结算存储）
            if (!(formula + num).isEmpty()){
                //判断当前的formula+num的最后一位为运算符还是数字还是符号
                char lastStr = (formula + num).charAt((formula + num).length() - 1);
                if (operatorsId.containsValue(lastStr)){
                    formula = controller.delOperator(formula, calculate.getOperators());
                    tvFormula.setText(formula);
                }
                else if (numbsId.containsValue(lastStr)) {
                    if (num.isEmpty()){
                        formula = controller.delNum(formula, calculate.getNums());
                        tvFormula.setText(formula);
                    }
                    else {
                        num = controller.delNum(num);
                        String i = formula + num;
                        tvFormula.setText(i);
                    }
                }
                else if (symbolsId.containsValue(lastStr)){
                    if (lastStr == '%'){
                        formula = controller.delSymbol(formula, calculate.getNums());
                        tvFormula.setText(formula);
                    }
                    else {
                        if (num.isEmpty()){
                            formula = controller.delSymbol(formula);
                            tvFormula.setText(formula);
                        }
                        else {
                            num = controller.delSymbol(num);
                            String i = formula + num;
                            tvFormula.setText(i);
                        }
                    }
                }
            }
        }
        else if (id == R.id.btGenhao){
            if (controller.isIsNum()){
                if (calculate.getOperators().isEmpty()){
                    ArrayList<Double> nums = new ArrayList<>();
                    nums.add(Double.parseDouble(num));
                    calculate.setNums(nums);
                }
                else {
                    if (!num.isEmpty()){
                        twoStr str = controller.joinNum(formula, num, calculate.getNums());
                        formula = str.getFirst();
                        num = str.getSecond();
                    }
                }

                double res = calculate.redicalCalculate();
                if (calculatorUtils.isInteger(res)){
                    String result = String.valueOf((int)res);
                    tvFormula.setText(result);
                }
                else {
                    String result = String.valueOf(res);
                    tvFormula.setText(result);
                }
            }
        }
        else if (id == R.id.btEqual){
            if (controller.isIsNum()){
                if (calculate.getOperators().isEmpty()){
                    ArrayList<Double> nums = new ArrayList<>();
                    nums.add(Double.parseDouble(num));
                    calculate.setNums(nums);
                }
                else {
                    if (!num.isEmpty()){
                        twoStr str = controller.joinNum(formula, num, calculate.getNums());
                        formula = str.getFirst();
                        num = str.getSecond();
                    }
                }
                double res = calculate.normalCalculate();
                if (calculatorUtils.isInteger(res)){
                    String result = String.valueOf((int)res);
                    tvFormula.setText(result);
                }
                else {
                    String result = String.valueOf(res);
                    tvFormula.setText(result);
                }
            }
        }
        //符号响应设置区
        else if (symbolsId.containsKey(id)){
            if (controller.isIsNum()){
                if (id == R.id.btBaiFenHao){
                    if (num.isEmpty()){
                        formula = controller.addSymbol(formula, calculate.getNums());
                        tvFormula.setText(formula);
                    }
                    else {
                        twoStr str = controller.addSymbol(formula, num, calculate.getNums());
                        formula = str.getFirst();
                        num = str.getSecond();
                        tvFormula.setText(formula);
                    }
                }
                else {
                    num = controller.addSymbol(num);
                    String i = formula + num;
                    tvFormula.setText(i);
                }
            }
        }

        //数字响应设置区
        else if (numbsId.containsKey(id)){
            if (!controller.isSymbolPercent()){
                num = controller.addNum(num, id, numbsId);
                String i = formula + num;
                tvFormula.setText(i);
            }
        }
    }
}