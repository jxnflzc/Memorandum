package com.countryfive.memorandum;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnAdd;
    private List<Memorandum> memorandumList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LitePal.getDatabase();

        btnAdd = findViewById(R.id.BtnAdd);
        recyclerView = findViewById(R.id.ShowMemorandum);
        memorandumList = DataSupport.findAll(Memorandum.class);
        MemorandumAdapter adapter;

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAddMemorandumActivity = new Intent(MainActivity.this, AddMemorandumActivity.class);
                gotoAddMemorandumActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                startActivity(gotoAddMemorandumActivity);
            }
        });

        if(memorandumList != null && !memorandumList.isEmpty()){
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new MemorandumAdapter(memorandumList);
        } else {
            Memorandum noMemorandum = new Memorandum();
            noMemorandum.setMemorandumName("没有备忘录");
            memorandumList.add(noMemorandum);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new MemorandumAdapter(memorandumList);
        }

        recyclerView.setAdapter(adapter);
    }
}
