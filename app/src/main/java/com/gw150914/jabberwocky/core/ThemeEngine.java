package com.gw150914.jabberwocky.core;

public class ThemeEngine {
    private static final int maxTheme = 30;
    private int themeCount;
    private Theme[] themeList;
    private Theme currentTheme;

    public ThemeEngine(){
        themeCount = 0;
        themeList = new Theme[maxTheme];
    }

    public Theme[] getThemeList(){
        return themeList;
    }
    public String[] getThemeNameList(){
        String[] themeNameList = new String [themeCount-1];
        for (int index=0; index<themeCount; ++index){
            themeNameList[index] = themeList[index].getName();
        }
        return themeNameList;
    }
    public int getThemeCount(){
        return themeCount;
    }
    public boolean addTheme(Theme newTheme){
        if(themeCount < maxTheme){
            themeList[themeCount++] = newTheme;
            if(themeCount == 1){
                currentTheme = newTheme;
            }
            return true;
        }
        else{
            return false;
        }
    }
    public Theme getCurrentTheme(){
        return currentTheme;
    }
    public void setCurrentTheme(Theme newTheme){
        currentTheme = newTheme;
    }
}
