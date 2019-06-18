package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class UsersPost extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_post);

        linearLayout=findViewById(R.id.linearLayout);

        Intent receivedIntentObject=getIntent();
        final String userName= receivedIntentObject.getStringExtra("Username");
        if(ParseUser.getCurrentUser().getUsername().equals(userName))
        {
            FancyToast.makeText(UsersPost.this,"Displaying: Your Posts",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
        }
        else
        {
            FancyToast.makeText(UsersPost.this,"Displaying: "+userName+ "'s Posts",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
        }


        setTitle(userName+"'s Posts");

        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("SharedPictures");
        parseQuery.whereEqualTo("username",userName);
        parseQuery.orderByDescending("createdAt");

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null)
                {
                    if(objects.size()>0)
                    {
                        for(ParseObject post:objects)
                        {
                            final TextView imageDes=new TextView(UsersPost.this);
                            imageDes.setText("Description: "+post.get("ImageDescription"));
                            ParseFile postPicture=(ParseFile)post.get("Picture");
                            postPicture.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e==null)
                                    {
                                        if(data!=null)
                                        {
                                            Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                                            ImageView postImageView=new ImageView(UsersPost.this);

                                            //Setting imageView Params
                                            LinearLayout.LayoutParams imageView_params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                            postImageView.setLayoutParams(imageView_params);
                                            postImageView.setScaleType(ImageView.ScaleType.FIT_START);
                                            postImageView.setAdjustViewBounds(true);
                                            postImageView.setImageBitmap(bitmap);
                                            postImageView.setBackgroundResource(android.R.color.transparent);
                                            //Setting Image_Description Params
                                            LinearLayout.LayoutParams desc_params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                            desc_params.setMargins(5,10,5,10);

                                            imageDes.setLayoutParams(desc_params);
                                            imageDes.setGravity(Gravity.CENTER);
                                            imageDes.setTextColor(Color.WHITE);
                                            imageDes.setBackgroundColor(Color.BLUE);
                                            imageDes.setTextSize(22f);

                                            //Adding these 2 views into our layout
                                            linearLayout.addView(postImageView);
                                            linearLayout.addView(imageDes);


                                        }

                                    }
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    }
                    else
                    {
                        FancyToast.makeText(UsersPost.this,"No Posts Yet",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                        finish();
                    }
                }
            }
        });
    }
}
