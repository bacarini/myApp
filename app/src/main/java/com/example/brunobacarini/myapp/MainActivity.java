package com.example.brunobacarini.myapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MyActivity";
    Button button;
    Button button2;
    Button button3;
    Button swipeBtn;
    Button addCars, getCars;

    TextView counter, scrollText;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate event");
        applyFont();

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));

        button2 = (Button) findViewById(R.id.chamada);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.next_page);
        button3.setOnClickListener(this);

        swipeBtn = (Button) findViewById(R.id.swipeBtn);
        swipeBtn.setOnClickListener(this);

        addCars = (Button)findViewById(R.id.addCars);
        addCars.setOnClickListener(this);
        addCars.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));

        getCars = (Button)findViewById(R.id.getCars);
        getCars.setOnClickListener(this);

        scrollText = (TextView)findViewById(R.id.scrollText);
        scrollText.setText("1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n");

        counter = (TextView)findViewById(R.id.counter);
    }

    public void onClick(View view) {
        if (view == button) {
            button.setText(getResources().getString(R.string.pressed));
            i++;
            counter.setText( "Clicado " + i + " vezes");
        }
        if (view == button2) {
            Intent call = new Intent();
            call.setAction(Intent.ACTION_CALL);
            call.setData(Uri.parse("tel:914444899"));
            startActivity(call);
        }
        if (view == button3) {
            Intent action = new Intent(this, Main2Activity.class);
            action.putExtra("mensagem",  "Clicado " + i + " vezes");
            startActivity(action);
        }
        if (view == addCars) {
            Intent a = new Intent(this, AddCars.class);
            startActivity(a);
        }
        if (view == getCars) {
            Intent a = new Intent(this, GetCarsActivity.class);
            startActivity(a);
        }
        if (view == swipeBtn) {
            Intent action = new Intent(this, SwipeActivity.class);
            startActivity(action);
        }
    }
    @Override
    protected void onStart() {
        Log.e(TAG, "onStart event");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart event");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume event");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause event");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop event");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy event");
        super.onDestroy();
    }

    public void applyFont() {
        Typeface myTypeface = Typeface.createFromAsset(
                getAssets(),
                "font.ttf" );

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.chamada);
        button3 = (Button) findViewById(R.id.next_page);
        addCars = (Button)findViewById(R.id.addCars);
        getCars = (Button)findViewById(R.id.getCars);
        //scrollText = (TextView)findViewById(R.id.scrollText);
        counter = (TextView)findViewById(R.id.counter);

        button.setTypeface(myTypeface);
        button2.setTypeface(myTypeface);
        button3.setTypeface(myTypeface);
        addCars.setTypeface(myTypeface);
        getCars.setTypeface(myTypeface);
        //scrollText.setTypeface(myTypeface);
        counter.setTypeface(myTypeface);
    }
}
