package com.sky.vsuwt_schedule_.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sky.vsuwt_schedule_.R;
import com.sky.vsuwt_schedule_.adapters.aboutAdapter;
import com.sky.vsuwt_schedule_.models.aboutModel;

import java.util.ArrayList;

import static android.graphics.Typeface.BOLD;

public class aboutActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    aboutAdapter ab;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        Toolbar toolbar =  findViewById(R.id.toolbar_about);
        toolbar.inflateMenu(R.menu.my_menu);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("О приложении");

        Typeface tp = Typeface.createFromAsset(getAssets(), "FuturaLightC.ttf");
        ((TextView)toolbar.getChildAt(1)).setTypeface(tp);

//        setActionBar(toolbar);
//        Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.my_menu_item)
                {
                    Intent intent = new Intent(aboutActivity.this, aboutActivity.class);
                   // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }


                return false;
            }
        });

        ArrayList<aboutModel> models = new ArrayList<>();

        aboutModel firstModel = new aboutModel();

        firstModel.setTitle("Проблема");

        firstModel.setSubTitle("Сообщить об ошибке, зависании, медленной или некорректной работе");

        models.add(firstModel);

        aboutModel secondModel = new aboutModel();

        secondModel.setTitle("Другое");

        secondModel.setSubTitle("Задать другой вопрос");

        models.add(secondModel);

        LinearLayoutManager ln = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerViewAbout);
        recyclerView.setLayoutManager(ln);
        ab = new aboutAdapter(this, models);
        recyclerView.setAdapter(ab);
        ab.notifyDataSetChanged();

        ab.setOnItemClickListener(new aboutAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));

                if (position == 0){


                    String[] to={"dev@vsuwt.ru"};
                    intent.putExtra(Intent.EXTRA_EMAIL,to);
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Ошибка в работе приложения");
                   // intent.putExtra(Intent.EXTRA_TEXT,"message");
                    intent.setType("message/rfc822");
                    intent.setPackage("com.google.android.gm");
                    startActivity(Intent.createChooser(intent, "Выберите почтовый клиент"));
                }

                if (position == 1){

                    String[] to={"dev@vsuwt.ru"};
                    intent.putExtra(Intent.EXTRA_EMAIL,to);
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Предложения и пожелания по работе приложения");
                   //intent.putExtra(Intent.EXTRA_TEXT,"message");
                    intent.setType("message/rfc822");
                    intent.setPackage("com.google.android.gm");
                    startActivity(Intent.createChooser(intent, "Выберите почтовый клиент"));
                }
            }

        });
        button = findViewById(R.id.aboutButton);


         Typeface fontTypeface = Typeface.createFromAsset(getAssets(), "FuturaBookC.ttf");

         Typeface subFontTypeface = Typeface.createFromAsset(getAssets(), "FuturaLightC.ttf");

        button.setTypeface(fontTypeface, BOLD);

         final Dialog dialog = new Dialog(aboutActivity.this);
        dialog.setContentView(R.layout.about_dialog_layout);

        TextView aboutDialogTitle = dialog.findViewById(R.id.dialogTitle);

         TextView aboutDialogSubTitle = dialog.findViewById(R.id.dialogText);

        Button dialogButton = dialog.findViewById(R.id.dialogButton);

          aboutDialogTitle.setTypeface(fontTypeface, BOLD);

          aboutDialogSubTitle.setTypeface(subFontTypeface);

            dialogButton.setTypeface(fontTypeface, BOLD);


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





              //  button.setTypeface(fontTypeface, BOLD);



            //  dialog.setTitle("Информация об использованных ресурсах");
              dialog.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }


}
