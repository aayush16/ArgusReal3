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
    DynamoDBMapper dynamoDBMapper;
    AmazonDynamoDBClient dynamoDBClient;
    String names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    public void goCurrent(View view){
        Intent currentclass = new Intent(this, Current.class);
        startActivity(currentclass);
    }

    public void goTemperature(View view){
        Intent temperatureclass = new Intent(this, Temperature.class);
        startActivity(temperatureclass);
    }

    Runnable runnable = new Runnable() {
        public void run() {
            String s, d;
            int foo = 0;

            Map<String,AttributeValue> item = new HashMap<String, AttributeValue>();
            item.put("UserId",
                    new AttributeValue().withS("3"));
            item.put("RecordId", new AttributeValue().withS("4"));
            ScanRequest scanRequest = new ScanRequest()
                    .withTableName("argus-mobilehub-1424956396-table1");

            ScanResult result = dynamoDBClient.scan(scanRequest);
            for (Map<String, AttributeValue> item2 : result.getItems()) {
                System.out.println(item2);
                s = item2.toString();
                String prefix = "{userId={S: ";
                String postfix = ",}}";
                d = s.substring(s.indexOf(prefix) + prefix.length(), s.indexOf(postfix));
                System.out.println(d);
                /*foo = Integer.parseInt(d);
                System.out.println(foo);*/
            }

        }
    };

    Thread mythread = new Thread(runnable);

    public void goVoltage(View view) {
        // Intent currentclass = new Intent(this, Values.class);
        Intent currentclass = new Intent(this, DynamoTable.class);
        startActivity(currentclass);
        mythread.start();

    }
}

