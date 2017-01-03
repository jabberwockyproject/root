package com.gw150914.jabberwocky.core;


/*
################################[ ThemeEngine.class ]##################################
# The ThemeEngine class is designed to hold a collection of themes and various        #
# methods to manipulate and check these stored themes.                                #
#######################################################################################
*/

import android.content.Context;

import com.gw150914.jabberwocky.R;

public class ThemeEngine {


    /*****************************************************************************************
     * ===================================[ PRIVATE FIELDS ]================================ *
     *****************************************************************************************/

    private static final int maxTheme = 30; //Maximum number of themes in a ThemeEngine. Final and static.
    private int themeCount;                 //Number of themes contained in this ThemeEngine.
    private Theme[] themeList;              //List of themes contained in this ThemeEngine.
    private Theme currentTheme;             //Current active theme of this ThemeEngine (displayed theme).


    /*****************************************************************************************
     * ====================================[ CONSTRUCTORS ]================================= *
     *****************************************************************************************/

    //Constructor 1
    public ThemeEngine(){
        themeCount = 0;
        themeList = new Theme[maxTheme];
    }


    /*****************************************************************************************
     * =================================[ GETTORS / SETTORS ]=============================== *
     *****************************************************************************************/

    //Get the number of themes contained in this ThemeEngine.
    public int getThemeCount() {
        return themeCount;
    }

    //Get the list of themes contained in this ThemeEngine.
    public Theme[] getThemeList() {
        return themeList;
    }

    //Get the current active theme of this theme.
    public Theme getCurrentTheme() {
        return currentTheme;
    }

    //Get a specific theme from themeList at the "index" position.
    public Theme getTheme(int index) {
        return themeList[index];
    }

    //Set the current active theme of the ThemeEngine to "newTheme".
    public void setCurrentTheme(Theme newTheme) {
        currentTheme = newTheme;
    }


    /*****************************************************************************************
     * ==================================[ PUBLIC METHODS ]================================= *
     *****************************************************************************************/

    /*****************[ getCleanThemeList ]******************
     * Return themeList with the correct size.              *
     * Create an array with themeCount as max size.         *
     * Fill it with the relevant values and return it.      *
     * Use it when half empty array are problematic so      *
     * you cannot use getThemeList().                       *
     *******************************************************/
    public Theme[] getCleanThemeList() {
        Theme[] cleanThemeList = new Theme[themeCount];
        for(int index = 0; index < themeCount; ++themeCount) {
            cleanThemeList[index] = themeList[index];
        }
        return cleanThemeList;
    }

    /*****************[ getThemeNameList ]******************
     * From themeList, extract only the names and return   *
     * an array of names corresponding to themes contained *
     * in this ThemeEngine.                                *
     *******************************************************/
    public String[] getThemeNameList() {
        String[] themeNameList = new String [themeCount];
        for (int index = 0; index < themeCount; ++index) {
            themeNameList[index] = themeList[index].getName();
        }
        return themeNameList;
    }

    /*********************[ addTheme ]**********************
     * Add a new Theme to this ThemeEngine.                *
     * Will only work if this ThemeEngine isn't full and   *
     * the new theme name is not already used by another   *
     * theme in this themeEngine themeList.                *
     * If no theme were present before, set this new theme *
     * as current theme.                                   *
     *******************************************************/
    public boolean addTheme(Theme newTheme) {
        if(themeCount < maxTheme && !checkThemeDuplicate(newTheme)) {
            themeList[themeCount++] = newTheme;
            if(themeCount == 1) {
                currentTheme = newTheme;
            }
            return true;
        }
        else {
            return false;
        }
    }

    //Remove a Theme from this ThemeEngine.
    public boolean removeTheme(Theme oldTheme) {

        //Get oldTheme position in themeList
        int position = getThemeIndex(oldTheme);

        //oldTheme is present in ThemeEngine.
        if(position >= 0) {

            //Shift left all themes from index + 1 to the themeList array's end.
            for(int index = position; index < themeCount - 1; ++index) {
                themeList[index] = themeList[index + 1];
            }
            --themeCount;
            return true;
        }

        //oldTheme is not present in themeList so nothing was removed.
        else {
            return false;
        }
    }

    //Return a String informing the user about the current active theme.
    //Will return null if no theme are present or if currentTheme is null.
    public String getCurrentThemeString(Context context) {
        if(themeCount > 0 && currentTheme != null) {
            return (context.getString(R.string.current_theme)+" "+currentTheme.getName());
        }
        else{
            return null;
        }
    }


    /*****************************************************************************************
     * =================================[ PRIVATE METHODS ]================================= *
     *****************************************************************************************/

    //Check whether a theme is already using the name of themeToCheck in the themeList. Internal use only.
    private boolean checkThemeDuplicate(Theme themeToCheck) {

        boolean duplicate = false;  //Is the theme a duplicate? (already present in this themeEngine)
        int index = 0;              //Current working index.

        //Iterate through themeList and look for a match. Break and set duplicate at true as soon as a match is found.
        while (!duplicate && index < themeCount) {
            if(themeToCheck.getName().compareTo(themeList[index++].getName()) == 0) {
                duplicate = true;
            }
        }
        return duplicate;
    }

    //Search for a theme position in themeList. Return -1 if not found.
    private int getThemeIndex(Theme themeToCheck){

        boolean nameFound = false;  //Is the themeName found?
        int index = 0;              //Current working index

        //Iterate through themeList and look for a match. Break and set nameFound to true as soon as a match is found.
        while (!nameFound && index < themeCount) {
            if(themeToCheck == themeList[index++]) {
                nameFound = true;
            }
        }

        //If a mach was found, return where.
        if(nameFound) {
            return index;
        }

        //If no match were found, return -1
        else {
            return -1;
        }
    }

    //Same as getThemeIndex but should be faster.
    private int getThemeNameIndex(String themeNameToCheck){

        boolean nameFound = false;  //Is the themeName found?
        int index = 0;              //Current working index

        //Iterate through themeList and look for a match. Break and set nameFound to true as soon as a match is found.
        while (!nameFound && index < themeCount) {
            if(themeNameToCheck.compareTo(themeList[index++].getName()) == 0) {
                nameFound = true;
            }
        }

        //If a mach was found, return where.
        if(nameFound) {
            return index;
        }

        //If no match were found, return -1
        else {
            return -1;
        }
    }
}
