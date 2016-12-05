package com.gw150914.jabberwocky.core;

import android.media.AudioManager;
import android.media.SoundPool;

public class SoundEngine {
    private SoundPool soundPool;
    private float customVolume;
    private float maxVolume;
    private boolean hasCustomVolume;
    private int loop;
    private int soundPriority;
    private float rate;
    private AudioManager audioManager;

    private class PlaySound extends Thread {
        private SoundPool sPool;
        private int sSoundId;
        private int sPriority;
        private int sLoop;
        private float sRate;
        private float sVolume;

        PlaySound(SoundPool nPool, int nSoundId, int nPriority, int nLoop, float nRate, float nVolume) {
            sPool = nPool;
            sSoundId = nSoundId;
            sPriority = nPriority;
            sLoop = nLoop;
            sRate = nRate;
            sVolume = nVolume;
        }
        public void run() {
            sPool.play(sSoundId, sVolume, sVolume, sPriority, sLoop, sRate);
        }
    }

    public SoundEngine(AudioManager newAudioManager) {
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC,0);
        loop = 0;
        soundPriority = 1;
        rate = 1f;
        audioManager = newAudioManager;
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }
    public void setCustomVolume(float newVolume) {
        customVolume = newVolume;
        hasCustomVolume = true;
    }
    public void setRate(float newRate) {
        rate = newRate;
    }
    public void setSoundPriority(int newPriority) {
        soundPriority = newPriority;
    }
    public void setLoop(int newLoop) {
        loop = newLoop;
    }
    public SoundPool getSoundPool() {
        return soundPool;
    }
    public void removeCustomVolume() {
        hasCustomVolume = false;
    }
    private float getCurVolume() {
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }
    private void playSound(int soundId){
        PlaySound playSoundThread = new PlaySound(soundPool, soundId, soundPriority, loop, rate, getFinalSoundVolume());
        playSoundThread.start();
    }
    private float getFinalSoundVolume() {
        if (hasCustomVolume) {
            return customVolume/maxVolume;
        }
        else {
            return getCurVolume()/maxVolume;
        }
    }
}
