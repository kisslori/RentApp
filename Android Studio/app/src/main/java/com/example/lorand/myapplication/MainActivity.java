package com.example.lorand.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
   public static ArrayList<Ap> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Ap ap1 = new Ap("rent", "street1", "2");
        Ap ap2 = new Ap("rental", "street111", "3");
        Ap ap3 = new Ap("rents", "street12", "5");
        Ap ap4 = new Ap("rentfdfs", "street55", "1");
        Ap ap5 = new Ap("rentfff", "street3", "222");
        arrayList.add(ap1);
        arrayList.add(ap2);
        arrayList.add(ap3);
        arrayList.add(ap4);
        arrayList.add(ap5);


        ArrayAdapter adapter = new ArrayAdapter<Ap>(this, android.R.layout.simple_list_item_1, arrayList);
        ListView mainListView = (ListView) findViewById(R.id.listView);
        adapter.notifyDataSetChanged();
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Ap a= (Ap) adapterView.getItemAtPosition(i);
                String[] b = {a.getTitle(),a.getAddress(),a.getNrCam()};

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("apartment",b);
                intent.putExtra("pos",i);
                startActivity(intent);

            }

        });

    }

    public void onClickAddLayoutButton(View view) {
        final int result = 1;

        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, result);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String[] appSentBack = data.getStringArrayExtra("apartment");

        Ap apReceived = new Ap(appSentBack[0], appSentBack[1], appSentBack[2]);
        arrayList.add(apReceived);

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.invalidate();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"kiss.lory@yahoo.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "New Apartment added");
        i.putExtra(Intent.EXTRA_TEXT   , apReceived.toString());
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }

//    public void OnListViewItemClick(View view) {
//        final int result = 1;
//
//        Intent intent = new Intent(this, EditActivity.class);
//        startActivityForResult(intent, result);
//
//
//    }


    public static class Ap {

        private String title;
        private String address;
        private String nrCam;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNrCam() {
            return nrCam;
        }

        public void setNrCam(String nrCam) {
            this.nrCam = nrCam;
        }

        @Override
        public String toString() {
            return "Ap: " + title + '\'' +
                    ", " + address + '\'' +
                    ", " + nrCam +
                    " camere";
        }

        public Ap(String title, String address, String nrCam) {
            this.title = title;
            this.address = address;
            this.nrCam = nrCam;
        }
    }
}
