package com.example.lorand.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<Ap> arrayList = new ArrayList<>();
    public ArrayAdapter adapter;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Ap ap1 = new Ap("rent", "street1", "2");
//        Ap ap2 = new Ap("rental", "street111", "3");
//        Ap ap3 = new Ap("rents", "street12", "5");
//        Ap ap4 = new Ap("rentfdfs", "street55", "1");
//        Ap ap5 = new Ap("rentfff", "street3", "222");
//        arrayList.add(ap1);
//        arrayList.add(ap2);
//        arrayList.add(ap3);
//        arrayList.add(ap4);
//        arrayList.add(ap5);
        try {
            readFile( MainActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        FloatingActionButton refresh = (FloatingActionButton) findViewById(R.id.floatingActionButton);
//        refresh.setOnClickListener((View.OnClickListener)(v) ->{
//
//        })

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        ListView mainListView = (ListView) findViewById(R.id.listView);
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Ap a = (Ap) adapterView.getItemAtPosition(i);
                String[] b = {a.getTitle(), a.getAddress(), a.getNrCam()};

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("apartment", b);
                intent.putExtra("pos", i);
                startActivity(intent);

            }

        });


    }

    public static void createFile( Context ctx) throws IOException, JSONException {
        JSONArray data = new JSONArray();
        JSONObject ap;

        for (int i = 0; i < arrayList.size(); i++) {
            ap = new JSONObject();
            ap.put("title", arrayList.get(i).getTitle());
            ap.put("address", arrayList.get(i).getAddress());
            ap.put("roomNr", arrayList.get(i).getNrCam());
            data.put(ap);
        }
        String text = data.toString();

        FileOutputStream fos = ctx.openFileOutput("apartmentsFile", MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();
    }

    public static void readFile( Context ctx) throws IOException, JSONException {

        FileInputStream fis = ctx.openFileInput("apartmentsFile");
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();
        JSONArray data = new JSONArray(b.toString());

        for (int i = 0; i < data.length(); i++) {
            String title = data.getJSONObject(i).getString("title");
            String address = data.getJSONObject(i).getString("address");
            String roomNr = data.getJSONObject(i).getString("roomNr");
            Ap apartment = new Ap(title, address, roomNr);
            arrayList.add(apartment);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        try {
            createFile( MainActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


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

        try {
            createFile( MainActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.invalidate();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"kiss.lory@yahoo.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "New Apartment added");
        i.putExtra(Intent.EXTRA_TEXT, apReceived.toString());
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }


    }


}
