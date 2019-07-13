package com.example.insta;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ProductViewHolder> {
    private Context mCtx;

    //we are storing all the products in a list
    private ArrayList<String> productList;

    //getting the context and product list with constructor
    public MyAdapter(Context mCtx, ArrayList<String> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_view_row, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder,final int position) {
        //getting the product of the specified position
        String product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx,UsersPost.class);
                intent.putExtra("Username",productList.get(position));
                mCtx.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                ParseQuery<ParseUser> parseQuery=ParseUser.getQuery();
                parseQuery.whereEqualTo("username",productList.get(position));

                parseQuery.getFirstInBackground(new GetCallback<ParseUser>() {
                    @Override
                    public void done(ParseUser object, ParseException e) {
                        if(e==null)
                        {
                            if(object!=null)
                            {
                                final PrettyDialog pd=new PrettyDialog(mCtx);
                                String pname,gender,prof,hobby,bio;
                                if((pname=(object.get("ProfileName")+"")).equals("null"))
                                {
                                    pname=object.getUsername();
                                }
                                if((gender=(object.get("Gender")+"")).equals("null"))
                                    gender="";
                                if((prof=(""+object.get("Profession"))).equals("null"))
                                    prof="";
                                if((hobby=(""+object.get("Hobby"))).equals("null"))
                                    hobby="";
                                if((bio=(""+object.get("Bio"))).equals("null"))
                                    bio="";
                                pd.setTitle(object.getUsername()+" 's Info")
                                        .setMessage("Profile Name: "+pname+
                                                "\nProfession: "+prof+
                                                "\nGender: "+gender+
                                                "\nHobbies: "+hobby+
                                                "\nBio: "+bio)
                                        .setIcon(R.drawable.user)
                                        .addButton("Ok",
                                                R.color.pdlg_color_white,
                                                R.color.pdlg_color_green,
                                                new PrettyDialogCallback() {
                                                    @Override
                                                    public void onClick() {
                                                        pd.dismiss();
                                                    }
                                                })
                                        .show();
                            }
                        }
                    }
                });

                return false;
            }
        });
        



    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView textViewTitle;
        

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textView);
            
        }
    }
}