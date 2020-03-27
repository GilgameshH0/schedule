package com.sky.vsuwt_schedule_.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sky.vsuwt_schedule_.Common.config;
import com.sky.vsuwt_schedule_.MainActivity;
import com.sky.vsuwt_schedule_.R;
import com.sky.vsuwt_schedule_.models.listModel;
import com.sky.vsuwt_schedule_.models.profileModel;
import com.sky.vsuwt_schedule_.pickerFragments.course;
import com.sky.vsuwt_schedule_.pickerFragments.facult;
import com.sky.vsuwt_schedule_.pickerFragments.group;
import com.sky.vsuwt_schedule_.pickerFragments.spec;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class schedulePicker extends AppCompatActivity  {

    public static int currentfra = 0;

    public static int row_index = -1;
   // public static mySaves saves;

    private RequestQueue requestQueue;

    public static String facultStr = "";
    public static String specStr = "";
    public static String courseStr = "";
    public static String groupStr = "";
    private ArrayList<listModel> groupList = new ArrayList<>();
    private String url = "";
    facult facultet = new facult();
    spec specials = new spec();
    course kurs = new course();
    group groupp = new group();
    String checkUrl;
    LinearLayout lin_gone;
    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    Button buttonFour;

    Button okeyButton;

    SharedPreferences mySaves;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_picker);

        overridePendingTransition(0,0);

        lin_gone = findViewById(R.id.lin_gone);

        mySaves = getSharedPreferences("url", Context.MODE_PRIVATE);
        //saves = this;
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.recycler_animation);
        final LinearLayout linearLayout = findViewById(R.id.schedule_picker_linear_layout);
        linearLayout.startAnimation(myAnim);
         buttonOne = findViewById(R.id.buttonOne);
         buttonTwo = findViewById(R.id.buttonTwo);
         buttonThree = findViewById(R.id.buttonThree);
         buttonFour = findViewById(R.id.buttonFour);
         okeyButton = findViewById(R.id.schedule_picker_okey_Button);

        buttonOne.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueGreen)));

         changeFragment(facultet);


         buttonOne.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 buttonOne.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueGreen)));
                 buttonTwo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                 buttonThree.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                 buttonFour.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));



                 changeFragment(facultet);

             }
         });


         buttonTwo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (!facultStr.equals("")) {
                     buttonOne.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                     buttonTwo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueGreen)));
                     buttonThree.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                     buttonFour.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                     changeFragment(specials);
                 }

             }
         });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!specStr.equals("")) {
                    buttonOne.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                    buttonTwo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                    buttonThree.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueGreen)));
                    buttonFour.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                    changeFragment(kurs);
                }

            }
        });

        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!courseStr.equals("")) {
                    buttonOne.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                    buttonTwo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                    buttonThree.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                    buttonFour.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueGreen)));

                    changeFragment(groupp);
                }

            }
        });

        okeyButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(getResources().getColor(R.color.colorDarkAzure), PorterDuff.Mode.SRC_ATOP);
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
        Typeface MainFont = Typeface.createFromAsset(getAssets(), "FuturaDemiC.ttf");
        okeyButton.setTypeface(MainFont, Typeface.BOLD);
        okeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               switch (currentfra){

                   case 1:

//
                       if (!facult.facultStr.equals("")){

                           buttonTwo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueGreen)));
                           buttonOne.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                           buttonThree.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                           buttonFour.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                           facultStr = facult.facultStr;
                           changeFragment(specials);
                       }

                       break;
                   case 2:


                       if (!spec.specStr.equals("")){
                           buttonTwo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                           buttonOne.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                           buttonThree.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueGreen)));
                           buttonFour.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                           specStr = spec.specStr;
                           changeFragment(kurs);
                       }

                       break;
                   case 3:


                       if (!course.courseStr.equals("")){
                           buttonTwo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                           buttonOne.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                           buttonThree.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                           buttonFour.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueGreen)));
                           courseStr = course.courseStr;
                           changeFragment(groupp);
                       }
                       break;

                   case 4:

                           //groupStr = "";

                           if (!group.groupStr.equals("")) {

                               groupStr = group.groupStr;

                               Animation fadeAnim = AnimationUtils.loadAnimation(schedulePicker.this, R.anim.fade);

                               linearLayout.startAnimation(fadeAnim);

                               groupList.clear();

                               Animation stAnim = AnimationUtils.loadAnimation(schedulePicker.this, R.anim.recycler_animation);

                               lin_gone.startAnimation(stAnim);

                               lin_gone.setVisibility(View.VISIBLE);
                               //groupStr = group.groupList.get(0).getListName();
                               getGroup();



                               }


//                       String json = new Gson().toJson(config.profiles);
//                       if (!json.equals("") && !config.agileUrl.equals("")) {
//                           mySaves.edit().putString("myArray", json).apply();
//                       }


                      // changeFragment(specials);
                       break;

               }
            }
        });

        requestQueue = Volley.newRequestQueue(this);

    }

    @Override
    protected void onPause() {
        super.onPause();

        String json = new Gson().toJson(config.profiles);

            mySaves.edit().putString("myArray", json).apply();

        //mySaves.edit().putString("picker", config.picker).apply();

    }

    public void changeFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.swap_layout, fragment).commit();

    }

    @Override
    public void onBackPressed() {
        finish();
       // super.onBackPressed();

    }

    private void getGroup() {

        //add to requestQueue

        requestQueue.add(getGroupData());

    }

    private JsonArrayRequest getGroupData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://vsuwt.ru/schedule/lists.php?getGroup="+groupStr,

                new Response.Listener<JSONArray>() {

                    @Override

                    public void onResponse(JSONArray response) {

                        //this is called when a response is gotten from the server





                        //after getting the data, I need to parse it the view

                        parseGroupData(response);


                    }

                }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    //TODO

                    //Toast.makeText(getContext(), "time out", Toast.LENGTH_SHORT).show();

                } else if (error instanceof AuthFailureError) {

                    //TODO

                } else if (error instanceof ServerError) {

                    //TODO

                   // Toast.makeText(getContext(), "server error", Toast.LENGTH_SHORT).show();

                } else if (error instanceof NetworkError) {

                    //TODO

                    //Toast.makeText(getContext(), "network error", Toast.LENGTH_SHORT).show();

                } else if (error instanceof ParseError) {

                    //TODO

                  //  Toast.makeText(getContext(), "parse error", Toast.LENGTH_SHORT).show();

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


    private void parseGroupData(JSONArray response) {

        Log.i("Response: ", String.valueOf(response));

        //create a forLoop

        for (int i = 0; i < response.length(); i++) {

            listModel listModel = new listModel();

            JSONObject jsonObject;

            //because from here they could be failures, so we use try and catch

            try {

                //get json object

                jsonObject = response.getJSONObject(i);

                // Log.i("Response: ", String.valueOf(jsonObject));

                //add data from object to objects in ListUnit
                listModel.setListName(jsonObject.getString("name"));

            } catch (JSONException e) {

                e.printStackTrace();

            }


            groupList.add(listModel);



            url = groupList.get(0).getListName();

            if (!mySaves.contains("url")) {

                profileModel mod = new profileModel();

                mod.setName("Моё расписание");

                mod.setUrl(url);
                mod.setChecked(true);
                config.profiles.add(mod);

                groupList.clear();
                Intent intent = new Intent(schedulePicker.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            final Dialog dialog = new Dialog(schedulePicker.this);
            dialog.setContentView(R.layout.get_name_picker);

            Button back;

            final EditText editText = dialog.findViewById(R.id.edit_text);

            back = dialog.findViewById(R.id.button_get_name_back);

            Button ok;

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();

                }
            });

            ok = dialog.findViewById(R.id.button_get_name_ok);


            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int i = 0;
                    while (i < config.profiles.size() ){

                        config.profiles.get(i).setChecked(false);
                        i++;
                    }
                    if (!editText.getText().toString().equals("")) {
                        profileModel model = new profileModel();
                        model.setName(editText.getText().toString());

                       // mySaves.edit().putString("url", url).apply();
                        model.setUrl(url);
                        model.setChecked(true);
                        groupList.clear();
                        mySaves.edit().putString("url", url).apply();


                        config.profiles.add(model);

                        Intent intent = new Intent(schedulePicker.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            });

            if (mySaves.contains("url")) {

                    dialog.show();

            }

            mySaves.edit().putString("url", url).apply();
        }


    }

    @Override
    protected void onResume() {

        super.onResume();




        if (mySaves.contains("url")) {



            checkUrl = mySaves.getString("url", "hey");
        }

        if (mySaves.contains("myArray")) {

            String jsonStr = (mySaves.getString("myArray", "Nan"));

            Gson gson = new Gson();

            Type type = new TypeToken<ArrayList<profileModel>>() {

            }.getType();

            config.profiles = gson.fromJson(jsonStr, type);


        }
    }


}
