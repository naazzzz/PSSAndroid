package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Toast;



public class whForm5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wh_form5);
    }

    public void transitionHomee(View view) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Объявление успешно опубликовано",
                Toast.LENGTH_LONG);
        toast. show () ;

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void sendAds(View view) {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage("Опубликовать объявление?")
                .setCancelable(false)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        transitionHomee(view);
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = alt_bld.create();
        alertDialog.setTitle("Объявление");
        alertDialog.show();
    }
}