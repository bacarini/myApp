package com.example.brunobacarini.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GetCarsActivity extends Activity {

    Button[] btn;
    Button button;
    boolean click = false;
    Car[] carObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cars);

        //fullscreen
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        button = (Button)findViewById(R.id.button);
        button.setTransformationMethod(null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click){
                    clearDB();
                }else{
                    click = true;
                    button.setText("Click to confirm");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run(){
                            click = false;
                            button.setText("Clear Database");
                        }
                    }, 2000);
                }

            }
        });

        // pedir os dados á base de dados
        Database db = new Database(getApplicationContext());
        Cursor data = db.getData();
        db.close();

        int numberCars = data.getCount();

        // sair de não existirem carros para consultar
        if(numberCars == 0){
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    "no cars to show",
                    Toast.LENGTH_LONG
            );
            toast.show();
            this.finish();

        }else {
            Log.e("COL", numberCars + "");

            carObject = new Car[numberCars + 1];

            // carregar os resultados da pesquisa para um array de Strings
            String[] cars = new String[numberCars + 1];
            int j = 1;
            do {        // ID - BRAND - MODEL - OWNER
                cars[j] = (data.getString(0) + ". " + data.getString(1) + " - " + data.getString(2) + " - " + data.getString(3));
                j++;
            } while (data.moveToNext());

            LinearLayout layout = (LinearLayout) findViewById(R.id.layout);

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            btn = new Button[numberCars + 1];
            for (int i = 1; i <= numberCars; i++) {
                btn[i] = new Button(getApplicationContext());
                btn[i].setText(cars[i]);
                param.setMargins(0, 5, 0, 5);
                btn[i].setTextColor(Color.parseColor("#FFFFFF"));
                btn[i].setTextSize(18);
                btn[i].setLayoutParams(param);
                btn[i].setPadding(10, 5, 10, 5);
                btn[i].setTransformationMethod(null); // permite que o botão tenha letras maiusculas e minusculas
                btn[i].setBackgroundColor(Color.LTGRAY);
                layout.addView(btn[i]);
                btn[i].setOnClickListener(handleOnClick(btn[i], i));
            }
        }
    }

    View.OnClickListener handleOnClick(final Button button, final int i) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Log.e("BTN",i + "");
                btn[i].setBackgroundColor(Color.DKGRAY);
                String aux = btn[i].getText().toString();
                String carToDelete = aux.split("\\.")[0];
                dialog(Integer.parseInt(carToDelete), aux, i);
            }
        };
    }


    public void restart(){
        Intent a = new Intent(this, GetCarsActivity.class);
        startActivity(a);
        this.finish();
    }

    public void dialog(final int i, String title, final int btn_i){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Choose an option for this car")
                .setTitle(title);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                deleteCar(i);
            }
        });
        builder.setNegativeButton("No Owner", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                noOwner(i, btn_i);
            }
        });
        AlertDialog dialog = builder.create();

        dialog.show();

    }

    public void deleteCar(int i){
        Database db = new Database(getApplicationContext());
        db.deleteCar(i + "");
        db.close();
        restart();
    }
    public void noOwner(int i, int btn_i){
        btn[btn_i].setBackgroundColor(Color.LTGRAY);
        Database db = new Database(getApplicationContext());
        db.updateCar("no owner",i + "");
        db.close();
        restart();
    }
    public void clearDB(){
        Database db = new Database(getApplicationContext());
        db.clearDatabase();
        db.close();
        restart();
    }

}

