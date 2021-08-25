package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private TextView tvID,tvResult,tvuuid,tvrssi,tvmajor,tvminor;
    private Spinner spID;
    private ImageView imgTitle;
    private Button btMap,btStatus;
    String[] number = new String[]{"25786407-ebc6-cfaf-b14f-e2a49306a5fd","52874670-be6c-fcfa-1bf4-2e4a39605adf",
            "fda50693a4e2-4fb1-afcf-c6eb-07647825","000000000000-0000-0000-0000-00000000"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgTitle = (ImageView)findViewById(R.id.imgTitle);
        spID = (Spinner)findViewById(R.id.spID);
        tvID = (TextView)findViewById(R.id.tvID);
        tvResult = (TextView)findViewById(R.id.tvResult);
        tvuuid = (TextView)findViewById(R.id.tvuuid);
        tvrssi = (TextView)findViewById(R.id.tvrssi);
        tvmajor = (TextView)findViewById(R.id.tvmajor);
        tvminor = (TextView)findViewById(R.id.tvminor);

        //Spinner
        ArrayAdapter<String> adapternumber = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,number);
        adapternumber.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   //設定顯示格式
        spID.setAdapter(adapternumber);    //設定資料來源
        spID.setOnItemSelectedListener(spIDListener);

    }
    private String uuid = this.uuid;
    private int rssi = this.rssi;
    private int major = this.major;
    private int minor = this.minor;
    private Spinner.OnItemSelectedListener spIDListener = new Spinner.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference UUID = database.getReference("UUID");  //從firebase裡的"UUID"接收訊息
            //myRef.setValue("00000");  設定訊息為00000
            UUID.addValueEventListener(new ValueEventListener() {//讀取firebase裡的資料
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String uuid = dataSnapshot.getValue(String.class);
                    //Log.d(TAG, "Value is: " + value);
                    tvuuid.setText("UUID : " + uuid);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

            DatabaseReference RSSI = database.getReference("RSSI");
            RSSI.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Integer rssi = dataSnapshot.getValue(Integer.class);
                    tvrssi.setText("RSSI : " + rssi.toString());
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

            DatabaseReference MAJOR = database.getReference("Major");
            MAJOR.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Integer major = dataSnapshot.getValue(Integer.class);
                    tvmajor.setText("Major : " + major.toString());
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

            DatabaseReference MINOR = database.getReference("Minor");
            MINOR.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Integer minor = dataSnapshot.getValue(Integer.class);
                    tvminor.setText("Minor : " + minor.toString());
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

        }

        @Override
        public void onNothingSelected(AdapterView<?>parent){

        }
    };
    /*
    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            String value = dataSnapshot.getValue(String.class);
            Log.d(TAG, "Value is: " + value);
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    });
     */
}