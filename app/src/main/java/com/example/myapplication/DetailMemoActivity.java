package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailMemoActivity extends AppCompatActivity {

    TextView detailmemo;
    DBHelper dbHelper;
    Memo memo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_memo);

        Toolbar tb = (Toolbar) findViewById(R.id.detail_toolbar) ;
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dbHelper = new DBHelper(getApplicationContext(), "Memo.db", null, 1);
        detailmemo = (TextView) findViewById(R.id.detailmemo);

        int position = getIntent().getExtras().getInt("position");
        memo = dbHelper.getMemo(position);
        detailmemo.setText(memo.getMemo());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int position = getIntent().getExtras().getInt("position");

        switch (item.getItemId()){
            case android.R.id.home:
                String content = detailmemo.getText().toString();
                if(content.length()>0){
                    Memo memo = new Memo();
                    memo.setMemo(content);
                    memo.setId(position);
                    dbHelper.updateMemo(memo);
                }else {
                    dbHelper.deleteMemo(position);
                }
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
}
