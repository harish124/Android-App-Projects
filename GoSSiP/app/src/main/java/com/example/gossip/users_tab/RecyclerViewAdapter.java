package com.example.gossip.users_tab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gossip.R;
import com.example.gossip.sign_up.MyUsers;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;

import print.Print;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<MyUsers> arrayList;
    private Context ctx;
    private Print p;

    public RecyclerViewAdapter(ArrayList<MyUsers> arrayList) {
        this.arrayList = arrayList;
        p=new Print();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx=parent.getContext());

        View view = inflater.inflate(R.layout.recycler_view_users,parent,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyUsers user = arrayList.get(position);
        p.print(ctx,"Reached OnBindView\nName = "+user.getUserName());
        holder.userName.setText(user.getUserName());
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userName;
        private ImageView imageView,shareIcon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            imageView=itemView.findViewById(R.id.userProfilePic);
            shareIcon=itemView.findViewById(R.id.shareIcon);

            shareIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
