package com.shin.moods.controller;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.shin.moods.R;
import com.shin.moods.model.MyAdapter;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    List history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView moodDays = (ListView) findViewById(R.id.list_mood);

        SharedPreferences mSharedPreferences;
        mSharedPreferences = getPreferences(MODE_PRIVATE);

        int background = mSharedPreferences.getInt("background",0);
        String comment = mSharedPreferences.getString("comment", "");


    }
}

