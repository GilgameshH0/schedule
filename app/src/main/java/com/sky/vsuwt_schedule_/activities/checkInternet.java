package com.sky.vsuwt_schedule_.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sky.vsuwt_schedule_.Common.config;
import com.sky.vsuwt_schedule_.MainActivity;
import com.sky.vsuwt_schedule_.R;

public class checkInternet extends AppCompatActivity {
    private long mLastClickTime = 0;

    private boolean flag;
    ProgressBar progressBar;
    CountDownTimer rt = null;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_internet);

        TextView textView = findViewById(R.id.textViewCheckInternet);


        progressBar = findViewById(R.id.progressBarCheckInternet);


        Button button = findViewById(R.id.button_check_internet);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "FuturaDemiC.ttf");

        button.setTypeface(typeFace, Typeface.BOLD);

        textView.setTypeface(typeFace, Typeface.BOLD);

        progressBar.setVisibility(View.INVISIBLE);

        button.setOnTouchListener(new View.OnTouchListener() {
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

        button.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {



                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                //cancelTimer();

if (!flag) {

    startTimer();
    if (config.isOnline()) {

        finish();
        Intent intent = new Intent(checkInternet.this, MainActivity.class);
        startActivity(intent);

    }

}
            }
        });

    }

    void startTimer(){
        flag = true;
        progressBar.setVisibility(View.VISIBLE);
        rt = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                flag = false;
                progressBar.setVisibility(View.INVISIBLE);

            }
        };
        rt.start();
    }



    @Override
    public void onBackPressed() {

        finish();
       // overridePendingTransition(0, 0);
        super.onBackPressed();

    }
}
