package com.example.cado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorRcmd extends AppCompatActivity {

    private ArrayList<RcmdData>arrayList;
    private RcmdAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Cado);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcmd);

        Intent intent = getIntent();
        String object = intent.getStringExtra("object");

        TextView textView = findViewById(R.id.tvTitle);
        textView.setText(object);

        recyclerView=(RecyclerView)findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        mainAdapter = new RcmdAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);

        Button Btn1 = (Button)findViewById(R.id.Btn1);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RcmdData mainData = new RcmdData(R.drawable.color,"톤온톤","검은색 하의");
                arrayList.add(mainData);
                mainAdapter.notifyDataSetChanged();
            }
        });

    }
}
