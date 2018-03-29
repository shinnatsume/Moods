package com.shin.moods.model;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.shin.moods.R;
import com.shin.moods.controller.HistoryActivity;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;


/**
 * Created by shin on 15/01/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



    /**
     * Object list of mood
     */
    private final List<Mood> mObjectList = Arrays.asList(
            new Mood(R.drawable.smiley_sad, R.color.faded_red, R.drawable.ic_comment_black_48px, R.drawable.ic_history_black, 1, null,null),
            new Mood(R.drawable.smiley_disappointed, R.color.warm_grey, R.drawable.ic_comment_black_48px, R.drawable.ic_history_black, 2, null,null),
            new Mood(R.drawable.smiley_normal, R.color.cornflower_blue_65, R.drawable.ic_comment_black_48px, R.drawable.ic_history_black, 3, null,null),
            new Mood(R.drawable.smiley_happy, R.color.light_sage, R.drawable.ic_comment_black_48px, R.drawable.ic_history_black, 4, null,null),
            new Mood(R.drawable.smiley_super_happy, R.color.banana_yellow, R.drawable.ic_comment_black_48px, R.drawable.ic_history_black, 5, null,null));

    /**
     * take Resouces from android resouces
     */

    private Resources res;


    /**
     * get context
     */
    public MyAdapter(Context ct) {
        res = ct.getResources();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_cell, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Mood mood = mObjectList.get(position);
        holder.display(mood);


    }

    @Override
    public int getItemCount() {
        return mObjectList.size();
    }


    /**
     * create class MyViewOlder
     */

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView emoticon;
        private final ConstraintLayout background;
        private Mood currentList;
        final ImageButton comment;
        final ImageButton history;
        private Context mContext;
        private String mDialogComment;
        private DialogClickListener mDialogClickListener;
        private ImageView imageMood;



        public MyViewHolder(final View itemView) {

            super(itemView);

            mContext = itemView.getContext();
            comment = ((ImageButton) itemView.findViewById(R.id.comment_btn));
            history = ((ImageButton) itemView.findViewById(R.id.history_btn));
            emoticon = ((ImageView) itemView.findViewById(R.id.emoticon));
            background = (ConstraintLayout) itemView.findViewById(R.id.layoutCell);
            imageMood = ((ImageView) itemView.findViewById(R.id.emoticon));



            /**
             * launch activity two
             * */
            history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent historyIntent = new Intent( mContext, HistoryActivity.class);
                    mContext.startActivity(historyIntent);
                }
            });

            /**
             * button for add comment text
             * */
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();

                    LayoutInflater layout = LayoutInflater.from(context);
                    final View dialogView = layout.inflate(R.layout.alert_dialog, null);
                    final EditText editText = (EditText) dialogView.findViewById(R.id.edit_comment);
                    editText.setText("");

                    AlertDialog show = new AlertDialog.Builder(context)
                            .setTitle(R.string.alertDialogTitle)
                            .setView(dialogView)
                            .setPositiveButton( R.string.positiveBtn, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mDialogClickListener.onDialogClick(editText.getText().toString());
                                }

                            })
                            .setNegativeButton( R.string.negativeBtn, new DialogInterface.OnClickListener(){


                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }
            });
                /**
                 * create alert dialog for add comment
                 * */
            mDialogClickListener = new DialogClickListener() {
                @Override
                public void onDialogClick(String comment) {
                    mDialogComment = comment;
                }
            };

            /**
             * item for save current mood and pass to activity history
             * */
            imageMood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int idMood = currentList.getIdMood();

                    int color = res.getColor(currentList.getBackground());

                    Date dateClick = new Date();

                    Mood mood =  new Mood(0,color,0,0,idMood,null,dateClick) ;

                    SharedPreferences mPreferences = mContext.getSharedPreferences("mood",0);
                    SharedPreferences.Editor editor = mPreferences.edit();
                    Gson gson = new Gson();
                    String json ;

                    if (mDialogComment!=null){
                        Log.i("mood ", "mood with comment"+mood.getDate()+mood.getIdMood()+mood.getCommentText()+mood.getBackground());
                        mood.setCommentText(mDialogComment);
                        json =gson.toJson(mood);
                        editor.putString("mood",json);
                        editor.apply();
                    }else {
                        Log.i("mood ", "mood with out comment"+mood.getDate()+mood.getIdMood()+mood.getBackground());
                        json =gson.toJson(mood);
                        editor.putString("mood",json);
                        editor.apply();
                    }
                }
            });


            /**
             * function to share
             * */
            imageMood.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent intent =  new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    int idMood = currentList.getIdMood();
                    Drawable icon= res.getDrawable(currentList.getIcon());
                    if (mDialogComment != null){
                        String com = mDialogComment;

                        switch (idMood){
                            case 1:
                                intent.putExtra(Intent.EXTRA_TEXT,res.getString(R.string.verySad)+" "+com);
                                break;
                            case 2:
                                intent.putExtra(Intent.EXTRA_TEXT,res.getString(R.string.sad)+" "+com);
                                break;
                            case 3:
                                intent.putExtra(Intent.EXTRA_TEXT,res.getString(R.string.normal)+" "+com);
                                break;
                            case 4:
                                intent.putExtra(Intent.EXTRA_TEXT,res.getString(R.string.happy)+" "+com);
                                break;
                            case 5:
                                intent.putExtra(Intent.EXTRA_TEXT,res.getString(R.string.veryHappy)+" "+com);
                                break;
                        }

                    }else{
                        switch (idMood){
                            case 1:
                                intent.putExtra(Intent.EXTRA_TEXT,res.getString(R.string.verySad));
                                break;
                            case 2:
                                intent.putExtra(Intent.EXTRA_TEXT,res.getString(R.string.sad));
                                break;
                            case 3:
                                intent.putExtra(Intent.EXTRA_TEXT,res.getString(R.string.normal));
                                break;
                            case 4:
                                intent.putExtra(Intent.EXTRA_TEXT,res.getString(R.string.happy));
                                break;
                            case 5:
                                intent.putExtra(Intent.EXTRA_TEXT,res.getString(R.string.veryHappy));
                                break;
                        }
                    }
                    mContext.startActivity(Intent.createChooser(intent,res.getString(R.string.share)));
                    return true;
                }
            });

        }





        /**
         * method display its for moddify imageView And BackgroondColor ...
         *
         * @param moodList list of Object
         */


        public void display(Mood moodList) {
            currentList = moodList;

            int color = res.getColor(moodList.getBackground());
            Drawable dr = res.getDrawable(moodList.getIcon());

            emoticon.setImageDrawable(dr);
            background.setBackgroundColor(color);

        }
    }
}





