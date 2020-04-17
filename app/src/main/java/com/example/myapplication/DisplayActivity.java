package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.AdminActivity.DATABASE_NAME;
import static com.example.myapplication.R.layout.activity_list;

public class DisplayActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    List<Admin> adminList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        sqLiteDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        adminList = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listViewDisplay);

        loadAdminDataFromDatabase();


    }

    private  void loadAdminDataFromDatabase(){

        String sql = "SELECT * FROM Healthcare";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()){

            do {
                adminList.add(new Admin(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                ));

            }while (cursor.moveToNext());

            AdminAdapter adapter = new AdminAdapter(this,R.layout.list_layout_display,adminList,sqLiteDatabase);
            listView.setAdapter(adapter);
        }

    }

}
