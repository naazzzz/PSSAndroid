package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.orm.SugarRecord;
import com.yandex.mapkit.MapKitFactory;

import java.util.List;
import java.util.Objects;


public class authActivity extends AppCompatActivity {

    private final String MAPKIT_API_KEY = "5e7b49b7-bd93-4f55-bbe6-c82fc40a4e7e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);
    }


    public void transitionReg(View view) {
        Intent intent = new Intent(this, regActivity.class);
        startActivity(intent);
    }

    public void transitionHome(View view) {
        EditText username_text= findViewById(R.id.editText2);
        String username = username_text.getText().toString();


        EditText password_text= findViewById(R.id.editText);
        String password = password_text.getText().toString();

        List<User> users= SugarRecord.find(User.class,"username=?",username);
        Context context = getApplicationContext();
        if (!users.isEmpty()){
        for(int i=0;i< users.size();i++) {
            User user = users.get(i);
            if (Objects.equals(user.pass, password)) {
                Toast toast = Toast.makeText(context, "Успешная авторизация",
                        Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(context, "Проверьте корректность введенных данных",
                        Toast.LENGTH_LONG);
                toast.show();
            }
        }
        }
        else{
            Toast toast = Toast.makeText(context, "Проверьте корректность введенных данных",
                    Toast.LENGTH_LONG);
            toast.show();
        }



    }
}