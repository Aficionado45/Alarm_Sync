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
    FirebaseDatabase rootNode;
    DatabaseReference reference;

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
        rootNode = FirebaseDatabase.getInstance();

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

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            // send verification link
                            Toast.makeText(SignUpActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            UserID = mAuth.getCurrentUser().getUid();
                            reference  = rootNode.getReference("Users");
                            Map<String,Object> user = new HashMap<>();
                            user.put("fullName",name);
                            user.put("email",email);
                            user.put("phone",mobile);
                            user.put("user_id",UserID);

                            reference.setValue("Please Work");

                            reference.child(UserID).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: User Profile is created for "+ UserID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                            finish();

                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

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



    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();;
        return super.onSupportNavigateUp();
    }
}

