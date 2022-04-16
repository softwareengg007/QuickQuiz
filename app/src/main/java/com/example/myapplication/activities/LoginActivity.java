package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    EditText et_username;
    EditText password_editText;
    Button login_btn;
    TextView register_txt;

    private FirebaseAuth auth;
    private DatabaseReference mRootRef;
    private ProgressDialog progressBar;

    RadioButton usLanguage;
    RadioButton spaLanguage;
    private Locale myLocale;
    private Configuration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        findViews();
    }

    private void findViews() {
        progressBar = new ProgressDialog(this);

        et_username = findViewById(R.id.et_username);
        password_editText = findViewById(R.id.password_editText);
        login_btn = findViewById(R.id.login_btn);
        usLanguage = findViewById(R.id.usLanguage);
        spaLanguage = findViewById(R.id.spaLanguage);

        register_txt = findViewById(R.id.register_txt);

        register_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(login);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginMethod();
            }
        });

        usLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLocale("en");
            }
        });

        spaLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLocale("en");
            }
        });

        if(Utilities.getpref(this,"LanguageType","").equals("en")){



        }else if(Utilities.getpref(this,"LanguageType","").equals("en")){

            

        }

    }

    private void changeLocale(String lang) {
        if (lang == ""){

        }else {
            myLocale = new Locale(lang);
            saveLocale(lang);
            Locale.setDefault(myLocale);
            config = new Configuration();
            config.locale = myLocale;
            getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

            Intent i = new Intent(LoginActivity.this,SplashActivity.class);
            startActivity(i);
        }
    }

    private void saveLocale(String lang) {
        Utilities.savePref(this,"LanguageType",lang);
    }

    private void loginMethod() {
        String email = et_username.getText().toString();
        final String password = password_editText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setMessage("Loading...");
        progressBar.setCanceledOnTouchOutside(false);
        progressBar.setCancelable(false);
        progressBar.show();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.dismiss();
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                et_username.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(LoginActivity.this, GameHomeActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                    }
                });


    }
}