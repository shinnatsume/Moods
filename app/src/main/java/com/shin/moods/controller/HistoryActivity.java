package com.shin.moods.controller;

import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shin.moods.R;




public class HistoryActivity extends AppCompatActivity {

    SharedPreferences mPreferences;
    public  static  final String COLOR = "COLOR";
    public  static  final String COMMENT = "COMMENT";
    LinearLayout moods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView moodDays = (ListView) findViewById(R.id.list_mood);
        ImageButton comText =(ImageButton) findViewById(R.id.comment_text_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);


        moods =(LinearLayout) findViewById(R.id.mood_days);






    }
    public void saveData(){
        SharedPreferences mPreferences = getSharedPreferences("pref",MODE_PRIVATE);
        mPreferences.edit().putInt(COLOR, 0 ).apply();
        mPreferences.edit().putString(COMMENT,null).apply();
    }

    public void loadData(){
       moods.setBackgroundColor(mPreferences.getInt(COLOR, Integer.parseInt("")));
        mPreferences.getString(COMMENT,null);
    }

}

