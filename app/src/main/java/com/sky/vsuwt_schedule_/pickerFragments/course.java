package com.sky.vsuwt_schedule_.pickerFragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sky.vsuwt_schedule_.Common.config;
import com.sky.vsuwt_schedule_.R;
import com.sky.vsuwt_schedule_.activities.checkInternet;
import com.sky.vsuwt_schedule_.activities.checkServer;
import com.sky.vsuwt_schedule_.activities.schedulePicker;
import com.sky.vsuwt_schedule_.adapters.courseListAdapter;
import com.sky.vsuwt_schedule_.adapters.facultListAdapter;
import com.sky.vsuwt_schedule_.models.listModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class course extends Fragment {


    ProgressBar progressBar;
    private RequestQueue requestQueue;
    private courseListAdapter myListAdapter;
    private SharedPreferences mySaves;
    private String specStr;
    public static String courseStr ="";

    private ArrayList<listModel> lists = new ArrayList<>();

    private String url;

    public course() {
        // Required empty public constructor
    }

    @Override
    public void onPause() {
        super.onPause();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.course_progressBar);



        Typeface Font = Typeface.createFromAsset(getContext().getAssets(), "FuturaDemiC.ttf");

        TextView text1 = view.findViewById(R.id.course_text1);
        TextView text2 = view.findViewById(R.id.course_text2);

        text1.setTypeface(Font, Typeface.BOLD);
        text2.setTypeface(Font, Typeface.BOLD);

        Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.recycler_animation);
        LinearLayout linearLayout = view.findViewById(R.id.linear_course);
        linearLayout.startAnimation(myAnim);
       // mySaves = Objects.requireNonNull(this.getActivity()).getSharedPreferences("pieces", Context.MODE_PRIVATE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recycler_course);
        recyclerView.setLayoutManager(layoutManager);
        myListAdapter = new courseListAdapter(getContext(), lists);
        recyclerView.setAdapter(myListAdapter);
        myListAdapter.setOnItemClickListener(new courseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {



                 courseStr = lists.get(position).getListName();
               // mySaves.edit().putString("course", courseStr).apply();
               // schedulePicker.saves.onSaveCourse(courseStr);
               // schedulePicker.updateLayout.group(true);
            }
        });
        requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
    }

    @Override
    public void onResume() {

        super.onResume();

//        if (!config.isOnline()){
//
//            Objects.requireNonNull(getActivity()).finish();
//            Intent intent = new Intent(getContext(), checkInternet.class);
//            // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
//            startActivity(intent);
//
//
//        }
//        else if (!config.isHostReachable(getContext())){
//
//            Objects.requireNonNull(getActivity()).finish();
//            Intent intent = new Intent(getContext(), checkServer.class);
//            // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
//            startActivity(intent);
//
//        }
        schedulePicker.currentfra = 3;
        progressBar.setVisibility(View.VISIBLE);
//        if (mySaves.contains("spec")) {
//
//            specStr = mySaves.getString("spec", "re:zero");
//        }

        specStr = schedulePicker.specStr;
        lists.clear();
        getLists();
    }

    private void getLists() {

        //add to requestQueue

        requestQueue.add(getListsData());




    }

    private JsonArrayRequest getListsData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://vsuwt.ru/schedule/lists.php?getSpec"+ specStr,

                new Response.Listener<JSONArray>() {

                    @Override

                    public void onResponse(JSONArray response) {

                        //this is called when a response is gotten from the server





                        //after getting the data, I need to parse it the view

                        parseListsData(response);


                    }

                }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

                if (error instanceof NoConnectionError) {

                    Objects.requireNonNull(getActivity()).finish();
                    Intent intent = new Intent(getContext(), checkInternet.class);
                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                    startActivity(intent);
                }
                if (error instanceof ServerError) {

                    Objects.requireNonNull(getActivity()).finish();
                    Intent intent = new Intent(getContext(), checkServer.class);
                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                    startActivity(intent);
                }

                /* progressBar.setVisibility(View.GONE); */

                //If an error occurs that means end of the list has reached



                // Toast.makeText(getApplicationContext(), "No More Result Available", Toast.LENGTH_SHORT).show();

            }

        });



        //some retrypoilicy for bad network

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000,0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



        //return array

        return jsonArrayRequest;

    }


    private void parseListsData(JSONArray response) {

        Log.i("Response: ", String.valueOf(response));

        //create a forLoop

        for(int i =0; i < response.length(); i++){

            listModel listModel = new listModel();

            JSONObject jsonObject;

            //because from here they could be failures, so we use try and catch

            try{

                //get json object

                jsonObject = response.getJSONObject(i);

                // Log.i("Response: ", String.valueOf(jsonObject));

                //add data from object to objects in ListUnit
                listModel.setListName(jsonObject.getString("name"));

            }catch (JSONException e){

                e.printStackTrace();

            }


            lists.add(listModel);



        }


        myListAdapter.notifyDataSetChanged();

        progressBar.setVisibility(View.INVISIBLE);
    }

}
