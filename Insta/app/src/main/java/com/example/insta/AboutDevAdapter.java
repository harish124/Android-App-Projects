package com.example.insta;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class AboutDevAdapter extends RecyclerView.Adapter<AboutDevAdapter.ProductViewHolder> {
    private Context mCtx;

    //we are storing all the products in a list
    private ArrayList<String> productList;

    //getting the context and product list with constructor
    public AboutDevAdapter(Context mCtx, ArrayList<String> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_view_row_about_dev, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder,final int position) {
        //getting the product of the specified position
        String product = productList.get(position);

        //binding the data with the viewholder views

            holder.textViewTitle.setText(product);
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