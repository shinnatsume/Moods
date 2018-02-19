package com.shin.moods.controller;



import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private List<Mood> mMoodList ;
    private Mood mood;
    private Date mDate = new Date();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /***
         *loading the preferences list  and if null creation of the list
         */

       List<Mood> list = load();



        /**
         * setting up recyclerview
         * */
        final RecyclerView rView = (RecyclerView) findViewById(R.id.recyclerView);
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setAdapter(new HistoryAdapter(this,list));

        /**
         * recovery of sharedPreferences mood
         * */
        SharedPreferences mPreferences = this.getSharedPreferences("mood",0);
        Gson gson= new Gson();
        String json = mPreferences.getString("mood","");
        mood = gson.fromJson(json,Mood.class);
        /**
         * add mood to the list
         * */
        mMoodList.add(mood);
        /**
         * save the list
         * */
        if (mood!=null && mDate.getMinutes()- mood.getDate().getMinutes()==1 ){
            save();
        }

    }

    /**
    * funtion for load or create the list
    * */
    private List<Mood> load() {


        SharedPreferences mPref = this.getSharedPreferences("list",0);
        Gson gson = new Gson();
        String json =mPref.getString("list",null);
        Type type = new TypeToken<List<Mood>>(){}.getType();
        mMoodList =  gson.fromJson(json,type);
        if (mMoodList== null ){
            mMoodList= new ArrayList<>();
        }
        return mMoodList;
    }

    /***
     *function for save the list
     */

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


