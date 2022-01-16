package com.example.soundmeter;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soundmeter.ui.main.SectionsPagerAdapter;
import com.example.soundmeter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static boolean isStartTimeMeasured = false;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        System.out.println("isStartTimeMeasured: " + isStartTimeMeasured);

        if(!isStartTimeMeasured) {
            long endTime = System.currentTimeMillis();
            Toast.makeText(this, "Czas uruchomienia: " + (endTime - startTime) + " ms", Toast.LENGTH_LONG).show();
            isStartTimeMeasured = true;
        }
    }
}