package com.ari.projectbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static listData ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void calc (View view){
        startActivity(new Intent(getApplicationContext(),secondActivity.class));
    }
    public void add (View view) {
        startActivity(new Intent(getApplicationContext(), tambahData.class));
    }
    public void lihat (View view){
        startActivity(new Intent(getApplicationContext(),listData.class));
    }
    public void Location (View view){
        startActivity(new Intent(getApplicationContext(),locationActivity.class));
    }
    public void addMysql(View view) {
        startActivity(new Intent(getApplicationContext(), addData.class));
    }
    public void readMysql(View view){
        startActivity(new Intent(getApplicationContext(), Read.class));
    }
}