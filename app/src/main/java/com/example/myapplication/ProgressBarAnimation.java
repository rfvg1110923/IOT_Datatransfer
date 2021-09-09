package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Context;

//import com.google.firebase.database.core.Context;

public class ProgressBarAnimation extends Animation {

    private Context context;
    private ProgressBar pgb;
    private TextView tvProgressrate;
    private float from,to;

    public ProgressBarAnimation(Context context, ProgressBar pgb, TextView tvProgressrate, float from, float to){
        this.context = context;
        this.pgb = pgb;
        this.tvProgressrate = tvProgressrate;
        this.from = from;
        this.to = to;
    }

    protected void applyTransformation(float interpolatedTime, Transformation t){
        super.applyTransformation(interpolatedTime,t);
        float value = from + (to - from) * interpolatedTime;
        pgb.setProgress((int)value);
        tvProgressrate.setText((int)value + " %");

        if(value == to){
            context.startActivity(new Intent(context, MainActivity.class));
        }
    }
}