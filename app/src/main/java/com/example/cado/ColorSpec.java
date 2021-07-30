package com.example.cado;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ColorSpec extends AppCompatActivity {
    TextView tvName;
    ImageView ivChip;
    EditText etMemo;
    Button btSave, btTop, btBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Cado);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_spec);

        tvName = findViewById(R.id.tvName);
        ivChip = findViewById(R.id.ivChip);
        etMemo = findViewById(R.id.etMemo);
        btSave = findViewById(R.id.btSave);
        btTop = findViewById(R.id.btTop);
        btBottom = findViewById(R.id.btBottom);

        Intent intent = getIntent();

        String colorName = intent.getStringExtra("colorname");
        String colorChip = intent.getStringExtra("colorchip");

        tvName.setText(colorName);
        Glide.with(this)
                .load(colorChip)
                .into(ivChip);

        SharedPreferences sharedPreferences = getSharedPreferences("file", 0);
        String value = sharedPreferences.getString("key","");
        etMemo.setText(value);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMemo(v);
            }
        });

        btTop.setText(colorName + " 상의와 어울리는 하의");
        btBottom.setText(colorName + " 하의와 어울리는 상의");

    }

    public void createMemo (View v){
        SharedPreferences  sharedPreferences = getSharedPreferences("file", 0);
        SharedPreferences.Editor editor  = sharedPreferences.edit();
        String value = etMemo.getText().toString();
        editor.putString("key", value);
        editor.commit();
    }

    public void topGoToRcmd(View v){
        String colorName = findViewById(R.id.tvName).toString();
        Intent intent = new Intent(this, ColorRcmd.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("object", btTop.getText());
        startActivity(intent);
    }

    public void btmGoToRcmd(View v){
        String colorName = findViewById(R.id.tvName).toString();
        Intent intent = new Intent(this, ColorRcmd.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);;
        intent.putExtra("object", btBottom.getText());
        startActivity(intent);
    }

}
