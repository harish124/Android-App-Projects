package com.example.timetable2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timetable2.compClassInterface.Cse;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CheckBox ck1,ck2,ck3,ck4,ck5;
    public StringBuilder sec;
    public Menu menu;

    public void init()
    {
        ck1=findViewById(R.id.checkBox);
        ck5=findViewById(R.id.checkBox5);
        ck4=findViewById(R.id.checkBox4);
        ck3=findViewById(R.id.checkBox3);
        ck2=findViewById(R.id.checkBox2);
        recyclerView = findViewById(R.id.recylclerview_timetable);
        sec = new StringBuilder("csea");
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
    }//echeck

    public void clear()
    {
        ck1.setChecked(false);
        ck5.setChecked(false);
        ck4.setChecked(false);
        ck3.setChecked(false);
        ck2.setChecked(false);
    }//eclear

    public void enableCkBox()
    {
        ck1.setEnabled(true);
        ck2.setEnabled(true);
        ck3.setEnabled(true);
        ck4.setEnabled(true);
        ck5.setEnabled(true);
    }
    public void disableCkBox()
    {
        ck1.setEnabled(false);
        ck2.setEnabled(false);
        ck3.setEnabled(false);
        ck4.setEnabled(false);
        ck5.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu,menu);
        this.menu=menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuItem label = menu.findItem(R.id.label);


        switch (item.getItemId())
        {
            case R.id.csea:
                //Toast.makeText(MainActivity.this,"Opt 1 selected",Toast.LENGTH_SHORT).show();
                label.setTitle("CSE - A");
                sec = new StringBuilder("csea");
                break;
            case R.id.cseb:
                label.setTitle("CSE - B");
                sec = new StringBuilder("cseb");
                break;
            case R.id.csec:
                label.setTitle("CSE - C");
                sec = new StringBuilder("csec");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        //Toast.makeText(MainActivity.this,new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()),Toast.LENGTH_SHORT).show();
        if(new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()).equalsIgnoreCase("mon"))
        {
            ck1.setChecked(true);
        }
        if(new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()).equalsIgnoreCase("tue"))
        {
            ck2.setChecked(true);
        }
        if(new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()).equalsIgnoreCase("wed"))
        {
            ck3.setChecked(true);
        }
        if(new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()).equalsIgnoreCase("thu"))
        {
            ck4.setChecked(true);
        }
        if(new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()).equalsIgnoreCase("fri"))
        {
            ck5.setChecked(true);
        }

        Cse dept = new Cse(MainActivity.this,sec.toString(),recyclerView,new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()));
        dept.resolveSection();
        dept.clear();
        dept.fetchDetails();
        try
        {

            ck1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //getSubNameTeachersName();
                    if(check())
                    {
                        clear();
                    }
                    ck1.setChecked(true);
                    disableCkBox();
                    Cse dept = new Cse(MainActivity.this,sec.toString(),recyclerView,"mon");
                    dept.resolveSection();
                    dept.clear();
                    dept.fetchDetails();
                    enableCkBox();


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
                    disableCkBox();
                    Cse dept = new Cse(MainActivity.this,sec.toString(),recyclerView,"tue");

                    dept.resolveSection();

                    dept.clear();
                    dept.fetchDetails();
                    enableCkBox();

                }
            });

            ck3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(check())
                    {
                        clear();
                    }

                    ck3.setChecked(true);
                    disableCkBox();
                    Cse dept = new Cse(MainActivity.this,sec.toString(),recyclerView,"wed");
                    dept.resolveSection();
                    dept.clear();
                    dept.fetchDetails();
                    enableCkBox();
                }
            });

            ck4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(check())
                    {
                        clear();
                    }

                    ck4.setChecked(true);
                    disableCkBox();
                    Cse dept = new Cse(MainActivity.this,sec.toString(),recyclerView,"thu");
                    dept.resolveSection();
                    dept.clear();
                    dept.fetchDetails();
                    enableCkBox();
                }
            });

            ck5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(check())
                    {
                        clear();
                    }

                    ck5.setChecked(true);
                    disableCkBox();
                    Cse dept = new Cse(MainActivity.this,sec.toString(),recyclerView,"fri");
                    dept.resolveSection();
                    dept.clear();
                    dept.fetchDetails();
                    enableCkBox();
                }
            });



        }
        catch(Exception e)
        {
            Toast.makeText(getBaseContext(),"Error: "+e.getMessage()+"\nCause: "+e.getCause(),Toast.LENGTH_LONG).show();
        }


        }



}
