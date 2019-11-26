package com.example.gossip;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gossip.firebaseStorage.UploadToFirebaseStorage;

import print.Print;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class Post extends Fragment implements View.OnClickListener{

    private ImageView imgPost;
    private TextView desc;
    private Bitmap bitmap;
    private Button uploadBtn;
    private Print p;
    public Post() {
        // Required empty public constructor
    }

    void init(View p)
    {
        imgPost=p.findViewById(R.id.post);
        desc=p.findViewById(R.id.desc);
        uploadBtn=p.findViewById(R.id.uploadButton);
        this.p=new Print();
    }
    void addListeners()
    {
        imgPost.setOnClickListener(this);
        desc.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View postImageView = inflater.inflate(R.layout.fragment_post, container, false);

        init(postImageView);
        addListeners();


        return postImageView;
    }

    public void selectImage()
    {
        if(Build.VERSION.SDK_INT<23)
        {
            Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,1000);
        }
        else if(Build.VERSION.SDK_INT>=23)
        {
            if(ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                },1000);
            }

            else
            {
                //if already permissions were graanted
                Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1000);

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1000 &&
                resultCode == RESULT_OK
                    && data!=null)
        {
            Uri choosenImage = data.getData();

            try
            {
                //p.sprintf(getContext(),"Reached here");
                bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),choosenImage);
                p.fprintf(getContext(),"Bitmap: "+bitmap);
                imgPost.setImageBitmap(bitmap);
                imgPost.setBackground(null);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1000 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            selectImage();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.post:
                selectImage();
                break;
            case R.id.uploadButton:
                int status = new UploadToFirebaseStorage(getContext()).uploadImageToStorage(imgPost,bitmap);
                ProgressDialog pd = new ProgressDialog(getContext());
                pd.setMessage("Uploading Image\nPls. wait...");
                pd.setCancelable(false);
                pd.show();
                imgPost.setClickable(false);
                desc.setEnabled(false);
                desc.setText("Uploading in background...");
                if(status ==-1 || status==1)
                {
                    //When irrespective of image is successfull or not we have to enable things
                    //back to their original states
                    imgPost.setClickable(true);
                    desc.setEnabled(true);
                    desc.setText(status==1?"Upload was successful":"Upload failed");
                    pd.dismiss();
                }
        }
    }


}//ePostFragment
