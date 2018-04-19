package com.example.android.susulan_modul5_onetika_1202154320;

/**
 * Created by One Tika Suryati on 3/25/2018.
 */

public class Listtodo { //buat kelas untuk adapter list
    private int id;
    private String nama, deskripsi, prioritas; //buat variabel untuk edittext nya
    public Listtodo( int id, String nama, String deskripsi, String prioritas ) { //buat konstruktor dengan value nya dari variabel edittext
        //manggil id dari kelasnya sendiri
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.prioritas= prioritas;
    }
    //mengget dan mengembalikan nilai dari setiap id
    public int getId(){return id;}

    public String getDekripsi() {
        return deskripsi;
    }

    public String getNama() {
        return nama;
    }

    public String getPrioritas() {
        return prioritas;
    }


}

