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
import java.util.List;

/**
 * Created by shin on 12/02/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolderHistory> {

        private List<Mood> mMoodList;
        private Context context ;


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
        if (currentMood!= null){
            holder.dateTextShow.setText(currentMood.getDate().toString());
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


