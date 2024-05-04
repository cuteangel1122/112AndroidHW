package com.example.foodimgmenu;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodimgmenu.R;

public class MainActivity extends AppCompatActivity {

    private CheckBox chk1, chk2, chk3, chk4,chk5;
    private LinearLayout outputContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Find checkboxes and output container
        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        chk4 = findViewById(R.id.chk4);
        chk5 = findViewById(R.id.chk5);
        outputContainer = findViewById(R.id.outputContainer);

        // Set initial state and listeners for checkboxes
        chk1.setChecked(false);
        chk2.setChecked(false);
        chk3.setChecked(false);
        chk4.setChecked(false);
        chk5.setChecked(false);
        // Add output images based on initial checkbox state
        addOutputImage();

        // Set listeners for checkboxes
        chk1.setOnCheckedChangeListener((buttonView, isChecked) -> addOutputImage());
        chk2.setOnCheckedChangeListener((buttonView, isChecked) -> addOutputImage());
        chk3.setOnCheckedChangeListener((buttonView, isChecked) -> addOutputImage());
        chk4.setOnCheckedChangeListener((buttonView, isChecked) -> addOutputImage());
        chk5.setOnCheckedChangeListener((buttonView, isChecked) -> addOutputImage());
    }


    private void addOutputImage() {
        outputContainer.removeAllViews(); // 清除容器中的所有视图

        int[] foodResources = {R.mipmap.burger, R.mipmap.frenchfry, R.mipmap.coffee, R.mipmap.softdrink, R.mipmap.soup};

        for (int resource : foodResources) {
            if (resource == R.mipmap.burger && chk1.isChecked()) {
                addImageToContainer(resource);
            } else if (resource == R.mipmap.frenchfry && chk2.isChecked()) {
                addImageToContainer(resource);
            } else if (resource == R.mipmap.coffee && chk3.isChecked()) {
                addImageToContainer(resource);
            } else if (resource == R.mipmap.softdrink && chk4.isChecked()) {
                addImageToContainer(resource);
            } else if (resource == R.mipmap.soup && chk5.isChecked()) {
                addImageToContainer(resource);
            }
        }
    }

    private void addImageToContainer(int resource) {
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                200, // 设置图片宽度为200px
                200  // 设置图片高度为200px
        );
        layoutParams.setMargins(0, 16, 0, 0); // 调整间距
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(resource);
        outputContainer.addView(imageView);
    }
}