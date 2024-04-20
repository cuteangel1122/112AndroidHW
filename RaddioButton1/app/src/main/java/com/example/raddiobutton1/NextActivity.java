package com.example.raddiobutton1;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_next);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 獲取從 MainActivity 傳遞過來的數據
        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
        String ticketCount = intent.getStringExtra("ticketCount");
        String ticketInfo = intent.getStringExtra("ticketInfo");

        // 將數據設置到對應的 TextView 中
        TextView txtGender = findViewById(R.id.txtGender);
        txtGender.setText("性別：" + gender);

        TextView txtTicketType = findViewById(R.id.txtTicketType);
        txtTicketType.setText("票種：" + ticketInfo);

        TextView txtTicketCount = findViewById(R.id.txtTicketCount);
        txtTicketCount.setText("張數：" + ticketCount);

        // 這裡需要計算金額，假設全票 500 元，兒童票 250 元，學生票 400 元
        int amount = calculateAmount(ticketInfo, Integer.parseInt(ticketCount));
        TextView txtAmount = findViewById(R.id.txtAmount);
        txtAmount.setText("金額：" + amount + " 元");
    }

    // 計算金額的方法
    private int calculateAmount(String ticketInfo, int ticketCount) {
        int amount = 0;
        if (ticketInfo.equals("全票")) {
            amount = 500 * ticketCount;
        } else if (ticketInfo.equals("兒童票")) {
            amount = 250 * ticketCount;
        } else if (ticketInfo.equals("學生票")) {
            amount = 400 * ticketCount;
        }
        return amount;
    }
}
