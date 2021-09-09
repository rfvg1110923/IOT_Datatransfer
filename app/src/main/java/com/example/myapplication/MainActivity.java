package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvID,tvResult,tvuuid,tvrssi,tvmajor,tvminor,tvStatus,tvDistance;
    private Spinner spID;
    private ImageView imgTitle;
    private Button btMap,btStatus;
    String[] number = new String[]{"esp32 no_0","esp32 no_1","esp32 no_2","esp32 no_3"};
//"25786407-ebc6-cfaf-b14f-e2a49306a5fd","52874670-be6c-fcfa-1bf4-2e4a39605adf",
//            "fda50693a4e2-4fb1-afcf-c6eb-07647825","000000000000-0000-0000-0000-00000000"


    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference UUID = database.getReference("/esp32 no_0 /UUID");
    public DatabaseReference RSSI = database.getReference("/esp32 no_0 /RSSI");
    public DatabaseReference MAJOR = database.getReference("/esp32 no_0 /Major");
    public DatabaseReference MINOR = database.getReference("/esp32 no_0 /Minor");
    public DatabaseReference DISTANCE = database.getReference("/esp32 no_0 /distance");
    public DatabaseReference STATUS = database.getReference("/esp32 no_0 /Status");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgTitle   = (ImageView)findViewById(R.id.imgTitle);

        spID       = (Spinner)findViewById(R.id.spID);

        tvID       = (TextView)findViewById(R.id.tvID);
        tvResult   = (TextView)findViewById(R.id.tvResult);
        tvuuid     = (TextView)findViewById(R.id.tvuuid);
        tvrssi     = (TextView)findViewById(R.id.tvrssi);
        tvmajor    = (TextView)findViewById(R.id.tvmajor);
        tvminor    = (TextView)findViewById(R.id.tvminor);
        tvStatus   = (TextView)findViewById(R.id.tvStatus);
        tvDistance = (TextView) findViewById(R.id.tvDistance);

        btStatus   = (Button)findViewById(R.id.btStatus);
        btMap   = (Button)findViewById(R.id.btMap);



        btStatus.setOnClickListener(btStatusListener);
        tvStatus.setText("閒置中");
        tvStatus.setTextColor(Color.parseColor("#228B22"));

        btMap.setOnClickListener(btMapListener);

        //Spinner
        ArrayAdapter<String> adapternumber = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,number);
        adapternumber.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   //設定顯示格式
        spID.setAdapter(adapternumber);    //設定資料來源
        spID.setOnItemSelectedListener(spIDListener);


        /*ArrayList<Data> mData = new ArrayList<>();
        mData.add(new Data("Sam",19,true));
        mData.add(new Data("Eddies",25,false));
        mData.add(new Data("Sally",17,true));
        mData.add(new Data("Noah",25,false));
        mData.add(new Data("Tina",30,false));*/


        //myRef.setValue("00000");  設定訊息為00000


        UUID.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String uuid = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "UUID : " + uuid);
                tvuuid.setText("UUID : " + uuid);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        RSSI.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer rssi = dataSnapshot.getValue(Integer.class);
                tvrssi.setText("RSSI : " + rssi);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        MAJOR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer major = dataSnapshot.getValue(Integer.class);
                tvmajor.setText("Major : " + major);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        MINOR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer minor = dataSnapshot.getValue(Integer.class);
                tvminor.setText("Minor : " + minor);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        DISTANCE.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double distance = dataSnapshot.getValue(Double.class);
                tvDistance.setText("Distance : " + distance);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    //下拉式選單
    private Spinner.OnItemSelectedListener spIDListener = new Spinner.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //tvuuid.setText(parent.getSelectedItem().toString());
        }
        @Override
        public void onNothingSelected(AdapterView<?>parent){

        }
    };

    //狀態切換按鈕
    private Button.OnClickListener btStatusListener = new Button.OnClickListener(){
        public void onClick(View v){
            if(tvStatus.getText()=="借用中"){
                tvStatus.setText("閒置中");
                tvStatus.setTextColor(Color.parseColor("#228B22"));//設定顏色
                STATUS.setValue("available");
            }else{
                tvStatus.setText("借用中");
                tvStatus.setTextColor(Color.parseColor("#FF0000"));
                STATUS.setValue("busy");
            }
        }
    };

    //地圖按鈕
    private Button.OnClickListener btMapListener = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, Map.class);
            startActivity(intent);
        }
    };
    /*
    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever com.example.myapplication.data at this location is updated.
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