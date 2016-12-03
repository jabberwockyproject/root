package com.gw150914.jabberwocky.core;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;

/**
 * Created by Eliott on 11/24/2016.
 */

public class Theme{

    //Private fields
    private static final int maxSoundPerTheme = 500;
    private static final int maxTheme = 20;
    private static String[] themeList = null;
    private String name;
    private int soundsCount;
    private Sound[] soundList;
    private ArrayList<String> soundNameList;

    //Public Fields

    //Constructors
    public Theme(){
        name = Theme.getNextGenericName();
        soundList = new Sound[maxSoundPerTheme];
        themeList = new String[maxTheme];
        soundNameList = new ArrayList<String>();
        soundsCount = 0;

        //TODO: add name to themeList
    }

    public Theme(String newName){
        name = newName;
        soundList = new Sound[maxSoundPerTheme];
        themeList = new String[maxTheme];
        soundNameList = new ArrayList<String>();
        soundsCount = 0;
        //TODO: add name to themeList
    }

    public Theme(String newName, Sound[] newSoundList){
        name = newName;
        soundList = newSoundList;
        themeList = new String[maxTheme];
        soundNameList = new ArrayList<String>();
        soundsCount = 0;
        //TODO: add name to themeList
    }

    //Private Methods
    private static String getNextGenericName(){
        //TODO: check themeList and return an unused generic name "New Theme $"
        return null;
    }

    private boolean checkSoundDuplicate (Sound soundToCheck){
        boolean duplicate = false;
        int index = 0;
        while(!duplicate && index < soundsCount) {
            Log.d("index value: " ,Integer.toString(index));
            if (soundToCheck.getSoundId() == soundList[index++].getSoundId()){
                duplicate = true;
            }
        }
        return duplicate;
    }

    //Public Methods
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }
    public Sound getRandomSound(){
        //TODO
        return null;
    }

    /*
     * Return values:
     * 0: Success
     * 1: Failed to add sound (duplicate)
     * 2: Failed to add sound (Index out of bound)
     * 3: Failed to add sound (other errors)
     */
    public int addSound(Sound newSound){
        if (!checkSoundDuplicate(newSound)){
            try{
                soundList[soundsCount] = newSound;
                soundNameList.add(soundsCount,newSound.getName());
            }
            catch(IndexOutOfBoundsException e){
                return 2;
            }
            catch(Exception e){
                return 3;
            }
            ++soundsCount;
            return 0;

        }
        else{
            return 1;
        }

    }

    public void removeSound(Sound oldSound){
        --soundsCount;
        //TODO
    }

    public ArrayList<String> getSoundNameList(){
        return soundNameList;
    }

    public Sound[] getSoundList(){
        return soundList;
    }

    public Sound getSound(int index){
        return soundList[index];
    }
}
