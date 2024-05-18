package com.example.spandlv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
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
        String mainCourse = intent.getStringExtra("main_course");
        String sideDish = intent.getStringExtra("side_dish");
        String drink = intent.getStringExtra("drink");

        // 在 TextView 中显示传递过来的数据
        TextView textViewMainCourse = findViewById(R.id.textViewMainCourse);
        textViewMainCourse.setText(mainCourse);

        TextView textViewSideDish = findViewById(R.id.textViewSideDish);
        textViewSideDish.setText(sideDish);

        TextView textViewDrink = findViewById(R.id.textViewDrink);
        textViewDrink.setText(drink);
    }
}