package com.example.timetabe;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class RecyclerView_Config {

    private Context mContext;
    private BookAdapter mdaily_timetableAdapter;
    private MediaPlayer player;

    public void setConfig(RecyclerView recyclerView,Context context,List<daily_timetable> books,List<String> keys)
    {
        mContext=context;
        mdaily_timetableAdapter = new BookAdapter(books,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mdaily_timetableAdapter);
    }
    class BookItemView extends RecyclerView.ViewHolder
    {
        private TextView mIsbn;
        private TextView mCategory;

        private String key;


        public BookItemView(ViewGroup parent)
        {
            super(LayoutInflater.from(mContext).
            inflate(R.layout.time_table_list,parent,false));


            mCategory= (TextView)itemView.findViewById(R.id.category_textview);
            mIsbn = (TextView)itemView.findViewById(R.id.isbn_textview);


        }

        public void bind(daily_timetable obj,String key)
        {

            String val=obj.getValue();
            mIsbn.setText(""+val);
            this.key=key;
            mIsbn.setTextColor(Color.BLACK);
            mCategory.setTextColor(Color.WHITE);
            Calendar cal = Calendar.getInstance();
            player= MediaPlayer.create(mContext,R.raw.smsringtone);

            /*int day=0;
            day = cal.get(Calendar.DAY_OF_WEEK);
            String dayName="";

            switch (day) {
                case Calendar.FRIDAY:
                    dayName="Saturday";
                    break;
                case Calendar.MONDAY:
                    dayName="Monday";
                    break;
                case Calendar.TUESDAY:
                    dayName="Tuesday";
                    break;

                case Calendar.WEDNESDAY:
                    dayName="Wednesday";
                    break;
                case Calendar.THURSDAY:
                    dayName="Thursday";
                    break;
            }*/
            if(val.equalsIgnoreCase("lab"))
            {
                player.start();
                Toast.makeText(mContext,"Don't forget to take your\nObservation and Record notebooks.",Toast.LENGTH_SHORT).show();
            }


            //24 hour format
            int hourofday = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);

            int time=0;
            time = (hourofday * 100) + minute;


            if(obj.getN().equals("one"))
            {
                mCategory.setText(" 1. ");
                if((800<=time)&&(time<=850))
                {
                    mIsbn.setTextColor(Color.RED);
                    mCategory.setTextColor(Color.RED);
                }
            }
            else if(obj.getN().equals("two"))
            {
                mCategory.setText(" 2. ");
                if((851 <= time) && (time <= 940))
                {
                    mIsbn.setTextColor(Color.RED);
                    mCategory.setTextColor(Color.RED);
                }

            }
            else if(obj.getN().equals("three"))
            {
                mCategory.setText(" 3. ");
                if((941 <= time) && (time <= 1030))
                {
                    mIsbn.setTextColor(Color.RED);
                    mCategory.setTextColor(Color.RED);
                }
            }
            else if(obj.getN().equals("four"))
            {
                mCategory.setText(" 4. ");
                if((1041 <= time) && (time <= 1130))
                {
                    mIsbn.setTextColor(Color.RED);
                    mCategory.setTextColor(Color.RED);
                }
            }
            else if(obj.getN().equals("five"))
            {
                mCategory.setText(" 5. ");
                if((1211 <= time) && (time <= 1300))
                {
                    mIsbn.setTextColor(Color.RED);
                    mCategory.setTextColor(Color.RED);
                }
            }
            else if(obj.getN().equals("six"))
            {
                mCategory.setText(" 6. ");
                if((1301 <= time) && (time <= 1350))
                {
                    mIsbn.setTextColor(Color.RED);
                    mCategory.setTextColor(Color.RED);
                }
            }
            else if(obj.getN().equals("seven"))
            {
                mCategory.setText(" 7. ");
                if((1401 <= time) && (time <= 1450))
                {
                    mIsbn.setTextColor(Color.RED);
                    mCategory.setTextColor(Color.RED);
                }
            }
            else
            {
                mCategory.setText(" 8. ");
                if((1451 <= time) && (time <= 1540))
                {
                    mIsbn.setTextColor(Color.RED);
                    mCategory.setTextColor(Color.RED);
                }
            }
        }
    }//eclassBookItemView

    class BookAdapter extends RecyclerView.Adapter<BookItemView>
    {
        private List<daily_timetable> mBookList;
        private List<String> mKeys;

        public BookAdapter(List<daily_timetable> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }


        @Override
        public BookItemView onCreateViewHolder(ViewGroup parent, int i) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(BookItemView holder, int position) {
            holder.bind(mBookList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
