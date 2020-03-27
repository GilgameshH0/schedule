package com.sky.vsuwt_schedule_.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sky.vsuwt_schedule_.interfaces.callqback;
import com.sky.vsuwt_schedule_.models.listModel;
import com.sky.vsuwt_schedule_.models.profileModel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class config {


    // public static schedule_item currentItem = null;
    public static listModel currentlistItem = null;



    public static profileModel secCurrentItem = null;
    public static ArrayList<profileModel> profiles = new ArrayList<>();
    public static int row_index = -1;
    public static String agileUrl ="";


    public static int facultRowIndex = -1;
    public static int specRowIndex = -1;
    public static int courseRowIndex = -1;
    public static int groupRowIndex = -1;



    public static boolean isHostReachable() {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 vsuwt.ru");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException | InterruptedException e)
        { e.printStackTrace(); }

        return false;

    }


    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException | InterruptedException e)
        { e.printStackTrace(); }

        return false;
    }




}
