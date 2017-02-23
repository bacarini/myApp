package com.example.brunobacarini.myapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class SwipeActivity extends AppCompatActivity {

    float initialX; // posição inicial
    float minimunDis = 150; // mínima distancia do movimento

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                if(Math.abs(finalX - initialX) < minimunDis)break;

                if (initialX > finalX) {
                    Toast.makeText(getApplicationContext(), "movimento para a esquerda", Toast.LENGTH_SHORT).show();
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                } else{
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    Toast.makeText(getApplicationContext(), "movimento para a direita", Toast.LENGTH_SHORT).show();
                }

                break;
        }
        return false;
    }
}
