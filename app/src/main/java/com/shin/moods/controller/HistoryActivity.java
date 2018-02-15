package com.shin.moods.controller;


import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.shin.moods.R;
import com.shin.moods.model.HistoryAdapter;
import com.shin.moods.model.Mood;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class HistoryActivity extends AppCompatActivity {




    private ListView moodDays;
    private Date date;
    private List<Mood> historyMood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
//        date = new Date();
        //recuperation des sharedPrefernces
        SharedPreferences mPreferences = this.getSharedPreferences("mood",0);
        //transformation des strings preferences en objet
        Gson gson = new Gson();
        String json =mPreferences.getString("mood","");
        Mood mood =gson.fromJson(json,Mood.class);





        historyMood = new ArrayList();
        historyMood.add(new Mood(0,0,0,0,0,null,null     ));

            historyMood.get(0).setBackground(mood.getBackground());
            historyMood.get(0).setCommentText(mood.getCommentText());
            historyMood.get(0).setDate(mood.getDate());


        moodDays = (ListView) findViewById(R.id.list_mood);


        HistoryAdapter adapter = new HistoryAdapter(
                this,
                R.layout.history_cell
        );
        moodDays.setAdapter(adapter);
        adapter.addAll(historyMood);
    }


}

