package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txvShow = (TextView) findViewById(R.id.txvShow);
        txvShow.setTextSize(36);
        Button btnCalc = (Button) findViewById(R.id.btnCalc);
        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnCalc.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText edtHeight = (EditText) findViewById(R.id.edtHeight);
        EditText edtWeight = (EditText) findViewById(R.id.edtWeight);
        txvShow = findViewById(R.id.txvShow);
        if (v.getId() == R.id.btnCalc) {
            String heightStr = edtHeight.getText().toString().trim();
            String weightStr = edtWeight.getText().toString().trim();

            if (heightStr.isEmpty() || weightStr.isEmpty()) {
                txvShow.setText("請輸入身高和體重。");
                return;
            }

            try {
                double height = Double.parseDouble(heightStr);
                double weight = Double.parseDouble(weightStr);

                // 檢查身高和體重是否有效（> 0）
                if (height <= 0 || weight <= 0) {
                    txvShow.setText("請輸入有效的身高和體重。");
                    return;
                }
                double bmi = weight / Math.pow(height / 100.0, 2);
                if (bmi >= 24)
                    txvShow.setTextColor(Color.RED);
                else if (bmi < 18.5)
                    txvShow.setTextColor(Color.BLUE);
                else
                    txvShow.setTextColor(Color.GREEN);

                txvShow.setText(String.format("%.2f", bmi));
            } catch (NumberFormatException e) {
                // 處理非數字輸入
                txvShow.setText("請輸入有效的數字值作為身高和體重。");
            }
        }
        else {
            edtHeight.setText("");
            edtWeight.setText("");
            txvShow.setText("");
            txvShow.setTextColor(Color.BLACK);
        }

    }
}