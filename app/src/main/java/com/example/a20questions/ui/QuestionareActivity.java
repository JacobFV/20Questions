package com.example.a20questions.ui;

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

/*public class QuestionareActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button menu, yes,no,maybe;
    TextView question;
    LinearLayout linear, button_lin;
    View view;
    RecyclerAdapter recyclerAdapter;
    List<String> list =new ArrayList<String>();
    List<String>list_2 = new ArrayList<String>();
    List<String>list_ans = new ArrayList<String>();
    String answer="";
    String guessed_character;
    int count =0;
    int list_no =0;

    Dialog dialog_guess,dialog_extended,menu_dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionarre);
        linear = findViewById(R.id.linear);
        menu = findViewById(R.id.menu_button);
        question = findViewById(R.id.question_view);
        button_lin = findViewById(R.id.linear_buttons);
        yes = findViewById(R.id.yes_button);
        no = findViewById(R.id.no_button);
        maybe = findViewById(R.id.maybe_button);
        view = findViewById(R.id.view1);
        dialog_guess = new Dialog(this);
        dialog_extended= new Dialog(this);
        menu_dialog = new Dialog(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerAdapter = new RecyclerAdapter(list_2, list_ans);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true));


        InputStream is = this.getResources().openRawResource(R.raw.questions); //read file from text
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is)); //buffer object
        StringBuffer stringBuffer = new StringBuffer(); //string buffer object
        String data ="";

        if(is!=null){
            try{

            while((data = bufferedReader.readLine())!=null){
                list.add(data);

            }
                String [] linesArray = list.toArray(new String[list.size()]);
                question.setText(linesArray[count]);
                count++;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
       // menu.setOnClickListener(view->{
               // Intent intent = new Intent (this, menu.class);
                //startActivity(intent);
       // });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmenu_dialog();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                answer = "Yes";
                question.setText(list.get(count++));
                list_2.add(list.get(list_no));
                list_ans.add(answer);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
                list_no++;
                if(list_no ==20){
                    openDialog();
                }
               else if(list_no ==25){
                 openDialogExtended();}
            }
        } );

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = "No";
                question.setText(list.get(count++));
                list_2.add(list.get(list_no));
                list_ans.add(answer);
                recyclerView.setAdapter(recyclerAdapter);
               // recyclerAdapter.notifyDataSetChanged();
                list_no++;
                if(list_no ==20){
                    openDialog();
                }
               else if(list_no ==25){
                 openDialogExtended();}


            }
        } );

        maybe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = "Maybe";
                question.setText(list.get(count++));
                list_2.add(list.get(list_no));
                list_ans.add(answer);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
                list_no++;
                if(list_no ==20){
                    openDialog();
                }
                else if(list_no ==25) {
                  openDialogExtended();
                }
            }
        } );
    }

    private void openmenu_dialog() {
        menu_dialog.setContentView(R.layout.dialogue_menu);
        menu_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView menu = menu_dialog.findViewById(R.id.menu_text);
        Button resume = menu_dialog.findViewById(R.id.resume_button);
        Button quit = menu_dialog.findViewById(R.id.quit_button);
        menu.setText("MENU");
        menu_dialog.show();
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_dialog.dismiss();
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getBaseContext(),MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }


    private void openDialog() {
        dialog_guess.setContentView(R.layout.guess_dialog);
        dialog_guess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageView = dialog_guess.findViewById(R.id.character);
        Button button_yes = dialog_guess.findViewById(R.id.button_yes_dial);
        Button button_no = dialog_guess.findViewById(R.id.button_no_dial);
        Button button_close = dialog_guess.findViewById(R.id.button_close);
        TextView textView = dialog_guess.findViewById(R.id.guess_text);
        textView.setText("Is this the character you are guessing?");
        dialog_guess.show();
        button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("I BEAT YOU!!!");
                button_no.setVisibility(View.INVISIBLE);
                button_yes.setVisibility(View.INVISIBLE);
                button_close.setVisibility(View.VISIBLE);

            }
        });
        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_guess.dismiss();
            }
        });
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getBaseContext(),MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }
    private void openDialogExtended() {
        dialog_extended.setContentView(R.layout.dialog_extended);
        dialog_extended.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageView2 = dialog_extended.findViewById(R.id.character_img);
        Button button_yes_dial = dialog_extended.findViewById(R.id.button_yes_dialog);
        Button button_no_dial = dialog_extended.findViewById(R.id.button_no_dialog);
        Button button_close_dial = dialog_extended.findViewById(R.id.button_close_dialog);
        EditText editText = dialog_extended.findViewById(R.id.edit_text);
        TextView textView_extended = dialog_extended.findViewById(R.id.Extendedguess_text);
        textView_extended.setText("Is this the character you are guessing?");
        dialog_extended.show();
        button_yes_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_extended.setText("I BEAT YOU!!!");
                button_yes_dial.setVisibility(View.INVISIBLE);
                button_no_dial.setVisibility(View.INVISIBLE);
                button_close_dial.setVisibility(View.VISIBLE);


            }
        });
        button_no_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_extended.setText("YOU BEAT ME!!!");
                button_yes_dial.setVisibility(View.INVISIBLE);
                button_no_dial.setVisibility(View.INVISIBLE);
                button_close_dial.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
            }
        });
        button_close_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessed_character = editText.getText().toString();
                //Intent intent = new Intent(getBaseContext(),MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }

}

*/