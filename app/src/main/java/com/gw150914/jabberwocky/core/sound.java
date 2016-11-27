package com.gw150914.jabberwocky.core;

/**
 * Created by eliott on 11/24/2016.
 */

public class Sound {
    private String name;

    //TODO: Constructors
    public Sound (){
        name = "unnamed";
    }
    public Sound (String newName){
        name = newName;
    }
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }
}
