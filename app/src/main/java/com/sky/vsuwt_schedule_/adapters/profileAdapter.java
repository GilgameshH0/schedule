package com.sky.vsuwt_schedule_.adapters;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.sky.vsuwt_schedule_.Common.config;
import com.sky.vsuwt_schedule_.R;
import com.sky.vsuwt_schedule_.interfaces.ItemClickListener;
import com.sky.vsuwt_schedule_.models.profileModel;

import java.util.ArrayList;

import static android.graphics.Typeface.BOLD;

public class profileAdapter extends RecyclerView.Adapter<profileAdapter.profileHolder> {



    private Context mContext;

    private ArrayList<profileModel> profiles;

    private OnItemClickListener mListener;

    //  private int row_index = -1;

    private SharedPreferences mySettings;

    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;
    }


    public interface OnItemClickListener {

        void onItemClick(int position);
    }


    public profileAdapter(Context mContext, ArrayList<profileModel> profiles)
    {
        this.mContext = mContext;
        this.profiles = profiles;
        this.notifyDataSetChanged();
    }



    @NonNull
    public profileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item,parent, false);



        return new profileHolder(v, mListener);
    }

    public void onBindViewHolder(@NonNull final profileHolder holder, int position){

        mySettings = mContext.getSharedPreferences("arrayData", Context.MODE_PRIVATE);

        holder.textName.setText(profiles.get(position).getName());




        holder.setItemClickListener(new ItemClickListener(){

            @Override
            public void onClick(View view, int position) {


                config.row_index = position;
                config.secCurrentItem = profiles.get(position);

                notifyDataSetChanged();
            }
        });


        if (config.profiles.get(position).isChecked()){

            holder.crown.setColorFilter(Color.parseColor("#02a9cf"));
        }
        else{

            holder.crown.setColorFilter(Color.parseColor("#EFEFEF"));
        }
//        if (config.row_index == position){
//
//            int i = 0;
//            while (i < config.profiles.size() ){
//
//                config.profiles.get(i).setChecked(false);
//                i++;
//
//
//
//            }
//            config.profiles.get(position).setChecked(true);
//
//        }

//        if (){}
//
//        {
//            if (position != 0) {
//                config.profiles.get(position).setChecked(false);
//            }
//        }
//
//        if ((position == 0 ) && (config.profiles.size() == 1)){
//
//            holder.crown.setColorFilter(Color.parseColor("#02a9cf"));
//        }

//        if (config.profiles.get(position).isChecked()){
//
//            holder.crown.setColorFilter(Color.parseColor("#02a9cf"));
//        }
//        else{
//
//            holder.crown.setColorFilter(Color.parseColor("#EFEFEF"));
//        }

        String json = new Gson().toJson(config.profiles);

        mySettings.edit().putString("myArray", json).apply();


    }

    @Override
    public void onViewDetachedFromWindow(@NonNull profileHolder holder) {
        super.onViewDetachedFromWindow(holder);

        String json = new Gson().toJson(config.profiles);

        mySettings.edit().putString("myArray", json).apply();

    }

    @Override
    public void onViewAttachedToWindow(@NonNull profileHolder holder) {
        super.onViewAttachedToWindow(holder);
        String json = new Gson().toJson(config.profiles);

        mySettings.edit().putString("myArray", json).apply();
    }

    public int getItemCount(){
        return profiles.size();
    }

    public class profileHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardview;

        TextView textName;
        Button buttDelete;
        ImageView crown;

        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener ) {
            this.itemClickListener = itemClickListener;
        }

        profileHolder(final View itemView, final OnItemClickListener listener) {   //, final OnItemClickListener listener
            super(itemView);

            crown = itemView.findViewById(R.id.profile_crown);


            textName = itemView.findViewById(R.id.profile_text_view);

            Typeface dateFont = Typeface.createFromAsset(mContext.getAssets(), "FuturaLightC.ttf");

            textName.setTypeface(dateFont);

            buttDelete = itemView.findViewById(R.id.button_delete_profile);

            buttDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION)

                    {
                        if (config.profiles.size() != 1) {
                            config.profiles.remove(position);
                        }
                        profileAdapter.this.notifyDataSetChanged();
                    }
                }
            });

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