package com.example.insta;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText pname,gender,profession,hobby,bio;
    private Button updateBtn;
    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View profileView = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        pname = profileView.findViewById(R.id.pname);
        gender = profileView.findViewById(R.id.gender);
        profession = profileView.findViewById(R.id.profession);
        hobby = profileView.findViewById(R.id.hobby);
        bio = profileView.findViewById(R.id.bio);
        updateBtn = profileView.findViewById(R.id.updateBtn);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if(parseUser.get("ProfileName")!=null)
        {
            pname.setText("Profile Name: "+parseUser.get("ProfileName")+"");
        }
        if(parseUser.get("Gender")!=null)
            gender.setText("Gender: "+parseUser.get("Gender")+"");
        if(parseUser.get("Profession")!=null)
            profession.setText("Profession: "+parseUser.get("Profession")+"");
        if(parseUser.get("Hobby")!=null)
            hobby.setText("Hobby: "+parseUser.get("Hobby")+"");
        if(parseUser.get("Bio")!=null)
            bio.setText("Bio: "+parseUser.get("Bio")+"");

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("ProfileName", pname.getText().toString().trim() + "");
                parseUser.put("Gender", gender.getText().toString().toUpperCase().charAt(0) + "");
                parseUser.put("Profession", profession.getText().toString().trim() + "");
                parseUser.put("Hobby", hobby.getText().toString().trim() + "");
                parseUser.put("Bio", bio.getText().toString().trim() + "");

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            //when there is no error
                            FancyToast.makeText(getContext(), "Updated Your Profile!!!", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                        }
                        else
                        {
                            FancyToast.makeText(getContext(), "Error in Updating Your Profile\nError: "+e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });


            }
        });

        return profileView;

    }


}
