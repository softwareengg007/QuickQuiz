package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.myapplication.R;
import com.example.myapplication.Utilities;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(3000);

                        if(Utilities.getBooleanPref(SplashActivity.this, "HasLogged_In", false)){
                            Intent login = new Intent(SplashActivity.this, GameHomeActivity.class);
                            startActivity(login);
                            finish();
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }else {
                            Intent login = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(login);
                            finish();
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                    }
                } catch (InterruptedException ex) {

                }
            }
        }.start();

    }
}