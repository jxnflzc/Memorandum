package com.countryfive.memorandum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class LoadMemorandumActivity extends AppCompatActivity {
    private List<Memorandum> memorandumList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_memorandum);

        recyclerView = findViewById(R.id.ShowMemorandum);
        memorandumList = DataSupport.findAll(Memorandum.class);

        MemorandumAdapter adapter;

        if(memorandumList != null && !memorandumList.isEmpty()){
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new MemorandumAdapter(memorandumList);
        } else {
            Memorandum noMemorandum = new Memorandum();
            noMemorandum.setMemorandumName("没有数据");
            memorandumList.add(noMemorandum);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new MemorandumAdapter(memorandumList);
        }

        recyclerView.setAdapter(adapter);
    }
}
