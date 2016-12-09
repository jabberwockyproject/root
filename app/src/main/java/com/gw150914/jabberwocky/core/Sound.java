package com.gw150914.jabberwocky.core;

public class Sound {


    /******************
     * PRIVATE FIELDS *
     ******************/

    private String name;
    private int soundId;


    /****************
     * CONSTRUCTORS *
     ****************/

    //Constructor 1
    public Sound() {
        name = "unnamed";
        soundId = 0;
    }

    //Constructor 2
    public Sound(String newName) {
        name = newName;
        soundId = 0;
    }

    //Constructor 3
    public Sound(String newName, int newSoundId) {
        name = newName;
        soundId = newSoundId;
    }


    /*********************
     * GETTORS / SETTORS *
     *********************/

    public String getName() {
        return name;
    }

    public int getSoundId() {
        return soundId;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setSoundID(int newSoundId) {
        soundId = newSoundId;
    }
}
