package com.example.fantasy;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.app.Activity;
import java.util.ArrayList;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SecondActivity extends ArrayAdapter<String> {

    private int resourceLayout;
    private Context mContext;

    public TextView firstTV;
    public TextView secondTV;
    public TextView thirdTV;
    public TextView fourthTV;
    public TextView fifthTV;


    public SecondActivity(Context context, int resource, int resourceLayout, Context mContext) {
        super(context, resource);
        this.resourceLayout = resourceLayout;
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }
        return v;
    }
}