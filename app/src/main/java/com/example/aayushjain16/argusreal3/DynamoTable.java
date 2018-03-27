package com.example.aayushjain16.argusreal3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "argus-mobilehub-1424956396-table1")

public class DynamoTable extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamo_table);
    }

    private String _userId;
    private String _1;
    private String _2;
    private String _3;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "1")
    public String get1() {
        return _1;
    }

    public void set1(final String _1) {
        this._1 = _1;
    }
    @DynamoDBAttribute(attributeName = "2")
    public String get2() {
        return _2;
    }

    public void set2(final String _2) {
        this._2 = _2;
    }
    @DynamoDBAttribute(attributeName = "3")
    public String get3() {
        return _3;
    }

    public void set3(final String _3) {
        this._3 = _3;
    }


}



