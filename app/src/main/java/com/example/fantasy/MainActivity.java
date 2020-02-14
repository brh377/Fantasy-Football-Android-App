package com.example.fantasy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    public SQLiteOpenHelper myDbHelper;
    public Cursor c = null;
    public SQLiteDatabase db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDbHelper = new DBHelper(this);
        Button buttonStart = findViewById(R.id.button_enter);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enter();
            }
        });
        db = myDbHelper.getReadableDatabase();
    }

    private void Enter(){
        Intent intent = new Intent(this, interfaces.class);
        startActivity(intent);
    }


}

