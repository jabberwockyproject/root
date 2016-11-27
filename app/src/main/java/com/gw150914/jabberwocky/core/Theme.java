package com.gw150914.jabberwocky.core;

import java.util.ArrayList;

/**
 * Created by Eliott on 11/24/2016.
 */

public class Theme{

    //Private fields
    private static final int maxSound = 500;
    private static String[] themeList = null;
    private String name;
    private int soundsCount;
    private Sound[] soundList;
    private ArrayList<String> soundNameList;

    //Public Fields

    //Constructors
    public Theme(){
        name = Theme.getNextGenericName();
        soundList = new Sound[maxSound];
        soundNameList = new ArrayList<String>();
        soundsCount = 0;
        //TODO: add name to themeList
    }
    public Theme(String newName){
        name = newName;
        soundList = new Sound[maxSound];
        soundNameList = new ArrayList<String>();
        soundsCount = 0;
        //TODO: add name to themeList
    }
    public Theme(String newName, Sound[] newSoundList){
        name = newName;
        soundList = newSoundList;
        soundNameList = new ArrayList<String>();
        soundsCount = 0;
        //TODO: add name to themeList
    }

    //Private Methods
    private static String getNextGenericName(){
        //TODO: check themeList and return an unused generic name "New Theme $"
        return null;
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
    public void addSound(Sound newSound){
        soundList[soundsCount] = newSound;
        soundNameList.add(soundsCount,newSound.getName());
        soundsCount++;
        //TODO: Check for duplicates
    }
    public void removeSound(Sound oldSound){
        soundsCount--;
        //TODO
    }
    public ArrayList<String> getSoundNameList(){
        return soundNameList;
    }
}
