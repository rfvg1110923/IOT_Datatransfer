package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private TextView tvuuid,tvrssi,tvmajor,tvminor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvuuid = (TextView)findViewById(R.id.tvuuid);
        tvrssi = (TextView)findViewById(R.id.tvrssi);
        tvmajor = (TextView)findViewById(R.id.tvmajor);
        tvminor = (TextView)findViewById(R.id.tvminor);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference UUID = database.getReference("UUID");
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
        RSSI.addValueEventListener(new ValueEventListener() {//讀取firebase裡的資料
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
        MAJOR.addValueEventListener(new ValueEventListener() {//讀取firebase裡的資料
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
        MINOR.addValueEventListener(new ValueEventListener() {//讀取firebase裡的資料
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