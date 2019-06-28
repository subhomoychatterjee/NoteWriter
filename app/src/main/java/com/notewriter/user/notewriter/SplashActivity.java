package com.notewriter.user.notewriter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    public static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Thread() {
            @Override
            public void run (){
                Intent firstActivity = new Intent(SplashActivity.this,FirstActivity.class);
                startActivity(firstActivity);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
