package com.gw150914.jabberwocky.core;

import java.util.ArrayList;

public class Theme {

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
    public Theme() {
        name = Theme.getNextGenericName();
        soundList = new Sound[maxSoundPerTheme];
        themeList = new String[maxTheme];
        soundNameList = new ArrayList<String>();
        soundsCount = 0;

        //TODO: add name to themeList
    }

    public Theme(String newName) {
        name = newName;
        soundList = new Sound[maxSoundPerTheme];
        themeList = new String[maxTheme];
        soundNameList = new ArrayList<String>();
        soundsCount = 0;
        //TODO: add name to themeList
    }

    public Theme(String newName, Sound[] newSoundList) {
        name = newName;
        soundList = newSoundList;
        themeList = new String[maxTheme];
        soundNameList = new ArrayList<String>();
        soundsCount = 0;
        //TODO: add name to themeList
    }

    //Private Methods
    private static String getNextGenericName() {
        //TODO: check themeList and return an unused generic name "New Theme $"
        return null;
    }

    private boolean checkSoundDuplicate(Sound soundToCheck) {
        boolean duplicate = false;
        int index = 0;
        while(!duplicate && index < soundsCount) {
            if (soundToCheck.getSoundId() == soundList[index++].getSoundId()) {
                duplicate = true;
            }
        }
        return duplicate;
    }

    //Check if soundList and soundNameList have a consistent index. Internal use only.
    private boolean checkIndexConsistency(){
        boolean isConsistent = true;
        int index = 0;
        while(isConsistent && index < soundsCount){
            if(!(soundList[index].getName().equals(soundNameList.get(index)))){
                isConsistent = false;
            }
        }
        return isConsistent;
    }

    //Public Methods
    public String getName() {
        return name;
    }
    public void setName(String newName) {
        name = newName;
    }

    public void sortIndex(){
        boolean sorted = false;     //lists are sorted
        boolean hasDoneSomething;   //Something has been done during this pass
        int index;                  //Current working index
        Sound soundBuffer;          //Sound buffer used during index/index+1 soundList switches
        String soundNameBuffer;     //String buffer used during index/index+1 soundNameList switches

        //Repeat passes until nothing has been done during a pass (meaning the list is sorted
        while(!sorted){

            //reset Something has been done during this pass to false
            hasDoneSomething = false;

            //Do a pass. Stop at soundCount-1 to prevent out of boundary accesses.
            for(index=0; index < (soundsCount-1); ++index){

                //If soundNameList @index+1 is strictly inferior to soundNameList @index
                //Then we need to switch index with index+1 values
                if(soundNameList.get(index).compareToIgnoreCase(soundNameList.get(index+1)) > 0){

                    soundNameBuffer = soundNameList.get(index);             //Buffer soundNameList @index
                    soundNameList.set(index, soundNameList.get(index+1));   //Move soundNameList @index+1 to index
                    soundNameList.set(index+1,soundNameBuffer);             //Move buffered soundNameList to index+1

                    soundBuffer = soundList[index];                         //Buffer soundList @index
                    soundList[index] = soundList[index+1];                  //Move soundList @index+1 to index
                    soundList[index+1] = soundBuffer;                       //Move buffered soundList to index+1

                    //Something has been done during this pass
                    hasDoneSomething = true;
                }
            }

            //If nothing has been done during this pass, then the lists are sorted.
            if(!hasDoneSomething){
                sorted = true;
            }
        }
    }

    public Sound getRandomSound() {
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
    public int addSound(Sound newSound) {
        if (!checkSoundDuplicate(newSound)) {
            try{
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
        else{
            return 1;
        }
    }

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
        if (soundFound) {
            soundNameList.remove(index);
            while (index < (soundsCount -1)) {
                soundList[index]=soundList[++index];
            }
            --soundsCount;
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<String> getSoundNameList() {
        return soundNameList;
    }

    public Sound[] getSoundList() {
        return soundList;
    }

    public int getSoundsCount() {
        return soundsCount;
    }

    public Sound getSound(int index) {
        return soundList[index];
    }
}
