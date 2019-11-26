package com.example.gossip.firebaseStorage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import print.Print;

public class UploadToFirebaseStorage {

    private String imageIdentifier;
    private Print p;
    private Context ctx;
    public UploadToFirebaseStorage(Context ctx)
    {
        this.ctx=ctx;
        p=new Print();
    }

    private void makeUUID()
    {
        imageIdentifier = UUID.randomUUID().toString()+".png";
    }


    public int uploadImageToStorage(ImageView imageView,Bitmap bitmap)
    {
        if(bitmap==null)
        {
            return -1;
        }
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();
        makeUUID();
        UploadTask uploadTask = FirebaseStorage.getInstance()
                .getReference()
                .child("my_images")
                .child(imageIdentifier)
                .putBytes(data);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                p.fprintfl(ctx,"Error: "+e.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                p.sprintf(ctx,"Image uploaded successfully!!!!");
            }
        });
        return 1;
    }

}
