package com.gw150914.jabberwocky.core;

import java.util.ArrayList;
import java.util.Random;


/*
###################################[ Theme.class ]#####################################
# The Theme class is designed to hold information about an unique theme and provides  #
# various methods to manipulate and check these information.                          #
# A Theme is a Sound collection. Usually only one theme is shown to the user.         #
#######################################################################################
*/

public class Theme {


    /*****************************************************************************************
     * ===================================[ PRIVATE FIELDS ]================================ *
     *****************************************************************************************/

    private static final int maxSoundPerTheme = 500;    //Maximum sounds a theme can hold. Final
    private int soundsCount;                            //Number of sound a theme contains.
    private String name;                                //Theme name.
    private Sound[] soundList;                          //List of sounds a theme contains
    private ArrayList<String> soundNameList;            //Names of sounds contained in a theme.


    /*****************************************************************************************
     * ====================================[ CONSTRUCTORS ]================================= *
     *****************************************************************************************/

    //Constructor 1
    public Theme() {
        name = Theme.getNextGenericName();
        soundList = new Sound[maxSoundPerTheme];
        soundNameList = new ArrayList<String>();
        soundsCount = 0;
    }

    //Constructor 2
    public Theme(String newName) {
        name = newName;
        soundList = new Sound[maxSoundPerTheme];
        soundNameList = new ArrayList<String>();
        soundsCount = 0;
    }

    //Constructor 3
    public Theme(String newName, Sound[] newSoundList) {
        name = newName;
        soundList = newSoundList;
        soundNameList = new ArrayList<String>();
        soundsCount = 0;
    }


    /*****************************************************************************************
     * =================================[ GETTORS / SETTORS ]=============================== *
     *****************************************************************************************/

    //Get the quantity of sounds in a theme
    public int getSoundsCount() {
        return soundsCount;
    }

    //Get a theme's name
    public String getName() {
        return name;
    }

    //Get a theme's sound list.
    public Sound[] getSoundList() {
        return soundList;
    }

    //Get a specific sound in a theme identified at "index" position
    public Sound getSound(int index) {
        return soundList[index];
    }

    //Get the name of sounds in a theme.
    public ArrayList<String> getSoundNameList() {
        return soundNameList;
    }

    //Set the name of a theme
    public void setName(String newName) {
        name = newName;
    }


    /*****************************************************************************************
     * ==================================[ PUBLIC METHODS ]================================= *
     *****************************************************************************************/

    //Get a random sound contained in a theme
    public Sound getRandomSound() {
        Random random = new Random();
        return soundList[random.nextInt(soundsCount)];
    }


    /**********************[ addSound ]**********************
     * Add a sound in a theme. Will only work if the sound  *
     * is not already present in this theme.                *
     * Both soundList and soundNameList are updated         *
     * Returns values:                                      *
     * 0: Success                                           *
     * 1: Failed to add sound (duplicate)                   *
     * 2: Failed to add sound (Index out of bound)          *
     * 3: Failed to add sound (other errors)                *
     ********************************************************/
    public int addSound(Sound newSound) {
        if(!checkSoundDuplicate(newSound)) {
            try {
                soundList[soundsCount] = newSound;
                soundNameList.add(soundsCount,newSound.getName());
            }
            catch(IndexOutOfBoundsException e) {
                return 2;
            }
            catch(Exception e) {
                return 3;
            }
            ++soundsCount;
            return 0;
        }
        else {
            return 1;
        }
    }

    /*********************[ removeSound ]********************
     * Remove a sound in a theme.                           *
     * Both soundList and soundNameList are updated         *
     * Returns values:                                      *
     * False: Sound has not been found (no action)          *
     * True: Sound has been removed                         *
     ********************************************************/
    public boolean removeSound(Sound oldSound) {
        boolean soundFound = false;
        int index = 0;
        while(!soundFound && index < soundsCount) {
            if (oldSound.getSoundId() == soundList[index].getSoundId()) {
                soundFound = true;
            }
            else{
                ++index;
            }
        }
        if(soundFound) {
            soundNameList.remove(index);
            while(index < (soundsCount - 1)) {
                soundList[index]=soundList[++index];
            }
            --soundsCount;
            return true;
        }
        else {
            return false;
        }
    }

    /**********************[ sortIndex ]*********************
     * Sort both soundList and soundNameList in an          *
     * alphanumerical order                                 *
     ********************************************************/
    public void sortIndex() {
        boolean sorted = false;     //lists are sorted
        boolean hasDoneSomething;   //Something has been done during this pass
        int index;                  //Current working index
        Sound soundBuffer;          //Sound buffer used during index/index+1 soundList switches
        String soundNameBuffer;     //String buffer used during index/index+1 soundNameList switches

        //Repeat passes until nothing has been done during a pass (meaning the list is sorted)
        while(!sorted) {

            //reset Something has been done during this pass to false
            hasDoneSomething = false;

            //Do a pass. Stop at soundCount-1 to prevent out of boundary accesses.
            for(index = 0; index < (soundsCount - 1); ++index) {

                //If soundNameList @index+1 is strictly inferior to soundNameList @index
                //Then we need to switch index with index+1 values
                if(soundNameList.get(index).compareToIgnoreCase(soundNameList.get(index + 1)) > 0) {

                    soundNameBuffer = soundNameList.get(index);             //Buffer soundNameList @index
                    soundNameList.set(index, soundNameList.get(index + 1));   //Move soundNameList @index+1 to index
                    soundNameList.set(index + 1,soundNameBuffer);             //Move buffered soundNameList to index+1

                    soundBuffer = soundList[index];                         //Buffer soundList @index
                    soundList[index] = soundList[index + 1];                  //Move soundList @index+1 to index
                    soundList[index + 1] = soundBuffer;                       //Move buffered soundList to index+1

                    //Something has been done during this pass
                    hasDoneSomething = true;
                }
            }

            //If nothing has been done during this pass, then the lists are sorted.
            if(!hasDoneSomething) {
                sorted = true;
            }
        }
    }


    /*****************************************************************************************
     * =================================[ PRIVATE METHODS ]================================= *
     *****************************************************************************************/

    //Check if soundList and soundNameList have a consistent index. Internal use only.
    private boolean checkIndexConsistency() {
        boolean isConsistent = true;
        int index = 0;
        while(isConsistent && index < soundsCount) {
            if(!(soundList[index].getName().equals(soundNameList.get(index)))) {
                isConsistent = false;
            }
        }
        return isConsistent;
    }

    //Check whether a sound is already in the soundList. Internal use only.
    private boolean checkSoundDuplicate(Sound soundToCheck) {
        boolean duplicate = false;
        int index = 0;
        while(!duplicate && index < soundsCount) {
            if(soundToCheck.getSoundId() == soundList[index++].getSoundId()) {
                duplicate = true;
            }
        }
        return duplicate;
    }

    //Return a generic name for a new unnamed theme. Internal use only.
    private static String getNextGenericName() {
        //TODO
        return null;
    }
}


