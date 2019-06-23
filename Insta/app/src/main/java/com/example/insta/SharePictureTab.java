package com.example.insta;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class SharePictureTab extends Fragment implements View.OnClickListener{


    private ImageView imageView,myPosts;
    private EditText editText;
    private Button sharebtn;
    private Bitmap receivedImageBitmap;
    public SharePictureTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View shareView=inflater.inflate(R.layout.fragment_share_picture_tab, container, false);
        imageView=shareView.findViewById(R.id.imageView);
        myPosts=shareView.findViewById(R.id.myPosts);
        editText=shareView.findViewById(R.id.editText);
        sharebtn=shareView.findViewById(R.id.sharebtn);

        imageView.setOnClickListener(SharePictureTab.this);
        sharebtn.setOnClickListener(SharePictureTab.this);
        myPosts.setOnClickListener(SharePictureTab.this);

        retrieveProfilePic();

        return shareView;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.imageView:
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
            case R.id.sharebtn:
                if(receivedImageBitmap!=null)
                {
                    sharebtn.setClickable(false);
                    sharebtn.setText("Uploading...");
                    sharebtn.setEnabled(false);
                    if(editText.getText().toString().equals(""))
                    {
                        FancyToast.makeText(getContext(),"You can't leave description empty\nType Something",FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                    }
                    else
                    {
                        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                        receivedImageBitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                        byte[] bytes=byteArrayOutputStream.toByteArray();
                        ParseFile parseFile=new ParseFile("img.png",bytes);
                        ParseObject parseObject=new ParseObject("SharedPictures");
                        parseObject.put("Picture",parseFile);
                        parseObject.put("ImageDescription",editText.getText().toString());
                        parseObject.put("username", ParseUser.getCurrentUser().getUsername());
                        final ProgressDialog progressDialog=new ProgressDialog(getContext());
                        progressDialog.setMessage("Uploading...");
                        progressDialog.show();
                        progressDialog.setCanceledOnTouchOutside(false);
                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e==null)
                                {
                                    FancyToast.makeText(getContext(),"Image Shared Successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                                }
                                else
                                {
                                    FancyToast.makeText(getContext(),"Image Upload Failed\nError: "+e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                                }
                                progressDialog.dismiss();
                                sharebtn.setClickable(true);
                                sharebtn.setText("SHARE");
                                sharebtn.setEnabled(true);
                            }
                        });

                    }
                }
                break;
            case R.id.myPosts:
                Intent intent=new Intent(getContext(),UsersPost.class);
                intent.putExtra("Username",ParseUser.getCurrentUser().getUsername());
                startActivity(intent);
                break;
        }
    }

    private void getChoosenImage()
    {
        //FancyToast.makeText(getContext(),"Now you can access images",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
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
                    imageView.setImageBitmap(receivedImageBitmap);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void retrieveProfilePic() {
        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("ProfilePictures");
        parseQuery.whereEqualTo("username",ParseUser.getCurrentUser().getUsername());
        //parseQuery.orderByDescending("createdAt");
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
                                        myPosts.setImageBitmap(bitmap);
                                        //myPosts.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                        //myPosts.setAdjustViewBounds(true);
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }

}//eclass
