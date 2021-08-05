package com.example.alarmsynchroniser;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class SignUpActivity extends AppCompatActivity {

    EditText mEmailEt, mPasswordEt,mMobileEt,mCpassowrdEt,mNameEt;
    Button mSignupBtn;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    TextView mHaveaccountTv;

    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Create Account");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mNameEt=findViewById(R.id.nameEt);
        mMobileEt=findViewById(R.id.mobileEt);
        mEmailEt=findViewById(R.id.emailEt);
        mPasswordEt=findViewById(R.id.passwordEt);
        mCpassowrdEt=findViewById(R.id.cpasswordEt);
        mSignupBtn=findViewById(R.id.signup_btn);
        mHaveaccountTv=findViewById(R.id.have_accountTv);

        mAuth=FirebaseAuth.getInstance();


        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Signing You Up...");

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mNameEt.getText().toString().trim();
                String mobile=mMobileEt.getText().toString().trim();
                String email= mEmailEt.getText().toString().trim();
                String password=mPasswordEt.getText().toString().trim();
                String cpassword=mCpassowrdEt.getText().toString().trim();



                if(password.length()<6){
                    mPasswordEt.setError("password Length at least 6 Characters");
                    mPasswordEt.setFocusable(true);
                }
                else if(!password.equals(cpassword)){
                    mPasswordEt.setError("Password and Confirm Password Does Not Match");
                    mPasswordEt.setFocusable(true);
                }
                registerUser(name,email,mobile,password);

            }
        });
        mHaveaccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });


    }

    private void registerUser(String name, String email, String mobile, String password) {

        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser user=mAuth.getCurrentUser();

                            String email=user.getEmail();
                            String uid=user.getUid();

                            HashMap<Object,String> hashmap=new HashMap<>();
                            hashmap.put("email",email);
                            hashmap.put("uid",uid);
                            hashmap.put("fullName",name);
                            hashmap.put("phone",mobile);

                            FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference reference=database.getReference("Users");
                            reference.child(uid).setValue(hashmap);


                            startActivity(new Intent(SignUpActivity.this, DashboardActivity.class));
                            finish();
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this, ""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();;
        return super.onSupportNavigateUp();
    }
}

