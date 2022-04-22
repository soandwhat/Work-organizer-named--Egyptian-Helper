package com.your.egyptianhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Bundle args = getIntent().getExtras();
        if (args != null) {
            ((EditText) findViewById(R.id.timeWork)).setText(args.getString("timeWork"));
            ((EditText) findViewById(R.id.timeRest)).setText(args.getString("timeRest"));
        }

        findViewById(R.id.save).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String timeWork = ((EditText) findViewById(R.id.timeWork)).getText().toString();
        String timeRest = ((EditText) findViewById(R.id.timeRest)).getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat(Settings.FORMAT_DATE);
        try {
            sdf.parse(timeWork);
            sdf.parse(timeRest);
            Intent intent = new Intent(this, VisionActivity.class);
            intent.putExtra("timeWork", timeWork);
            intent.putExtra("timeRest", timeRest);
            startActivity(intent);
        } catch (ParseException e) {
            Toast.makeText(this, getResources().getString(R.string.errorFormat) + Settings.FORMAT_DATE, Toast.LENGTH_LONG).show();
        }

    }
}
