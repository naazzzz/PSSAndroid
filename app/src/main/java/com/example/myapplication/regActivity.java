package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class regActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
    }

    public void transistionAuth(View view) {

        Intent intent = new Intent(this, authActivity.class);
        startActivity(intent);
    }

    public void Accept(View view){
        EditText username_text= findViewById(R.id.login);
        String username = username_text.getText().toString();

        EditText email_text= findViewById(R.id.email);
        String email = username_text.getText().toString();

        EditText password_text= findViewById(R.id.pass);
        String password = username_text.getText().toString();

        User user_check = new User(username,password,email);

        user_check.save();

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Успешная регистрация",
                Toast.LENGTH_LONG);
        toast. show () ;

        Intent intent = new Intent(this, authActivity.class);
        startActivity(intent);
    }
}