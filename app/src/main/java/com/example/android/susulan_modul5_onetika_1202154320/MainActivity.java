package com.example.android.susulan_modul5_onetika_1202154320;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //inisialisasi variabel
    Database mDb;
    private List<Listtodo> List = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adaptertodo mAdapter;
    int i=0;
    int[] id  = new int[20]; ;
    String[] nama = new String[20];
    String[] deskripsi;
    String[] prioritas;
    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view); //inisialisasi variabel dengan id dari recycler view
        mAdapter = new Adaptertodo(this, List); //inisialisasi dari variabel adapter
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext()); //menginisiasi adapter untuk recycleView
        recyclerView.setLayoutManager(mLayoutManager);      //menghubungkan adapter dan layout
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter); //set si adapter nya dengan value nya atribute mAdapter
        mDb = new Database(this);
        prepareData(); //memanggil method prepareData

        //buat ngeswipe si cardview supaya bisa terhapus
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN
                        | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            /**
             * Method that defines the drag and drop functionality
             * @param recyclerView The RecyclerView that contains the list items
             * @param viewHolder The SportsViewHolder that is being moved
             * @param target The SportsViewHolder that you are switching the original one with.
             * @return returns true if the item was moved, false otherwise
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                //Get the from and to position
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                //Swap the items and notify the adapter
                Collections.swap(List, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            /**
             * Method that defines the swipe to dismiss functionality
             * @param viewHolder The viewholder being swiped
             * @param direction The direction it is swiped in
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Listtodo todoList = List.get(viewHolder.getAdapterPosition()); //method ketika dihapus, maka posisi dari cardview nya berubah
                Log.d("msg",todoList.getNama()); //menampilkan pesan log untuk memastikan swipe berjalan
                String id = String.valueOf(todoList.getId());
                //Remove the item from the dataset
                List.remove(viewHolder.getAdapterPosition());
                mDb.deleteData(todoList.getId());
                //Notify the adapter
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        //Attach the helper to the RecyclerView
        helper.attachToRecyclerView(recyclerView);

        //-------

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //inisialisasi untuk toolbar
        setSupportActionBar(toolbar); //untuk set si floating buttonnya

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab); //inisialisasi si floating button  nya
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //ketika di klik akan menjalankan fungsinya

                Intent i = new Intent(getApplicationContext(), Tambah.class); //merujuk ke kelas tambah
                startActivity(i); //untuk menjalankan aktivitas nya
            }
        });

        SharedPreferences Preference = PreferenceManager.getDefaultSharedPreferences(this);
        color = Preference.getString("chosenColor","-1"); //buat menyimpan log sehingga klo di exit tidak akan restart
    }

    private void prepareData() {
        Cursor data = mDb.getData(); //buat attribute dengan get si atribute mDb
        List.clear(); //menghapus si list
        while (data.moveToNext()){
            List.add(new Listtodo(data.getInt(0),data.getString(1),data.getString(2),data.getString(3)));
        }
        mAdapter.notifyDataSetChanged();//buat notifikasi perubahan
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item); //mengembalikan nilai si method nya sendiri
    }

}

