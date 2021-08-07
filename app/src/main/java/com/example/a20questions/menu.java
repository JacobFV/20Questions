package com.example.a20questions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class menu extends AppCompatActivity {
    TextView menu;
    Button resume, quit;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menu = findViewById(R.id.menu_text);
        resume = findViewById(R.id.resume_button);
        quit = findViewById(R.id.quit_button);

        resume.setOnClickListener(view ->{
            Intent intent = new Intent(this,QuestionareActivity.class);
            startActivity(intent);
        });
        quit.setOnClickListener(view ->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}