package com.shin.moods.model;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.shin.moods.R;
import com.shin.moods.controller.HistoryActivity;

import java.util.Arrays;
import java.util.List;

import static android.view.MotionEvent.ACTION_UP;

/**
 * Created by shin on 15/01/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    // TODO mise en forme de l'activite historic
    // TODO passer sur les fragments
    // TODO mettre les com en anglais
    // TODO trouver pourquoi alert dialog crash l'appli si on click une segonde fois pour la lancer
    // Todo recuperer les commentaires de l alert dialog pour les enregistrers dans  l objet Mood


    /**
     * Object list of mood
     */
    private final List<Mood> mObjectList = Arrays.asList(
            new Mood(R.drawable.smiley_sad, R.color.faded_red, R.drawable.ic_comment_black_48px, R.drawable.ic_history_black, 1, null),
            new Mood(R.drawable.smiley_disappointed, R.color.warm_grey, R.drawable.ic_comment_black_48px, R.drawable.ic_history_black, 2, null),
            new Mood(R.drawable.smiley_normal, R.color.cornflower_blue_65, R.drawable.ic_comment_black_48px, R.drawable.ic_history_black, 3, null),
            new Mood(R.drawable.smiley_happy, R.color.light_sage, R.drawable.ic_comment_black_48px, R.drawable.ic_history_black, 4, null),
            new Mood(R.drawable.smiley_super_happy, R.color.banana_yellow, R.drawable.ic_comment_black_48px, R.drawable.ic_history_black, 5, null));

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
        Mood arrays = mObjectList.get(position);
        holder.display(arrays);


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
        Context mContext;
        private String mDialogComment;
        private DialogClickListener mDialogClickListener;
        private ImageView imageMood;
        public List historyMood;
        private ImageButton comText;







        public MyViewHolder(final View itemView) {

            super(itemView);

            mContext = itemView.getContext();

            comment = ((ImageButton) itemView.findViewById(R.id.comment_btn));
            history = ((ImageButton) itemView.findViewById(R.id.history_btn));

            emoticon = ((ImageView) itemView.findViewById(R.id.emoticon));
            background = (ConstraintLayout) itemView.findViewById(R.id.layoutCell);

            imageMood = ((ImageView) itemView.findViewById(R.id.emoticon));

            comText = (ImageButton) itemView.findViewById(R.id.comment_text_view);



            history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent historyIntent = new Intent( mContext, HistoryActivity.class);

                    mContext.startActivity(historyIntent);



                }
            });


        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                LayoutInflater layout = LayoutInflater.from(context);
                final View dialogView = layout.inflate(R.layout.alert_dialog, null);
                final EditText editText = (EditText) dialogView.findViewById(R.id.edit_comment);
                editText.setText("replace text here");

              AlertDialog show = new AlertDialog.Builder(context)
                        .setTitle("pick up your comment")
                        .setView(dialogView)
                        .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDialogClickListener.onDialogClick(editText.getText().toString());
                                Log.i("Alert dialog","text submit : " +editText.getText());


                            }

                        })
                      .setNegativeButton( "cancel", new DialogInterface.OnClickListener(){


                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {

                          }
                      })
                        .show();
            }
        });

        mDialogClickListener = new DialogClickListener() {
            @Override
            public void onDialogClick(String comment) {
                mDialogComment = comment;
                Log.i("MainActivity", "Text retrieved : " + mDialogComment);




            }
        };

       imageMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               int color;
                color = res.getColor(currentList.getBackground());
                //historyMood.add(color);

             //  Log.i("idMoood", "id mood clicked " +historyMood.get(color));


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


            //scrolltoposition
           int first = moodList.getIdPosition();
            if (first == 3){
                int color = res.getColor(moodList.getBackground());
                Drawable dr = res.getDrawable(moodList.getIcon());

                emoticon.setImageDrawable(dr);
                background.setBackgroundColor(color);
            }
            else if(first < 3){

                int color = res.getColor(moodList.getBackground());
                Drawable dr = res.getDrawable(moodList.getIcon());

                emoticon.setImageDrawable(dr);
                background.setBackgroundColor(color);
            }

            else if (first > 3){
                int color = res.getColor(moodList.getBackground());
                Drawable dr = res.getDrawable(moodList.getIcon());

                emoticon.setImageDrawable(dr);
                background.setBackgroundColor(color);
            }



        }
    }
}





