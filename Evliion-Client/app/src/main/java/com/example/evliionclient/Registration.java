package com.example.evliionclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Registration extends AppCompatActivity {
    String name;
    String username;
    String email;
    String phone;
    String password;
    String urlAdress = "http://evcharge-dev.us-east-1.elasticbeanstalk.com/api/auth/signup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
    }

    public void createAcc(View view){
        EditText nameText = findViewById(R.id.editName);
        EditText usernameText = findViewById(R.id.editUsername);
        EditText emailText = findViewById(R.id.editEmail);
        EditText phoneText = findViewById(R.id.editPhone);
        EditText passwordText = findViewById(R.id.editPassword);

        name = nameText.getText().toString();
        username = usernameText.getText().toString();
        email = emailText.getText().toString();
        phone = phoneText.getText().toString();
        password = passwordText.getText().toString();

        sendPost(name, username, email, password);

        Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
    }

    public void sendPost(final String na, final String user, final String em, final String pass) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlAdress);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("name", na);
                    jsonParam.put("username", user);
                    jsonParam.put("email", em);
                    jsonParam.put("password", pass);

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
