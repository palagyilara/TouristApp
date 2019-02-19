package com.example.laura.touristapp;

import android.app.Application;
import android.content.Context;

import com.example.laura.touristapp.Helper.LocaleHelper;

public class MainApplication extends Application{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base,"hu"));
    }
}
