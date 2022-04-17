package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.Utilities;

public class GameHomeActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout start_btn,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_home);
        findViews();
    }

    private void findViews() {
        start_btn = (LinearLayout) findViewById(R.id.start_btn);
        start_btn.setOnClickListener(this);

        logout= (LinearLayout) findViewById(R.id.logout);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.start_btn:
                Intent intent = new Intent(GameHomeActivity.this, FirstPageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;

            case R.id.logout:
                Utilities.savebooleanPref(
                        this,
                        "IsLoggedIn",
                        false
                );
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                overridePendingTransition(R.anim.enter_from_left_frag, R.anim.exit_to_right_frag);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent exit = new Intent(Intent.ACTION_MAIN);
        exit.addCategory(Intent.CATEGORY_HOME);
        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exit);
    }

}