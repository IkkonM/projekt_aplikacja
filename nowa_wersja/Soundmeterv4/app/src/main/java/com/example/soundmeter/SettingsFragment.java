package com.example.soundmeter;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SettingsFragment extends Fragment {
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }
    private static boolean isStartTimeMeasured = false;
    long startTime = System.currentTimeMillis()/1000;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        System.out.println("isStartTimeMeasured: " + isStartTimeMeasured);


        TextView czas = (TextView) view.findViewById(R.id.textTime);
   //     setContetView(czas);



        Timer timer = new Timer();

        new Thread(new Runnable() {
            @Override public void run() {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        long endTime = System.currentTimeMillis();
                        czas.setText("Czas uruchomienia: " + (endTime/1000 - startTime) + " s");

                    }
                }, 0, 1);//wait 0 ms before doing the action and do it evry 1000ms (1second)
            }
        }).start();



        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("AppSettingPrefs", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean darkMode = sharedPreferences.getBoolean("NightMode", false);

        if(darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        Switch switchDarkMode = view.findViewById(R.id.switch_dark_mode);
        switchDarkMode.setChecked(darkMode);

        switchDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(darkMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean("NightMode", false);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("NightMode", true);
                }
                editor.apply();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}