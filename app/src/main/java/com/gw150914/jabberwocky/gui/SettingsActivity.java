package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.gw150914.jabberwocky.R;

public class SettingsActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{

    int linearLoadingThread = 5; //Package wide

    int loadingThreadButton0Id, loadingThreadButton1Id, loadingThreadButton2Id, loadingThreadButton3Id;
    RadioButton loadingThreadButton0, loadingThreadButton1, loadingThreadButton2, loadingThreadButton3;
    RadioGroup loadingThreadRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button acceptButton     = (Button) findViewById(R.id.settings_accept_button);
        Button cancelButton     = (Button) findViewById(R.id.settings_cancel_button);
        loadingThreadButton0    = (RadioButton) findViewById(R.id.linear_loading_thread_0);
        loadingThreadButton1    = (RadioButton) findViewById(R.id.linear_loading_thread_1);
        loadingThreadButton2    = (RadioButton) findViewById(R.id.linear_loading_thread_2);
        loadingThreadButton3    = (RadioButton) findViewById(R.id.linear_loading_thread_3);
        loadingThreadRadioGroup = (RadioGroup) findViewById(R.id.linear_loading_thread_radio_group);

        acceptButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        loadingThreadButton0Id = loadingThreadButton0.getId();
        loadingThreadButton1Id = loadingThreadButton1.getId();
        loadingThreadButton2Id = loadingThreadButton2.getId();
        loadingThreadButton3Id = loadingThreadButton3.getId();
        loadingThreadRadioGroup.setOnCheckedChangeListener(this);

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

        if (view == findViewById(R.id.settings_accept_button)) {
            System.out.println("DEBUG: linearLoadingThread: " + linearLoadingThread);
            Intent intent = new Intent();
            intent.putExtra("linearLoadingThread", linearLoadingThread);
            setResult(1, intent);
            finish();
        }
        if (view == findViewById(R.id.settings_cancel_button)) {
            setResult(0);
            finish();
        }
    }
    public void onCheckedChanged(RadioGroup radioGroup, int item) {
        if(radioGroup == findViewById(R.id.linear_loading_thread_radio_group)) {
            System.out.println("DEBUG: Checked ID: " + item);
            if(item == loadingThreadButton0Id) {
                linearLoadingThread = 0;
            }
            if(item == loadingThreadButton1Id) {
                linearLoadingThread = 1;
            }
            if(item == loadingThreadButton2Id) {
                linearLoadingThread = 2;
            }
            if(item == loadingThreadButton3Id) {
                linearLoadingThread = 3;
            }
        }
    }
}

