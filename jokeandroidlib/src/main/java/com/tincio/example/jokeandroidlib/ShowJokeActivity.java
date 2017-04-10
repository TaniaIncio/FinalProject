package com.tincio.example.jokeandroidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class ShowJokeActivity extends AppCompatActivity {

    TextView mJoke;
    public static String PARAM_TEXT = "extra_joke";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);
        mJoke = (TextView)findViewById(R.id.txt_joke);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mJoke.setText(getIntent().getStringExtra(PARAM_TEXT) == null ? "":getIntent().getStringExtra(PARAM_TEXT));
    }
}
