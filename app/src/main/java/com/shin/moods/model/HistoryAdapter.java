package com.shin.moods.model;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.shin.moods.R;

import java.util.Date;
import java.util.List;

/**
 * Created by shin on 12/02/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolderHistory> {

    private List<Mood> mMoodList;
    private Context context ;
    Date date;


    public HistoryAdapter(Context ct,List<Mood> moodList){
        context = ct;
        mMoodList=moodList;
    }

    @Override
    public MyViewHolderHistory onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.history_cell, parent, false);
        return new MyViewHolderHistory(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolderHistory holder, int position) {

        Mood currentMood = mMoodList.get(position);
        date= new Date();
        if ( currentMood != null && mMoodList.size() !=0 && currentMood.getDate()!= null){






                int widthSet;
                switch (currentMood.getIdMood()) {
                    case 1:
                        widthSet = 850;
                        holder.moods.setLayoutParams(new LinearLayout.LayoutParams(widthSet, 301));
                        break;
                    case 2:
                        widthSet = 950;
                        holder.moods.setLayoutParams(new LinearLayout.LayoutParams(widthSet, 301));
                        break;
                    case 3:
                        widthSet = 1050;
                        holder.moods.setLayoutParams(new LinearLayout.LayoutParams(widthSet, 301));
                        break;
                    case 4:
                        widthSet = 1250;
                        holder.moods.setLayoutParams(new LinearLayout.LayoutParams(widthSet, 301));
                        break;
                    case 5:
                        widthSet = ViewGroup.LayoutParams.MATCH_PARENT;
                        holder.moods.setLayoutParams(new LinearLayout.LayoutParams(widthSet, 301));
                        break;
                }

                switch (date.getMinutes() - currentMood.getDate().getMinutes()) {
                    case 6:
                        holder.dateTextShow.setText("seven days ago");
                        break;
                    case 5:
                        holder.dateTextShow.setText("six days ago");
                        break;
                    case 4:
                        holder.dateTextShow.setText("five days ago");
                        break;
                    case 3:
                        holder.dateTextShow.setText("four days ago");
                        break;
                    case 2:
                        holder.dateTextShow.setText("tree days ago");
                        break;
                    case 1:
                        holder.dateTextShow.setText("yesterday");
                        break;
                    case 0:
                        holder.dateTextShow.setText("today");
                        break;

            }



            int color =currentMood.getBackground();
            final String comment = currentMood.getCommentText();
            holder.moods.setBackgroundColor(color);
            if (comment != null){
                holder. comText.setVisibility(View.VISIBLE);
                holder. comText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,comment,Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }





    @Override
    public int getItemCount() {

        return mMoodList.size();
    }

    public class MyViewHolderHistory extends RecyclerView.ViewHolder {

        private LinearLayout moods;
        private ImageButton comText;
        private TextView dateTextShow;


        public MyViewHolderHistory(View mView) {
            super(mView);

            comText = (ImageButton) mView.findViewById(R.id.comment_text_view);
            comText.setVisibility(View.INVISIBLE);
            moods = (LinearLayout) mView.findViewById(R.id.mood_days);
            dateTextShow = (TextView) mView.findViewById(R.id.date_text);


        }




    }

}


