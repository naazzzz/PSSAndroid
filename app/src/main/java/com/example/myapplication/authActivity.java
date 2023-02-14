package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class authActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

    }


    public void transitionReg(View view) {
        Intent intent = new Intent(this, regActivity.class);
        startActivity(intent);
    }

    public void transitionHome(View view) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Успешная авторизация",
                Toast.LENGTH_LONG);
        toast. show () ;

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}