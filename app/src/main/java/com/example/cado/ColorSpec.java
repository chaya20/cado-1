package com.example.cado;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Locale;

public class ColorSpec extends AppCompatActivity implements TextToSpeech.OnInitListener {
    TextView tvName;
    ImageView ivChip;
    EditText etMemo;
    Button btSave, btTop, btBottom;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Cado);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_spec);

        tvName = findViewById(R.id.tvName);
        ivChip = findViewById(R.id.ivChip);
        //etMemo = findViewById(R.id.etMemo);
        //btSave = findViewById(R.id.btSave);
        btTop = findViewById(R.id.btTop);
        btBottom = findViewById(R.id.btBottom);
        tts = new TextToSpeech(this, this);

        Intent intent = getIntent();

        String colorName = intent.getStringExtra("colorname");
        String colorChip = intent.getStringExtra("colorchip");

        tvName.setText(colorName);
        Glide.with(this)
                .load(colorChip)
                .into(ivChip);
        /*
        SharedPreferences sharedPreferences = getSharedPreferences("file", 0);
        String value = sharedPreferences.getString("key","");
        etMemo.setText(value);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMemo(v);
            }
        });*/

        tvName.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });


        btTop.setText(colorName + " 상의와 어울리는 하의");
        btBottom.setText(colorName + " 하의와 어울리는 상의");

    }
    /*
    public void createMemo (View v){
        SharedPreferences  sharedPreferences = getSharedPreferences("file", 0);
        SharedPreferences.Editor editor  = sharedPreferences.edit();
        String value = etMemo.getText().toString();
        editor.putString("key", value);
        editor.commit();
    }*/

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void speakOut() {
        CharSequence text = tvName.getText();
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


    public void topGoToRcmd(View v){
        //String colorName = findViewById(R.id.tvName).toString();
        Intent intent = new Intent(this, ColorRcmdTop.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("title", btTop.getText());
        intent.putExtra("name", tvName.getText());
        startActivity(intent);
    }

    public void btmGoToRcmd(View v) {
        //String colorName = findViewById(R.id.tvName).toString();
        Intent intent = new Intent(this, ColorRcmdBtm.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("title", btBottom.getText());
        intent.putExtra("name", tvName.getText());
        startActivity(intent);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.KOREA);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                //tvName.setEnabled(true);
                speakOut();
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }
}
