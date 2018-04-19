package com.example.android.susulan_modul5_onetika_1202154320;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by One Tika Suryati on 3/25/2018.
 */

public class Adaptertodo extends RecyclerView.Adapter<Adaptertodo.MyViewHolder> {

    //inisialisasi variabel
    private LayoutInflater mInflater;
    private List<Listtodo> List;
    Database mDb;
    int id;
    MainActivity main = new MainActivity();
    String color;

    public Adaptertodo(Context context, List<Listtodo> Listtodo) {
        mInflater = LayoutInflater.from(context); //inisiasi inflater dari context
        this.List = Listtodo;}
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_todo, parent, false);
        //method untuk menginflate dengan class lainnya
        return new MyViewHolder(mItemView); //mengembalikan nilai dengan parameter mItemView
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Listtodo Listtodo = this.List.get(position); //inisialisasi untuk get posisinya
        id= Listtodo.getId(); //get variabel id
        holder.deskripsi.setText(Listtodo.getDekripsi());            //get value ke textView
        holder.nama.setText(Listtodo.getNama()); //get nilai dari atribute nama
        holder.prioritas.setText(Listtodo.getPrioritas()); //get nilai dari atribute prioritas
        switch (color){ //buat pemilihan warna nya, menggunakan warna yang udah ada di android studionya
            case "Merah":holder.bgColor.setBackgroundResource(R.color.colorAccent);break;
            case "Biru":holder.bgColor.setBackgroundColor(Color.BLUE);break;
            case "Abu-Abu":holder.bgColor.setBackgroundColor(Color.GRAY);break;
            case "Hijau":holder.bgColor.setBackgroundColor(Color.GREEN);break;

        }
    }

    @Override
    public int getItemCount() {
        return List.size();
    } //untuk menentukan size dari layoutnya

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // class MyCiewHolder
        public TextView nama, deskripsi, prioritas;
        ConstraintLayout bgColor;


        public MyViewHolder(View view) {
            super(view);//menginisiasi variable2 attribute dengan id setiap variabel
            nama = (TextView) view.findViewById(R.id.nama);
            deskripsi = (TextView) view.findViewById(R.id.deskripsi);
            prioritas = (TextView) view.findViewById(R.id.prioritas);
            bgColor = (ConstraintLayout) view.findViewById(R.id.layout_background);
            SharedPreferences Preference = PreferenceManager.getDefaultSharedPreferences(view.getContext());
            color = Preference.getString("chosenColor","-1");
            //buat menyimpan log sehingga klo di exit tidak akan restart
            view.setOnClickListener(this); //membuat method untuk klik menu
        }


        @Override
        public void onClick(View view) {               // ketika di klik salah satu menu
            int mPosition = getLayoutPosition();
// Use that to access the affected item in mWordList.
            String element = List.get(mPosition).toString();
            Toast.makeText(view.getContext(), nama.getText(), Toast.LENGTH_SHORT).show();

        }
    }
}

