package com.example.a20questions.ui.accounts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a20questions.R;
import com.example.a20questions.data.UserHelperKt;
import com.example.a20questions.ui.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;
    private Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.etUsername);
        Password = (EditText) findViewById(R.id.etPass);
        Login = (Button) findViewById(R.id.btnLogin);
        Register = (Button) findViewById(R.id.btnRegister);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }

    private void validate(String uname, String pass){
        if (UserHelperKt.isValidUser(uname, pass, getApplication())) {
            Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
            homeIntent.putExtra("USERNAME", uname);
            startActivity(homeIntent);
            //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this,
                    "Username or password are incorrect",
                    Toast.LENGTH_LONG).show();
        }
    }
}