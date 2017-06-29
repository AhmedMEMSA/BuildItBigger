package com.example.ahmed.jokesdisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    public static final String JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent i = getIntent();
        String s = i.getStringExtra(JOKE);
        TextView textView = (TextView) findViewById(R.id.joke_tv);
        textView.setText(s);
    }
}
