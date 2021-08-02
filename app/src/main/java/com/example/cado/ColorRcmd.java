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

public class ColorRcmd extends AppCompatActivity  implements TextToSpeech.OnInitListener {

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
        setContentView(R.layout.activity_rcmd);

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
                speakOut();
            }
        });

        btOn = findViewById(R.id.btOn);
        btOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RcmdData mainData = new RcmdData(withColorChip(colorName),"톤온톤",withColorName(colorName));
                arrayList.add(mainData);
                mainAdapter.notifyDataSetChanged();
            }
        });
    }

    private int withColorChip(String colorName){
        Toast toast = Toast.makeText(getApplicationContext(),colorName,Toast.LENGTH_SHORT);
        toast.show();

        if(colorName.equals("노란색")) {
            return R.drawable.blue;
        } else {
            return R.drawable.black;
        }
    }

    private String withColorName(String colorName){
        if(colorName.equals("노란색")) {
            return "파란색";
        } else {
            return "어울리는 색이 없습니다";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void speakOut() {
        CharSequence text = tvTitle.getText();
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
                speakOut();
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }
}
