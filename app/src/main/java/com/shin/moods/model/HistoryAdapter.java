package com.shin.moods.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shin.moods.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by shin on 12/02/2018.
 */

public class HistoryAdapter extends ArrayAdapter<Mood> {


    private Context mContext;
    int mInt;
    private LinearLayout moods;
    private ImageButton comText;
    private TextView dateTextShow;
    private Date date;
    Mood currentMood;
    List<Mood> list = new ArrayList();



    public HistoryAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.mContext = context;
        this.mInt = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View mView = inflater.inflate(mInt, parent, false);

        comText = (ImageButton) mView.findViewById(R.id.comment_text_view);
        comText.setVisibility(View.INVISIBLE);
        moods = (LinearLayout) mView.findViewById(R.id.mood_days);
        dateTextShow = (TextView) mView.findViewById(R.id.date_text);
        date = new Date();
        currentMood = getItem(position);
         load();
        


            if (list== null){
            currentMood = getItem(position);
            moods.setBackgroundColor(currentMood.getBackground());
                dateTextShow.setText( currentMood.getDate().toString());
                final String comment = currentMood.getCommentText();
                if (comment != null){
                    comText.setVisibility(View.VISIBLE);
                }
                comText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext,comment, Toast.LENGTH_LONG ).show();
                    }
                });
                if (date.getMinutes() > currentMood.getDate().getMinutes()){
                    save();
                }
            }else {
                currentMood = getItem(position);
                moods.setBackgroundColor(currentMood.getBackground());
                dateTextShow.setText( currentMood.getDate().toString());
                final String com = currentMood.getCommentText();
                if (com != null){
                    comText.setVisibility(View.VISIBLE);
                }
                comText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext,com, Toast.LENGTH_LONG ).show();
                    }
                });

                int sizeList = list.size();
                Toast.makeText(mContext,String.valueOf(list.size()),Toast.LENGTH_SHORT).show();
                for (Mood mood:list
                     ) {

                    moods.setBackgroundColor(mood.getBackground());
                    dateTextShow.setText(mood.getDate().toString());
                    final String comment = mood.getCommentText();
                    if (comment != null){
                        comText.setVisibility(View.VISIBLE);
                        comText.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext,comment, Toast.LENGTH_LONG ).show();
                            }
                        });
                    }
                }





            }


        return mView;




    }

    public void save(){
        list = (List<Mood>) Arrays.asList(new Mood(0,currentMood.getBackground(),0,0,0,currentMood.getCommentText(),currentMood.getDate()));
        //recuperation des sharedPrefernces
        SharedPreferences mPreferences = mContext.getSharedPreferences("list",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        Gson gsonL = new Gson();
        String jsonL =gsonL.toJson(list);
        editor.putString("list",jsonL).apply();
    }

    public void load(){
        //recuperation des sharedPrefernces
        SharedPreferences mPref = mContext.getSharedPreferences("list",0);
        //transformation des strings preferences en objet
        Gson gson = new Gson();
        String json =mPref.getString("list","");
            Type type = new TypeToken<List<Mood>>(){}.getType();
        list =  gson.fromJson(json,type);


    }

}

