package com.example.brunobacarini.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddCars extends AppCompatActivity implements View.OnClickListener {

    Button enter;
    EditText brandTxt, modelTxt, ownerTxt;
    TextView brand, model, owner;
    CheckBox checkbox;
    String ownerString;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cars);

        enter = (Button)findViewById(R.id.enter);
        enter.setOnClickListener(this);

        brandTxt = (EditText)findViewById(R.id.brandTxt);
        modelTxt = (EditText)findViewById(R.id.modelTxt);
        ownerTxt = (EditText)findViewById(R.id.ownerTxt);

        brand = (TextView)findViewById(R.id.brand);
        model = (TextView)findViewById(R.id.model);
        owner = (TextView)findViewById(R.id.owner);

        layout = (LinearLayout)findViewById(R.id.layout);

        checkbox = (CheckBox)findViewById(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkbox.isChecked()){
                    ownerTxt.setEnabled(false);
                    ownerTxt.setHint("---");
                    layout.setVisibility(View.INVISIBLE);
                } else {
                    ownerTxt.setEnabled(true);
                    ownerTxt.setHint("Owner");
                    layout.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void onClick(View v) {
        if(v == enter) {

            if(brandTxt.getText().toString().equals("")) {
                makeToast("Brand Missing");
                return;
            }
            if(modelTxt.getText().toString().equals("")) {
                makeToast("Model Missing");
                return;
            }
            if(ownerTxt.getText().toString().equals("") && !checkbox.isChecked()) {
                makeToast("Owner Missing");
                return;
            }
            if(checkbox.isChecked())
                ownerString = "No Owner";
            else
                ownerString = ownerTxt.getText().toString();

            String text = ("Brand: " + brandTxt.getText().toString() +
                    "\nModel: " + modelTxt.getText().toString() +
                    "\nOwner: " + ownerString);

            makeToast(text);
            brandTxt.setText("");
            modelTxt.setText("");
            ownerTxt.setText("");
        }
    }

    public void makeToast(String text) {
        Toast.makeText(
                getApplicationContext().getApplicationContext(),
                text,
                Toast.LENGTH_SHORT
        ).show();
    }
}
