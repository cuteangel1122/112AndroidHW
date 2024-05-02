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

        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
        String ticketCount = intent.getStringExtra("ticketCount");
        String ticketInfo = intent.getStringExtra("ticketInfo");


        TextView txtGender = findViewById(R.id.txtGender);
        txtGender.setText("Gender：" + gender);

        TextView txtTicketType = findViewById(R.id.txtTicketType);
        txtTicketType.setText("Ticket Type：" + ticketInfo);

        TextView txtTicketCount = findViewById(R.id.txtTicketCount);
        txtTicketCount.setText("Count:" + ticketCount);

        int amount = getTicketPrice(ticketInfo, Integer.parseInt(ticketCount));
        TextView txtAmount = findViewById(R.id.txtAmount);
        txtAmount.setText("Total：" + amount + " dollars");
    }

    private int getTicketPrice(String ticketInfo, int ticketCount) {
        int amount = 0;
        if (ticketInfo.equals("Regular Ticket")) {
            amount = 500 * ticketCount;
        } else if (ticketInfo.equals("Children Ticket")) {
            amount = 250 * ticketCount;
        } else if (ticketInfo.equals("Student Ticket")) {
            amount = 400 * ticketCount;
        }
        return amount;
    }
}
