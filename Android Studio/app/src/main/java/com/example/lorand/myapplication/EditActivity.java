package com.example.lorand.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import static com.example.lorand.myapplication.UserActivity.arrayList;

public class EditActivity extends AppCompatActivity {
    public int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_apartment_layout);

        Intent intent = getIntent();
        position = intent.getIntExtra("pos", -1);

        String[] ap = intent.getStringArrayExtra("apartment");

        EditText titleEdit = (EditText) findViewById(R.id.title_edit);
        EditText addressEdit = (EditText) findViewById(R.id.address_edit);
        EditText nrEdit = (EditText) findViewById(R.id.room_nr_edit);
        titleEdit.append(ap[0]);
        addressEdit.append(ap[1]);
        nrEdit.append(ap[2]);


    }


    public void onClickSaveApButton(View view) {
        EditText titleText = (EditText) findViewById(R.id.title_edit);
        EditText addressText = (EditText) findViewById(R.id.address_edit);
        EditText nrText = (EditText) findViewById(R.id.room_nr_edit);
        String tt = String.valueOf(titleText.getText());
        String at = String.valueOf(addressText.getText());
        String nt = String.valueOf(nrText.getText());

        Apartment app = new Apartment(tt, at, nt);

        arrayList.set(position, app);
        finish();
    }

    public void onClickDeleteApButton(View view) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(EditActivity.this);
        builder1.setMessage("Are you sure you want to delete this apartment ?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        arrayList.remove(position);
                        finish();

                    }
                });
        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog alert11 = builder1.create();
        alert11.show();



    }


}
