package com.gw150914.jabberwocky.core;


/*
################################[ ThemeEngine.class ]##################################
# The ThemeEngine class is designed to hold a collection of themes and various        #
# methods to manipulate and check these stored themes.                                #
#######################################################################################
*/

public class ThemeEngine {


    /*****************************************************************************************
     * ===================================[ PRIVATE FIELDS ]================================ *
     *****************************************************************************************/

    private static final int maxTheme = 30; //Maximum number of themes in a ThemeEngine. Final and static.
    private int themeCount;                 //Number of themes contained in this ThemeEngine.
    private Theme[] themeList;              //List of themes contained in this ThemeEngine.
    private Theme currentTheme;             //Current active theme of this ThemeEngine (displayed theme)


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

    public Theme[] getCleanThemeList() {
        Theme[] cleanThemeList = new Theme[themeCount];
        for(int index = 0; index < themeCount; ++themeCount) {
            cleanThemeList[index] = themeList[index];
        }
        return cleanThemeList;
    }

    public String[] getThemeNameList() {
        String[] themeNameList = new String [themeCount];
        for (int index = 0; index < themeCount; ++index) {
            themeNameList[index] = themeList[index].getName();
        }
        return themeNameList;
    }

    public boolean addTheme(Theme newTheme) {
        if(themeCount < maxTheme) {
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

    public boolean removeTheme(Theme oldTheme){
        //TODO
        return false;
    }
}
