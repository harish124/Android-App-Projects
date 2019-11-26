package com.example.gossip.users_tab;


import android.Manifest;
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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gossip.R;
import com.example.gossip.sign_up.MyUsers;
import com.example.gossip.users_tab.RecyclerViewAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class Users extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<MyUsers> myUsersArrayList;
    private RecyclerViewAdapter adapter;
    private Button fetchUsersBtn;
    private Bitmap bitmap;  //when shareIcon is pressed
    public Users() {
        // Required empty public constructor
    }

    void init(View view)
    {
        recyclerView=view.findViewById(R.id.users_recycler_view);
        myUsersArrayList=new ArrayList<>();
        fetchUsersBtn=view.findViewById(R.id.fetchUsersBtn);
        adapter=new RecyclerViewAdapter(myUsersArrayList);
        SnapHelper snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);  //this gives a snap to center effect

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View usersView=inflater.inflate(R.layout.fragment_users, container, false);
        init(usersView);

        fetchUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                recyclerView.setAdapter(adapter);
                fetchUsers();
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));




            }
        });


        return usersView;
    }



    void fetchUsers()
    {
        myUsersArrayList.clear();
        FirebaseDatabase.getInstance()
                .getReference()
                .child("my_users")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        MyUsers user=new MyUsers();


                        user.setUserName(dataSnapshot.child("userName").getValue().toString());
                        user.setemail(dataSnapshot.child("email").getValue().toString());
                        user.setUniqueId(dataSnapshot.child("uniqueId").getValue().toString());


                        myUsersArrayList.add(user);
                        adapter.notifyDataSetChanged();




                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }//efetchUsers

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




}//eusers
