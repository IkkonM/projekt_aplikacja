package com.example.soundmetermain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mapa extends AppCompatActivity {
    private Button buttonA;
    private Button buttonB;
    private Button buttonC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });


        buttonA = findViewById(R.id.buttonA);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        buttonB = findViewById(R.id.buttonB);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity0();
            }
        });

    }

    private void openActivity3() {
        Intent intent = new Intent(this, Ustawienia.class);
        startActivity(intent);
    }

    private void openActivity1() {
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);
    }
    private void openActivity0() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

