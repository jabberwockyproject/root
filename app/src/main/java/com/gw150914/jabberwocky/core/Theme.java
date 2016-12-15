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

    //Get the quantity of sounds in this theme
    public int getSoundsCount() {
        return soundsCount;
    }

    //Get this theme's name
    public String getName() {
        return name;
    }

    //Get this theme's sound list.
    public Sound[] getSoundList() {
        return soundList;
    }

    //Get a specific sound in this theme identified at "index" position
    public Sound getSound(int index) {
        return soundList[index];
    }

    //Get the name of sounds in this theme.
    public ArrayList<String> getSoundNameList() {
        return soundNameList;
    }

    //Set the name of this theme
    public void setName(String newName) {
        name = newName;
    }


    /*****************************************************************************************
     * ==================================[ PUBLIC METHODS ]================================= *
     *****************************************************************************************/

    //Get a random sound contained in this theme
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

        //If the sound is not already present in this theme.
        if(!checkSoundDuplicate(newSound)) {
            try {
                soundList[soundsCount] = newSound;                  //Add the new sound in soundList
                soundNameList.add(soundsCount,newSound.getName());  //Add the new sound's name in soundNameList
            }

            //Out of bound error
            catch(IndexOutOfBoundsException e) {
                return 2;
            }

            //Other errors
            catch(Exception e) {
                return 3;
            }

            //Increment soundsCount and return 0 (success)
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

        boolean soundFound = false; //Has the sound been found?
        int index = 0;              //Current working index

        //Iterate through the soundList until the sound is found or all sounds are scanned.
        while(!soundFound && index < soundsCount) {

            //If the sound is a match, set soundFound as true. Sounds' soundIds are used to find a match.
            if (oldSound.getSoundId() == soundList[index].getSoundId()) {
                soundFound = true;
            }

            //If sound sound isn't a match, increment the working index.
            else{
                ++index;
            }
        }

        //If the sound has been found.
        if(soundFound) {

            //Remove it from soundNameList
            soundNameList.remove(index);

            //Remove it from soundList and shift left all sounds from index + 1 to the soundList array's end.
            while(index < (soundsCount - 1)) {
                soundList[index]=soundList[++index];
            }

            //Decrement soundsCount and return true.
            --soundsCount;
            return true;
        }

        //If the sound hasn't been found, return false.
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
                    soundNameList.set(index, soundNameList.get(index + 1)); //Move soundNameList @index+1 to index
                    soundNameList.set(index + 1,soundNameBuffer);           //Move buffered soundNameList to index+1

                    soundBuffer = soundList[index];                         //Buffer soundList @index
                    soundList[index] = soundList[index + 1];                //Move soundList @index+1 to index
                    soundList[index + 1] = soundBuffer;                     //Move buffered soundList to index+1

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

        boolean isConsistent = true;    //Is the index consistent?
        int index = 0;                  //Current working index.

        //Iterate through both lists and check consistency. Break as soon as inconsistency is detected.
        while(isConsistent && index < soundsCount) {
            if(!(soundList[index].getName().equals(soundNameList.get(index)))) {
                isConsistent = false;
            }
        }
        return isConsistent;
    }

    //Check whether a sound is already in the soundList. Internal use only.
    private boolean checkSoundDuplicate(Sound soundToCheck) {

        boolean duplicate = false;  //Is the sound a duplicate? (already present in this theme)
        int index = 0;              //Current working index.

        //Iterate through soundList and look for a match. Break and set duplicate at true as soon as a match is found.
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

/*
TERRE DES HOMMES - A.deSaintExupery
**
La terre ainsi est à la fois déserte et riche.
Riche de ces jardins secrets, cachés, difficiles à atteindre,
mais auxquels le métier nous ramène toujours, un jour ou l’autre.
Les camarades, la vie peut-être nous en écarte, nous empêche d’y beaucoup penser,
mais ils sont quelque part, on ne sait trop où, silencieux et oubliés, mais tellement fidèles !
Et si nous croisons leur chemin, ils nous secouent par les épaules avec de belles flambées de joie !
Bien sûr, nous avons l’habitude d’attendre…
**
Mais peu à peu nous découvrons que le rire clair de celui-là nous ne l’entendrons plus jamais,
nous découvrons que ce jardin-là nous est interdit pour toujours.
Alors commence notre deuil véritable qui n’est point déchirant mais un peu amer.

Rien, jamais, en effet, ne remplacera le compagnon perdu. On ne se crée point de vieux camarades.
Rien ne vaut le trésor de tant de souvenirs communs, de tant de brouilles, de réconciliations, de mouvements du cœur.
On ne reconstruit pas ces amitiés-là. Il est vain, si l’on plante un chêne, d’espérer s’abriter bientôt sous son feuillage.

Ainsi va la vie. Nous nous sommes enrichis d’abord, nous avons planté pendant des années,
mais viennent les années où le temps défait ce travail et déboise.
Les camarades, un à un, nous retirent leur ombre. Et à nos deuils se mêle désormais le regret secret de vieillir.
*/


}


