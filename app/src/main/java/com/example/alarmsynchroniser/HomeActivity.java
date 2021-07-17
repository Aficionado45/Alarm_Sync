package com.example.alarmsynchroniser;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Button mlogoutBtn;
    TextView nameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Home");
        mlogoutBtn=findViewById(R.id.logoutBtn);

        mlogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        });


        firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    private void checkuserstatus(){
        FirebaseUser user= firebaseAuth.getCurrentUser();
        if(user!=null){

        }
        else{
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart(){
        checkuserstatus();
        super.onStart();
    }
}