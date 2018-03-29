package com.example.aayushjain16.argusreal3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.util.HashMap;
import java.util.Map;

public class MenuScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_screen);


    }




    public void goCurrent(View view){
        Intent currentclass = new Intent(this, Current.class);
        startActivity(currentclass);
    }

    public void goTemperature(View view){
        Intent temperatureclass = new Intent(this, Temperature.class);
        startActivity(temperatureclass);

    }


    public void goVoltage(View view) {
        // Intent currentclass = new Intent(this, Values.class);
        Intent currentclass = new Intent(this, DynamoTable.class);
        startActivity(currentclass);


    }
}

