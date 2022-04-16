package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapplication.R;

public class GameHomeActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout start_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_home);

        findViews();
    }

    private void findViews() {
        start_btn = (LinearLayout) findViewById(R.id.start_btn);
        start_btn.setOnClickListener(this);
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
        }
    }
}