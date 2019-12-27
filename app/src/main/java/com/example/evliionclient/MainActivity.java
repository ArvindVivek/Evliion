package com.example.evliionclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toSignUp(View view){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    public void toLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}
