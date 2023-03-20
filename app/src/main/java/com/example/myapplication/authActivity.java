package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.orm.SugarRecord;
import com.yandex.mapkit.MapKitFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;


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

        //List<User> users= SugarRecord.find(User.class,"username=?",username);
        Context context = getApplicationContext();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Create URL
                URL githubEndpoint = null;

                try {
                    githubEndpoint = new URL("http://10.0.2.2:8080/user/"+username);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                    // Create connection
                try {
                    HttpURLConnection myConnection =
                            (HttpURLConnection) githubEndpoint.openConnection();
                    myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    myConnection.setRequestMethod("GET");
                    if (myConnection.getResponseCode() == 200) {
                        // Success
                        // Further processing here
                        //Чтение тела ответа
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginObject(); // Start processing the JSON object
                        boolean name=false;
                        boolean pass=false;
                        while (jsonReader.hasNext()) { // Loop through all keys
                            String key = jsonReader.nextName(); // Fetch the next key
                            if (key.equals("username") || key.equals("pass")) { // Check if desired key
                                // Fetch the value as a String
                                String value1 = jsonReader.nextString();
                                System.out.println(value1);
                                if(value1.equals(username)){
                                    name=true;
                                }
                                if(value1.equals(password)){
                                    pass=true;
                                }
                                 // Break out of the loop
                            } else {
                                jsonReader.skipValue(); // Skip values of other keys
                            }

                        }

                        myConnection.disconnect();
                        if(name && pass){
                            authActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast toast = Toast.makeText(context, "Успешная авторизация", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            });
                            Intent intent = new Intent(authActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            authActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast toast = Toast.makeText(context, "Проверьте корректность введенных данных", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            });
                        }


                    } else {
                        authActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast toast = Toast.makeText(context, "Проверьте корректность введенных данных", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });

                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }


        });



//
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);


//        if (!users.isEmpty()){
//        for(int i=0;i< users.size();i++) {
//            User user = users.get(i);
//            if (Objects.equals(user.pass, password)) {
//                Toast toast = Toast.makeText(context, "Успешная авторизация",
//                        Toast.LENGTH_LONG);
//                toast.show();
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//            } else {
//
//            }
//        }
//        }
//        else{
//            Toast toast = Toast.makeText(context, "Проверьте корректность введенных данных",
//                    Toast.LENGTH_LONG);
//            toast.show();
//        }



    }

}