package com.example.gossip;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.gossip.firebaseStorage.UploadToFirebaseStorage;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import print.Print;

public class SocialMediaActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public Print p;
    private ViewPager viewPager;
    private TabAdapter tabAdapter;
    private TabLayout tabLayout;

    void init()
    {
        mAuth = FirebaseAuth.getInstance();
        viewPager = findViewById(R.id.viewPager);
        tabAdapter=new TabAdapter(getSupportFragmentManager());

        tabLayout=findViewById(R.id.tabLayout);

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager,true);

        p = new Print();
    }



    void logOut()
    {
        try{
            mAuth.signOut();
            p.sprintf(SocialMediaActivity.this,"Logged Out Successfully");
        }
        catch (Exception e)
        {
            p.fprintf(SocialMediaActivity.this,"Error: "+e.getMessage());
        }
        finish();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
        init();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logOut();
                p.sprintf(SocialMediaActivity.this, "Logged Out Successfully");

        }
        return true;
    }


    @Override
    public void onBackPressed() {
        logOut();
        super.onBackPressed();
    }



}//eclass



