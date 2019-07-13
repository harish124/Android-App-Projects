package com.example.insta;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerView_Config {

    private Context mContext;
    private UserAdapter userAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<String> arrayList)
    {
        mContext=context;
        userAdapter = new UserAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(userAdapter);
    }
    class UserItemView extends RecyclerView.ViewHolder
    {
        private TextView list_item;

        public UserItemView(ViewGroup parent)
        {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.recycler_view_row,parent,false));

            list_item= (TextView)itemView.findViewById(R.id.list_item);


        }

        public void bind(String username)
        {
            Toast.makeText(mContext,"Bind Username: "+username, Toast.LENGTH_SHORT).show();
            list_item.setText(""+username);
        }
    }//eclassBookItemView

    class UserAdapter extends RecyclerView.Adapter<UserItemView>
    {
        private List<String> arrayList;

        public UserAdapter(List<String> arrayList) {
            this.arrayList = arrayList;
        }


        @Override
        public UserItemView onCreateViewHolder(ViewGroup parent, int i) {
            return new UserItemView(parent);
        }

        @Override
        public void onBindViewHolder(UserItemView holder, int position) {
            Toast.makeText(mContext,"Adapter Username: "+arrayList.get(position)+"\nPos = "+position, Toast.LENGTH_SHORT).show();
            holder.bind(arrayList.get(position));
        }

        @Override
        public int getItemCount() {

            Toast.makeText(mContext,"Size = "+arrayList.size(), Toast.LENGTH_SHORT).show();
            return arrayList.size();
        }
    }
}
