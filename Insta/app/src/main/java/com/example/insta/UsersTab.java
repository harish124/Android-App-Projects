package com.example.insta;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersTab extends Fragment {


    private ListView listView;
    private ArrayList arrayList;
    private ArrayAdapter arrayAdapter;
    public UsersTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View usersView=inflater.inflate(R.layout.fragment_users_tab, container, false);

        listView=usersView.findViewById(R.id.listView);
        arrayList=new ArrayList();
        arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);
        ParseQuery<ParseUser> parseQuery=ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());        //Find all other users.

        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if(e==null)
                {
                    if(users.size()>0)
                    {
                        for(ParseUser x:users)
                        {
                            arrayList.add(x.getUsername());
                            //Toast.makeText(getContext(),"Name: "+x.getUsername(),Toast.LENGTH_SHORT).show();

                        }
                        listView.setAdapter(arrayAdapter);
                    }
                }
            }
        });

        return usersView;
    }

}
