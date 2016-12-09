package com.gw150914.jabberwocky.core;

import android.media.AudioManager;
import android.media.SoundPool;


/*
################################[ SoundEngine.class ]##################################
# The SoundEngine is designed to play sounds with some specific settings and options. #
#######################################################################################
*/

public class SoundEngine {

    /*****************************************************************************************
     * ===================================[ PRIVATE FIELDS ]================================ *
     *****************************************************************************************/

    private int loop;                   //Should the sound loop? -1: infinite, 0: no, >0: specific loop
    private int soundPriority;          //Sound priority
    private boolean hasCustomVolume;    //Sound won't use the system/music sound volume if set at TRUE
    private float customVolume;         //User defined volume
    private float maxVolume;            //System maximum volume
    private float rate;                 //Sound playback rate. 1: normal
    private SoundPool soundPool;        //The SoundPool object managing playback and sound loading
    private AudioManager audioManager;  //The System audio manager


    /*****************************************************************************************
     * ===================================[ NESTED CLASSES ]================================ *
     *****************************************************************************************/

    //Thread class whose goal is to play a sound identified by an ID
    private class PlaySound extends Thread {
        private int soundId;

        PlaySound(int soundId) {
            this.soundId = soundId;
        }
        public void run() {
            soundPool.play(soundId, getFinalSoundVolume(), getFinalSoundVolume(), soundPriority, loop, rate);
        }
    }


    /*****************************************************************************************
     * ====================================[ CONSTRUCTORS ]================================= *
     *****************************************************************************************/

    //Constructor 1
    public SoundEngine(AudioManager newAudioManager) {
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC,0);
        loop = 0;
        soundPriority = 1;
        rate = 1f;
        audioManager = newAudioManager;
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }


    /*****************************************************************************************
     * =================================[ GETTORS / SETTORS ]=============================== *
     *****************************************************************************************/

    //Get the soundPool system
    public SoundPool getSoundPool() {
        return soundPool;
    }

    //Set the loop value for sound playback
    public void setLoop(int newLoop) {
        loop = newLoop;
    }

    //Set the priority value for sound playback
    public void setSoundPriority(int newPriority) {
        soundPriority = newPriority;
    }

    //Set the custom volume value for sound playback
    public void setCustomVolume(float newVolume) {
        customVolume = newVolume;
        hasCustomVolume = true;
    }

    //Set the rate value for sound playback
    public void setRate(float newRate) {
        rate = newRate;
    }


    /*****************************************************************************************
     * ==================================[ PUBLIC METHODS ]================================= *
     *****************************************************************************************/

    //Switch back to the system/music volume
    public void removeCustomVolume() {
        hasCustomVolume = false;
    }

    //Get the current system/music volume
    private float getCurVolume() {
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    //Start a thread to play the sound "soundId"
    public void playSound(int soundId) {
        PlaySound playSoundThread = new PlaySound(soundId);
        playSoundThread.start();
    }


    /*****************************************************************************************
     * =================================[ PRIVATE METHODS ]================================= *
     *****************************************************************************************/

    /****************[ getFinalSoundVolume ]*****************
     * Return the final volume used to play sounds          *
     * If the volume is custom, return the custom/max ratio *
     * Else return the current/max ratio                    *
     * Ratios are used because Max volume is sometimes      *
     * not equal to 1 on some devices/situation             *
     ********************************************************/
    private float getFinalSoundVolume() {
        if(hasCustomVolume) {
            return customVolume / maxVolume;
        }
        else {
            return getCurVolume() / maxVolume;
        }
    }
}
