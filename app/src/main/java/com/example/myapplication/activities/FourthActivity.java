package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Utilities;

public class FourthActivity extends AppCompatActivity implements View.OnClickListener {

    private int screen_Width,screen_height;
    private TextView fourth_answer;
    private int fourth_ans;
    private Button confirm_fourth;
    private Dialog confirmdialog;
    private TextView confirm;
    private TextView no;
    RadioGroup radioGroup_fourth_question;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        findViews();
    }

    private void findViews() {
        DisplayMetrics dm = new DisplayMetrics();
        FourthActivity.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screen_Width = dm.widthPixels;
        screen_height = dm.heightPixels;

        confirm_fourth = (Button) findViewById(R.id.confirm_fourth);
        confirm_fourth.setOnClickListener(this);

        radioGroup_fourth_question = (RadioGroup) findViewById(R.id.radioGroup_fourth_question);

        fourth_answer = (TextView) findViewById(R.id.fourth_answer);
        String third_answer = Utilities.getpref(FourthActivity.this,"third","");
        if(Utilities.Third_Ans.equals("148million km sq")){
            fourth_ans = Integer.valueOf(third_answer)+10;
            fourth_answer.setText("You got "+fourth_ans+" Points");
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
        }else{
            fourth_ans = Integer.valueOf(third_answer);
            fourth_answer.setText("You got "+fourth_ans+" Points");
            Toast.makeText(this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
        }
        Utilities.savePref(FourthActivity.this,"four",String.valueOf(fourth_ans));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.confirm_fourth:

                confirm_Popup();

                break;

        }
    }

    private void confirm_Popup() {
        confirmdialog = new Dialog(this);
        confirmdialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        confirmdialog.setContentView(R.layout.confirm_popup);
        confirmdialog.setCancelable(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(confirmdialog.getWindow().getAttributes());
        lp.width = (int) (screen_Width * 0.95);//WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        confirmdialog.getWindow().setAttributes(lp);

        confirm = (TextView) confirmdialog.findViewById(R.id.confirm);
        no = (TextView) confirmdialog.findViewById(R.id.no);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm_method();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmdialog.dismiss();
            }
        });

        confirmdialog.show();

    }

    private void confirm_method() {
        int selectedId = radioGroup_fourth_question.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        try {
            if (!radioButton.getText().toString().isEmpty() && radioButton.getText().toString() != null) {
                Intent tosecond = new Intent(FourthActivity.this, FifthActivity.class);
                startActivity(tosecond);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);
                Utilities.Fourth_Ans = radioButton.getText().toString();
            } else {
                Toast.makeText(getApplicationContext(), "Please choose your option", Toast.LENGTH_SHORT).show();
                confirmdialog.dismiss();
            }
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "Please choose your option", Toast.LENGTH_SHORT).show();
            confirmdialog.dismiss();
        }
    }
}