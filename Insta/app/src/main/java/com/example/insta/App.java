package com.example.insta;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("4X9cSmmbfkxoIc7fcOxei6KkzEi3UN6lEIzH1TZo")
                // if defined
                .clientKey("ECcTnlfEnTYu72XsfjYZXeTpFkb8YOKjHleREzrA")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
