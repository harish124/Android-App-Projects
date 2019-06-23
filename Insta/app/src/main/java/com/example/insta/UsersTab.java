package com.example.insta;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
public class UsersTab extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {


    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    public UsersTab() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View usersView=inflater.inflate(R.layout.fragment_users_tab, container, false);

        listView=usersView.findViewById(R.id.listView);
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

        listView.setOnItemClickListener(UsersTab.this);
        listView.setOnItemLongClickListener(UsersTab.this);

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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getContext(),UsersPost.class);
        intent.putExtra("Username",arrayList.get(position));
        startActivity(intent);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        ParseQuery<ParseUser> parseQuery=ParseUser.getQuery();
        parseQuery.whereEqualTo("username",arrayList.get(position));
        parseQuery.getFirstInBackground(new GetCallback<ParseUser>() {
             @Override
             public void done(ParseUser object, ParseException e) {
                if(e==null)
                {
                    if(object!=null)
                    {
                        final PrettyDialog pd=new PrettyDialog(getContext());
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



}
