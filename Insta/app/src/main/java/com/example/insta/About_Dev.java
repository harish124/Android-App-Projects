package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class About_Dev extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private RecyclerView recyclerView;
    private AboutDevAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__dev);

        setTitle("            About Developer!!!");

        arrayList=new ArrayList<>();

        arrayList.add("B.Harish");
        arrayList.add("Age: 19");
        arrayList.add("github.com/harish124");
        arrayList.add("linkedin.com/in/harish-balaji-594b49179");
        arrayList.add("Sri Sivasubramaniya Nadar College Of Engineering");

        recyclerView=findViewById(R.id.recyclerViewAboutDev);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter=new AboutDevAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);

    }
}
