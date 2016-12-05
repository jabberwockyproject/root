package com.gw150914.jabberwocky.core;

public class Sound {
    private String name;
    private int soundId;

    //TODO: Constructors
    public Sound() {
        name = "unnamed";
        soundId = 0;
    }
    public Sound(String newName) {
        name = newName;
        soundId = 0;
    }
    public Sound(String newName, int newSoundId) {
        name = newName;
        soundId = newSoundId;
    }
    public String getName() {
        return name;
    }
    public void setName(String newName) {
        name = newName;
    }
    public int getSoundId() {
        return soundId;
    }
    public void setSoundID(int newSoundId) {
        soundId = newSoundId;
    }
}
