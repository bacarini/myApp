package com.example.brunobacarini.myapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run(){
                nextAct();
            }
        }, 2000);
    }
    private void nextAct(){
        Intent action = new Intent(this, MainActivity.class);
        startActivity(action);
        this.finish();
    }
}
