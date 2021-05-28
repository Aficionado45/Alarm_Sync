package net.gitanjit.final_group_alarm;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{
    TextView Fnameid,phoneid;
    View view;

    public MyViewHolder(@NonNull View itemView) {

        super(itemView);

        Fnameid=itemView.findViewById(R.id.Fname);
        phoneid=itemView.findViewById(R.id.phone);
        view = itemView;
    }
}
