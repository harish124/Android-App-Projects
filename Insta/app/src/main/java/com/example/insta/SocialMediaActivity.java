package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SocialMediaActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;      //modified line for androidx
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabAdapter tabAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialmedia);
        setTitle("Social Media Page!!!");

        toolbar=findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        viewPager=findViewById(R.id.viewPager);

        tabAdapter=new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        tabLayout=findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager,true);






    }

    long bp=System.currentTimeMillis();
    int backCount=0;
    @Override
    public void onBackPressed() {

        if(backCount==0)
        {
            backCount++;
            FancyToast.makeText(SocialMediaActivity.this,"Press back Once again\nTo Logout", FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
            bp=System.currentTimeMillis();

        }
        else if(backCount==1)
        {
            backCount=0;
            if((System.currentTimeMillis()-bp)<=1500)
            {
                finish();   //this activity would get over and it would revert back to the activity that called this activity [similar to activities in a stack].
                FancyToast.makeText(SocialMediaActivity.this,"Logged Out Successfully!!", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
            }
        }
        else
        {
            backCount=0;
        }

    }
}//eclass
