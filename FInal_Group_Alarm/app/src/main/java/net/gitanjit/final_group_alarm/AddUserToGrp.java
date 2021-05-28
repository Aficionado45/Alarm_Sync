package net.gitanjit.final_group_alarm;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddUserToGrp extends AppCompatActivity {
    TextView fullname , phoneno,group;
    Button adduser,rettomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_to_grp);
        fullname = findViewById(R.id.profileName);
        phoneno= findViewById(R.id.profilePhone);

        String fname=getIntent().getStringExtra("FName");
        String phone=getIntent().getStringExtra("Phone");

        fullname.setText(fname);
        phoneno.setText(phone);
    }
}