package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    EditText eusername;
    EditText eemail;
    EditText epassword;
    EditText econfirmPassword;
    Button registration_btn;

    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eusername = findViewById(R.id.eusername);
        econfirmPassword = findViewById(R.id.econfirmPassword);
        eemail = findViewById(R.id.eemail);
        epassword = findViewById(R.id.epassword);
        econfirmPassword = findViewById(R.id.econfirmPassword);
        registration_btn = findViewById(R.id.registration_btn);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);

        registration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUsername = eusername.getText().toString();
                String txtEmail = eemail.getText().toString();
                String txtPassword = epassword.getText().toString();
                String cPassword = econfirmPassword.getText().toString();

                if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){
                    Toast.makeText(RegistrationActivity.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() < 6){
                    Toast.makeText(RegistrationActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                    if(txtPassword.equals(cPassword)) {
                        registerUser(txtUsername, txtEmail, txtPassword);
                    }else{
                        Toast.makeText(RegistrationActivity.this, "Password and Confirm Password are not same", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void registerUser(String txtUsername, String txtEmail, String txtPassword) {
        pd.setMessage("Please Wait!");
        pd.show();

        mAuth.createUserWithEmailAndPassword(txtEmail,txtPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                HashMap<String , Object> map = new HashMap<>();
                map.put("email", txtEmail);
                map.put("username" , txtUsername);
                map.put("id" , mAuth.getCurrentUser().getUid());
                map.put("password" , txtPassword);

                mRootRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(map);
                pd.dismiss();

                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}