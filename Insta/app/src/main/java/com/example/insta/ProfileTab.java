package com.example.insta;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.ByteArrayOutputStream;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment implements View.OnClickListener {

    private EditText pname,gender,profession,hobby,bio;
    private Button updateBtn;
    private ImageView profilePic;
    private Bitmap receivedImageBitmap;
    private Button profileUpdateBtn;


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
        profilePic=profileView.findViewById(R.id.profilePic);
//        profilePic.setAdjustViewBounds(true);
        profileUpdateBtn=profileView.findViewById(R.id.profileUpdateBtn);
        profilePic.setOnClickListener(ProfileTab.this);
        profileUpdateBtn.setOnClickListener(ProfileTab.this);
        profileUpdateBtn.setVisibility(View.INVISIBLE);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        retrieveProfilePic();

        if(parseUser.get("ProfileName")!=null)
        {
            pname.setText(parseUser.get("ProfileName")+"");
        }
        if(parseUser.get("Gender")!=null)
            gender.setText(parseUser.get("Gender")+"");
        if(parseUser.get("Profession")!=null)
            profession.setText(parseUser.get("Profession")+"");
        if(parseUser.get("Hobby")!=null)
            hobby.setText(parseUser.get("Hobby")+"");
        if(parseUser.get("Bio")!=null)
            bio.setText(parseUser.get("Bio")+"");

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBtn.setText("Updating...");
                updateBtn.setEnabled(false);
                updateBtn.setClickable(false);
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

                updateBtn.setText("UPDATE PROFILE");
                updateBtn.setEnabled(true);
                updateBtn.setClickable(true);
            }
        });

        return profileView;

    }

    private void retrieveProfilePic() {
        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("ProfilePictures");
        parseQuery.whereEqualTo("username",ParseUser.getCurrentUser().getUsername());
        parseQuery.orderByDescending("createdAt");
        parseQuery.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null)
                {
                    if(object!=null)
                    {
                        final ParseFile profilePicture=(ParseFile)object.get("Picture");
                        profilePicture.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if(e==null)
                                {
                                    if (data != null)
                                    {
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        profilePic.setImageBitmap(bitmap);
//                                        profilePic.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.profilePic:
                if(android.os.Build.VERSION.SDK_INT>=23 &&
                        ActivityCompat.checkSelfPermission(getContext(),
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]
                                    {Manifest.permission.READ_EXTERNAL_STORAGE},
                            1000);
                }
                else
                {
                    getChoosenImage();
                }
                break;
            case R.id.profileUpdateBtn:
                //Now upload profile pic to the server
                if(receivedImageBitmap!=null)
                {
                    profileUpdateBtn.setEnabled(false);
                    //FancyToast.makeText(getContext(),"Entered this...",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                    final ProgressDialog progressDialog=new ProgressDialog(getContext());
                    progressDialog.setMessage("Uploading Pic...");
                    progressDialog.show();
                    progressDialog.setCanceledOnTouchOutside(false);
                    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                    receivedImageBitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                    byte[] bytes=byteArrayOutputStream.toByteArray();
                    ParseFile parseFile=new ParseFile("profilePic.png",bytes);


                    ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("ProfilePictures");
                    parseQuery.whereEqualTo("username",ParseUser.getCurrentUser().getUsername());
                    parseQuery.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {

                            if(e==null)
                            {
                                if(objects.size()>0)
                                {
                                    for(ParseObject object:objects)
                                    {
                                        object.deleteInBackground(new DeleteCallback() {
                                            @Override
                                            public void done(ParseException e) {
                                                if(e==null)
                                                {
                                                    //FancyToast.makeText(getContext(),"Profile Picture Deletion Successfull",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                                                }
                                                else
                                                {
                                                    //FancyToast.makeText(getContext(),"Profile Picture Deletion Unsuccessfull\nError: "+e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            }

                        }
                    });

                    //FancyToast.makeText(getContext(),"Entered Deletion Stage",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

                    ParseObject parseObject=new ParseObject("ProfilePictures");
                    parseObject.put("username", ParseUser.getCurrentUser().getUsername());
                    parseObject.put("Picture",parseFile);
                    //FancyToast.makeText(getContext(),"Entered Updating Stage",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null)
                            {
                                FancyToast.makeText(getContext(),"Profile Picture Updated Successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                                profileUpdateBtn.setEnabled(true);
                                profileUpdateBtn.setVisibility(View.INVISIBLE);
                            }
                            else
                            {
                                FancyToast.makeText(getContext(),"Profile Picture Upload Failed",FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                                profileUpdateBtn.setEnabled(true);
                            }
                        }
                    });
                    progressDialog.dismiss();
                }
                else
                {
                    FancyToast.makeText(getContext(),"This Profile Pic can't\nbe uploaded due to\nTechnical reasons",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    FancyToast.makeText(getContext(),"Try a different Picture",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                    profileUpdateBtn.setVisibility(View.INVISIBLE);
                    profileUpdateBtn.setEnabled(true);
                }

                break;
        }
    }

    private void getChoosenImage() {
        Intent intent=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==1000)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                getChoosenImage();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2000)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try
                {
                    Uri selectedImage=data.getData();
                    String[] filePathColumn={MediaStore.Images.Media.DATA};
                    Cursor cursor=getActivity().getContentResolver().query(selectedImage,
                            filePathColumn,null,null,null);
                    cursor.moveToFirst();
                    int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath=cursor.getString(columnIndex);
                    cursor.close();
                    receivedImageBitmap= BitmapFactory.decodeFile(picturePath);
                    profilePic.setImageBitmap(receivedImageBitmap);
                    profileUpdateBtn.setVisibility(View.VISIBLE);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
