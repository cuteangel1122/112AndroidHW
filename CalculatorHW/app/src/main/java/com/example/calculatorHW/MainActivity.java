package com.example.calculatorHW;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import java.math.BigDecimal;

import androidx.appcompat.app.AppCompatActivity;

enum State {FirstNumberInput, OperatorInputed, NumberInput}
enum OP { None, Add, Sub, Mul, Div}

public class MainActivity extends AppCompatActivity {
    private BigDecimal theValue = BigDecimal.ZERO;
    private BigDecimal operand1 = BigDecimal.ZERO;
    private BigDecimal operand2 = BigDecimal.ZERO;

    private OP op=OP.None;
    private State state = State.FirstNumberInput;
    private boolean isDecimalPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

       /* GridLayout keysGL = (GridLayout) findViewById(R.id.keys);

        int kbHeight = (int) (keysGL.getHeight() / keysGL.getRowCount());
        int kbWidth = (int) (keysGL.getWidth()/keysGL.getColumnCount());

        Button btn;

        for( int i=0; i< keysGL.getChildCount();i++){
            btn = (Button) keysGL.getChildAt(i);
            btn.setHeight(kbHeight);
            btn.setWidth(kbWidth);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36 );
        }*/
    }


    public void processKeyInput(View view){
        Button b= (Button)view;    // 取得發生事件的按鈕
        String bstr= b.getText().toString();   // 取得發生事件的按鈕上的文字
        int bint; // 透過R.id.display取得顯示結果的EditText元件
        EditText edt = (EditText) findViewById(R.id.display);
        BigDecimal digit;
        switch(bstr) { // 依據發生事件的按鈕上的文字值，進行不同的處理
            // 數字按鈕被點按時
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                switch(state) { // 依據當時的狀態決定不同的處理
                    case FirstNumberInput:
                        // 如果当前状态为 FirstNumberInput，则更新显示的值
                        theValue = theValue.multiply(BigDecimal.TEN).add(new BigDecimal(bstr));
                        if (isDecimalPressed) {
                            theValue = theValue.divide(BigDecimal.TEN);
                        }
                        break;
                    case OperatorInputed:
                        theValue = new BigDecimal(bstr);
                        state = State.NumberInput;
                        break;
                    case NumberInput:
                        digit = new BigDecimal(bstr);
                        if (theValue.signum() < 0) {
                            theValue = theValue.multiply(BigDecimal.TEN).subtract(digit);
                        } else {
                            if (theValue.equals(BigDecimal.ZERO)) {
                                theValue = digit;
                            } else {
                                theValue = theValue.multiply(BigDecimal.TEN).add(digit);
                            }
                        }
                        break;
                }
                edt.setText(String.valueOf(theValue));
                break;
            case "Clear": // 清除並重設相關變數
                state = State.FirstNumberInput;
                theValue = BigDecimal.ZERO;
                isDecimalPressed = false;
                edt.setText("0");
                op = OP.None;
                operand2 = operand1 = BigDecimal.ZERO;
                break;
            case "Back": // 倒退鍵
                theValue = theValue.divideToIntegralValue(BigDecimal.TEN);
                edt.setText(theValue.toPlainString());
                break;
            case ".":
                if (!isDecimalPressed) {
                    isDecimalPressed = true;
                }
                break;
            case "+":
            case "-":
                switch (state) {
                    case FirstNumberInput:
                        operand1 = theValue;
                        state = State.OperatorInputed;
                        op = OP.Sub;
                        break;
                    case OperatorInputed:
                        op = OP.Sub;
                        break;
                    case NumberInput:
                        digit = new BigDecimal(bstr);
                        theValue = theValue.multiply(BigDecimal.TEN).subtract(digit);
                        break;
                }
                break;
            case "*":
            case "/":
                switch (state) {
                    case FirstNumberInput:
                        operand1 = theValue;
                        operand2 = theValue;
                        switch (bstr) {
                            case "+":
                                op = OP.Add;
                                break;
                            case "-":
                                op = OP.Sub;
                                break;
                            case "*":
                                op = OP.Mul;
                                break;
                            case "/":
                                op = OP.Div;
                                break;
                        }
                        state = State.OperatorInputed;
                        break;
                    case OperatorInputed:
                        switch (bstr) {
                            case "+":
                                op = OP.Add;
                                break;
                            case "-":
                                op = OP.Sub;
                                break;
                            case "*":
                                op = OP.Mul;
                                break;
                            case "/":
                                op = OP.Div;
                                break;
                        }
                        operand2 = theValue;
                        break;
                    case NumberInput:
                        operand2 = theValue;
                        switch (op) {
                            case Add:
                                theValue = operand1.add(operand2);
                                break;
                            case Sub:
                                theValue = operand1.subtract(operand2);
                                break;
                            case Mul:
                                theValue = operand1.multiply(operand2);
                                break;
                            case Div:
                                theValue = operand1.divide(operand2);
                                break;
                        }
                        operand1 = theValue;
                        switch (bstr) {
                            case "+":
                                op = OP.Add;
                                break;
                            case "-":
                                op = OP.Sub;
                                break;
                            case "*":
                                op = OP.Mul;
                                break;
                            case "/":
                                op = OP.Div;
                                break;
                        }
                        state = State.OperatorInputed;
                        edt.setText(theValue.toPlainString());
                        break;
                }
                break;
            case "=": // 當＝號被點選時，依據當時的狀態決定不同的處理
                switch(state) {
                    case OperatorInputed:
                        switch(op) {
                            case Add:
                                theValue = operand1.add(operand2);
                                break;
                            case Sub:
                                theValue = operand1.subtract(operand2);
                                break;
                            case Mul:
                                theValue = operand1.multiply(operand2);
                                break;
                            case Div:
                                if (operand2.compareTo(BigDecimal.ZERO) != 0) {
                                    theValue = operand1.divide(operand2, 10, BigDecimal.ROUND_HALF_UP);
                                } else {
                                    // handle division by zero error
                                    // 示範中簡單處理為將結果設為零
                                    theValue = BigDecimal.ZERO;
                                    Log.e("Error", "Division by zero");
                                }
                                break;
                        }
                        operand1 = theValue;
                        state = State.NumberInput;
                        break;
                    case NumberInput:
                        operand2 = theValue;
                        switch(op) {
                            case Add:
                                theValue = operand1.add(operand2);
                                break;
                            case Sub:
                                theValue = operand1.subtract(operand2);
                                break;
                            case Mul:
                                theValue = operand1.multiply(operand2);
                                break;
                            case Div:
                                if (operand2.compareTo(BigDecimal.ZERO) != 0) {
                                    theValue = operand1.divide(operand2, 10, BigDecimal.ROUND_HALF_UP);
                                } else {
                                    // handle division by zero error
                                    // 示範中簡單處理為將結果設為零
                                    theValue = BigDecimal.ZERO;
                                    Log.e("Error", "Division by zero");
                                }
                                break;
                        }
                        operand1 = theValue;
                        state = State.NumberInput;
                        break;
                }
                edt.setText(theValue.toPlainString());
                break;
        }
    }
}
