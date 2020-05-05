package com.example.bicap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setSplashScreenAnimation();
        setVersionText();
    }

    private void setSplashScreenAnimation(){
        ImageView bImageView = (ImageView) findViewById(R.id.bImageView);
        ImageView icapImageView = (ImageView) findViewById(R.id.icapImageView);
        Animation bAnimation = AnimationUtils.loadAnimation(this, R.anim.b_animation);
        bAnimation.setFillAfter(true);
        Animation icapAnimation = AnimationUtils.loadAnimation(this, R.anim.icap_animation);
        icapAnimation.setFillAfter(true);
        bImageView.setAnimation(bAnimation);
        icapImageView.setAnimation(icapAnimation);
    }

    private void setVersionText(){
        TextView versionTextView = (TextView) findViewById(R.id.versionTextView);
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            versionTextView.setText(versionTextView.getText() + " " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
