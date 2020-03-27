package com.sky.vsuwt_schedule_.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.sky.vsuwt_schedule_.MainActivity;
import com.sky.vsuwt_schedule_.R;

import static android.graphics.Typeface.BOLD;

public class splashActivity extends AppCompatActivity {

    SharedPreferences mySaves;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        mySaves = getSharedPreferences("url", Context.MODE_PRIVATE);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.splash_transition);



        ImageView img = findViewById(R.id.img_splash);
        TextView text1 = findViewById(R.id.text1_splash);
        TextView text2 = findViewById(R.id.text2splash);

        Typeface tp = Typeface.createFromAsset(getAssets(), "FuturaDemiC.ttf");

        text1.setTypeface(tp, BOLD);
        text2.setTypeface(tp, BOLD);

        img.startAnimation(myAnim);
        text1.startAnimation(myAnim);
        text2.startAnimation(myAnim);

        final Intent i;
        if (mySaves.contains("url")) {
            i = new Intent(this, MainActivity.class);
        }
        else{

             i = new Intent(this, startPicker.class);
        }
        //startPicker.class
        Thread timer = new Thread(){

            public void run (){


                try{

                    sleep(1500);
                }
                catch (InterruptedException e){

                    e.printStackTrace();
                }

                finally {
                        startActivity(i);
                    overridePendingTransition(0,0);
                        finish();
                    overridePendingTransition(0,0);

                }
            }
        };

            timer.start();

    }
}
