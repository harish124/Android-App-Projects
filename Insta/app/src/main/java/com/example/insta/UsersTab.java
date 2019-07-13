package com.example.insta;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersTab extends Fragment{

    private RecyclerView recyclerView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    public UsersTab() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View usersView=inflater.inflate(R.layout.fragment_users_tab, container, false);


        arrayList=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,arrayList)
        {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(25);
                textView.setTypeface(null, Typeface.BOLD);


                return view;
            }
        };
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
                        recyclerView = usersView.findViewById(R.id.recyclerView);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        MyAdapter adapter=new MyAdapter(getContext(),arrayList);
                        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });

        return usersView;
    }

}
