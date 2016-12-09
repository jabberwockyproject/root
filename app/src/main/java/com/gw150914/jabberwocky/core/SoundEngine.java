package com.gw150914.jabberwocky.core;

import android.media.AudioManager;
import android.media.SoundPool;

public class SoundEngine {

    /******************
     * PRIVATE FIELDS *
     ******************/

    private int loop;
    private int soundPriority;
    private boolean hasCustomVolume;
    private float customVolume;
    private float maxVolume;
    private float rate;
    private SoundPool soundPool;
    private AudioManager audioManager;


    /*******************
     * PRIVATE CLASSES *
     *******************/

    private class PlaySound extends Thread {
        private int soundId;

        PlaySound(int soundId) {
            this.soundId = soundId;
        }
        public void run() {
            soundPool.play(soundId, getFinalSoundVolume(), getFinalSoundVolume(), soundPriority, loop, rate);
        }
    }

    /****************
     * CONSTRUCTORS *
     ****************/

    //Constructor 1
    public SoundEngine(AudioManager newAudioManager) {
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC,0);
        loop = 0;
        soundPriority = 1;
        rate = 1f;
        audioManager = newAudioManager;
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }


    /*********************
     * GETTORS / SETTORS *
     *********************/

    public void setLoop(int newLoop) {
        loop = newLoop;
    }

    public void setSoundPriority(int newPriority) {
        soundPriority = newPriority;
    }

    public void setCustomVolume(float newVolume) {
        customVolume = newVolume;
        hasCustomVolume = true;
    }

    public void setRate(float newRate) {
        rate = newRate;
    }

    public SoundPool getSoundPool() {
        return soundPool;
    }


    /******************
     * PUBLIC METHODS *
     ******************/

    public void removeCustomVolume() {
        hasCustomVolume = false;
    }

    private float getCurVolume() {
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    public void playSound(int soundId) {
        PlaySound playSoundThread = new PlaySound(soundId);
        playSoundThread.start();
    }


    /*******************
     * PRIVATE METHODS *
     *******************/
    
    private float getFinalSoundVolume() {
        if(hasCustomVolume) {
            return customVolume / maxVolume;
        }
        else {
            return getCurVolume() / maxVolume;
        }
    }
}
