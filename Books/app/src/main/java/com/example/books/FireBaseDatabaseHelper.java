package com.example.books;

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
    private DatabaseReference mReferenceBooks;
    private List<Books> books=new ArrayList<>();

    public FireBaseDatabaseHelper() {
        mDatabase =FirebaseDatabase.getInstance();
        mReferenceBooks=mDatabase.getReference("books");

    }

    public interface DataStatus
    {
        void DataIsLoaded(List<Books> books,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void readBooks(final DataStatus dataStatus)
    {
        mReferenceBooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot keyNode:dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    Books book =keyNode.getValue(Books.class);
                    books.add(book);
                }
                dataStatus.DataIsLoaded(books,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
