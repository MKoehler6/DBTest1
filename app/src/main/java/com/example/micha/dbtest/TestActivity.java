package com.example.micha.dbtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    Context context;
    ArrayList arrayA;
    ArrayList arrayB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        DBTestOpenHandler openHandler = new DBTestOpenHandler(this);
        TextView tv = (TextView) findViewById(R.id.textView5);
        arrayA = openHandler.readA();
        arrayB = openHandler.readB();
        tv.setText(arrayA.toString() + arrayB.toString());

    }
}
