package com.sky.vsuwt_schedule_.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.sky.vsuwt_schedule_.R;
import com.sky.vsuwt_schedule_.interfaces.ItemClickListener;
import com.sky.vsuwt_schedule_.models.aboutModel;

import java.util.ArrayList;

import static android.graphics.Typeface.BOLD;

public class aboutAdapter extends RecyclerView.Adapter<aboutAdapter.aboutHolder> {



    private Context mContext;

    private ArrayList<aboutModel> lists;

    private OnItemClickListener mListener;

    //  private int row_index = -1;

    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;
    }


    public interface OnItemClickListener {

        void onItemClick(int position);
    }


    public aboutAdapter(Context mContext, ArrayList<aboutModel> lists)
    {
        this.mContext = mContext;
        this.lists = lists;
        this.notifyDataSetChanged();
    }



    @NonNull
    public aboutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.about_activity_item,parent, false);



        return new aboutHolder(v, mListener);
    }

    public void onBindViewHolder(@NonNull aboutHolder holder, int position){


        holder.title.setText(lists.get(position).getTitle());

        holder.subTitle.setText(lists.get(position).getSubTitle());

      //  holder.textName.setText(profiles.get(position).getName());
        holder.setItemClickListener(new ItemClickListener(){

            @Override
            public void onClick(View view, int position) {







            }
        });

    }





    public int getItemCount(){
        return lists.size();
    }

    public class aboutHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;

        TextView subTitle;
        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener ) {
            this.itemClickListener = itemClickListener;
        }

        aboutHolder(final View itemView, final OnItemClickListener listener) {   //, final OnItemClickListener listener
            super(itemView);


            title = itemView.findViewById(R.id.aboutTextViewTitle);

            Typeface fontTypefaceTitle = Typeface.createFromAsset(mContext.getAssets(), "FuturaBookC.ttf");

            title.setTypeface(fontTypefaceTitle, BOLD);

            subTitle = itemView.findViewById(R.id.aboutTextViewSubTitle);

            Typeface fontTypefaceSubTitle = Typeface.createFromAsset(mContext.getAssets(), "FuturaLightC.ttf");

            subTitle.setTypeface(fontTypefaceSubTitle, BOLD);

            itemView.setOnClickListener(this);

        }

        @Override
            public void onClick(View view) {

                if (mListener !=null){
//
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION)
                    {
                        mListener.onItemClick(position);
                    }

                }

            itemClickListener.onClick(view,getAdapterPosition());
        }

    }


}