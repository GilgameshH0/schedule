package com.sky.vsuwt_schedule_.adapters;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sky.vsuwt_schedule_.R;
import com.sky.vsuwt_schedule_.models.lessonModel;

import java.util.ArrayList;

import static android.graphics.Typeface.BOLD;

public class lessonAdapter extends RecyclerView.Adapter<lessonAdapter.LessonHolder> {



    private Context mContext;

    private ArrayList<lessonModel> lessonModels;








    public lessonAdapter ( Context mContext, ArrayList<lessonModel> lessonModels)
    {
        this.mContext = mContext;
        this.lessonModels = lessonModels;

    }

    public void setData(ArrayList<lessonModel> models){

        this.lessonModels = models;

    }





    @NonNull
    public LessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_item,parent, false);
        return new LessonHolder(v);
    }

    public void onBindViewHolder(@NonNull LessonHolder holder, int position) {


        if ((lessonModels.get(position).getL1Class().equals("")) && (lessonModels.get(position).getL2Class().equals("")) &&
                (lessonModels.get(position).getL3Class().equals("")) &&
                (lessonModels.get(position).getL4Class().equals("")) &&
                (lessonModels.get(position).getL5Class().equals("")) &&
                (lessonModels.get(position).getL6Class().equals("")) && (lessonModels.get(position).getL7Class().equals("")) && (lessonModels.get(position).getL8Class().equals(""))) {


            holder.l1.setVisibility(View.GONE);
            holder.l2.setVisibility(View.GONE);
            holder.l3.setVisibility(View.GONE);
            holder.l4.setVisibility(View.GONE);
            holder.l5.setVisibility(View.GONE);
            holder.l6.setVisibility(View.GONE);
            holder.l7.setVisibility(View.GONE);
            holder.l8.setVisibility(View.GONE);
            holder.nol.setVisibility(View.VISIBLE);

        } else {

            holder.l1.setVisibility(View.VISIBLE);
            holder.l2.setVisibility(View.VISIBLE);
            holder.l3.setVisibility(View.VISIBLE);
            holder.l4.setVisibility(View.VISIBLE);
            holder.l5.setVisibility(View.VISIBLE);
            holder.l6.setVisibility(View.VISIBLE);
            holder.l7.setVisibility(View.VISIBLE);
            holder.l8.setVisibility(View.VISIBLE);
            holder.nol.setVisibility(View.GONE);


        }

        if (lessonModels.get(position).getL1Class().equals("")) {

            holder.l1.setVisibility(View.GONE);

        } else {

            holder.l1.setVisibility(View.VISIBLE);

        }
        if (lessonModels.get(position).getL2Class().equals("")) {

            holder.l2.setVisibility(View.GONE);

        } else {

            holder.l2.setVisibility(View.VISIBLE);

        }
        if (lessonModels.get(position).getL3Class().equals("")) {

            holder.l3.setVisibility(View.GONE);

        } else {

            holder.l3.setVisibility(View.VISIBLE);

        }
        if (lessonModels.get(position).getL4Class().equals("")) {

            holder.l4.setVisibility(View.GONE);

        } else {

            holder.l4.setVisibility(View.VISIBLE);

        }
        if (lessonModels.get(position).getL5Class().equals("")) {

            holder.l5.setVisibility(View.GONE);

        } else {

            holder.l5.setVisibility(View.VISIBLE);

        }
        if (lessonModels.get(position).getL6Class().equals("")) {

            holder.l6.setVisibility(View.GONE);

        } else {

            holder.l6.setVisibility(View.VISIBLE);

        }
        if (lessonModels.get(position).getL7Class().equals("")) {

            holder.l7.setVisibility(View.GONE);

        } else {

            holder.l7.setVisibility(View.VISIBLE);

        }

        if (lessonModels.get(position).getL8Class().equals("")) {

            holder.l8.setVisibility(View.GONE);

        } else {

            holder.l8.setVisibility(View.VISIBLE);

        }


        if (lessonModels.get(position).getGetCurrentLesson().equals("1")) {

            holder.clock0.setVisibility(View.VISIBLE);

        } else {
            holder.clock0.setVisibility(View.INVISIBLE);
        }

        if (lessonModels.get(position).getGetCurrentLesson().equals("2")) {

            holder.clock1.setVisibility(View.VISIBLE);

        } else {
            holder.clock1.setVisibility(View.INVISIBLE);
        }

        if (lessonModels.get(position).getGetCurrentLesson().equals("3")) {

            holder.clock2.setVisibility(View.VISIBLE);

        } else {
            holder.clock2.setVisibility(View.INVISIBLE);
        }

        if (lessonModels.get(position).getGetCurrentLesson().equals("4")) {

            holder.clock3.setVisibility(View.VISIBLE);

        } else {
            holder.clock3.setVisibility(View.INVISIBLE);
        }

        if (lessonModels.get(position).getGetCurrentLesson().equals("5")) {

            holder.clock4.setVisibility(View.VISIBLE);

        } else {
            holder.clock4.setVisibility(View.INVISIBLE);
        }

        if (lessonModels.get(position).getGetCurrentLesson().equals("6")) {

            holder.clock5.setVisibility(View.VISIBLE);

        } else {
            holder.clock5.setVisibility(View.INVISIBLE);
        }

        if (lessonModels.get(position).getGetCurrentLesson().equals("7")) {

            holder.clock6.setVisibility(View.VISIBLE);

        } else {
            holder.clock6.setVisibility(View.INVISIBLE);
        }

        if (lessonModels.get(position).getGetCurrentLesson().equals("8")) {

            holder.clock7.setVisibility(View.VISIBLE);

        } else {
            holder.clock7.setVisibility(View.INVISIBLE);
        }






        if (lessonModels.get(position).getIsChanges().equals("true"))
        {

            holder.changes.setVisibility(View.VISIBLE);

        }
        else{

            holder.changes.setVisibility(View.INVISIBLE);
        }





        if ((lessonModels.get(position).getL1type().equals("Практика")) || (lessonModels.get(position).getL1type().equals("Лабораторная работа"))){

            holder.l1Type.setBackgroundColor(mContext.getResources().getColor(R.color.colorSapphire));
        }
        else{

            holder.l1Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
        }
        if ((lessonModels.get(position).getL2type().equals("Практика")) || (lessonModels.get(position).getL2type().equals("Лабораторная работа"))){

            holder.l2Type.setBackgroundColor(mContext.getResources().getColor(R.color.colorSapphire));
        }
        else{

            holder.l2Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
        }
        if ((lessonModels.get(position).getL3type().equals("Практика")) || (lessonModels.get(position).getL3type().equals("Лабораторная работа"))){

            holder.l3Type.setBackgroundColor(mContext.getResources().getColor(R.color.colorSapphire));
        }
        else{

            holder.l3Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
        }
        if ((lessonModels.get(position).getL4type().equals("Практика")) || (lessonModels.get(position).getL4type().equals("Лабораторная работа"))){

            holder.l4Type.setBackgroundColor(mContext.getResources().getColor(R.color.colorSapphire));
        }
        else{

            holder.l4Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
        }

        if ((lessonModels.get(position).getL5type().equals("Практика")) || (lessonModels.get(position).getL5type().equals("Лабораторная работа"))){

            holder.l5Type.setBackgroundColor(mContext.getResources().getColor(R.color.colorSapphire));
        }
        else{

            holder.l5Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
        }



        if ((lessonModels.get(position).getL6type().equals("Практика")) || (lessonModels.get(position).getL6type().equals("Лабораторная работа"))){

            holder.l6Type.setBackgroundColor(mContext.getResources().getColor(R.color.colorSapphire));
        }
        else{

            holder.l6Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
        }



        if ((lessonModels.get(position).getL7type().equals("Практика")) || (lessonModels.get(position).getL7type().equals("Лабораторная работа"))){

            holder.l7Type.setBackgroundColor(mContext.getResources().getColor(R.color.colorSapphire));
        }
        else{

            holder.l7Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
        }



        if ((lessonModels.get(position).getL8type().equals("Практика")) || (lessonModels.get(position).getL8type().equals("Лабораторная работа"))){

            holder.l8Type.setBackgroundColor(mContext.getResources().getColor(R.color.colorSapphire));
        }
        else{

            holder.l8Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
        }



//        if (lessonModels.get(position).getL1type().equals("Лекция")){
//
//            holder.l1Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//        if (lessonModels.get(position).getL2type().equals("Лекция")){
//
//            holder.l2Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//        if (lessonModels.get(position).getL3type().equals("Лекция")){
//
//            holder.l3Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//        if (lessonModels.get(position).getL4type().equals("Лекция")){
//
//            holder.l4Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//
//        if (lessonModels.get(position).getL5type().equals("Лекция")){
//
//            holder.l5Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//        if (lessonModels.get(position).getL6type().equals("Лекция")){
//
//            holder.l6Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//        if (lessonModels.get(position).getL7type().equals("Лекция")){
//
//            holder.l7Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//
//        if (lessonModels.get(position).getL8type().equals("Лекция")){
//
//            holder.l8Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }




//        if (lessonModels.get(position).getL1type().equals("Аудиторное занятие")){
//
//            holder.l1Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//        if (lessonModels.get(position).getL2type().equals("Аудиторное занятие")){
//
//            holder.l2Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//        if (lessonModels.get(position).getL3type().equals("Аудиторное занятие")){
//
//            holder.l3Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//        if (lessonModels.get(position).getL4type().equals("Аудиторное занятие")){
//
//            holder.l4Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//
//        if (lessonModels.get(position).getL5type().equals("Аудиторное занятие")){
//
//            holder.l5Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//        if (lessonModels.get(position).getL6type().equals("Аудиторное занятие")){
//
//            holder.l6Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//        if (lessonModels.get(position).getL7type().equals("Аудиторное занятие")){
//
//            holder.l7Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }
//
//        if (lessonModels.get(position).getL8type().equals("Аудиторное занятие")){
//
//            holder.l8Type.setBackgroundColor(mContext.getResources().getColor(R.color.pacificBlue));
//        }








        holder.isDay.setText((lessonModels).get(position).getIs_day());
        holder.date.setText(lessonModels.get(position).getIsDate());
        holder.l1Name.setText((lessonModels).get(position).getL1Name());
        holder.l2Name.setText((lessonModels).get(position).getL2Name());
        holder.l3Name.setText((lessonModels).get(position).getL3Name());
        holder.l4Name.setText((lessonModels).get(position).getL4Name());
        holder.l5Name.setText((lessonModels).get(position).getL5Name());
        holder.l6Name.setText((lessonModels).get(position).getL6Name());
        holder.l7Name.setText((lessonModels).get(position).getL7Name());
        holder.l8Name.setText((lessonModels).get(position).getL8Name());

        holder.l1Type.setText((lessonModels).get(position).getL1type());
        holder.l2Type.setText((lessonModels).get(position).getL2type());
        holder.l3Type.setText((lessonModels).get(position).getL3type());
        holder.l4Type.setText((lessonModels).get(position).getL4type());
        holder.l5Type.setText((lessonModels).get(position).getL5type());
        holder.l6Type.setText((lessonModels).get(position).getL6type());
        holder.l7Type.setText((lessonModels).get(position).getL7type());
        holder.l8Type.setText((lessonModels).get(position).getL8type());

        holder.l1Clss.setText((lessonModels).get(position).getL1Class());
        holder.l2Clss.setText((lessonModels).get(position).getL2Class());
        holder.l3Clss.setText((lessonModels).get(position).getL3Class());
        holder.l4Clss.setText((lessonModels).get(position).getL4Class());
        holder.l5Clss.setText((lessonModels).get(position).getL5Class());
        holder.l6Clss.setText((lessonModels).get(position).getL6Class());
        holder.l7Clss.setText((lessonModels).get(position).getL7Class());
        holder.l8Clss.setText((lessonModels).get(position).getL8Class());

        holder.l1Teacher.setText((lessonModels).get(position).getL1Teacher());
        holder.l2Teacher.setText((lessonModels).get(position).getL2Teacher());
        holder.l3Teacher.setText((lessonModels).get(position).getL3Teacher());
        holder.l4Teacher.setText((lessonModels).get(position).getL4Teacher());
        holder.l5Teacher.setText((lessonModels).get(position).getL5Teacher());
        holder.l6Teacher.setText((lessonModels).get(position).getL6Teacher());
        holder.l7Teacher.setText((lessonModels).get(position).getL7Teacher());
        holder.l8Teacher.setText((lessonModels).get(position).getL8Teacher());


    }

    public int getItemCount(){
        return lessonModels.size();
    }



     class LessonHolder extends RecyclerView.ViewHolder {

        ImageView changes;
        ImageView clock0;
        ImageView clock1;
        ImageView clock2;
        ImageView clock3;
        ImageView clock4;
        ImageView clock5;
        ImageView clock6;
        ImageView clock7;

        LinearLayout nol;
        LinearLayout l1;
        LinearLayout l2;
        LinearLayout l3;
        LinearLayout l4;
        LinearLayout l5;
        LinearLayout l6;
        LinearLayout l7;
        LinearLayout l8;

        TextView date;
        TextView isDay;
        TextView l1Name;
        TextView l2Name;
        TextView l3Name;
        TextView l4Name;
        TextView l5Name;
        TextView l6Name;
        TextView l7Name;
        TextView l8Name;
        TextView l1Type;
        TextView l2Type;
        TextView l3Type;
        TextView l4Type;
        TextView l5Type;
        TextView l6Type;
        TextView l7Type;
        TextView l8Type;
        TextView l1Clss;
        TextView l2Clss;
        TextView l3Clss;
        TextView l4Clss;
        TextView l5Clss;
        TextView l6Clss;
        TextView l7Clss;
        TextView l8Clss;

        TextView l1Teacher;
        TextView l2Teacher;
        TextView l3Teacher;
        TextView l4Teacher;
        TextView l5Teacher;
        TextView l6Teacher;
        TextView l7Teacher;
        TextView l8Teacher;


     public    LessonHolder(View itemView) {   //, final OnItemClickListener listener
            super(itemView);
         Typeface MainFont = Typeface.createFromAsset(mContext.getAssets(), "FuturaDemiC.ttf");

         Typeface dateFont = Typeface.createFromAsset(mContext.getAssets(), "FuturaLightC.ttf");

            TextView time1 = itemView.findViewById(R.id.timeOne);
            TextView time2 = itemView.findViewById(R.id.timetwo);
            TextView time3 = itemView.findViewById(R.id.timethree);
            TextView time4 = itemView.findViewById(R.id.timeFour);
            TextView time5 = itemView.findViewById(R.id.timeFive);
            TextView time6 = itemView.findViewById(R.id.timeSix);
            TextView time7 = itemView.findViewById(R.id.timeSeven);
            TextView time8 = itemView.findViewById(R.id.timeeight);

            time1.setTypeface(MainFont, BOLD);
            time2.setTypeface(MainFont, BOLD);
            time3.setTypeface(MainFont, BOLD);
            time4.setTypeface(MainFont, BOLD);
            time5.setTypeface(MainFont, BOLD);
            time6.setTypeface(MainFont, BOLD);
            time7.setTypeface(MainFont, BOLD);
            time8.setTypeface(MainFont, BOLD);

            clock0 = itemView.findViewById(R.id.clock_0);
            clock1 = itemView.findViewById(R.id.clock_1);
            clock2 = itemView.findViewById(R.id.clock_2);
            clock3 = itemView.findViewById(R.id.clock_3);
            clock4 = itemView.findViewById(R.id.clock_4);
            clock5 = itemView.findViewById(R.id.clock_5);
            clock6 = itemView.findViewById(R.id.clock_6);
            clock7 = itemView.findViewById(R.id.clock_7);

            changes = itemView.findViewById(R.id.isChanges);
            l1 = itemView.findViewById(R.id.one_l);
            l2 = itemView.findViewById(R.id.two_l);
            l3 = itemView.findViewById(R.id.three_l);
            l4 = itemView.findViewById(R.id.four_l);
            l5 = itemView.findViewById(R.id.five_l);
            l6 = itemView.findViewById(R.id.six_l);
            l7 = itemView.findViewById(R.id.seven_l);
            l8 = itemView.findViewById(R.id.eight_l);
            nol = itemView.findViewById(R.id.no_lessons);
            isDay = itemView.findViewById(R.id.is_day);


            isDay.setTypeface(MainFont, BOLD);

            date = itemView.findViewById(R.id.is_date);

         //Typeface dateFont = Typeface.createFromAsset(mContext.getAssets(), "FuturaDemiC.ttf");
         date.setTypeface(dateFont);

            l1Name = itemView.findViewById(R.id.text_l1name_lesson);
            l2Name = itemView.findViewById(R.id.text_l2name_lesson);
            l3Name = itemView.findViewById(R.id.text_l3name_lesson);
            l4Name = itemView.findViewById(R.id.text_l4name_lesson);
            l5Name = itemView.findViewById(R.id.text_l5name_lesson);
            l6Name = itemView.findViewById(R.id.text_l6name_lesson);
            l7Name = itemView.findViewById(R.id.text_l7name_lesson);
            l8Name = itemView.findViewById(R.id.text_l8name_lesson);
            l1Type = itemView.findViewById(R.id.text_l1type_lesson);
            l2Type = itemView.findViewById(R.id.text_l2type_lesson);
            l3Type = itemView.findViewById(R.id.text_l3type_lesson);
            l4Type = itemView.findViewById(R.id.text_l4type_lesson);
            l5Type = itemView.findViewById(R.id.text_l5type_lesson);
            l6Type = itemView.findViewById(R.id.text_l6type_lesson);
            l7Type = itemView.findViewById(R.id.text_l7type_lesson);
            l8Type = itemView.findViewById(R.id.text_l8type_lesson);
            l1Clss = itemView.findViewById(R.id.text_l1class_lesson);
            l2Clss = itemView.findViewById(R.id.text_l2class_lesson);
            l3Clss = itemView.findViewById(R.id.text_l3class_lesson);
            l4Clss = itemView.findViewById(R.id.text_l4class_lesson);
            l5Clss = itemView.findViewById(R.id.text_l5class_lesson);
            l6Clss = itemView.findViewById(R.id.text_l6class_lesson);
            l7Clss = itemView.findViewById(R.id.text_l7class_lesson);
            l8Clss = itemView.findViewById(R.id.text_l8class_lesson);



            l1Teacher = itemView.findViewById(R.id.text_l1teacher_lesson);
            l2Teacher = itemView.findViewById(R.id.text_l2teacher_lesson);
            l3Teacher = itemView.findViewById(R.id.text_l3teacher_lesson);
            l4Teacher = itemView.findViewById(R.id.text_l4teacher_lesson);
            l5Teacher = itemView.findViewById(R.id.text_l5teacher_lesson);
            l6Teacher = itemView.findViewById(R.id.text_l6teacher_lesson);
            l7Teacher = itemView.findViewById(R.id.text_l7teacher_lesson);
            l8Teacher = itemView.findViewById(R.id.text_l8teacher_lesson);

         l1Type.setTypeface(dateFont);
         l2Type.setTypeface(dateFont);
         l3Type.setTypeface(dateFont);
         l4Type.setTypeface(dateFont);
         l5Type.setTypeface(dateFont);
         l6Type.setTypeface(dateFont);
         l7Type.setTypeface(dateFont);
         l8Type.setTypeface(dateFont);

         l1Clss.setTypeface(dateFont);
         l2Clss.setTypeface(dateFont);
         l3Clss.setTypeface(dateFont);
         l4Clss.setTypeface(dateFont);
         l5Clss.setTypeface(dateFont);
         l6Clss.setTypeface(dateFont);
         l7Clss.setTypeface(dateFont);
         l8Clss.setTypeface(dateFont);

         l1Teacher.setTypeface(dateFont);
         l2Teacher.setTypeface(dateFont);
         l3Teacher.setTypeface(dateFont);
         l4Teacher.setTypeface(dateFont);
         l5Teacher.setTypeface(dateFont);
         l6Teacher.setTypeface(dateFont);
         l7Teacher.setTypeface(dateFont);
         l8Teacher.setTypeface(dateFont);


         l1Name.setTypeface(MainFont);
         l2Name.setTypeface(MainFont);
         l3Name.setTypeface(MainFont);
         l4Name.setTypeface(MainFont);
         l5Name.setTypeface(MainFont);
         l6Name.setTypeface(MainFont);
         l7Name.setTypeface(MainFont);
         l8Name.setTypeface(MainFont);


        }


    }



}