package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.sql.Date;
import java.util.Calendar;

public class whFormNext extends AppCompatActivity {

    String kind;
    String sex;
    String photo = "bla-bla.jpg";
    String place;
    String date;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        int page= getIntent().getIntExtra("page",0);
        switch (page){
            case 0:
                setContentView(R.layout.wh_form1);
                MaterialButtonToggleGroup materialButtonToggleGroup = findViewById(R.id.materialButtonToggleGroupKind);
                materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener(){
                    @Override
                    public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                        if (isChecked) {
                            if (checkedId == R.id.buttondog) {
                                kind ="dog";
                            }
                            if (checkedId == R.id.buttoncat) {
                                kind ="cat";
                            }
                        }
                    }
                });
                materialButtonToggleGroup = findViewById(R.id.materialButtonToggleGroupSex);
                materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener(){
                    @Override
                    public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                        if (isChecked) {
                            if (checkedId == R.id.buttonboy) {
                                sex ="boy";
                            }
                            if (checkedId == R.id.buttongirl) {
                                sex ="girl";
                            }
                        }
                    }
                });
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
            kind=getIntent().getStringExtra("kind");
            intent.putExtra("kind",kind);
            sex=getIntent().getStringExtra("sex");
            intent.putExtra("sex",sex);
            intent.putExtra("photo",photo);
            date=getIntent().getStringExtra("date");
            intent.putExtra("date",date);
            place=getIntent().getStringExtra("place");
            intent.putExtra("place",place);
            EditText description_text= findViewById(R.id.editTextDescription);
            description=description_text.getText().toString();
            intent.putExtra("description",description);
        }else{
            intent = new Intent(this, whFormNext.class);
            switch (page){
                case 1:
                    intent.putExtra("kind",kind);
                    intent.putExtra("sex",sex);
                    break;
                case 2:
                    kind=getIntent().getStringExtra("kind");
                    intent.putExtra("kind",kind);
                    sex=getIntent().getStringExtra("sex");
                    intent.putExtra("sex",sex);
                    intent.putExtra("photo",photo);
                    break;
                case 3:
                    kind=getIntent().getStringExtra("kind");
                    intent.putExtra("kind",kind);
                    sex=getIntent().getStringExtra("sex");
                    intent.putExtra("sex",sex);
                    intent.putExtra("photo",photo);
                    DatePicker datePicker = findViewById(R.id.date);
                            date=datePicker.getYear()+"-"+ datePicker.getMonth()+"-"+datePicker.getDayOfMonth();
                    intent.putExtra("date",date);

                    EditText place_text= findViewById(R.id.editTextPlace);
                    place=place_text.getText().toString();

                    intent.putExtra("place",place);
                    break;
            }
        }

        intent.putExtra("page",page);
        startActivity(intent);

    }
}