package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView memoRecyclerView;
    MemoAdapter memoAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoRecyclerView = (RecyclerView) findViewById(R.id.memoRecyclerView);

        Toolbar tb = (Toolbar) findViewById(R.id.main_toolbar) ;
        setSupportActionBar(tb);
        init();


    }

    private void init() {
        memoRecyclerView = (RecyclerView) findViewById(R.id.memoRecyclerView);
        dbHelper = new DBHelper(getApplicationContext(), "Memo.db", null, 1);

        ArrayList<Memo> memoList = dbHelper.getMemos();
        memoAdapter = new MemoAdapter(memoList);
        memoRecyclerView.setAdapter(memoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.add:

                Intent addIntent = new Intent(MainActivity.this, AddMemoActivity.class);
                startActivity(addIntent);

        }
        return super.onOptionsItemSelected(item);
    }


}
