package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.gw150914.jabberwocky.R;
import com.gw150914.jabberwocky.core.Theme;
import com.gw150914.jabberwocky.core.Sound;

public class MainActivity extends Activity implements View.OnClickListener,View.OnLongClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    private ArrayAdapter<String> adapter;
    private Theme all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list;

        Button searchButton = (Button) findViewById(R.id.search_button);
        Button themeButton = (Button) findViewById(R.id.theme_button);
        Button randomButton = (Button) findViewById(R.id.random_button);
        Button settingsButton = (Button) findViewById(R.id.settings_button);
        ListView soundList = (ListView) findViewById(R.id.soundList);

        searchButton.setOnClickListener(this);
        searchButton.setOnLongClickListener(this);
        themeButton.setOnClickListener(this);
        themeButton.setOnLongClickListener(this);
        randomButton.setOnClickListener(this);
        randomButton.setOnLongClickListener(this);
        settingsButton.setOnClickListener(this);
        settingsButton.setOnLongClickListener(this);
        soundList.setOnItemClickListener(this);
        soundList.setOnItemLongClickListener(this);

        all = new Theme("all");

        /*-------------------------------------
        Below code is for testing purposes only
        -------------------------------------*/
        final int maxSound = 40;

        list = (ListView) findViewById(R.id.soundList);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,all.getSoundNameList());

        list.setAdapter(adapter);

        for (int i = 0; i < maxSound; i++){
            String soundName = "Sample #" + Integer.toString(i);
            all.addSound(new Sound(soundName));
        }

        //update display
        adapter.notifyDataSetChanged();
        /*-----------------
        End of testing code
        -----------------*/
    }

    //Sound onClick handle
    //DOES NOT SEEM TO WORK
    public void soundClickHandler(View v) {
        all.addSound(new Sound("sample"));
        adapter.notifyDataSetChanged();
    }
    //Button click handler
    public void onClick(View v) {
        if(findViewById(R.id.search_button) == v) {
            all.addSound(new Sound("search"));
            adapter.notifyDataSetChanged();
        }
        if(findViewById(R.id.theme_button) == v) {
            all.addSound(new Sound("theme"));
            adapter.notifyDataSetChanged();
        }
        if(findViewById(R.id.random_button) == v) {
            all.addSound(new Sound("random"));
            adapter.notifyDataSetChanged();
        }
        if(findViewById(R.id.settings_button) == v) {
            all.addSound(new Sound("settings"));
            adapter.notifyDataSetChanged();
        }
    }
    //Button Long Click (context) handler
    public boolean onLongClick(View v) {
        if(findViewById(R.id.search_button) == v) {
            all.addSound(new Sound("l-search"));
            adapter.notifyDataSetChanged();
            return true;
        }
        if(findViewById(R.id.theme_button) == v) {
            all.addSound(new Sound("l-theme"));
            adapter.notifyDataSetChanged();
            return true;
        }
        if(findViewById(R.id.random_button) == v) {
            all.addSound(new Sound("l-random"));
            adapter.notifyDataSetChanged();
            return true;
        }
        if(findViewById(R.id.settings_button) == v) {
            all.addSound(new Sound("l-settings"));
            adapter.notifyDataSetChanged();
            return true;
        }
        return false;
    }
    public void onItemClick(AdapterView parent, View v, int pos, long id){
        if(findViewById(R.id.soundList) == parent) {
            all.addSound(new Sound(Integer.toString(pos)));
            adapter.notifyDataSetChanged();
        }
    }
    public boolean onItemLongClick(AdapterView parent, View v, int pos, long id){
        if(findViewById(R.id.soundList) == parent) {
            all.addSound(new Sound(Long.toString(id)));
            adapter.notifyDataSetChanged();
            return true;
        }
        return false;
    }
}