package com.example.soundmeter.audio.core;

public interface Callback {
    void onBufferAvailable(byte[] buffer);
}