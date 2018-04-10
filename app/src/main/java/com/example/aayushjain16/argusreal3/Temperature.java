package com.example.aayushjain16.argusreal3;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Temperature extends AppCompatActivity {
    DynamoDBMapper dynamoDBMapper;
    AmazonDynamoDBClient dynamoDBClient;
    double send_double;


    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AWSMobileClient.getInstance().initialize(this).execute();
        setContentView(R.layout.activity_temperature);


        dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();




        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Temperature °C");
        Viewport viewport = graph.getViewport();
        viewport.setXAxisBoundsManual(true);
        viewport.setMinX(0);
        viewport.setMaxX(20);
        //viewport.setYAxisBoundsManual(true);
        //viewport.setMinY(20);
        //viewport.setMaxY(23);

        viewport.setScalable(true);
        viewport.setScalableY(true);


        graph.addSeries(series);


    }

    Runnable runnable = new Runnable() {
        public void run() {
            String s, d, temp, time, prefixtime, postfixtime, prefixtemp, postfixtemp, prefixvoltage, postfixvoltage, voltage;
            double tempread, finaltemp = 0.00, voltageread = 0;
            double timeread, previoustimeread = 0, finalvoltage = 0;
            int foo = 0;
            int i = 0;
            Map<String,AttributeValue> item = new HashMap<String, AttributeValue>();
            ScanRequest scanRequest = new ScanRequest()
                    .withTableName("argus-mobilehub-692691894-ARGUS-Mobile");
            // .withTableName("argus-mobilehub-1424956396-table1");

            ScanResult result = dynamoDBClient.scan(scanRequest);
            for (Map<String, AttributeValue> item2 : result.getItems()) {
                System.out.println(item2);
                s = item2.toString();
                //System.out.println(s);
                prefixtemp = "userId={S: ArgusPi,}, payload={M: {temp_c={N: ";
                postfixtemp = ",}, timestamp";
                temp = s.substring(s.indexOf(prefixtemp) + prefixtemp.length(), s.indexOf(postfixtemp));
                prefixtime = "userId={S: ArgusPi,}, payload={M: {temp_c={N: " + temp + ",}, timestamp={N: ";
                postfixtime = ",}, id={S: ArgusPi,}";
                String timetemp = s.substring(s.indexOf(prefixtime) + prefixtime.length(), s.indexOf(postfixtime));
                prefixvoltage = prefixtime + timetemp + postfixtime + ", voltage={N: ";
                postfixvoltage = ",}},}}";
                voltage = s.substring(s.indexOf(prefixvoltage) + prefixvoltage.length(), s.indexOf(postfixvoltage));
                voltageread = Double.parseDouble(voltage);
                tempread = Double.parseDouble(temp);
                timeread = Double.parseDouble(timetemp);
                if (timeread > previoustimeread)
                {
                    finaltemp = tempread;
                    previoustimeread = timeread;
                    finalvoltage = voltageread;

                }
                System.out.println(temp);
                System.out.println(timetemp);
                System.out.println(timeread);
                System.out.println(voltage);

            }
            send_double = finaltemp;
            System.out.println(finaltemp);

        }
    };

    Thread mythread = new Thread(runnable);

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();
                        }
                    });

                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        // manage error ...
                    }
                }
            }
        }).start();
    }

    // add random data to graph
    private void addEntry() {
        mythread.start();
        if (send_double != 0.00) {

            if (send_double >= 60) {
                send_double = 60;
            } else if (send_double <= -20) {
                send_double = -20;
            }
            series.appendData(new DataPoint(lastX++, send_double), true, 20);
        }
    }

    public void goBack(View view){
        Intent menuclass = new Intent(this, MenuScreen.class);
        startActivity(menuclass);
    }
}
