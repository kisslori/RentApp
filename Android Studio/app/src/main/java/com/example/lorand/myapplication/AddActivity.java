package com.example.lorand.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import static android.app.Activity.RESULT_OK;

public class AddActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }

    public void onClickAddApButton(View view) {
        EditText titleText =(EditText)findViewById(R.id.title_input);
        EditText addressText =(EditText)findViewById(R.id.address_input);
        EditText nrText =(EditText)findViewById(R.id.room_nr_input);
         String tt =String.valueOf(titleText.getText());
         String at =String.valueOf(addressText.getText());
         String nt =String.valueOf(nrText.getText());
//        MainActivity.Ap app =new MainActivity.Ap(titleText.getText().toString(),addressText.getText().toString(),nrText.getText().toString());
        String[] app1 = {tt,at,nt};
        Intent goingBackIntent = new Intent(this, MainActivity.class);
         goingBackIntent.putExtra("apartment",app1);
        setResult(RESULT_OK,goingBackIntent);
        finish();

    }
}
