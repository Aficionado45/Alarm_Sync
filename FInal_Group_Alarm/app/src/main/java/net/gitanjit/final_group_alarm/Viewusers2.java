package net.gitanjit.final_group_alarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Viewusers2 extends AppCompatActivity {
    DatabaseReference reference;
    private FirebaseRecyclerOptions<UserHelperClass> options;
    private FirebaseRecyclerAdapter<UserHelperClass,MyViewHolder> adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewusers2);
       // reference =  FirebaseDatabase.getInstance().getReference("Users");
        reference =  FirebaseDatabase.getInstance().getReference().child("Users");
        recyclerView = findViewById(R.id.recyclerusers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        options = new FirebaseRecyclerOptions.Builder<UserHelperClass>().setQuery(reference,UserHelperClass.class).build();
        adapter = new FirebaseRecyclerAdapter<UserHelperClass, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull UserHelperClass model) {


                holder.view.setOnClickListener(new View.OnClickListener() {
                    final String fname = model.getFullName();
                    final String phone = model.getPhone();
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AddUserToGrp.class);
                        intent.putExtra("FName",""+fname);
                        intent.putExtra("Phone",""+phone);
                        startActivity(intent);
                    }
                });

                holder.Fnameid.setText(model.getFullName());
                holder.phoneid.setText(model.getPhone());
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleuserlay,parent,false);

                return new MyViewHolder(v);
            }
        };

adapter.startListening();
recyclerView.setAdapter(adapter);
    }
}