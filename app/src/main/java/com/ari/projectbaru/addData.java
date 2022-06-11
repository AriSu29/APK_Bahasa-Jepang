package com.ari.projectbaru;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class addData extends AppCompatActivity {
    EditText et_noinduk,et_nama,et_alamat,et_hobi;
    String noinduk,nama,alamat,hobi;
    Button btn_submit;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        et_noinduk = findViewById(R.id.et_noinduk);
        et_nama = findViewById(R.id.et_nama);
        et_alamat = findViewById(R.id.et_alamat);
        et_hobi = findViewById(R.id.et_hobi);
        btn_submit = findViewById(R.id.btn_submit);
        progressDialog = new ProgressDialog(this);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Menambahkan Data...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                noinduk = et_noinduk.getText().toString();
                nama = et_nama.getText().toString();
                alamat = et_alamat.getText().toString();
                hobi = et_hobi.getText().toString();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validasiData();
                    }
                },1000);
            }
        });
    }

    void validasiData() {
        if(noinduk.equals("") || nama.equals("") || alamat.equals("") || hobi.equals("")){
            progressDialog.dismiss();
            Toast.makeText(this, "Periksa kembali data yang anda masukkan !", Toast.LENGTH_SHORT).show();
        }else {
            kirimData();
        }
    }

     void kirimData() {
         AndroidNetworking.post("http://10.0.2.2/api-siswa/tambahSiswa.php")
                 .addBodyParameter("noinduk",noinduk)
                 .addBodyParameter("nama",nama)
                 .addBodyParameter("alamat",alamat)
                 .addBodyParameter("hobi",hobi)
                 .setPriority(Priority.MEDIUM)
                 .setTag("Tambah Data")
                 .build()
                 .getAsJSONObject(new JSONObjectRequestListener() {
                     @Override
                     public void onResponse(JSONObject response) {
                         progressDialog.dismiss();
                         Log.d("cek Tambah",""+ response);
                         try {
                             Boolean status = response.getBoolean("status");
                             String pesan = response.getString("result");
                             Toast.makeText(addData.this, " " + pesan, Toast.LENGTH_SHORT).show();
                             Log.d("status",""+status);
                             if(status){
                                 new AlertDialog.Builder(addData.this).setMessage("Berhasil Menambahkan Data !").show();
                             }
                             else{
                                 new AlertDialog.Builder(addData.this).setMessage("Gagal Menambahkan Data !").show();
                             }
                         }catch (Exception e){
                             e.printStackTrace();
                         }
                     }
                     @Override
                     public void onError(ANError anError) {
                         progressDialog.dismiss();
                         new AlertDialog.Builder(addData.this).setMessage("Gagal!").show();
                     }
                 });
     }
    }