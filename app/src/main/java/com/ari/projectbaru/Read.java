package com.ari.projectbaru;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
public class Read extends AppCompatActivity {
    SwipeRefreshLayout srl_main;
    RecyclerView rv_main;
    ArrayList<String> array_nama,array_noinduk,array_alamat,array_hobi;
    ProgressDialog progressDialog;
    RecycleViewAdapter recycleViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        srl_main = findViewById(R.id.srl_main);
        rv_main = findViewById(R.id.rv_main);
        progressDialog = new ProgressDialog(this);
        rv_main.hasFixedSize();
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_main.setLayoutManager(layoutManager);
        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();srl_main.setRefreshing(false);
            }
        });
        scrollRefresh();
    }
    public void scrollRefresh(){
        progressDialog.setMessage("Mengambil Data.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        },1000);
    }
    void initializeArray(){
        array_noinduk = new ArrayList<>();
        array_nama = new ArrayList<>();
        array_alamat = new ArrayList<>();
        array_hobi = new ArrayList<>();
        array_noinduk.clear();
        array_nama.clear();
        array_alamat.clear();
        array_hobi.clear();
    }
    public void getData(){
        initializeArray();
        AndroidNetworking.get("http://10.0.2.2/api-siswa/getData.php")
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        try{
                            Boolean status = response.getBoolean("status");
                            if(status){
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon",""+ja);
                                for(int i = 0 ; i < ja.length() ; i++){
                                    JSONObject jo = ja.getJSONObject(i);
                                    array_noinduk.add(jo.getString("noinduk"));
                                    array_nama.add(jo.getString("nama"));
                                    array_alamat.add(jo.getString("alamat"));
                                    array_hobi.add(jo.getString("hobi"));
                                }
                            }else{
                                Toast.makeText(Read.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                            }
                            recycleViewAdapter = new RecycleViewAdapter(Read.this,array_noinduk,array_nama,array_alamat,array_hobi
                            );
                            rv_main.setAdapter(recycleViewAdapter);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                scrollRefresh();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Canceled",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}