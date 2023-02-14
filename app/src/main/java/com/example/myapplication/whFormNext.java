package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class whFormNext extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int page= getIntent().getIntExtra("page",0);
        switch (page){
            case 0:
                setContentView(R.layout.wh_form1);
                break;
            case 1:
                setContentView(R.layout.wh_form2);
                break;
            case 2:
                setContentView(R.layout.wh_form3);
                break;
            case 3:
                setContentView(R.layout.wh_form4);
                break;
            case 4:
                setContentView(R.layout.wh_form5);
                break;
        }

    }

    public void transistionNext(View view){


        int page= getIntent().getIntExtra("page",0);
        page++;
        Intent intent;
        if(page==4){
            intent = new Intent(this, whForm5.class);
        }else{
            intent = new Intent(this, whFormNext.class);
        }
        intent.putExtra("page",page);
        startActivity(intent);

    }
}