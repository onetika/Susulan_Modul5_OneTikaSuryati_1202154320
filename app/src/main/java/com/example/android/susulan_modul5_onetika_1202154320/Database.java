package com.example.android.susulan_modul5_onetika_1202154320;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by One Tika Suryati on 3/25/2018.
 */

public class Database extends SQLiteOpenHelper {

    //inisialisasi tiap variable dengan nilai tetap
    private static final String Table = "listtodo";
    private static final String baris1 = "id";
    private static final String baris2 = "nama";
    private static final String baris3 = "deskripsi";
    private static final String baris4 = "prioritas";

    public Database(Context context) {
        super(context, Table, null, 1);
    } //konstruktor dengan context

    @Override
    public void onCreate(SQLiteDatabase db) {

        //buat tabel untuk sql nya
        String createSql = "CREATE TABLE " + Table + "(" + baris1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                baris2 + " TEXT," +
                baris3 + " TEXT," +
                baris4 + " TEXT)";
        db.execSQL(createSql); //eksekusi pembuatan tabel
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table); //supaya tabel tidak redudansi, jika ada akan terhapus
        onCreate(db);
    }

    public boolean addData(String nama, String deskripsi, String prioritas) { //buat nambahin data dengan parameter nama,deskripsi dan prioritas
        SQLiteDatabase db = this.getWritableDatabase(); //mengget si kelas nya sendiri
        ContentValues in = new ContentValues(); //
        //inputin si tiap isi dari data nya ke tabel
        in.put(baris2, nama);
        in.put(baris3, deskripsi);
        in.put(baris4, prioritas);
        //untuk membuat variable dengan tipe long untuk inputan dari data
        long result = db.insert(Table, null, in);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table; //memilih data dari tabel
        Cursor data = db.rawQuery(query, null);
        return data; //mengembalikan nilai data
    }

    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "DELETE FROM " + Table + " WHERE " + baris1 + " = '" + id + "'"; //buat variabel untuk hapus data dari tabel
        db.execSQL(q); //mengeksekusi variabel untuk menghapus data

    }
}