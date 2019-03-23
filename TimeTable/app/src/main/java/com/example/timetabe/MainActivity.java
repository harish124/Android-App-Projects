package com.example.timetabe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private CheckBox ck1,ck2,ck3,ck4,ck5;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    void init()
    {
        ck1=(CheckBox)findViewById(R.id.checkBox);
        ck2=(CheckBox)findViewById(R.id.checkBox2);
        ck3=(CheckBox)findViewById(R.id.checkBox3);
        ck4=(CheckBox)findViewById(R.id.checkBox4);
        ck5=(CheckBox)findViewById(R.id.checkBox5);
        mRecyclerView = (RecyclerView)findViewById(R.id.recylclerview_timetable);
    }

    void clear()
    {
        ck1.setChecked(false);
        ck5.setChecked(false);
        ck4.setChecked(false);
        ck3.setChecked(false);
        ck2.setChecked(false);
    }

    public boolean check()
    {
        if(ck1.isChecked()==true||
                ck2.isChecked()==true||
                ck3.isChecked()==true||
                ck4.isChecked()==true||
                ck5.isChecked()==true)
        {
            return true;
        }
        return false;
    }

    void call(String path)
    {

        new FireBaseDatabaseHelper(""+path).readdaily_timetable(new FireBaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<daily_timetable> books, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView,MainActivity.this,books,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }

    //Navigation Code**********************************************************************

    //******************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try
        {
            init();

            //Navigation code below********************************************************************************




            //*****************************************************************************************************

            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


            ck1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(check())
                        clear();
                    ck1.setChecked(true);
                    if(ck1.isChecked()==true)
                    {
                        ck1.setChecked(true);
                    }
                    call("cse_mon");
                }
            });
            ck2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(check())
                    {
                        clear();

                    }
                    ck2.setChecked(true);
                    if(ck2.isChecked()==true)
                    {
                        ck2.setChecked(true);
                    }


                    call("cse_tue");
                }
            });
            ck3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(check())
                        clear();
                    ck3.setChecked(true);
                    if(ck3.isChecked()==true)
                    {
                        ck3.setChecked(true);
                    }
                    call("cse_wed");
                }
            });
            ck4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(check())
                        clear();
                    ck4.setChecked(true);
                    if(ck4.isChecked()==true)
                    {
                        ck4.setChecked(true);
                    }
                    call("cse_thu");
                }
            });
            ck5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(check())
                        clear();
                    ck5.setChecked(true);
                    if(ck5.isChecked()==true)
                    {
                        ck5.setChecked(true);
                    }

                    call("cse_fri");
                }
            });


        }
        catch(Exception e)
        {
            Toast.makeText(getBaseContext(),"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }//eoncreate


}//eclass
