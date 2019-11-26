package com.example.timetable2.compClassInterface;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.timetable2.MyAdapter;
import com.example.timetable2.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

import static com.parse.Parse.getApplicationContext;


//Using factoryDesign Pattern
interface Details
{
    ArrayList<String> subjects = new ArrayList<>();
    ArrayList<String> teachers = new ArrayList<>();
    public void getSubNameTeachersName();
    public void clear();

}

class Csea implements Details
{
    RecyclerView recyclerView;
    Context ctx;
    String day;
    MyAdapter adapter;
    Csea(Context ctx, RecyclerView recyclerView,String day)
    {
        this.recyclerView = recyclerView;
        this.ctx = ctx;
        this.day=day;
    }

    public void getTeacherName(String subName)
    {
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Teachers");
        parseQuery.whereEqualTo("subName",subName);
        parseQuery.whereEqualTo("sec","A");
        //Toast.makeText(ctx,"In getTeachers",Toast.LENGTH_SHORT).show();

        try
        {
            ParseObject obj = parseQuery.getFirst();
            if(obj!=null)
            {
                teachers.add((""+obj.get("teacherName")));
            }
            else
            {
                teachers.add("No teacher found");
            }
        }
        catch (Exception e)
        {
            teachers.add("No teacher found");
            //Toast.makeText(ctx,"Error in fetching Teacher details\n"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void getSubNameTeachersName()
    {

        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("TimeTable");
        //parseQuery.whereEqualTo("day",day);
        //parseQuery.whereEqualTo("teacherName","Harish");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e==null && objects!=null)
                {
                    if(objects.size()>0)
                    {
                        // = new MyAdapter(getApplicationContext(),subjects,teachers);
                        //Toast.makeText(ctx,"Size: "+subjects.size(),Toast.LENGTH_LONG).show();
                        if(subjects.size()>0)
                        {
                            subjects.clear();
                            teachers.clear();
                            //adapter.notifyDataSetChanged();
                        }
                        for(ParseObject obj:objects)
                        {
                            subjects.add(""+obj.get("subName"));
                            getTeacherName(""+obj.get("subName"));
                            //teachers.add(""+obj.get("teacherName"));
                        }
                        //Toast.makeText(ctx,"Size: "+subjects.size()+"\nTesize: "+teachers.size(),Toast.LENGTH_LONG).show();
                        //First clear the recycler view
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
                        recyclerView.setItemAnimator(new SlideInUpAnimator());
                        adapter = new MyAdapter(ctx,subjects,teachers,'A');
                        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(adapter);


                    }
                    else
                    {
                        Toast.makeText(ctx,"Error: Obj size < 0",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(ctx,"Error: Wifi/Mobile Data is disabled",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void clear() {
        subjects.clear();
        teachers.clear();
        adapter = new MyAdapter(ctx,subjects,teachers,'C');
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }


}

class Cseb implements Details
{
    RecyclerView recyclerView;
    Context ctx;
    String day;
    MyAdapter adapter;

    Cseb(Context ctx, RecyclerView recyclerView,String day)
    {
        this.recyclerView = recyclerView;
        this.ctx = ctx;
        this.day=day;
    }

    public void getTeacherName(String subName)
    {
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Teachers");
        parseQuery.whereEqualTo("subName",subName);
        parseQuery.whereEqualTo("sec","A");
        //Toast.makeText(ctx,"In getTeachers",Toast.LENGTH_SHORT).show();

        try
        {
            ParseObject obj = parseQuery.getFirst();
            if(obj!=null)
            {
                teachers.add((""+obj.get("teacherName")));
            }
            else
            {
                teachers.add("No teacher found");
            }
        }
        catch (Exception e)
        {
            teachers.add("No teacher found");
            //Toast.makeText(ctx,"Error in fetching Teacher details\n"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void getSubNameTeachersName()
    {

        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("TimeTable");
        //parseQuery.whereEqualTo("day",day);
        //parseQuery.whereEqualTo("teacherName","Harish");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e==null && objects!=null)
                {
                    if(objects.size()>0)
                    {
                        // = new MyAdapter(getApplicationContext(),subjects,teachers);
                        //Toast.makeText(ctx,"Size: "+subjects.size(),Toast.LENGTH_LONG).show();
                        if(subjects.size()>0)
                        {
                            subjects.clear();
                            teachers.clear();
                            //adapter.notifyDataSetChanged();
                        }
                        for(ParseObject obj:objects)
                        {
                            subjects.add(""+obj.get("subName"));
                            getTeacherName(""+obj.get("subName"));
                            //teachers.add(""+obj.get("teacherName"));
                        }
                        //Toast.makeText(ctx,"Size: "+subjects.size()+"\nTesize: "+teachers.size(),Toast.LENGTH_LONG).show();
                        //First clear the recycler view
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
                        recyclerView.setItemAnimator(new SlideInUpAnimator());
                        adapter = new MyAdapter(ctx,subjects,teachers,'B');
                        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(adapter);


                    }
                    else
                    {
                        Toast.makeText(ctx,"Error: Obj size < 0",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(ctx,"Error: Wifi/Mobile Data is disabled",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void clear() {
        subjects.clear();
        teachers.clear();
        adapter = new MyAdapter(ctx,subjects,teachers,'C');
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }


}

class Csec implements Details
{

    RecyclerView recyclerView;
    Context ctx;
    String day;
    MyAdapter adapter;
    Csec(Context ctx, RecyclerView recyclerView,String day)
    {
        this.recyclerView = recyclerView;
        this.ctx = ctx;
        this.day=day;
    }

    public void getTeacherName(String subName)
    {
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Teachers");
        parseQuery.whereEqualTo("subName",subName);
        parseQuery.whereEqualTo("sec","A");
        //Toast.makeText(ctx,"In getTeachers",Toast.LENGTH_SHORT).show();

        try
        {
            ParseObject obj = parseQuery.getFirst();
            if(obj!=null)
            {
                teachers.add((""+obj.get("teacherName")));
            }
            else
            {
                teachers.add("No teacher found");
            }
        }
        catch (Exception e)
        {
            teachers.add("No teacher found");
            //Toast.makeText(ctx,"Error in fetching Teacher details\n"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void getSubNameTeachersName()
    {

        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("TimeTable");
        //parseQuery.whereEqualTo("day",day);
        //parseQuery.whereEqualTo("teacherName","Harish");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e==null && objects!=null)
                {
                    if(objects.size()>0)
                    {
                        // = new MyAdapter(getApplicationContext(),subjects,teachers);
                        //Toast.makeText(ctx,"Size: "+subjects.size(),Toast.LENGTH_LONG).show();
                        if(subjects.size()>0)
                        {
                            subjects.clear();
                            teachers.clear();
                            //adapter.notifyDataSetChanged();
                        }
                        for(ParseObject obj:objects)
                        {
                            subjects.add(""+obj.get("subName"));
                            getTeacherName(""+obj.get("subName"));
                            //teachers.add(""+obj.get("teacherName"));
                        }
                        //Toast.makeText(ctx,"Size: "+subjects.size()+"\nTesize: "+teachers.size(),Toast.LENGTH_LONG).show();
                        //First clear the recycler view
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
                        recyclerView.setItemAnimator(new SlideInUpAnimator());
                        adapter = new MyAdapter(ctx,subjects,teachers,'C');
                        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(adapter);


                    }
                    else
                    {
                        Toast.makeText(ctx,"Error: Obj size < 0",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(ctx,"Error: Wifi/Mobile Data is disabled",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void clear() {
        subjects.clear();
        teachers.clear();
        adapter = new MyAdapter(ctx,subjects,teachers,'C');
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }
}

public class Cse {
    String className;
    RecyclerView recyclerView;
    Context ctx;
    String day;
    public Cse(Context ctx,String className, RecyclerView recyclerView,String day)
    {
        this.className = className;
        this.recyclerView = recyclerView;
        this.ctx = ctx;
        this.day = day;
    }
    Details d;

    public void resolveSection()
    {
        if(className.equalsIgnoreCase("csea"))
        {
            d=new Csea(ctx,recyclerView,day);
        }
        else if(className.equalsIgnoreCase("cseb"))
        {
            d=new Cseb(ctx,recyclerView,day);
        }
        else
        {
            d = new Csec(ctx,recyclerView,day);
        }

    }
    public void fetchDetails()
    {
        d.getSubNameTeachersName();
    }
    public void clear()
    {
        d.clear();
    }
}//ecse
