package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.util.JsonReader;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class whForm5 extends AppCompatActivity {

    String name;
    String number;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wh_form5);
    }

    public void transitionHomee(View view) {

    }

    public void sendAds(View view) {
        String kind = getIntent().getStringExtra("kind");
        String sex= getIntent().getStringExtra("sex");
        String photo = getIntent().getStringExtra("photo");
        String place= getIntent().getStringExtra("place");
        String date= getIntent().getStringExtra("date");
        String description= getIntent().getStringExtra("description");

        EditText name_text= findViewById(R.id.editTextName);
        name=name_text.getText().toString();

        EditText number_text= findViewById(R.id.editTextNumber);
        number=number_text.getText().toString();

        EditText email_text= findViewById(R.id.editTextEmail);
        email=email_text.getText().toString();

        System.out.println("kind: "+kind+", sex: "+sex+", photo: "+photo+", place: "+place+", date: "+date+", description: "+description);
        System.out.println("name: "+name+", number: "+number+", email:"+email);
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage("Опубликовать объявление?")
                .setCancelable(false)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Context context = getApplicationContext();
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                // Create URL
                                URL githubEndpoint = null;

                                try {
                                    githubEndpoint = new URL("http://10.0.2.2:8080/ad/create");
                                } catch (MalformedURLException e) {
                                    throw new RuntimeException(e);
                                }
                                // Create connection
                                try {
                                    HttpURLConnection myConnection =
                                            (HttpURLConnection) githubEndpoint.openConnection();
                                    myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                                    myConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                                    myConnection.setRequestMethod("POST");
                                    // Create the data
                                    JSONObject myData = new JSONObject();
                                    myData.put("kind",kind);
                                    myData.put("sex",sex);
                                    myData.put("photo",photo);
                                    myData.put("date",date);
                                    myData.put("place",place);
                                    myData.put("description",description);
                                    myData.put("user_description",1);                //переделать



                                    myConnection.setDoInput(true);
                                    myConnection.setDoOutput(true);
                                    System.out.println(myData.toString());

                                    myConnection.getOutputStream().write(myData.toString().getBytes());

                                    if (myConnection.getResponseCode() == 200) {
                                        System.out.println("ВСЕ ОК");

                                        whForm5.this.runOnUiThread(new Runnable() {
                                            public void run() {
                                                Toast toast = Toast.makeText(context, "Объявление успешно опубликовано",
                                                        Toast.LENGTH_LONG);
                                                toast. show () ;
                                            }
                                        });

                                        Intent intent = new Intent(whForm5.this, MainActivity.class);
                                        startActivity(intent);

                                    } else {
                                        whForm5.this.runOnUiThread(new Runnable() {
                                            public void run() {
                                                Toast toast = Toast.makeText(context, "Проверьте корректность введенных данных", Toast.LENGTH_LONG);
                                                toast.show();
                                            }
                                        });

                                    }

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                            }


                        });


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