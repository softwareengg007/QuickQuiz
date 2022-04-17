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

import io.grpc.okhttp.internal.Util;

public class FirstPageActivity extends AppCompatActivity implements View.OnClickListener {

    private int screen_Width,screen_height;
    private TextView first_answer;
    private int first_ans;
    private Button yes_button;
    private Button no_button;
    private ImageView cancel_popup;
    private Button confirm_first;
    private RadioButton rb_one,rb_two,rb_three,rb_four;
    private Dialog confirmdialog;
    private TextView confirm;
    private TextView no;
    private RadioGroup radioGroup_first_question;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        findViews();
    }

    private void findViews() {
        DisplayMetrics dm = new DisplayMetrics();
        FirstPageActivity.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screen_Width = dm.widthPixels;
        screen_height = dm.heightPixels;

        first_answer = (TextView) findViewById(R.id.first_answer);
        confirm_first = (Button) findViewById(R.id.confirm_first);

        radioGroup_first_question = (RadioGroup) findViewById(R.id.radioGroup_first_question);
        rb_one = findViewById(R.id.rb_one);
        rb_two = findViewById(R.id.rb_two);
        rb_three = findViewById(R.id.rb_three);
        rb_four = findViewById(R.id.rb_four);

        first_ans = 0;
        first_answer.setText("Your Score "+first_ans);
        Utilities.savePref(FirstPageActivity.this,"first",String.valueOf(first_ans));

        confirm_first.setOnClickListener(this);

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

        yes_button = (Button) dialog.findViewById(R.id.yes_button);
        no_button = (Button) dialog.findViewById(R.id.no_button);
        cancel_popup = (ImageView) dialog.findViewById(R.id.cancel_popup);

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FirstPageActivity.this, GameHomeActivity.class);
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

            case R.id.confirm_first :

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
        int selectedId = radioGroup_first_question.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);

        try {
            if (!radioButton.getText().toString().isEmpty() && radioButton.getText().toString() != null) {
                Utilities.First_Ans = radioButton.getText().toString();
                Intent tosecond = new Intent(FirstPageActivity.this, SecondPageActivity.class);
                startActivity(tosecond);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);
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