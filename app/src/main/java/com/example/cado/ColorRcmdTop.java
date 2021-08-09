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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ColorRcmdTop extends AppCompatActivity  implements TextToSpeech.OnInitListener {

    ArrayList<RcmdData>arrayList;
    RcmdAdapter mainAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    TextView tvTitle;
    TextToSpeech tts;
    Button btOn, btIn, btOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Cado);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcmd_top);

        tts = new TextToSpeech(this, this);

        Intent intent = getIntent();
        String colorName = intent.getStringExtra("name");

        String title = intent.getStringExtra("title");
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(title);

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
                speakOut(tvTitle.getText());
            }
        });

        RcmdData tontData = new RcmdData(TonT.withChip_top(colorName),"톤온톤",TonT.withName_top(colorName));
        arrayList.add(tontData);
        //mainAdapter.notifyDataSetChanged();

        RcmdData tintData = new RcmdData(TinT.withChip_top(colorName),"톤인톤",TinT.withName_top(colorName));
        arrayList.add(tintData);
        //mainAdapter.notifyDataSetChanged();

        RcmdData opstData = new RcmdData(Opst.withChip_top(colorName),"보색",Opst.withName_top(colorName));
        arrayList.add(opstData);
        mainAdapter.notifyDataSetChanged();


        btOn = findViewById(R.id.btOn);
        btOn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                CharSequence text = "톤온톤 추천 색은 " + TonT.withName_top(colorName);
                speakOut(text);
            }
        });

        btIn = findViewById(R.id.btIn);
        btIn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                CharSequence text = "톤인톤 추천 색은 " + TinT.withName_top(colorName);
                speakOut(text);
            }
        });

        btOp = findViewById(R.id.btOp);
        btOp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                CharSequence text = "보색 추천 색은 " + Opst.withName_top(colorName);
                speakOut(text);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void speakOut(CharSequence text) {
        //CharSequence text = tvTitle.getText();
        tts.setPitch((float) 0.6);
        tts.setSpeechRate((float) 0.9);
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
                speakOut(tvTitle.getText());
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }
}
