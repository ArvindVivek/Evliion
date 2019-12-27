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

public class Login extends AppCompatActivity {
    String usernameOrEmail;
    String password;
    String urlAdress = "http://evcharge-dev.us-east-1.elasticbeanstalk.com/api/auth/signup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void loginAcc(View view){
        EditText usernameText = findViewById(R.id.editUsername);
        EditText passwordText = findViewById(R.id.editPassword);

        usernameOrEmail = usernameText.getText().toString();
        password = passwordText.getText().toString();

        sendPost(usernameOrEmail, password);

        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, AddVehicle.class);
        startActivity(intent);
    }

    public void sendPost(final String user, final String pass) {
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
                    jsonParam.put("usernameOrEmail", user);
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
