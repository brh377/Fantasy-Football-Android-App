package com.example.fantasy;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import android.database.sqlite.SQLiteCursor;

import java.util.ArrayList;


public class interfaces extends AppCompatActivity {

    public SQLiteDatabase db;
    public ArrayList<ArrayAdapter<String>> listItem;
    public ArrayAdapter<String> adapter;
    ListView listView;
    Toolbar toolbar;
    public SecondActivity customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaces);
        EditText filter =(EditText)findViewById(R.id.SearchFilter);
        adapter = new ArrayAdapter<String>(interfaces.this,android.R.layout.simple_list_item_1);
        listView = findViewById(R.id.firstPage);
        toolbar = findViewById(R.id.toolbar);
        listView.setAdapter(adapter);
        listItem = new ArrayList<ArrayAdapter<String>>();


        SQLiteOpenHelper mDBH = new DBHelper(this);
        db = mDBH.getWritableDatabase();

        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (interfaces.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    public void showTeams(View view) {
        adapter.clear();
        int i;
        String query = "SELECT * FROM TEAMS";
        Cursor c = db.rawQuery(query, null);
        String[] array = new String[c.getCount()];
        i=0;
        c.moveToFirst();
        while(!c.isAfterLast()){
            array[i] = c.getString(1);
            i++;
            c.moveToNext();
        }
        adapter.addAll(array);
        c.close();
    }
    public void showGames(View view) {
        adapter.clear();
        int i;
        String query = "SELECT * FROM GAME";
        Cursor c = db.rawQuery(query, null);
        String[] array = new String[c.getCount()];
        i=0;
        c.moveToFirst();

        while(!c.isAfterLast()){
            array[i] = "Week: "+c.getString(1)+" \n"+"Date : "+c.getString(2)+"\n"+"Home: "+c.getString(3)+" Away: "+c.getString(4)+"\n"+"Time :"+c.getString(5);
            i++;
            c.moveToNext();
        }
        adapter.addAll(array);
        c.close();
    }
    public void showPlayers(View view) {
        adapter.clear();
        int i;
        String query = "SELECT * FROM PLAYERS";
        Cursor c = db.rawQuery(query, null);
        String[] array = new String[c.getCount()];
        i=0;
        c.moveToFirst();
        while(!c.isAfterLast()){
            array[i] = c.getString(4)+" "+c.getString(3)+" \n"+"Position: "+c.getString(7)+"\n"+"Team: "+c.getString(6);
            i++;
            c.moveToNext();
        }
        adapter.addAll(array);
        c.close();

    }


    public void showStats(View view) {
        adapter.clear();
        int i;
        String query = "SELECT * FROM STATS natural join PLAYERS";
        Cursor c = db.rawQuery(query, null);
        System.out.println(c.getColumnIndex("displayName"));
        String[] array = new String[c.getCount()];
        i=0;
        c.moveToFirst();
        while(!c.isAfterLast()){
            System.out.println(c.getString(13));
            switch (c.getString(13)) {
                case "QB":
                    array[i] = c.getString(11) + " " + c.getString(13) + " \n" + "Passing Yards: " + c.getString(2) + "\n" + "Rushing Yards: " + c.getString(3)+
                            "\n"+"TD: "+(c.getInt(5)+c.getInt(6));
                    break;
                case "WR":
                    array[i] = c.getString(11) + " " + c.getString(13)+" \n" +"Recieving Yards: "+c.getString(4)+
                            "\n"+"TD: "+(c.getInt(5)+c.getInt(6));
                    break;
                case "TE":
                    array[i] = c.getString(11) + " " + c.getString(13)+" \n"+"Recieving Yards: "+c.getString(4)+
                            "\n"+"TD: "+(c.getInt(5)+c.getInt(6));
                    break;
                case "RB":
                    array[i] = c.getString(11) + " " + c.getString(13)+" \n" +"Rushing Yards: "+c.getString(4)+
                            "\n"+"TD: "+(c.getInt(5)+c.getInt(6));
                    break;
                case "K":
                    array[i] = c.getString(11) + " " + c.getString(13)+
                            "\n"+"FG: "+(c.getInt(5)+c.getInt(6));
            }
            i++;
            c.moveToNext();
        }
        adapter.addAll(array);
        c.close();
    }
    public String deletePlayerTrigger(){
        String deleteRecord = "CREATE TRIGGER if not exists delete_Player " + " AFTER DELETE " + " ON[player] " + " for each row " + " BEGIN " + "  delete from Players where PLAYERID = old.PLAYERID; " + "  delete from Players where PLAYERID = old.PLAYERID; " +  " END; ";
        return deleteRecord;
    }
    public String insertPlayerTrigger(){String insertRecord = "CREATE TRIGGER if not exists add_Player " + " AFTER INSERT " + " ON[player] " + " for each row " + " BEGIN " + " insert into Player values ( new.PLAYERID );"
        + " END;";
        return insertRecord;
    }

}
