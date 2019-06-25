package com.example.sukhminder.proshoot;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class gallerypage extends AppCompatActivity {
    GridView gridView;
    ArrayList<gallery> list;
    galleryAdapter adapter=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_gallery);
        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new galleryAdapter(this,R.layout.grid_item, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = MainActivity.databaseHelper.getinfo("SELECT * FROM GALLERY");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name= cursor.getString(1);
            byte[] image = cursor.getBlob(2);

            list.add(new gallery(name, image, id));
        }
        adapter.notifyDataSetChanged();//dataset has changed and any view reflecting data should change itself
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Toast.makeText(gallerypage.this, "" + position,
                        Toast.LENGTH_SHORT).show();

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "photo session is wonderful.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });



    }}