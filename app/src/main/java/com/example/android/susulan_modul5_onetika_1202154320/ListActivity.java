package com.example.android.susulan_modul5_onetika_1202154320;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    Database mDb; //buat variabel dari kelas
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.listView); //inisialisasi dengan menggunakan id dari layout list view
        mDb = new Database(this); //inisialisasi variabel new database
        showData(); //menampilkan data
    }

    public void showData(){
        Cursor data = mDb.getData(); //
        ArrayList<String> list = new ArrayList<>(); //ambil array dari list array
        while (data.moveToNext()){ // manggil data array dari database nya
            list.add(data.getString(6));
        }
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list); //manggil adapter
        listView.setAdapter(listAdapter); //set adapter
    }
}