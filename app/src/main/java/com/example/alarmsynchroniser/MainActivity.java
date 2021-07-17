package com.example.alarmsynchroniser;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import com.google.android.gms.common.internal.GmsLogger;

public class MainActivity extends AppCompatActivity {

    Button mSignupBtn,mLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSignupBtn=findViewById(R.id.signup_btn);
        mLoginBtn=findViewById(R.id.login_btn);

        mSignupBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent signupnav= new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(signupnav);

            }

        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginnav= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginnav);
            }
        });
    }
}