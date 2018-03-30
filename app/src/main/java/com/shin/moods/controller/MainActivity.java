package com.shin.moods.controller;



import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;


import com.shin.moods.R;
import com.shin.moods.model.MyAdapter;

public class MainActivity extends AppCompatActivity {

LinearLayoutManager mLinearLayoutManager;
    private RecyclerView recyclerView;
    private int position;
    private int positionSaved=-1;
    private int positionInit=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences pref = this.getSharedPreferences("state",0);
        positionSaved = pref.getInt("state", -1);

       /**
        * setting up recyclerview
        * */

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);

        /**
         *allows to start the application on the emoticon wish
         * */
        if ( positionSaved!=positionInit&& positionSaved!=-1) {
            mLinearLayoutManager.scrollToPosition(positionSaved);
        }else{
            mLinearLayoutManager.scrollToPosition(positionInit);
        }
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(new MyAdapter(this));


        /**
        * function allowing the complete slide from one mood to another
        * */

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

    }
    public void onStart() {

        super.onStart();
    }

    public void onStop(){
        position=mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
        SharedPreferences preferences = this.getSharedPreferences("state", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("state",position);
        editor.apply();
//        Log.i("position","position"+position);
        super.onStop();
    }
    public void onPause() {
        super.onPause();
    }
    public void onResume() {
        super.onResume();
    }



}
