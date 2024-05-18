package com.example.spandlv;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private Spinner spinner;
    private TextView textMainCourse, textSideDish, textDrink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinner = findViewById(R.id.spinner);
        listview = findViewById(R.id.listview);
        textMainCourse = findViewById(R.id.textMainCourse);
        textSideDish = findViewById(R.id.textSideDish);
        textDrink = findViewById(R.id.textDrink);

        // 创建Spinner的适配器
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        // 设置Spinner的选择监听器
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                updateListView(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 初始化ListView显示主餐的数据
        updateListView(0);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                switch (spinner.getSelectedItemPosition()) {
                    case 0:
                        textMainCourse.setText("主餐： " + selectedItem);
                        break;
                    case 1:
                        textSideDish.setText("附餐： " + selectedItem);
                        break;
                    case 2:
                        textDrink.setText("飲料： " + selectedItem);
                        break;
                }
            }
        });
    }
    private void updateListView(int position) {
        int arrayId = 0;
        switch (position) {
            case 0:
                arrayId = R.array.main_courses;
                break;
            case 1:
                arrayId = R.array.side_dishes;
                break;
            case 2:
                arrayId = R.array.drinks;
                break;
        }
        ArrayAdapter<CharSequence> listViewAdapter = ArrayAdapter.createFromResource(this,
                arrayId, android.R.layout.simple_list_item_1);
        listview.setAdapter(listViewAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.send) {
            sendSelection();
            return true;
        } else if (id == R.id.cancel) {
            cancelSelection();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendSelection() {
        Intent intent = new Intent(MainActivity.this, NextActivity.class);
        intent.putExtra("main_course", textMainCourse.getText().toString());
        intent.putExtra("side_dish", textSideDish.getText().toString());
        intent.putExtra("drink", textDrink.getText().toString());
        startActivity(intent);
    }
    private void cancelSelection() {
        // 清除下方TextView的内容，恢复为默认值
        textMainCourse.setText("主餐：請選擇");
        textSideDish.setText("附餐：請選擇");
        textDrink.setText("飲料：請選擇");
    }
}