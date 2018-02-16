package com.shin.moods.controller;



import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shin.moods.R;
import com.shin.moods.model.HistoryAdapter;
import com.shin.moods.model.Mood;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HistoryActivity extends AppCompatActivity {
    List<Mood> mMoodList ;
    Mood mood;
    Date mDate = new Date();
    private LinearLayout moods;
    private ImageButton comText;
    private TextView dateTextShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       List<Mood> list = load();


        comText = (ImageButton) findViewById(R.id.comment_text_view);

        moods = (LinearLayout) findViewById(R.id.mood_days);
        dateTextShow = (TextView) findViewById(R.id.date_text);
        //mise en place recyclerview
        final RecyclerView rView = (RecyclerView) findViewById(R.id.recyclerView);

        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setAdapter(new HistoryAdapter(this,list));


        SharedPreferences mPreferences = this.getSharedPreferences("mood",0);
        Gson gson= new Gson();
        String json = mPreferences.getString("mood","");
        mood = gson.fromJson(json,Mood.class);

       // Toast.makeText(this, (CharSequence) mood.getDate().toString(),Toast.LENGTH_SHORT).show();
        mMoodList.add(mood);


//        Log.i("size list","size list !"+mMoodList.size());
        if (mood!=null && mDate.getMinutes()> mood.getDate().getMinutes()){
            save();
//            Toast.makeText(this,mMoodList.size(),Toast.LENGTH_SHORT).show();
            Log.i("size list","size list !"+mMoodList.size());
        }




    }
    private List<Mood> load() {

        //recuperation des sharedPrefernces
        SharedPreferences mPref = this.getSharedPreferences("list",0);
        //transformation des strings preferences en objet
        Gson gson = new Gson();
        String json =mPref.getString("list",null);
        Type type = new TypeToken<List<Mood>>(){}.getType();
        mMoodList =  gson.fromJson(json,type);
        if (mMoodList== null ){
            mMoodList= new ArrayList<>();
        }
        return mMoodList;
    }
    public void save(){

        if(mMoodList.size()>6){
            mMoodList.remove(0);
        }
        SharedPreferences preferences = this.getSharedPreferences("list",0);
        SharedPreferences.Editor editor =preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mMoodList);
        editor.putString("list",json).apply();

    }

}


