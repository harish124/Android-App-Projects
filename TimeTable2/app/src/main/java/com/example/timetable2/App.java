
package com.example.timetable2;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("k3GlfXeEWrgKQRew14U0r1kR4UlwjqL0H40htIJ3")
                // if defined
                .clientKey("10J7MbL6uBgRaSJrOLFhoi9EVw1hjKgM0kuVFfGd")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
    //android:icon="@drawable/googleg_standard_color_18"
    //24 hour format
//    int hourofday = cal.get(Calendar.HOUR_OF_DAY);
//    int minute = cal.get(Calendar.MINUTE);
//
//    int time=0;
//    time = (hourofday * 100) + minute;
//
//
//            if(obj.getN().equals("one"))
//    {
//        mCategory.setText(" 1. ");
//        if((800<=time)&&(time<=850))
//        {
//            mIsbn.setTextColor(Color.RED);
//            mCategory.setTextColor(Color.RED);
//        }
//    }
}