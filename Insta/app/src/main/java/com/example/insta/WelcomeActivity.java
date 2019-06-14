package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;


public class WelcomeActivity extends AppCompatActivity {

    private TextView welcometxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcometxt=findViewById(R.id.welcometxt);

        welcometxt.setText("Welcome "+" "+ ParseUser.getCurrentUser().get("username")+" ! ");



    }

    long bp=System.currentTimeMillis();
    int backCount=0;
    @Override
    public void onBackPressed() {

        if(backCount==0)
        {
            backCount++;
            FancyToast.makeText(WelcomeActivity.this,"Press back Once again\nTo Logout", FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
            bp=System.currentTimeMillis();

        }
        else if(backCount==1)
        {
            backCount=0;
            if((System.currentTimeMillis()-bp)<=1500)
            {
                finish();   //this activity would get over and it would revert back to the activity that called this activity [similar to activities in a stack].
                FancyToast.makeText(WelcomeActivity.this,"Logged Out Successfully!!", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
            }
        }
        else
        {
            backCount=0;
        }

    }
}//eclass
