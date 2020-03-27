package com.sky.vsuwt_schedule_.ui.myTimeTables;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sky.vsuwt_schedule_.Common.config;
import com.sky.vsuwt_schedule_.R;
import com.sky.vsuwt_schedule_.activities.schedulePicker;
import com.sky.vsuwt_schedule_.adapters.profileAdapter;
import com.sky.vsuwt_schedule_.models.profileModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class myTimeTablesFragment extends Fragment {

    profileAdapter adapter;

    RecyclerView recyclerView;

    SharedPreferences mySaves;
    Button buttonAdd;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_my_time_tables, container, false);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mySaves = Objects.requireNonNull(this.getActivity()).getSharedPreferences("url", Context.MODE_PRIVATE);

        buttonAdd= view.findViewById(R.id.button_Add);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Intent intent = new Intent(getContext(), schedulePicker.class);
                    startActivity(intent);


            }
        });


        buttonAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(getResources().getColor(R.color.blueGreen), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        LinearLayoutManager ln = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recycler_my_time_tables);
        recyclerView.setLayoutManager(ln);
    }

    @Override
    public void onResume() {
        super.onResume();



        if (mySaves.contains("index")){

            config.row_index = mySaves.getInt("index",-1);

        }

        if (mySaves.contains("myArray")) {

            String jsonStr = (mySaves.getString("myArray", "Nan"));

            Gson gson = new Gson();

            Type type = new TypeToken<ArrayList<profileModel>>() {
            }.getType();

            config.profiles = gson.fromJson(jsonStr, type);

            adapter = new profileAdapter(getContext(), config.profiles);

            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();

            adapter.setOnItemClickListener(new profileAdapter.OnItemClickListener() {


                @Override
                public void onItemClick(int position) {

                    // row_index = position;

                    //  Common.myItem = Common.profiles.get(position);

                    profileModel profItem = config.profiles.get(position);

                    config.agileUrl = profItem.getUrl();

                    int i = 0;

                    while (i< config.profiles.size() ){
//
                config.profiles.get(i).setChecked(false);

                i++;

            }
            config.profiles.get(position).setChecked(true);
                    mySaves.edit().putString("url", config.agileUrl).apply();

                   // config.row_index = position;

                    // Common.profiles.get(position).setChecked(true);

                    // pickProfileActivity.super.onBackPressed();

                }
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();


        String json = new Gson().toJson(config.profiles);

        mySaves.edit().putString("myArray", json).apply();

        if (!config.agileUrl.equals("")) {
            mySaves.edit().putString("url", config.agileUrl).apply();
        }

        mySaves.edit().putInt("index", config.row_index).apply();

    }
}