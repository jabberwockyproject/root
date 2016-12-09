package com.gw150914.jabberwocky.core;


/*
###################################[ Sound.class ]#####################################
# The Sound class is designed to hold information about an unique sound and provides  #
# various methods to manipulate these information.                                    #
#######################################################################################
*/

public class Sound {


    /*****************************************************************************************
     * ===================================[ PRIVATE FIELDS ]================================ *
     *****************************************************************************************/

    private String name;    //Sound name.
    private int soundId;    //Sound ID. provided by a SoundPool. CHANGES AT EACH COLD-START


    /*****************************************************************************************
     * ====================================[ CONSTRUCTORS ]================================= *
     *****************************************************************************************/

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


    /*****************************************************************************************
     * =================================[ GETTORS / SETTORS ]=============================== *
     *****************************************************************************************/

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
