package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DetailMemoActivity extends AppCompatActivity {

    TextView detailmemo;
    DBHelper dbHelper;
    Memo memo;
    Toolbar tb1 = null;
    Toolbar tb2 = null;
    boolean check = false;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_memo);

        dbHelper = new DBHelper(getApplicationContext(), "Memo.db", null, 1);
        detailmemo = (TextView) findViewById(R.id.detailmemo);

//        check = getIntent().getExtras().getBoolean("menuCheck");
        position = getIntent().getExtras().getInt("position");

        tb1 = (Toolbar) findViewById(R.id.add_toolbar) ;
        tb2 = (Toolbar) findViewById(R.id.detail_toolbar) ;

        if(position > 0){
            tb1.setVisibility(View.INVISIBLE);
            tb2.setVisibility(View.VISIBLE);
            setSupportActionBar(tb2);
            int position = getIntent().getExtras().getInt("position");
            memo = dbHelper.getMemo(position);
            detailmemo.setText(memo.getMemo());
        }else{
            tb1.setVisibility(View.VISIBLE);
            tb2.setVisibility(View.INVISIBLE);
            setSupportActionBar(tb1);
            detailmemo.setHint("ここで内容を書いて下さい。");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        check = getIntent().getExtras().getBoolean("menuCheck");
        if(check == true){
            menuInflater.inflate(R.menu.add_menu, menu);
        }else{
            menuInflater.inflate(R.menu.detail_menu, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        boolean check = getIntent().getExtras().getBoolean("menuCheck");

        switch (item.getItemId()){
            case android.R.id.home:
                backAction();
                finish();

                break;

            case R.id.delete:
                final AlertDialog.Builder popup = new AlertDialog.Builder(this);
                popup.setMessage("削除しますか？");
                popup.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteMemo(getIntent().getExtras().getInt("position"));
                        finish();
                    }
                });
                popup.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                popup.show();
                break;
            }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
//        int position = getIntent().getExtras().getInt("position");
        backAction();
        super.onBackPressed();
    }

    public void backAction(){
        String content = detailmemo.getText().toString();
        content = content.replaceAll("'","''");

        if(content.length()>0){
            Memo memo = new Memo();
            memo.setMemo(content);

            if(position > 0){
                memo.setId(position);
                dbHelper.updateMemo(memo);
            }else{
                dbHelper.addMemo(memo);
            }

        }else {
            dbHelper.deleteMemo(position);
        }
    }
}
