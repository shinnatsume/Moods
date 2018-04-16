package com.shin.moods.controller;





import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shin.moods.R;
import com.shin.moods.model.HistoryAdapter;
import com.shin.moods.model.Mood;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class HistoryActivity extends AppCompatActivity {
    private List<Mood> mMoodList ;
    private Mood mood;
    private Date mDate = new Date();
    int result;

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
         * save the list. getMinute() is there to test the application
         * */
//        if (mood!=null && (mDate.getMinutes()- mood.getDate().getMinutes()==1) ){
//            save();
//            mPreferences.edit().clear().apply();
//        }
        /**
         * save the list for application in production
         * */

        if (mood!= null && mood.getDate()!=null){
            String format = "dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);
            String currentDate = simpleDateFormat.format(mDate);
            String moodDate = simpleDateFormat.format(mood.getDate());

            result=  currentDate.compareTo(moodDate);
            Log.i("result","result" + result);


            if (result ==1 && result!=0 && result!=-1){
                save();
                mPreferences.edit().clear().apply();
            }
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
//        Log.i("size List", "size list mood"+mMoodList.size());
        SharedPreferences preferences = this.getSharedPreferences("list",0);
        SharedPreferences.Editor editor =preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mMoodList);
        editor.putString("list",json).apply();

    }

}


