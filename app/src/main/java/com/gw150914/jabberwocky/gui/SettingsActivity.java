package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.gw150914.jabberwocky.R;

public class SettingsActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{

    public static final String SETTINGS_FILE_NAME = "Settings";
    private int LINEAR_LOADING_THREAD_DEFAULT = 2;

    int linearLoadingThread; //Package wide

    int linearLoadingThreadButton0Id, linearLoadingThreadButton1Id, linearLoadingThreadButton2Id, linearLoadingThreadButton3Id;
    RadioButton linearLoadingThreadButton0, linearLoadingThreadButton1, linearLoadingThreadButton2, linearLoadingThreadButton3;
    RadioGroup linearLoadingThreadRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button acceptButton             = (Button) findViewById(R.id.settings_accept_button);
        Button cancelButton             = (Button) findViewById(R.id.settings_cancel_button);
        linearLoadingThreadButton0      = (RadioButton) findViewById(R.id.linear_loading_thread_0);
        linearLoadingThreadButton1      = (RadioButton) findViewById(R.id.linear_loading_thread_1);
        linearLoadingThreadButton2      = (RadioButton) findViewById(R.id.linear_loading_thread_2);
        linearLoadingThreadButton3      = (RadioButton) findViewById(R.id.linear_loading_thread_3);
        linearLoadingThreadRadioGroup   = (RadioGroup) findViewById(R.id.linear_loading_thread_radio_group);

        acceptButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        linearLoadingThreadButton0Id = linearLoadingThreadButton0.getId();
        linearLoadingThreadButton1Id = linearLoadingThreadButton1.getId();
        linearLoadingThreadButton2Id = linearLoadingThreadButton2.getId();
        linearLoadingThreadButton3Id = linearLoadingThreadButton3.getId();
        linearLoadingThreadRadioGroup.setOnCheckedChangeListener(this);

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

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(SETTINGS_FILE_NAME, 0);
        linearLoadingThread = settings.getInt("linearLoadingThread", LINEAR_LOADING_THREAD_DEFAULT);
        if(linearLoadingThread == 0) {
            linearLoadingThreadButton0.setChecked(true);
        }
        if(linearLoadingThread == 1) {
            linearLoadingThreadButton1.setChecked(true);
        }
        if(linearLoadingThread == 2) {
            linearLoadingThreadButton2.setChecked(true);
        }
        if(linearLoadingThread == 3) {
            linearLoadingThreadButton3.setChecked(true);
        }
    }

    public void onClick(View view) {

        if (view == findViewById(R.id.settings_accept_button)) {

            SharedPreferences settings = getSharedPreferences(SETTINGS_FILE_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("linearLoadingThread", linearLoadingThread);
            editor.commit();

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
            if(item == linearLoadingThreadButton0Id) {
                linearLoadingThread = 0;
            }
            if(item == linearLoadingThreadButton1Id) {
                linearLoadingThread = 1;
            }
            if(item == linearLoadingThreadButton2Id) {
                linearLoadingThread = 2;
            }
            if(item == linearLoadingThreadButton3Id) {
                linearLoadingThread = 3;
            }
        }
    }
}

