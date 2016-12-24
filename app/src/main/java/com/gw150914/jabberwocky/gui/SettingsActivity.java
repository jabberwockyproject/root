package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import com.gw150914.jabberwocky.R;

public class SettingsActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button acceptButton = (Button) findViewById(R.id.settings_accept_button);
        Button cancelButton = (Button) findViewById(R.id.settings_cancel_button);

        acceptButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        TabHost tabHost = (TabHost)findViewById(R.id.settings_tab_host);
        tabHost.setup();

        //Display Tab
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Display");
        tabSpec.setContent(R.id.settings_tab_display);
        tabSpec.setIndicator(getString(R.string.display_tab_name));
        tabHost.addTab(tabSpec);

        //Sound Tab
        tabSpec = tabHost.newTabSpec("Sound");
        tabSpec.setContent(R.id.settings_tab_sound);
        tabSpec.setIndicator(getString(R.string.sound_tab_name));
        tabHost.addTab(tabSpec);

        //Advanced Tab
        tabSpec = tabHost.newTabSpec("Advanced");
        tabSpec.setContent(R.id.settings_tab_advanced);
        tabSpec.setIndicator(getString(R.string.advanced_tab_name));
        tabHost.addTab(tabSpec);
    }

    public void onClick(View view) {

        if(view == findViewById(R.id.settings_accept_button)) {
            String [] settingsBundle = new String[30];
            Intent intent = new Intent();
            intent.putExtra("settings", settingsBundle);
            setResult(1,intent);
            finish();
        }
        if(view == findViewById(R.id.settings_cancel_button)) {
            setResult(0);
            finish();
        }

    }

}

