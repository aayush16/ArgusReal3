package com.example.aayushjain16.argusreal3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Current extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
    }

    public void goBack(View view){
        Intent menuclass = new Intent(this, MenuScreen.class);
        startActivity(menuclass);
    }
}
