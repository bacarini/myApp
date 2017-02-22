package com.example.brunobacarini.myapp;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    TextView text;

    Button btnToast;
    Button btnNotifications;
    Button btnPreferences;
    Button btnDialog;

    Boolean red;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent extdata = getIntent();
        String txt = extdata.getStringExtra("mensagem");

        text = (TextView)findViewById(R.id.textView2);
        text.setText(txt);

        btnToast = (Button) findViewById(R.id.btnToast);
        btnToast.setOnClickListener(this);

        btnNotifications = (Button) findViewById(R.id.btnNotifications);
        btnNotifications.setOnClickListener(this);

        btnPreferences = (Button) findViewById(R.id.btnPreferences);
        btnPreferences.setOnClickListener(this);

        btnDialog = (Button) findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(this);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String clube = settings.getString("clube", "Sporting");
        red = settings.getBoolean("red", false);
        red = !red;
        changeColor();

        if(clube.equals("Sporting")){
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        }
        if(clube.equals("Porto")){
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }
        if(clube.equals("Benfica")){
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
    }
    public void onClick(View view) {
        if (view == btnToast) {
            Toast.makeText(getApplicationContext(), R.string.myToast, Toast.LENGTH_SHORT).show();
        }
        if (view == btnNotifications) {
            show_notification();
        }
        if (view == btnPreferences) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        if (view == btnDialog) {
            dialog();
        }
    }
    public void show_notification() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("titulo")
                        .setContentText("Teste de uma notificação");
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());

    }
    public void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("colocar aqui a string com a mensagem ")
                .setTitle("colocar a string com o titulo");

        builder.setPositiveButton("mudar a cor", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                changeColor();
            }
        });

        builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void changeColor(){
        if(!red){
            btnDialog.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
            red = true;
        }else{
            btnDialog.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
            red = false;
        }
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("red", red);
        editor.apply();
    }
}
