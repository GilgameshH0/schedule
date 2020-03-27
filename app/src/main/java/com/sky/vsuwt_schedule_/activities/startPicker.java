package com.sky.vsuwt_schedule_.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.sky.vsuwt_schedule_.R;

import static android.graphics.Typeface.BOLD;

public class startPicker extends AppCompatActivity {
    private long mLastClickTime = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_picker);

        overridePendingTransition(0,0);

        TextView text1 = findViewById(R.id.text1_start);

        TextView text2 = findViewById(R.id.text2_start);
        Typeface tp = Typeface.createFromAsset(getAssets(), "FuturaDemiC.ttf");

        text1.setTypeface(tp, BOLD);
        text2.setTypeface(tp, BOLD);
//        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.splash_transition);
//        ConstraintLayout img = findViewById(R.id.logo_cativity);
//        img.startAnimation(myAnim);
        final Button button = findViewById(R.id.button_start_picker);

        button.setTypeface(tp, BOLD);
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.splash_transition);

        button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

               // fs = false;
               // button.setEnabled(false);
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

        button.startAnimation(myAnim);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                  Intent intent = new Intent(startPicker.this, schedulePicker.class);
                  startActivity(intent);
                finish();
               // button.setEnabled(false);


            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
