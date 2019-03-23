package com.example.timetabe;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferencedaily_timetable;
    private List<daily_timetable> days=new ArrayList<>();
    private String val;

    public FireBaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferencedaily_timetable=mDatabase.getReference("cse_mon");

    }

    public FireBaseDatabaseHelper(String val) {
        this.val = val;
        mDatabase = FirebaseDatabase.getInstance();
        mReferencedaily_timetable=mDatabase.getReference(""+val);
    }

    public interface DataStatus
    {
        void DataIsLoaded(List<daily_timetable> days, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void readdaily_timetable(final DataStatus dataStatus)
    {
        mReferencedaily_timetable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                days.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot keyNode:dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    daily_timetable periods =keyNode.getValue(daily_timetable.class);
                    days.add(periods);
                }
                dataStatus.DataIsLoaded(days,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
