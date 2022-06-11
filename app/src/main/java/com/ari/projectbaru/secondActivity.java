package com.ari.projectbaru;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {
    EditText bil1, bil2;
    TextView hasil;
    int b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bil1 = findViewById(R.id.bil1);
        bil2 = findViewById(R.id.bil2);
        hasil = findViewById(R.id.hasil);
    }
    public void tambah (View view) {
        b1 = Integer.parseInt(bil1.getText().toString());
        b2 = Integer.parseInt(bil2.getText().toString());
        hasil.setText(Integer.toString(b1+b2));
    }
    public void kurang (View view) {
        b1 = Integer.parseInt(bil1.getText().toString());
        b2 = Integer.parseInt(bil2.getText().toString());
        hasil.setText(Integer.toString(b1-b2));
    }
    public void kali (View view) {
        b1 = Integer.parseInt(bil1.getText().toString());
        b2 = Integer.parseInt(bil2.getText().toString());
        hasil.setText(Integer.toString(b1*b2));
    }
    public void bagi (View view) {
        b1 = Integer.parseInt(bil1.getText().toString());
        b2 = Integer.parseInt(bil2.getText().toString());
        hasil.setText(Integer.toString(b1/b2));
    }
    public void reset (View view) {
        bil1.getText().clear();
        bil2.getText().clear();
        hasil.setText("");
    }
}
