package com.example.insta;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


//This class is going to help us add the fragments to the appBarLayout

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position)
        {
            case 0:
                return new ProfileTab();
            case 1:
                return new UsersTab();
            case 2:
                return new SharePictureTab();
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 3;   //since we have 3 tabs
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {        //To return the page title
        switch (position)
        {
            case 0:
                return "Profile";
            case 1:
                return "Users";
            case 2:
                return "Share";
            default:return null;
        }
    }
}