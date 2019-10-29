package com.wiem.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("NmhEX3vG6KaNYcspEqipSyJ77trMqpNWGCWjWO0P")
                // if defined
                .clientKey("bM52Bk6MFHjmCKiNqvxlOIiSGJjYoUb5FwRbOhD6")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
