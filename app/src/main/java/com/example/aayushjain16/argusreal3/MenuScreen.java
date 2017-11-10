package com.example.aayushjain16.argusreal3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
    }

    public void goVoltage(View view){
        Intent voltageclass = new Intent(this, voltage.class);
        startActivity(voltageclass);
    }

    public void goCurrent(View view){
        Intent currentclass = new Intent(this, Current.class);
        startActivity(currentclass);
    }

    public void goTemperature(View view){
        Intent temperatureclass = new Intent(this, Temperature.class);
        startActivity(temperatureclass);
    }
}
