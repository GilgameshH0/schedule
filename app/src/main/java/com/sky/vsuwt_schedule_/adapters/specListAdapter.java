package com.sky.vsuwt_schedule_.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sky.vsuwt_schedule_.Common.config;
import com.sky.vsuwt_schedule_.R;
import com.sky.vsuwt_schedule_.activities.schedulePicker;
import com.sky.vsuwt_schedule_.interfaces.ItemClickListener;
import com.sky.vsuwt_schedule_.models.listModel;

import java.util.ArrayList;

import static android.graphics.Typeface.BOLD;

public class specListAdapter extends RecyclerView.Adapter<specListAdapter.listViewHolder> {

    private Context mContext;

    private ArrayList<listModel> models;

    private OnItemClickListener mListener;

    private int row_index = -1;

    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;
    }


    public interface OnItemClickListener {

        void onItemClick(int position);
    }


    public specListAdapter(Context mContext, ArrayList<listModel> models)
    {
        this.mContext = mContext;
        this.models = models;
        this.notifyDataSetChanged();
    }



    @NonNull
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent, false);
        return new listViewHolder(v, mListener);
    }

    public void onBindViewHolder(@NonNull listViewHolder holder, int position){

        holder.name.setText(models.get(position).getListName());
        holder.setItemClickListener(new ItemClickListener(){

            @Override
            public void onClick(View view, int position) {
                config.specRowIndex = position;
                config.currentlistItem = models.get(position);
                notifyDataSetChanged();
            }
        });


        if (config.specRowIndex == position){

            holder.itemView.setBackgroundColor(Color.parseColor("#0064B9"));
            holder.name.setTextColor(Color.parseColor("#FFFFFF"));
        }

        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF")); //белый

            holder.name.setTextColor(Color.parseColor("#0064B9"));
        }

    }

    public int getItemCount(){
        return models.size();
    }

    public class listViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView name;

        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener ) {
            this.itemClickListener = itemClickListener;
        }

        listViewHolder(final View itemView, final OnItemClickListener listener) {   //, final OnItemClickListener listener
            super(itemView);

            name = itemView.findViewById(R.id.list_name);

            Typeface tp = Typeface.createFromAsset(mContext.getAssets(), "FuturaLightC.ttf");

            name.setTypeface(tp, BOLD);
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