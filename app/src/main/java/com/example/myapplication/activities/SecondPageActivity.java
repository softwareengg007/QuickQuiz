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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Utilities;

public class SecondPageActivity extends AppCompatActivity implements View.OnClickListener {

    private int screen_Width;
    private Button confirm_second;
    private ImageView cancel_popup;
    private TextView second_answer;
    private int second_ans;
    private Dialog confirmdialog;
    private TextView confirm;
    private TextView no;
    private RadioGroup radioGroup_second_question;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        findViews();
    }

    private void findViews() {

        DisplayMetrics dm = new DisplayMetrics();
        SecondPageActivity.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screen_Width = dm.widthPixels;
        int screen_height = dm.heightPixels;

        second_answer = (TextView) findViewById(R.id.second_answer);
        confirm_second= (Button) findViewById(R.id.confirm_second);
        confirm_second.setOnClickListener(this);

        radioGroup_second_question = (RadioGroup) findViewById(R.id.radioGroup_second_question);

        String first_answer = Utilities.getpref(SecondPageActivity.this,"first","");
        if(Utilities.First_Ans.equals("US and Canada")){
            second_ans = Integer.valueOf(first_answer)+10;
            second_answer.setText("You got "+second_ans+" Points");
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
        }else{
            second_ans = Integer.valueOf(first_answer);
            second_answer.setText("You got "+second_ans+" Points");
            Toast.makeText(this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
        }
        Utilities.savePref(SecondPageActivity.this,"second",String.valueOf(second_ans));

    }

    @Override
    public void onBackPressed() {

        final Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.exit_popup);
        dialog.setCancelable(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = (int) (screen_Width * 0.95);//WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        Button yes_button = (Button) dialog.findViewById(R.id.yes_button);
        Button no_button = (Button) dialog.findViewById(R.id.no_button);
        cancel_popup = (ImageView) dialog.findViewById(R.id.cancel_popup);

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SecondPageActivity.this, GameHomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        cancel_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id){

            case R.id.confirm_second :

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
        int selectedId = radioGroup_second_question.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        try {
            if (!radioButton.getText().toString().isEmpty() && radioButton.getText().toString() != null) {
                Intent tosecond = new Intent(SecondPageActivity.this, ThirdPageActivity.class);
                startActivity(tosecond);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);
                Utilities.Second_Ans = radioButton.getText().toString();
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