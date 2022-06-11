package com.ari.projectbaru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<String> array_noinduk,array_nama,array_alamat,array_hobi;
    ProgressDialog progressDialog;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_noinduk,tv_nama,tv_alamat,tv_hobi;
        public CardView cv_main;
        public MyViewHolder(View view) {
            super(view);
            cv_main = itemView.findViewById(R.id.cv_main);
            tv_noinduk = itemView.findViewById(R.id.tv_noind);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_alamat = itemView.findViewById(R.id.tv_alamat);
            tv_hobi = itemView.findViewById(R.id.tv_hobi);
            progressDialog = new ProgressDialog(mContext);
        }
    }
    public RecycleViewAdapter(Context mContext,
                              ArrayList<String> array_noinduk,
                              ArrayList<String> array_nama,
                              ArrayList<String> array_alamat,
                              ArrayList<String> array_hobi) {
        super();
        this.mContext = mContext;
        this.array_noinduk = array_noinduk;
        this.array_nama = array_nama;
        this.array_alamat = array_alamat;
        this.array_hobi = array_hobi;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        View itemView =
                layoutInflater.inflate(R.layout.template_rv,parent,false);
        return new RecycleViewAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
        holder.tv_noinduk.setText(array_noinduk.get(position));
        holder.tv_nama.setText(array_nama.get(position));
        holder.tv_alamat.setText(array_alamat.get(position));
        holder.tv_hobi.setText(array_hobi.get(position));

        holder.cv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,edit.class);
                i.putExtra("noinduk",array_noinduk.get(holder.getAdapterPosition()));
                i.putExtra("nama",array_nama.get(holder.getAdapterPosition()));
                i.putExtra("alamat",array_alamat.get(holder.getAdapterPosition()));
                i.putExtra("hobi",array_hobi.get(holder.getAdapterPosition()));
                ((Read)mContext).startActivityForResult(i,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array_noinduk.size();
    }
}
