package com.sky.vsuwt_schedule_;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sky.vsuwt_schedule_.Common.config;
import com.sky.vsuwt_schedule_.activities.aboutActivity;
import com.sky.vsuwt_schedule_.activities.checkInternet;
import com.sky.vsuwt_schedule_.ui.home.HomeFragment;
import com.sky.vsuwt_schedule_.ui.myTimeTables.myTimeTablesFragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment hf = new HomeFragment();
    myTimeTablesFragment timetab = new myTimeTablesFragment();
    CountDownTimer rt = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =  findViewById(R.id.toolbar_main);

        Typeface tp = Typeface.createFromAsset(getAssets(), "FuturaLightC.ttf");;
       // setActionBar(toolbar);
        toolbar.inflateMenu(R.menu.my_menu);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("ВГУВТ.Расписание");

        ((TextView)toolbar.getChildAt(1)).setTypeface(tp);

       //

       // toolbar.setTitleTextAppearance(this,tp );


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.my_menu_item)
                {
                    Intent intent = new Intent(MainActivity.this, aboutActivity.class);
//            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
                }


                return false;
            }
        });
        changeFragment(hf);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        // ((TextView)bottomNavigationView.getChildAt(1)).setTypeface(tp);


        bottomNavigationView.setOnNavigationItemSelectedListener(

                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                setTitle("Новости");
                                changeFragment(hf);
                                break;
                            case R.id.navigation_my_time_tables:
                                setTitle("Расписание");
                                changeFragment(timetab);
                                break;
                        }
                        return true;

                    }

                });

        overridePendingTransition(0,0);


//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_my_time_tables)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
//



        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.splash_transition);
        RelativeLayout constr = findViewById(R.id.main_container);
        constr.startAnimation(myAnim);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.my_menu_item) {

            Intent intent = new Intent(MainActivity.this, aboutActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        if (!config.isOnline()) {
//                Intent intent = new Intent(MainActivity.this, checkInternet.class);
//                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                // intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//
//                 //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                 //Objects.requireNonNull(getActivity()).overridePendingTransition(0, 0);
//            }

//            else {

//            if (!config.isURLReachable(MainActivity.this)) {
//                Intent intent = new Intent(MainActivity.this, checkServer.class);
//                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                startActivity(intent);
//                //Objects.requireNonNull(getActivity()).overridePendingTransition(0, 0);
//            }

//            else{

//                Intent intent = new Intent(MainActivity.this, schedulePicker.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//
//            }
//        }
//
//
//
        //startTimer();
       // overridePendingTransition (R.anim.nav_default_pop_enter_anim, R.anim.nav_default_pop_exit_anim);

        //finish();

        //Objects.requireNonNull(getSupportActionBar()).hide();
    }

//    void startTimer(){
//
//      //  progressBar.setVisibility(View.VISIBLE);
//        rt = new CountDownTimer(4000, 1000) {
//            public void onTick(long millisUntilFinished) {
//            }
//            public void onFinish() {
//
//                Intent intent = new Intent(MainActivity.this, schedulePicker.class);
//                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//             //   progressBar.setVisibility(View.INVISIBLE);
//            }
//        };
//        rt.start();
//    }
//
//    void cancelTimer() {
//
//     //   progressBar.setVisibility(View.INVISIBLE);
//        if(rt!=null)
//            rt.cancel();
//    }

    public void changeFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main_swap_layout, fragment).commit();

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();


    }
}
