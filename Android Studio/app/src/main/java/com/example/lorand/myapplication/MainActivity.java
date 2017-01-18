package com.example.lorand.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<Apartment> arrayList = new ArrayList<>();
    public ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnRegistration_Click(View v){
        Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(i);
    }

    public void btnLogin_Click(View v){
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        try {
//            readFile( MainActivity.this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
//
//        ListView mainListView = (ListView) findViewById(R.id.listView);
//        mainListView.setAdapter(adapter);
//        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Apartment a = (Apartment) adapterView.getItemAtPosition(i);
//                String[] b = {a.getTitle(), a.getAddress(), a.getNrCam()};
//
//                Intent intent = new Intent(MainActivity.this, EditActivity.class);
//                intent.putExtra("apartment", b);
//                intent.putExtra("pos", i);
//                startActivity(intent);
//
//            }
//
//        });
//
//
//    }
//
//    public static void createFile( Context ctx) throws IOException, JSONException {
//        JSONArray data = new JSONArray();
//        JSONObject ap;
//
//        for (int i = 0; i < arrayList.size(); i++) {
//            ap = new JSONObject();
//            ap.put("title", arrayList.get(i).getTitle());
//            ap.put("address", arrayList.get(i).getAddress());
//            ap.put("roomNr", arrayList.get(i).getNrCam());
//            data.put(ap);
//        }
//        String text = data.toString();
//
//        FileOutputStream fos = ctx.openFileOutput("apartmentsFile", MODE_PRIVATE);
//        fos.write(text.getBytes());
//        fos.close();
//    }
//
//    public static void readFile( Context ctx) throws IOException, JSONException {
//
//        FileInputStream fis = ctx.openFileInput("apartmentsFile");
//        BufferedInputStream bis = new BufferedInputStream(fis);
//        StringBuffer b = new StringBuffer();
//        while (bis.available() != 0) {
//            char c = (char) bis.read();
//            b.append(c);
//        }
//        bis.close();
//        fis.close();
//        JSONArray data = new JSONArray(b.toString());
//
//        for (int i = 0; i < data.length(); i++) {
//            String title = data.getJSONObject(i).getString("title");
//            String address = data.getJSONObject(i).getString("address");
//            String roomNr = data.getJSONObject(i).getString("roomNr");
//            Apartment apartment = new Apartment(title, address, roomNr);
//            arrayList.add(apartment);
//        }
//
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        adapter.notifyDataSetChanged();
//        try {
//            createFile( MainActivity.this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//
//    public void onClickAddLayoutButton(View view) {
//        final int result = 1;
//
//        Intent intent = new Intent(this, AddActivity.class);
//        startActivityForResult(intent, result);
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        String[] appSentBack = data.getStringArrayExtra("apartment");
//
//        Apartment apReceived = new Apartment(appSentBack[0], appSentBack[1], appSentBack[2]);
//        arrayList.add(apReceived);
//
//        try {
//            createFile( MainActivity.this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ListView lv = (ListView) findViewById(R.id.listView);
//        lv.invalidate();
//
//        Intent i = new Intent(Intent.ACTION_SEND);
//        i.setType("message/rfc822");
//        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"kiss.lory@yahoo.com"});
//        i.putExtra(Intent.EXTRA_SUBJECT, "New Apartment added");
//        i.putExtra(Intent.EXTRA_TEXT, apReceived.toString());
//        try {
//            startActivity(Intent.createChooser(i, "Send mail..."));
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }


}
