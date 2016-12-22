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
    private int resId;      //Resource ID.
    private int soundId;    //Sound ID. provided by a SoundPool. CHANGES AT EACH COLD-START
    private boolean onDemandFlag;


    /*****************************************************************************************
     * ====================================[ CONSTRUCTORS ]================================= *
     *****************************************************************************************/

    //Constructor 1
    public Sound(String name) {
        this.name = name;
        resId = 0;
        soundId = 0;
        onDemandFlag = false;
    }

    //Constructor 2
    public Sound(String name, int resId) {
        this.name = name;
        this.resId = resId;
        soundId = 0;
        onDemandFlag = false;
    }

    //Constructor 3
    public Sound(String name, int resId, int soundId) {
        this.name = name;
        this.resId = resId;
        this.soundId = soundId;
        onDemandFlag = false;
    }

    /*****************************************************************************************
     * =================================[ GETTORS / SETTORS ]=============================== *
     *****************************************************************************************/

    //Return Sound name
    public String getName() {
        return name;
    }

    //Return Resource ID
    public int getResId() {
        return resId;
    }

    //Return Sound ID
    public int getSoundId() {
        return soundId;
    }

    //Return onDemand flag
    public boolean getonDemandFlag() {
        return onDemandFlag;
    }

    //Set Sound name
    public void setName(String name) {
        this.name = name;
    }

    //Set Resource ID
    public void setResId(int resId) {
        this.resId = resId;
    }

    //Set Sound ID
    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }

    //Set onDemand flag
    public void setOnDemandFlag(boolean flag) {
        onDemandFlag = flag;
    }
}

/*
MEDITATIONS VII.XL - M.Aurelius
**
Then hath a man attained to the estate of perfection in his life and conversation,
when he so spends every day, as if it were his last day:
never hot and vehement in his affections,
nor yet so cold and stupid as one that had no sense;
and free from all manner of dissimulation.
*/
