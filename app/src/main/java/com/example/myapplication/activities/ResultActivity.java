package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Utilities;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    Button homeScreen;
    TextView result;
    private int tenth_ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViews();
    }

    private void findViews() {
        result = (TextView) findViewById(R.id.result);
        String tenth_answer = Utilities.getpref(ResultActivity.this,"tenth","");
        if(Utilities.Tenth_Ans.equals("Neil Armstrong")){
            tenth_ans = Integer.valueOf(tenth_answer)+10;
            result.setText("You got "+tenth_ans+" Points");
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
        }else{
            tenth_ans = Integer.valueOf(tenth_answer);
            result.setText("You got "+tenth_ans+" Points");
            Toast.makeText(this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
        }

        homeScreen = (Button) findViewById(R.id.homeScreen);
        homeScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.homeScreen :
                Intent i = new Intent(ResultActivity.this, GameHomeActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}