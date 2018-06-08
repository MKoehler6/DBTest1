package com.example.micha.dbtest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBTestOpenHandler openHandler;
    ArrayList werteArrayA = new ArrayList();
    ArrayList werteArrayB = new ArrayList();
    String[] stringArrayA;
    String[] stringArrayB;
    Context context = this;

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.my_recyclerview_2);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager2 = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView2.setLayoutManager(mLayoutManager2);

        openHandler = new DBTestOpenHandler(this);

        final Button btnInsert = (Button) findViewById(R.id.button2);
        final Button btnDelete = (Button) findViewById(R.id.button4);
        final Button btnPlus = (Button) findViewById(R.id.button5);
        final Button btnMal = (Button) findViewById(R.id.button);
        final Button btnActivity = (Button) findViewById(R.id.button3);
        final EditText eingabeA1 = (EditText) findViewById(R.id.editText4);
        final EditText eingabeA2 = (EditText) findViewById(R.id.editText3);
        final EditText eingabeB1 = (EditText) findViewById(R.id.editText);
        final EditText eingabeB2 = (EditText) findViewById(R.id.editText5);

        werteArrayA = openHandler.readA();
        werteArrayB = openHandler.readB();
        stringArrayA = new String[werteArrayA.size()];
        stringArrayB = new String[werteArrayB.size()];
        for (int i = 0; i < werteArrayA.size(); i++) {
            stringArrayA[i] = werteArrayA.get(i).toString();
        }
        for (int i = 0; i < werteArrayB.size(); i++) {
            stringArrayB[i] = werteArrayB.get(i).toString();
        }
        mAdapter = new Adapter(stringArrayA, stringArrayB);
        mRecyclerView.setAdapter(mAdapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String eingabe1 = eingabeA1.getText().toString();
                String eingabe2 = eingabeA2.getText().toString();
                String eingabe3 = eingabeB1.getText().toString();
                String eingabe4 = eingabeB2.getText().toString();
                int eing1 = 0;
                int eing2 = 0;
                int eing3 = 0;
                int eing4 = 0;
                try {
                    eing1 = Integer.parseInt(eingabe1);
                    eing2 = Integer.parseInt(eingabe2);
                    eing3 = Integer.parseInt(eingabe3);
                    eing4 = Integer.parseInt(eingabe4);
                } catch (Exception e) {
                    Toast.makeText(context, R.string.wrong, Toast.LENGTH_SHORT).show();
                }

                openHandler.insert(eing1, eing2, eing3, eing4);
                werteArrayA = openHandler.readA();
                werteArrayB = openHandler.readB();
                stringArrayA = new String[werteArrayA.size()];
                stringArrayB = new String[werteArrayB.size()];
                for (int i = 0; i < werteArrayA.size(); i++) {
                    stringArrayA[i] = werteArrayA.get(i).toString();
                }
                for (int i = 0; i < werteArrayB.size(); i++) {
                    stringArrayB[i] = werteArrayB.get(i).toString();
                }
                mAdapter = new Adapter(stringArrayA, stringArrayB);
                mRecyclerView.setAdapter(mAdapter);
                ArrayList summe = openHandler.plus();
                String[] stringSumme = new String[summe.size()];
                for (int i = 0; i < summe.size(); i++) {
                    stringSumme[i] = summe.get(i).toString();
                }
                mAdapter2 = new Adapter2(stringSumme);
                mRecyclerView2.setAdapter(mAdapter2);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openHandler.delete();
            }
        });

        btnActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TestActivity.class);
                startActivity(intent);
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList summe = openHandler.plus();
                String[] stringSumme = new String[summe.size()];
                for (int i = 0; i < summe.size(); i++) {
                    stringSumme[i] = summe.get(i).toString();
                }
                mAdapter2 = new Adapter2(stringSumme);
                mRecyclerView2.setAdapter(mAdapter2);
            }
        });
        btnMal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList produkt = openHandler.mal();
                String[] stringProdukt = new String[produkt.size()];
                for (int i = 0; i < produkt.size(); i++) {
                    stringProdukt[i] = produkt.get(i).toString();
                }
                mAdapter2 = new Adapter2(stringProdukt);
                mRecyclerView2.setAdapter(mAdapter2);
            }
        });
    }
}

