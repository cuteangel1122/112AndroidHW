package com.example.raddiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;


public class MainActivity extends AppCompatActivity {
    private String gender;
    private String outputStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup type = findViewById(R.id.rgType);
        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updatePreview();
            }
        });
        RadioButton boyRadioButton = findViewById(R.id.rdbBoy);
        boyRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePreview();
            }
        });

        RadioButton girlRadioButton = findViewById(R.id.rdbGirl);
        girlRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePreview();
            }
        });

        EditText ticketCountEditText = findViewById(R.id.editTextNumber);
        ticketCountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 不需要实现
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updatePreview();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 不需要实现
            }
        });

        Button button = (Button) findViewById(R.id.btnOK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = "";

                RadioButton boy = (RadioButton) findViewById(R.id.rdbBoy);
                RadioButton girl = (RadioButton) findViewById(R.id.rdbGirl);
                if (boy.isChecked())
                    gender += "男生\n";
                else if (girl.isChecked())
                    gender += "女生\n";
                outputStr = gender;
                RadioGroup type = (RadioGroup) findViewById(R.id.rgType);
                //switch (type.getCheckedRadioButtonId()){
                   // case R.id.rdbAdult:
                     //   outputStr += "全票\n";
                     //   break;
                   // case R.id.rdbChild:
                     //   outputStr += "兒童票\n";
                     //   break;
                   // case R.id.rdbStudent:
                    //    outputStr += "學生票\n";
                    //    break;
               // }
                if(type.getCheckedRadioButtonId() == R.id.rdbAdult)
                    outputStr += "全票\n";
                else if(type.getCheckedRadioButtonId() == R.id.rdbChild)
                    outputStr += "兒童票\n";
                else
                    outputStr += "學生票\n";

                //TextView output = (TextView) findViewById(R.id.lblOutput);
                //output.setText(outputStr);
                updatePreview();
                sendString();
            }
            public void sendString() {
                TextView ticketCountTextView = findViewById(R.id.editTextNumber);
                String ticketCount = ticketCountTextView.getText().toString();

                // 获取性别
                RadioButton boy = findViewById(R.id.rdbBoy);
                RadioButton girl = findViewById(R.id.rdbGirl);
                String gender = "";
                if (boy.isChecked()) {
                    gender = "男生";
                } else if (girl.isChecked()) {
                    gender = "女生";
                }

                // 获取票种
                RadioGroup type = findViewById(R.id.rgType);
                String ticketType = "";
                if(type.getCheckedRadioButtonId() == R.id.rdbAdult) {
                    ticketType = "全票";
                } else if(type.getCheckedRadioButtonId() == R.id.rdbChild) {
                    ticketType = "兒童票";
                } else {
                    ticketType = "學生票";
                }

                Intent intent = new Intent(MainActivity.this, NextActivity.class);
                intent.putExtra("gender", gender);
                intent.putExtra("ticketCount", ticketCount);
                intent.putExtra("ticketInfo", ticketType);
                startActivity(intent);
            }
        });
    }
private void updatePreview() {
    RadioButton boy = findViewById(R.id.rdbBoy);
    RadioButton girl = findViewById(R.id.rdbGirl);
    String gender = "";
    if (boy.isChecked()) {
        gender = "男生";
    } else if (girl.isChecked()) {
        gender = "女生";
    }

    RadioGroup type = findViewById(R.id.rgType);
    String ticketType = "";
    if(type.getCheckedRadioButtonId() == R.id.rdbAdult) {
        ticketType = "全票";
    } else if(type.getCheckedRadioButtonId() == R.id.rdbChild) {
        ticketType = "兒童票";
    } else {
        ticketType = "學生票";
    }

    EditText ticketCountEditText = findViewById(R.id.editTextNumber);
    String ticketCount = ticketCountEditText.getText().toString(); // 获取票数
    if(ticketCount.isEmpty()) {
        ticketCount = "0";
    }

    TextView previewTextView = findViewById(R.id.previewTextView);
    previewTextView.setText("目前選擇：\n" + "性别：" + gender + "\n票種：" + ticketType + "\n張數：" + ticketCount);
}}