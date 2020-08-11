package com.example.sfedymob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.news_vk.NewsActivity;
import com.example.timetable.BaseActivity;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main);

        Button b = findViewById(R.id.timatableb);
        b.setOnClickListener(v -> {
            Intent intent = new Intent(this, BaseActivity.class);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.timatableb2).setOnClickListener(v -> {
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
            finish();
        });

    }
}
