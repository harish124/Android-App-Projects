package com.example.timetable2;

import android.content.Context;

import android.graphics.Color;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

import static com.parse.Parse.getApplicationContext;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ProductViewHolder> {
    private Context mCtx;

    //we are storing all the products in a list
    private ArrayList<String> productList;
    private ArrayList<String> teachers;
    public char sec;

    //getting the context and product list with constructor
    public MyAdapter(Context mCtx, ArrayList<String> productList,ArrayList<String> teachers,char sec) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.teachers = teachers;
        this.sec = sec;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_view_row, null);
        return new ProductViewHolder(view,sec);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder,final int position) {
        //getting the product of the specified position
        String product = productList.get(position);
        String teacherName = teachers.get(position);
        if(product.toLowerCase().contains("lab"))
        {
            holder.dropbtn.setBackgroundResource(R.drawable.ic_info_outline_black_24dp);
        }
        //binding the data with the viewholder views
        holder.textViewTitle.setText(product);
        holder.teacherName.setText(teacherName);

        Calendar cal = Calendar.getInstance();

        //24 hour format
        int hourofday = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        int time=0;
        time = (hourofday * 100) + minute;
        //Toast.makeText(mCtx,"time = "+position,Toast.LENGTH_SHORT).show();

        if(position == 0)
        {
            if ((800 <= time) && (time <= 850)) {

            holder.teacherName.setTextColor(Color.RED);
            holder.textViewTitle.setTextColor(Color.RED);
            holder.time.setTextColor(Color.RED);
            holder.cardView.setCardBackgroundColor(Color.BLACK);
            holder.syllabus.setTextColor(Color.RED);
        }

        }

        if(position==1)
        {
            if((851 <= time) && (time <= 940))
            {
                holder.teacherName.setTextColor(Color.RED);
                holder.textViewTitle.setTextColor(Color.RED);
                holder.time.setTextColor(Color.RED);
                holder.cardView.setCardBackgroundColor(Color.parseColor("#000000"));
                holder.dropbtn.setBackgroundResource(R.drawable.ic_info_black_24dp);
                holder.syllabus.setTextColor(Color.RED);
            }
        }

        if(position==2)
        {
            if((941 <= time) && (time <= 1030))
            {
                holder.teacherName.setTextColor(Color.RED);
                holder.textViewTitle.setTextColor(Color.RED);
                holder.time.setTextColor(Color.RED);
                holder.cardView.setCardBackgroundColor(Color.parseColor("#000000"));
                holder.dropbtn.setBackgroundResource(R.drawable.ic_info_black_24dp);
                holder.syllabus.setTextColor(Color.RED);
            }
        }

        if(position==3)
        {
            if((1041 <= time) && (time <= 1130))
            {
                holder.teacherName.setTextColor(Color.RED);
                holder.textViewTitle.setTextColor(Color.RED);
                holder.time.setTextColor(Color.RED);
                holder.cardView.setCardBackgroundColor(Color.parseColor("#000000"));
                holder.dropbtn.setBackgroundResource(R.drawable.ic_info_black_24dp);
                holder.syllabus.setTextColor(Color.RED);
            }
        }
        if(position==4)
        {
            if((1131 <= time) && (time <= 1220))
            {
                holder.teacherName.setTextColor(Color.RED);
                holder.textViewTitle.setTextColor(Color.RED);
                holder.time.setTextColor(Color.RED);
                holder.cardView.setCardBackgroundColor(Color.parseColor("#000000"));
                holder.dropbtn.setBackgroundResource(R.drawable.ic_info_black_24dp);
                holder.syllabus.setTextColor(Color.RED);
            }
        }
        if(position==5)
        {
            if((1301 <= time) && (time <= 1350))
            {
                holder.teacherName.setTextColor(Color.RED);
                holder.textViewTitle.setTextColor(Color.RED);
                holder.time.setTextColor(Color.RED);
                holder.cardView.setCardBackgroundColor(Color.parseColor("#000000"));
                holder.dropbtn.setBackgroundResource(R.drawable.ic_info_black_24dp);
                holder.syllabus.setTextColor(Color.RED);

            }
        }
        if(position==6)
        {
            if((1401 <= time) && (time <= 1450))
            {
                holder.teacherName.setTextColor(Color.RED);
                holder.textViewTitle.setTextColor(Color.RED);
                holder.time.setTextColor(Color.RED);
                holder.cardView.setCardBackgroundColor(Color.parseColor("#000000"));
                holder.dropbtn.setBackgroundResource(R.drawable.ic_info_black_24dp);
                holder.syllabus.setTextColor(Color.RED);
            }
        }
        if(position==7)
        {
            if((1451 <= time) && (time <= 1540))
            {
                holder.teacherName.setTextColor(Color.RED);
                holder.textViewTitle.setTextColor(Color.RED);
                holder.time.setTextColor(Color.RED);
                holder.cardView.setCardBackgroundColor(Color.parseColor("#000000"));
                holder.dropbtn.setBackgroundResource(R.drawable.ic_info_black_24dp);
                holder.syllabus.setTextColor(Color.RED);
            }
        }


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    void clear()
    {
        int nteachers = teachers.size();
        for(int i=0;i<nteachers;i++)
        {
            productList.remove(i);
            teachers.remove(i);
            notifyItemRemoved(i);
        }

    }

    public char getSec()
    {
        return sec;
    }


    class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView textViewTitle;
        TextView teacherName;
        TextView time;
        public TextView dropbtn;
        LinearLayout linearLayout;
        private CardView cardView;
        TextView syllabus;

        private Button ut1,ut2,ut3,ut4,ut5;


        public ProductViewHolder(View itemView, final char sec) {
            super(itemView);
            syllabus = (itemView).findViewById(R.id.textView5);
            teacherName = itemView.findViewById(R.id.textView3);
            cardView = itemView.findViewById(R.id.cardview);
            textViewTitle = itemView.findViewById(R.id.textView);
            dropbtn = itemView.findViewById(R.id.textView4);
            linearLayout = itemView.findViewById(R.id.expandableLayout);
            time = itemView.findViewById(R.id.textView2);

            ut1=itemView.findViewById(R.id.ut1);
            ut2=itemView.findViewById(R.id.ut2);
            ut3=itemView.findViewById(R.id.ut3);
            ut4=itemView.findViewById(R.id.ut4);
            ut5=itemView.findViewById(R.id.ut5);

            ut1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Syllabus");
                    parseQuery.whereEqualTo("subName",textViewTitle.getText());
                    parseQuery.whereEqualTo("unit","1");
                    final PrettyDialog pd = new PrettyDialog(mCtx);
                    parseQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                     @Override
                     public void done(ParseObject obj, ParseException e) {
                         if(e==null)
                         {
                             if(obj!=null)
                             {
                                 pd.setTitle("Unit-1")
                                 .setMessage(""+obj.get("msg"))
                                         .setIcon(
                                                 R.drawable.pdlg_icon_info,
                                                 R.color.pdlg_color_blue,
                                                 new PrettyDialogCallback() {
                                                     @Override
                                                     public void onClick() {
                                                         //for now nothing
                                                     }
                                                 }
                                         )
                                         .addButton(
                                                 "Ok",
                                                 R.color.pdlg_color_white,
                                                 R.color.pdlg_color_green,
                                                 new PrettyDialogCallback() {
                                                     @Override
                                                     public void onClick() {
                                                         pd.dismiss();
                                                     }
                                                 }
                                         ).show();
                             }
                         }
                         else
                         {
                             Toast.makeText(mCtx,"Error in fetching data",Toast.LENGTH_SHORT).show();
                         }
                     }
                 });

                }
            });
            ut2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Syllabus");
                    parseQuery.whereEqualTo("subName",textViewTitle.getText());
                    parseQuery.whereEqualTo("unit","2");
                    final PrettyDialog pd = new PrettyDialog(mCtx);
                    parseQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject obj, ParseException e) {
                            if(e==null)
                            {
                                if(obj!=null)
                                {
                                    pd.setTitle("Unit-2")
                                            .setMessage(""+obj.get("msg"))
                                            .setIcon(
                                                    R.drawable.pdlg_icon_info,
                                                    R.color.pdlg_color_blue,
                                                    new PrettyDialogCallback() {
                                                        @Override
                                                        public void onClick() {
                                                            //for now nothing
                                                        }
                                                    }
                                            )
                                            .addButton(
                                                    "Ok",
                                                    R.color.pdlg_color_white,
                                                    R.color.pdlg_color_green,
                                                    new PrettyDialogCallback() {
                                                        @Override
                                                        public void onClick() {
                                                            pd.dismiss();
                                                        }
                                                    }
                                            ).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(mCtx,"Error in fetching data",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
            ut3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Syllabus");
                    parseQuery.whereEqualTo("subName",textViewTitle.getText());
                    parseQuery.whereEqualTo("unit","3");
                    final PrettyDialog pd = new PrettyDialog(mCtx);
                    parseQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject obj, ParseException e) {
                            if(e==null)
                            {
                                if(obj!=null)
                                {
                                    pd.setTitle("Unit-3")
                                            .setMessage(""+obj.get("msg"))
                                            .setIcon(
                                                    R.drawable.pdlg_icon_info,
                                                    R.color.pdlg_color_blue,
                                                    new PrettyDialogCallback() {
                                                        @Override
                                                        public void onClick() {
                                                            //for now nothing
                                                        }
                                                    }
                                            )
                                            .addButton(
                                                    "Ok",
                                                    R.color.pdlg_color_white,
                                                    R.color.pdlg_color_green,
                                                    new PrettyDialogCallback() {
                                                        @Override
                                                        public void onClick() {
                                                            pd.dismiss();
                                                        }
                                                    }
                                            ).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(mCtx,"Error in fetching data",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
            ut4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Syllabus");
                    parseQuery.whereEqualTo("subName",textViewTitle.getText());
                    parseQuery.whereEqualTo("unit","4");
                    final PrettyDialog pd = new PrettyDialog(mCtx);
                    parseQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject obj, ParseException e) {
                            if(e==null)
                            {
                                if(obj!=null)
                                {
                                    pd.setTitle("Unit-4")
                                            .setMessage(""+obj.get("msg"))
                                            .setIcon(
                                                    R.drawable.pdlg_icon_info,
                                                    R.color.pdlg_color_blue,
                                                    new PrettyDialogCallback() {
                                                        @Override
                                                        public void onClick() {
                                                            //for now nothing
                                                        }
                                                    }
                                            )
                                            .addButton(
                                                    "Ok",
                                                    R.color.pdlg_color_white,
                                                    R.color.pdlg_color_green,
                                                    new PrettyDialogCallback() {
                                                        @Override
                                                        public void onClick() {
                                                            pd.dismiss();
                                                        }
                                                    }
                                            ).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(mCtx,"Error in fetching data",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
            ut5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Syllabus");
                    parseQuery.whereEqualTo("subName",textViewTitle.getText());
                    parseQuery.whereEqualTo("unit","5");
                    final PrettyDialog pd = new PrettyDialog(mCtx);
                    parseQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject obj, ParseException e) {
                            if(e==null)
                            {
                                if(obj!=null)
                                {
                                    pd.setTitle("Unit-5")
                                            .setMessage(""+obj.get("msg"))
                                            .setIcon(
                                                    R.drawable.pdlg_icon_info,
                                                    R.color.pdlg_color_blue,
                                                    new PrettyDialogCallback() {
                                                        @Override
                                                        public void onClick() {
                                                            //for now nothing
                                                        }
                                                    }
                                            )
                                            .addButton(
                                                    "Ok",
                                                    R.color.pdlg_color_white,
                                                    R.color.pdlg_color_green,
                                                    new PrettyDialogCallback() {
                                                        @Override
                                                        public void onClick() {
                                                            pd.dismiss();
                                                        }
                                                    }
                                            ).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(mCtx,"Error in fetching data",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            dropbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mCtx,"Btn Click Worked\n"+linearLayout.getVisibility(),Toast.LENGTH_LONG).show();
                    if((""+textViewTitle.getText()).toLowerCase().contains("lab"))
                    {
                        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Syllabus");
                        parseQuery.whereEqualTo("unit","lab");
                        parseQuery.whereEqualTo("subName",textViewTitle.getText());
                        final PrettyDialog pd = new PrettyDialog(mCtx);
                        parseQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                            @Override
                            public void done(ParseObject obj, ParseException e) {
                                if(e==null)
                                {
                                    if(obj!=null)
                                    {
                                        pd.setTitle("Lab Syllabus")
                                                .setMessage(""+obj.get("msg"))
                                                .setIcon(
                                                        R.drawable.pdlg_icon_info,
                                                        R.color.pdlg_color_blue,
                                                        new PrettyDialogCallback() {
                                                            @Override
                                                            public void onClick() {
                                                                //for now nothing
                                                            }
                                                        }
                                                )
                                                .addButton(
                                                        "Ok",
                                                        R.color.pdlg_color_white,
                                                        R.color.pdlg_color_green,
                                                        new PrettyDialogCallback() {
                                                            @Override
                                                            public void onClick() {
                                                                pd.dismiss();
                                                            }
                                                        }
                                                ).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(mCtx,"Error in fetching data",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }
                    else
                    {
                        if(linearLayout.getVisibility()== View.GONE)
                        {
                            TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                            linearLayout.setVisibility(View.VISIBLE);
                            if(teacherName.getCurrentTextColor()==Color.RED)
                            {
                                dropbtn.setBackgroundResource(R.drawable.ic_info_black_24dp);
                            }
                            else
                            {
                                dropbtn.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
                            }
                        }
                        else
                        {
                            TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                            linearLayout.setVisibility(View.GONE);
                            if(teacherName.getCurrentTextColor()==Color.RED)
                            {
                                dropbtn.setBackgroundResource(R.drawable.ic_info_black_24dp);
                            }
                            else
                            {
                                dropbtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                            }
                        }
                    }

                }
            });

            teacherName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Toast.makeText(getApplicationContext(),"Long Click Event\n"+teacherName.getText(),Toast.LENGTH_SHORT).show();
                    ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Teachers");
                    parseQuery.whereEqualTo("teacherName", teacherName.getText());
                    parseQuery.whereEqualTo("sec", ""+sec);
                    parseQuery.whereEqualTo("subName",textViewTitle.getText());
                    parseQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject obj, ParseException e) {
                            if(e==null)
                            {
                                if(obj!=null)
                                {
//                                    Toast.makeText(getApplicationContext(),"Name: "+teacherName.getText()+"\nContact No: "+obj.get("Contact")+
//                                           "\nEmail: "+obj.get("email"),Toast.LENGTH_LONG).show();
                                    final PrettyDialog pd = new PrettyDialog(mCtx);
                                    pd.setTitle("Teacher's Info")
                                            .setMessage("Name: "+teacherName.getText()+"\nContact No: "+obj.get("Contact")+
                                                    "\nEmail: "+obj.get("email"))
                                            .setIcon(
                                                    R.drawable.pdlg_icon_info,
                                                    R.color.pdlg_color_blue,
                                                    new PrettyDialogCallback() {
                                                        @Override
                                                        public void onClick() {
                                                            //for now nothing
                                                        }
                                                    }
                                            )
                                            .addButton(
                                                    "Ok",
                                                    R.color.pdlg_color_white,
                                                    R.color.pdlg_color_green,
                                                    new PrettyDialogCallback() {
                                                        @Override
                                                        public void onClick() {
                                                            pd.dismiss();
                                                        }
                                                    }
                                            ).show();
                                }
                            }
                            else
                            {
                                //Toast.makeText(getApplicationContext(),"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    return true;
                }
            });
        }


    }
}