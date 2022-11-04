package com.asm.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Animationfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animationfile);

       // Intent iHome = new Intent(Animationfile.this,MainActivity.class);

      new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent iHome = new Intent(Animationfile.this,MainActivity.class);

               startActivity(iHome);
              finish();

           }
       },5000);

    }
}