package com.example.cado;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ColorRcmd extends AppCompatActivity  implements TextToSpeech.OnInitListener {

    private ArrayList<RcmdData>arrayList;
    private RcmdAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    TextView tvTitle;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Cado);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcmd);

        tts = new TextToSpeech(this, this);

        Intent intent = getIntent();
        String object = intent.getStringExtra("object");

        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(object);

        recyclerView=(RecyclerView)findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        mainAdapter = new RcmdAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);

        tvTitle.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void speakOut() {
        CharSequence text = tvTitle.getText();
        tts.setPitch((float) 0.6);
        tts.setSpeechRate((float) 0.8);
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,"id1");
    }

    @Override public void onDestroy() {
        if (tts != null) {
            tts.stop(); tts.shutdown();
        }
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.KOREA);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                //tvTitle.setEnabled(true);
                speakOut();
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }
}
