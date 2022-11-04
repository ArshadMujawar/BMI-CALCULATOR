package com.asm.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

public class MainActivity extends AppCompatActivity {

    String GameID = "4935949";
    String BannerID="Banner_Android";
    String InterID="Interstitial_Android";
    Boolean TestMode = false; //(select any one. if you test then you select true)
    LinearLayout bannerAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadInterstital();


       bannerAd=findViewById(R.id.banner_ad);
        UnityAds.initialize(this,GameID,TestMode);
        BannerView view = new BannerView(MainActivity.this,BannerID,new UnityBannerSize(320,50));
        view.load();
        bannerAd.addView(view);

        EditText edtWeight, edtHeightFt, edtHeightIn;
        Button btnCalculate;
        TextView txtResult;
        LinearLayout llMain;

        edtWeight = findViewById(R.id.edtWeight);
        edtHeightFt = findViewById(R.id.edtHeightFt);
        edtHeightIn = findViewById(R.id.edtHeightIn);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);
        llMain = findViewById(R.id.llMain);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UnityAds.show(MainActivity.this,InterID);

              int wt = Integer.parseInt(edtWeight.getText().toString());
              int ft = Integer.parseInt(edtHeightFt.getText().toString());
              int in = Integer.parseInt(edtHeightIn.getText().toString());

              int totalIn = ft*12 + in;
              double totalCm = totalIn*2.53;
              double totalM = totalCm/100;

              double bmi = wt/(totalM*totalM);

              if(bmi>25){
                  txtResult.setText("You're Overweight");
                  llMain.setBackgroundColor(getResources().getColor(R.color.red));
              }
              else if(bmi<18){
                  txtResult.setText("You're Underweight");
                  llMain.setBackgroundColor(getResources().getColor(R.color.yellow));
              }
              else {
                  txtResult.setText("You're Healthy");
                  llMain.setBackgroundColor(getResources().getColor(R.color.green));

              }


            }

        });



    }

    private void loadInterstital() {

        if (UnityAds.isInitialized()){
            UnityAds.load((InterID));
        }else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    UnityAds.load(InterID);

                }
            }, 5000);
        }
    }
}