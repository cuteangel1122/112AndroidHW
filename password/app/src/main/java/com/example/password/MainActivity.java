package com.example.password;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView txvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String correctUsername = "angelyin";
        String correctPassword = "12345";
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void button_Click(View view) {
        EditText edtName = findViewById(R.id.edtName);
        EditText edtPassword = findViewById(R.id.edtPassword);
        txvShow = findViewById(R.id.txvShow);

        String username = edtName.getText().toString();
        String password = edtPassword.getText().toString();

        if (view.getId() == R.id.btnOK) {
            if (username.equals("angelyin") && password.equals("12345")) {
                txvShow.setText("登入成功");
            } else if (!username.equals("angelyin") && !password.equals("12345")) {
                txvShow.setText("帳號和密碼皆錯誤");
            } else if (!username.equals("angelyin")) {
                txvShow.setText("帳號錯誤");
            } else {
                txvShow.setText("密碼錯誤");
            }
        }
    }
}
