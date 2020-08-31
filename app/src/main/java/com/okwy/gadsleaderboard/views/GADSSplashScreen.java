package com.okwy.gadsleaderboard.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.okwy.gadsleaderboard.R;

public class GADSSplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_gads_splash);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gotoGADSLeaderboard();
                }
            }, 3000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void gotoGADSLeaderboard() {
        startActivity(new Intent(this, GADSLeaderBoard.class));
        //startActivity(new Intent(this, GADSSubmission.class));
        finish();
    }

}
