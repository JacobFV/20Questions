package com.example.a20questions;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView title, lastScore,recentScore,allScore,copyRight;
    Button play;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        play = findViewById(R.id.play_button);
        image = findViewById(R.id.imageView);

        play.setOnClickListener(view ->{

        Intent intent =new Intent(this,QuestionareActivity.class);
        startActivity(intent);
        });

    }
}
