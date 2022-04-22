package com.your.egyptianhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TwoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        CardView vision = findViewById(R.id.vision_cardview);
        vision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TwoActivity.this,VisionActivity.class);
                startActivity(intent);
            }
        });

        CardView notepad = findViewById(R.id.notepad_cardview);
        notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TwoActivity.this,NotepadActivity.class);
                startActivity(intent);
            }
        });

        CardView calc = findViewById(R.id.calc_cardview);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TwoActivity.this,CalculatorActivity.class);
                startActivity(intent);
            }
        });

    }

    public void inter (View view) {
        Intent intent = new Intent(TwoActivity.this, InternetActivity.class);
        startActivity(intent);
    }
}