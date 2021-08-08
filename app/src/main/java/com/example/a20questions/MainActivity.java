package com.example.a20questions;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    RecyclerView scoresRecyclerView;
    RecyclerAdapter scoresRecyclerAdapter;
    List<String> gamecount =new ArrayList<String>();
    List<String> win_value = new ArrayList<String>();
    List<String> lose_value = new ArrayList<String>();
    TextView title, lastScore,recentScore,allScore,copyRight;
    Button play;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        play = findViewById(R.id.play_button);
        image = findViewById(R.id.imageView);
        scoresRecyclerView = findViewById(R.id.scoresrecyclerview);
        scoresRecyclerAdapter = new RecyclerAdapter(gamecount, win_value); //don't know how to check if win_value or lose_value

        play.setOnClickListener(view ->{

        Intent intent =new Intent(this,QuestionareActivity.class);
        startActivity(intent);

        });

    }


}
