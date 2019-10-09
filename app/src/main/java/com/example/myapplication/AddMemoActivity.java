package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class AddMemoActivity extends AppCompatActivity {

    EditText inputmemo;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);

        dbHelper = new DBHelper(getApplicationContext(), "Memo.db", null, 1);

        Toolbar tb = (Toolbar) findViewById(R.id.add_toolbar) ;
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                inputmemo =(EditText)findViewById(R.id.inputmemo);
                String content = inputmemo.getText().toString();
                if(content.length()>0){
                    Memo memo = new Memo();
                    memo.setMemo(content);
                    dbHelper.addMemo(memo);
                }

                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
