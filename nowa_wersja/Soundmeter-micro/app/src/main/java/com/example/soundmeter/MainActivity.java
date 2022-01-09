package com.example.soundmeter;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.soundmeter.databinding.ActivityMainBinding;
import com.example.soundmeter.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.soundmeter.audio.core.Callback;
import com.example.soundmeter.audio.core.Recorder;
import com.example.soundmeter.audio.calculators.AudioCalculator;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soundmeter.ui.main.SectionsPagerAdapter;
import com.example.soundmeter.databinding.ActivityMainBinding;

import com.example.soundmeter.R.layout.*;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Recorder recorder;
    private AudioCalculator audioCalculator;
    private Handler handler;

    private TextView textAmplitude;
    private TextView textDecibel;
    private TextView textFrequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
         tabs.setupWithViewPager(viewPager);

         setContentView(R.layout.fragment_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        recorder = new Recorder(callback);
        audioCalculator = new AudioCalculator();
        handler = new Handler(Looper.getMainLooper());

        textAmplitude = (TextView) findViewById(R.id.textAmplitude);
        textDecibel = (TextView) findViewById(R.id.textDecibel);
        textFrequency = (TextView) findViewById(R.id.textFrequency);





    }

    private Callback callback = new Callback() {

        @Override
        public void onBufferAvailable(byte[] buffer) {
            audioCalculator.setBytes(buffer);
            int amplitude = audioCalculator.getAmplitude();
            double decibel = audioCalculator.getDecibel();
            double frequency = audioCalculator.getFrequency();

            final String amp = String.valueOf(amplitude + " Amp");
            final String db = String.valueOf(decibel + " db");
            final String hz = String.valueOf(frequency + " Hz");

            handler.post(new Runnable() {
                @Override
                public void run() {

                    textAmplitude.setText(amp);
                    textDecibel.setText(db);
                    textFrequency.setText(hz);
                }
            });
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        recorder.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        recorder.stop();
    }
}