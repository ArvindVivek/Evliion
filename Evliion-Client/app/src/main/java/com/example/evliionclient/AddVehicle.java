package com.example.evliionclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddVehicle extends AppCompatActivity {
    private Spinner spinner1, spinner2, spinner3;
    static String type, make, model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);

        spinners();
    }

    public void addVehicle(View view) {
        type = String.valueOf(spinner1.getSelectedItem());
        make = String.valueOf(spinner2.getSelectedItem());
        type = String.valueOf(spinner3.getSelectedItem());
        Toast.makeText(this, "Vehicle Added!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, EVMapView.class);
        startActivity(intent);
    }

    public void spinners(){
        addItemsOnSpinner1();
        addItemsOnSpinner2();
        addItemsOnSpinner3();
    }

    public void addItemsOnSpinner1() {
        spinner1 = findViewById(R.id.spinner1);
        List<String> list = new ArrayList<>();
        list.add("Type");
        list.add("Two Wheeler");
        list.add("Four Wheeler");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinner2() {
        spinner2 = findViewById(R.id.spinner2);
        List<String> list = new ArrayList<>();
        list.add("Make");
        list.add("Mahindra Electric");
        list.add("Tesla");
        list.add("Nissan");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinner3() {
        spinner3 = findViewById(R.id.spinner3);
        List<String> list = new ArrayList<>();
        list.add("Model");
        list.add("Mahindra E20");
        list.add("Tesla Model S");
        list.add("Tesla Model X");
        list.add("Nissan Leaf");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter);
    }
}
