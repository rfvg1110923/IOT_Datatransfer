package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingScreen extends AppCompatActivity {

    private ImageView imgSyringe;
    private TextView tvProgressrate;
    private ProgressBar pgb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pgb =(ProgressBar)findViewById(R.id.pgb);
        tvProgressrate = (TextView)findViewById(R.id.tvProgressrate);

        pgb.setMax(100);
        pgb.setScaleY(3f);

        pgbAnimation();
    }

    public void pgbAnimation(){
        ProgressBarAnimation anim = new ProgressBarAnimation(this, pgb, tvProgressrate, 0f, 100f);
        anim.setDuration(5000);
        pgb.setAnimation(anim);
    }

}