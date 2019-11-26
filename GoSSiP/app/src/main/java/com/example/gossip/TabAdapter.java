package com.example.gossip;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gossip.users_tab.Users;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {
        switch (tabPosition)
        {
            case 0:
                Post p =new Post();
                return p;
            case 1:
                return new Users();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {        //To return the page title
        switch (position)
        {
            case 0:
                return "Post";
            case 1:
                return "Users";
            case 2:
                return "Share";
            default:return null;
        }
    }

}
