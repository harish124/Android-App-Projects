package com.example.timetabe;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private CheckBox ck1,ck2,ck3,ck4,ck5;
    private MediaPlayer player;
    private String deptUrl= "cse_";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    clear();
                    call("clear");      //since there is nothing like 'clear' in my firebase no recylcerview would be formed.
                    deptUrl="cse_";

                    //Toast.makeText(getBaseContext(),"Showing CSE - 'A'",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    clear();
                    call("clear");
                    deptUrl="cseb_";

                    //Toast.makeText(getBaseContext(),"Showing CSE - 'B'",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    clear();
                    call("clear");
                    deptUrl="csesenior_";

                    //Toast.makeText(getBaseContext(),"Showing CSE -'A' Year 3",Toast.LENGTH_SHORT).show();
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

        player= MediaPlayer.create(this,R.raw.waterringtone);



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

                    player.start();
                    if(check())
                        clear();
                    ck1.setChecked(true);
                    if(ck1.isChecked()==true)
                    {
                        ck1.setChecked(true);
                    }
                    call(deptUrl+"mon");
                }
            });
            ck2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    player.start();
                    if(check())
                    {
                        clear();

                    }
                    ck2.setChecked(true);
                    if(ck2.isChecked()==true)
                    {
                        ck2.setChecked(true);
                    }


                    call(deptUrl+"tue");
                }
            });
            ck3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    player.start();
                    if(check())
                        clear();
                    ck3.setChecked(true);
                    if(ck3.isChecked()==true)
                    {
                        ck3.setChecked(true);
                    }
                    call(deptUrl+"wed");
                }
            });
            ck4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    player.start();
                    if(check())
                        clear();
                    ck4.setChecked(true);
                    if(ck4.isChecked()==true)
                    {
                        ck4.setChecked(true);
                    }
                    call(deptUrl+"thu");
                }
            });
            ck5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    player.start();
                    if(check())
                        clear();
                    ck5.setChecked(true);
                    if(ck5.isChecked()==true)
                    {
                        ck5.setChecked(true);
                    }

                    call(deptUrl+"fri");
                }
            });

            String arr[]={"You look\nGreat today","I'm a genius","You are\nbeautiful","TherikaVidalama","Just Conquer\nEverything","Try pressing the Vol-Up\n button twice"};

            Random r =new Random();
            int test=r.nextInt(6);
            Toast.makeText(getBaseContext(),""+arr[test],Toast.LENGTH_SHORT).show();

        }
        catch(Exception e)
        {
            Toast.makeText(getBaseContext(),"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }//eoncreate

    long bp= System.currentTimeMillis(),up=System.currentTimeMillis();
    int volcount=0;
    int volupcount=0;
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    volupcount++;
                    if(volupcount==2)
                    {
                        if((System.currentTimeMillis()-up)<=500)
                        {
                            //call("clear");

                            Toast.makeText(getBaseContext(),"Developed BY: B.Harish",Toast.LENGTH_SHORT).show();
                            Toast.makeText(getBaseContext(),"My LinkedIn Details:\n"+"http://linkedin.com/in/harish-balaji-594b49179",Toast.LENGTH_LONG).show();
                            call("clear");
                        }
                        //Toast.makeText(getBaseContext(),"volume up button pressed twice",Toast.LENGTH_LONG).show();
                        volupcount=0;
                    }
                    if(volupcount>2)
                    {
                        volupcount=0;
                    }
                    if(volupcount==1)
                        up= System.currentTimeMillis();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    volcount++;
                    if(volcount==2)
                    {
                        if((System.currentTimeMillis()-bp)<=500)
                        {
                            clear();
                            deptUrl="csec_";
                            Toast.makeText(getBaseContext(),"Showing CSE - 'C'",Toast.LENGTH_SHORT).show();
                            Toast.makeText(getBaseContext(),"Select any day",Toast.LENGTH_SHORT).show();
                            call("clear");
                        }
                        //Toast.makeText(getBaseContext(),"volume down button pressed twice",Toast.LENGTH_LONG).show();
                        volcount=0;
                    }
                    if(volcount>2)
                    {
                        volcount=0;
                    }
                    if(volcount==1)
                        bp= System.currentTimeMillis();

                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }


}//eclass
