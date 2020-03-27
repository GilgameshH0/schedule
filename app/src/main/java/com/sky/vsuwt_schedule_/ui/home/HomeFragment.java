package com.sky.vsuwt_schedule_.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sky.vsuwt_schedule_.Common.config;
import com.sky.vsuwt_schedule_.R;
import com.sky.vsuwt_schedule_.activities.checkInternet;
import com.sky.vsuwt_schedule_.activities.checkServer;
import com.sky.vsuwt_schedule_.adapters.lessonAdapter;
import com.sky.vsuwt_schedule_.interfaces.callqback;
import com.sky.vsuwt_schedule_.models.lessonModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;

import static android.graphics.Typeface.BOLD;

public class HomeFragment extends Fragment {

   // private SharedPreferences mySettings;

    private com.sky.vsuwt_schedule_.adapters.lessonAdapter lessonAdapter;
    private ArrayList<lessonModel> lessons = new ArrayList<>();

    private RequestQueue requestQueue;
    SharedPreferences mySaves;
    String weekstr; // переменная где хранится тип недели
    private String weekUrl = "";
    private Button show_before;
    private Button show_next;
    TextView textDate;
    RecyclerView recyclerView;
    TextView textWeek;
    public String secondHardVar;
   public String hardVariable;
    private int b = 0;
    ProgressBar progressBar0;
    ProgressBar progressBar1;
    LinearLayout hideln;
    TextView textViewIsNotLessons;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onResume() {

        super.onResume();





        if (!config.isOnline()){

            Objects.requireNonNull(getActivity()).finish();
            Intent intent = new Intent(getContext(), checkServer.class);
            // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
            startActivity(intent);


        }
//        if (!config.isHostReachable()){
//
//            Objects.requireNonNull(getActivity()).finish();
//           Intent intent = new Intent(getContext(), checkServer.class);
//            // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
//            startActivity(intent);
//
//
//        }
//        else {
//            config.hostReach(new callqback() {
//                @Override
//                public void onReady() {
//                    if (!config.host)
//                    Objects.requireNonNull(getActivity()).finish();
//                    Intent intent = new Intent(getContext(), checkServer.class);
//                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
//                    startActivity(intent);
//                }
//            });


//        }




        progressBar1.setVisibility(View.VISIBLE);
        progressBar0.setVisibility(View.VISIBLE);


//

        getIt(new callqback() {

                    @Override
                    public void onReady() {

                        if ((!hardVariable.equals("weekDays")) && (!hardVariable.equals("stopRight")) && (!hardVariable.equals("stopLeft")) ) {


                            if (hardVariable.equals("up")) {

                                weekstr = "v";

                                textWeek.setText("Верхняя");

                            } else if (hardVariable.equals("down")) {

                                weekstr = "n";

                                textWeek.setText("Нижняя");

                            }



                recyclerView.setVisibility(View.VISIBLE);

                if (mySaves.contains("url")) {

                    weekUrl = "http://vsuwt.ru/schedule/android.php?newsMobile=true&clear_cache=Y&getWeekB=true&b="+b+"&sectionCode=" + mySaves.getString("url", "hey")+weekstr;
                }
                lessons.clear();




                getlessons();

                        }
                        else{
                                    hideln.setVisibility(View.GONE);

                               progressBar0.setVisibility(View.GONE);
//                                progressBar1.setVisibility(View.GONE);
//                                textDate.setVisibility(View.INVISIBLE);
//                                textWeek.setVisibility(View.INVISIBLE);
//                                show_before.setEnabled(false);
//                                show_next.setEnabled(false);
//                                show_before.setVisibility(View.INVISIBLE);
//                                show_next.setVisibility(View.INVISIBLE);
                                textViewIsNotLessons.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);

                        }



            }
        });





    }




    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        textViewIsNotLessons = view.findViewById(R.id.thereIsNoLessons);

        hideln = view.findViewById(R.id.hideLayout);

        mySaves = Objects.requireNonNull(this.getActivity()).getSharedPreferences("url", Context.MODE_PRIVATE);

        progressBar0 = view.findViewById(R.id.sch_progress_week);

        progressBar1 = view.findViewById(R.id.sch_progress_top);

        //weekIndicator = findViewById(R.id.week_indicator);

        //show_current = view.findViewById(R.id.home);

        show_next = view.findViewById(R.id.homeNext_week);

        show_next.setEnabled(true);

        show_before = view.findViewById(R.id.homeBefore_week);

        show_before.setEnabled(true);

        textDate = view.findViewById(R.id.textViewCurrentDate);

        textWeek = view.findViewById(R.id.textView_currentWeek);


        Typeface activitiesTypes = Typeface.createFromAsset(Objects.requireNonNull(getContext()).getAssets(), "FuturaDemiC.ttf");

        Typeface weekFont = Typeface.createFromAsset(Objects.requireNonNull(getContext()).getAssets(), "FuturaLightC.ttf");


        show_next.setTypeface(activitiesTypes, BOLD);

        show_before.setTypeface(activitiesTypes, BOLD);

        textDate.setTypeface(activitiesTypes, BOLD);

        textWeek.setTypeface(weekFont);


        show_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // flag = true;




                if  (!hardVariable.equals("stopRight")) {


                    //Страница определяет по сегодняшнему дню * b , тип недели (Верх или низ)
                    //Мы грузим в приложении неделю
                    //при пролистывании меняется b


                    // Функция загрузки текущей типа недели


                    b = b + 1;
                      //  getLists();

                      //  hardVariable = lists.get(0).getListName();

                    getIt(new callqback() {
                        @Override
                        public void onReady() {




                            if (hardVariable.equals("up")){

                                weekstr = "v";

                                textWeek.setText("Верхняя");

                            }
                            else if (hardVariable.equals("down")) {

                                weekstr = "n";

                                textWeek.setText("Нижняя");

                            }



                            weekUrl = "http://vsuwt.ru/schedule/android.php?newsMobile=true&clear_cache=Y&getWeekB=true&b=" + b + "&sectionCode=" + mySaves.getString("url", "hey") + weekstr;
                            progressBar0.setVisibility(View.VISIBLE);
                            progressBar1.setVisibility(View.VISIBLE);
                            lessons.clear();
                            getlessons();


                        }
                    });


                    //weekIndicator.setText("Следующая неделя");


                }

            }
        });
//
        show_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //weekIndicator.setText("Предыдущая неделя");
                //flag = true;



                if (!hardVariable.equals("stopLeft")) {

                    b = b - 1;

                  //  getLists();

                   // hardVariable = lists.get(0).getListName();

                    getIt(new callqback() {
                        @Override
                        public void onReady() {



                            if (hardVariable.equals("up")){

                                weekstr = "v";

                                textWeek.setText("Верхняя");

                            }
                            else if (hardVariable.equals("down")) {

                                weekstr = "n";

                                textWeek.setText("Нижняя");

                            }



                            weekUrl = "http://vsuwt.ru/schedule/android.php?newsMobile=true&clear_cache=Y&getWeekB=true&b=" + b + "&sectionCode=" + mySaves.getString("url", "hey") + weekstr;
                            progressBar0.setVisibility(View.VISIBLE);
                            progressBar1.setVisibility(View.VISIBLE);
                            lessons.clear();
                            getlessons();


                        }
                    });
                }

            }
        });





        LinearLayoutManager ln = new LinearLayoutManager(getContext());


         recyclerView = view.findViewById(R.id.recycle_shc_main);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setNestedScrollingEnabled(false);
       // recyclerView.getRecycledViewPool().setMaxRecycledViews(7, 0);
        recyclerView.setLayoutManager(ln);
        lessonAdapter = new lessonAdapter(getContext(), lessons);
        recyclerView.setAdapter(lessonAdapter);

        //recyclerView.addOnScrollListener(prOnScrollListener);
      // ln.scrollToPositionWithOffset(calendDay(),0);


        requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));




        // mySettings = Objects.requireNonNull(this.getActivity()).getSharedPreferences("checkInternet", Context.MODE_PRIVATE);
    }

    public void getIt(final callqback q) {

        show_before.setEnabled(false);
        show_next.setEnabled(false);

        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        String url ="http://vsuwt.ru/schedule/typeweek.php?newsMobile=true&&b="+b;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                       hardVariable = response;
                       q.onReady();
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

            //.setText("That didn't work!");
        }
    });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    private void getlessons(){



        JsonArrayRequest jsr = new JsonArrayRequest(weekUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                        try {
            JSONArray jsw = response.getJSONArray(0);

           textWeek.setText(jsw.getString(0));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i =0; i < response.length(); i++){

            lessonModel lModel = new lessonModel();

            JSONObject jsonObject;

            //because from here they could be failures, so we use try and catch

            try{

                //get json object

                jsonObject = response.getJSONObject(i);



                Log.i("Response: ", String.valueOf(jsonObject));

                //add data from object to objects in ListUnit
                lModel.setIs_day(jsonObject.getString("isDay"));
                lModel.setL1Name(jsonObject.getString("l1Name"));
                lModel.setL2Name(jsonObject.getString("l2Name"));
                lModel.setL3Name(jsonObject.getString("l3Name"));
                lModel.setL4Name(jsonObject.getString("l4Name"));
                lModel.setL5Name(jsonObject.getString("l5Name"));
                lModel.setL6Name(jsonObject.getString("l6Name"));
                lModel.setL7Name(jsonObject.getString("l7Name"));
                lModel.setL8Name(jsonObject.getString("l8Name"));

                lModel.setL1type(jsonObject.getString("l1Type"));
                lModel.setL2type(jsonObject.getString("l2Type"));
                lModel.setL3type(jsonObject.getString("l3Type"));
                lModel.setL4type(jsonObject.getString("l4Type"));
                lModel.setL5type(jsonObject.getString("l5Type"));
                lModel.setL6type(jsonObject.getString("l6Type"));
                lModel.setL7type(jsonObject.getString("l7Type"));
                lModel.setL8type(jsonObject.getString("l8Type"));

                lModel.setL1Class(jsonObject.getString("l1Class"));
                lModel.setL2Class(jsonObject.getString("l2Class"));
                lModel.setL3Class(jsonObject.getString("l3Class"));
                lModel.setL4Class(jsonObject.getString("l4Class"));
                lModel.setL5Class(jsonObject.getString("l5Class"));
                lModel.setL6Class(jsonObject.getString("l6Class"));
                lModel.setL7Class(jsonObject.getString("l7Class"));
                lModel.setL8Class(jsonObject.getString("l8Class"));

                lModel.setL1Teacher(jsonObject.getString("l1Teacher"));
                lModel.setL2Teacher(jsonObject.getString("l2Teacher"));
                lModel.setL3Teacher(jsonObject.getString("l3Teacher"));
                lModel.setL4Teacher(jsonObject.getString("l4Teacher"));
                lModel.setL5Teacher(jsonObject.getString("l5Teacher"));
                lModel.setL6Teacher(jsonObject.getString("l6Teacher"));
                lModel.setL7Teacher(jsonObject.getString("l7Teacher"));
                lModel.setL8Teacher(jsonObject.getString("l8Teacher"));
                lModel.setGetCurrentLesson(jsonObject.getString("currentLesson"));
                lModel.setIsChanges(jsonObject.getString("changes"));
                lModel.setIsDate(jsonObject.getString("date"));

            }catch (JSONException e){

                e.printStackTrace();

            }



            //add all the above to the array list


            lessons.add(lModel);

        }

        textDate.setText(lessons.get(0).getIsDate()+" - "+lessons.get(6).getIsDate());

        //notify the adapter that some things has changed


        //recyclerView.smoothScrollToPosition(calendDay());

                Calendar c = GregorianCalendar.getInstance();
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                DateFormat df = new SimpleDateFormat("dd.MM", Locale.getDefault());
                String startDate = df.format(c.getTime());


        if (lessons.get(0).getIsDate().equals(startDate)) {
            recyclerView.scrollToPosition(calendDay());
        }

        lessonAdapter.setData(lessons);

        lessonAdapter.notifyDataSetChanged();



        progressBar0.setVisibility(View.GONE);
        progressBar1.setVisibility(View.GONE);
        show_next.setEnabled(true);

        show_before.setEnabled(true);

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

            }
        });

        RequestQueue rq = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

        rq.add(jsr);

    }






    private int calendDay (){

        Calendar myCalendar = Calendar.getInstance();

        int weekDay = 0;

        int dayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek)
        {

            case Calendar.MONDAY:
                weekDay = 0;
                break;
            case Calendar.TUESDAY:
                weekDay = 1;
                break;
            case Calendar.WEDNESDAY:
                weekDay = 2;
                break;
            case Calendar.THURSDAY:
                weekDay = 3;
                break;
            case Calendar.FRIDAY:
                weekDay = 4;
                break;
            case Calendar.SATURDAY:
                weekDay = 5;
                break;
            case Calendar.SUNDAY:
                weekDay = 6;
                break;
        }

        return weekDay;

    }

    @Override
    public void onPause() {
        super.onPause();
        b = 0;
    }

//  hardVariable = lists.get(0).getListName();




}