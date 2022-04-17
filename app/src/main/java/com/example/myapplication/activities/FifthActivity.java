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

public class FifthActivity extends AppCompatActivity implements View.OnClickListener {
    private int screen_Width,screen_height;
    private Button yes_button;
    private Button no_button;
    private ImageView cancel_popup;
    private TextView fifth_answer;
    private int fifth_ans;
    private Button confirm_five;
    private Dialog confirmdialog;
    private TextView confirm;
    private TextView no;
    private RadioGroup radioGroup_five_question;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        findViews();
    }

    private void findViews() {
        DisplayMetrics dm = new DisplayMetrics();
        FifthActivity.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screen_Width = dm.widthPixels;
        screen_height = dm.heightPixels;

        confirm_five = (Button) findViewById(R.id.confirm_five);
        confirm_five.setOnClickListener(this);
        radioGroup_five_question = (RadioGroup) findViewById(R.id.radioGroup_five_question);

        fifth_answer = (TextView) findViewById(R.id.fifth_answer);
        String third_answer = Utilities.getpref(FifthActivity.this,"four","");
        if(Utilities.Fourth_Ans.equals("Vasco da Gama")){
            fifth_ans = Integer.valueOf(third_answer)+10;
            fifth_answer.setText("You got "+fifth_ans+" Points");
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
        }else{
            fifth_ans = Integer.valueOf(third_answer);
            fifth_answer.setText("You got "+fifth_ans+" Points");
            Toast.makeText(this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
        }
        Utilities.savePref(FifthActivity.this,"five",String.valueOf(fifth_ans));
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
                Intent i = new Intent(FifthActivity.this, GameHomeActivity.class);
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

        switch (id) {

            case R.id.confirm_five :

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
        int selectedId = radioGroup_five_question.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        try {
            if (!radioButton.getText().toString().isEmpty() && radioButton.getText().toString() != null) {
                Intent tosecond = new Intent(FifthActivity.this, SixthActivity.class);
                startActivity(tosecond);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);
                Utilities.Fifth_Ans = radioButton.getText().toString();
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