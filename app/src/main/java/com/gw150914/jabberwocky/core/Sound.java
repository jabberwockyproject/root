package com.gw150914.jabberwocky.core;


/*+---------[ Sound.class ]------------+
  |The Sound class represents a sound. |
  |Sounds Are loaded in memory and have|
  |a name and a ID.                    |
  |IDs are used by SoundPools to find  |
  |sounds in memory                    |
  +------------------------------------+*/

public class Sound {


    /******************
     * PRIVATE FIELDS *
     ******************/

    private String name;    //Sound name.
    private int soundId;    //Sound ID. provided by a SoundPool. CHANGES AT EACH COLD-START


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

    //Return Sound name
    public String getName() {
        return name;
    }

    //Return Sound ID
    public int getSoundId() {
        return soundId;
    }

    //Set Sound name
    public void setName(String newName) {
        name = newName;
    }

    //Set Sound ID
    public void setSoundID(int newSoundId) {
        soundId = newSoundId;
    }
}
