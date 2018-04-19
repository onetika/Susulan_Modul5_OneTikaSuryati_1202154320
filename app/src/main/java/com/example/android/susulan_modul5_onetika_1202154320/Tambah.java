package com.example.android.susulan_modul5_onetika_1202154320;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Tambah extends AppCompatActivity {

    //inisialisasi variabel
    Database mDb;
    private EditText tNama,tDeskripsi, tPrioritas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        //inisialisasi variabel dengan manggil id dari tiap variabel
        tNama = (EditText) findViewById(R.id.tNama);
        tDeskripsi = (EditText) findViewById(R.id.tDeskripsi);
        tPrioritas = (EditText) findViewById(R.id.tPrioritas);
        mDb = new Database(this);
    }
    public void tambah(View view) {
        //buat variabel tipe data string, dan get dari si edit text
        String nama = tNama.getText().toString();
        String deskripsi = tDeskripsi.getText().toString();
        String prioritas = tPrioritas.getText().toString();
        AddData(nama, deskripsi,prioritas); //masukin data nya ke tiap variabel

    }
    public void AddData(String nama,String deskripsi,String prioritas ){
        boolean inserData = mDb.addData(nama,deskripsi,prioritas); //buat boolean dengan manggil si database

        if (inserData){ //kondisi ketika masukin data akan munculin toast dan masuk ke kelas MainActivity
            Toast.makeText(this,"Berhasil Menambahkan Data", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else {
            Toast.makeText(this,"Gagal, Coba Sekali Lagi", Toast.LENGTH_LONG).show();
        }

    }


    public void tampilDAta(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i); // nampilin data yang telah diinput ke kelas MainActivity
    }
}
