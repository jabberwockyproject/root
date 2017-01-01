package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TabHost;

import com.gw150914.jabberwocky.R;

public class SettingsActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, Switch.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {


    /*****************************************************************************************
     * =============================[ PUBLIC FINAL STATIC FIELDS ]========================== *
     *****************************************************************************************/

    public final static boolean SHOW_HELP_DEFAULT = true;
    public final static boolean advSwitch1_DEFAULT = false;
    public final static boolean advSwitch2_DEFAULT = false;
    public final static boolean advSwitch3_DEFAULT = false;
    public final static boolean CUSTOM_VOLUME_DEFAULT = false;
    public final static int LINEAR_LOADING_THREAD_DEFAULT = 1;
    public final static int SMART_LOADING_THREAD_DEFAULT = 1;
    public final static int ONDEMAND_LOADING_THREAD_DEFAULT = 2;
    public final static int SKIN_ID_DEFAULT = 0;
    public final static int CUSTOM_VOLUME_VALUE_DEFAULT = 100;
    public final static int CUSTOM_VOLUME_MAX_DEFAULT = 100;
    public final static String SETTINGS_FILE_NAME = "Settings";


    /*****************************************************************************************
     * ===================================[ PRIVATE FIELDS ]================================ *
     *****************************************************************************************/

    private boolean showHelpValue;
    private boolean advSwitch1Value;
    private boolean advSwitch2Value;
    private boolean advSwitch3Value;
    private boolean customVolume;
    private int customVolumeValue;
    private int skinId;
    private int linearLoadingThread;
    private int smartLoadingThread;
    private int ondemandLoadingThread;
    private SeekBar customVolumeSeekBar;

    
    /*****************************************************************************************
     * ==============================[ ACTIVITY STATE METHODS ]============================= *
     *****************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*
         * UI variables declaration and initialization *
         *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

        //Global settings UI elements
        TabHost tabHost     = (TabHost) findViewById(R.id.settings_tab_host);
        Button acceptButton = (Button) findViewById(R.id.settings_accept_button);
        Button cancelButton = (Button) findViewById(R.id.settings_cancel_button);

        //Display Tab UI elements
        Spinner skinsSpinner = (Spinner) findViewById(R.id.skins_spinner);
        CheckBox showHelp    = (CheckBox) findViewById(R.id.showHelpCheckBox);

        //Sound Tab UI elements
        CheckBox customVolumeCheckBox   = (CheckBox) findViewById(R.id.customVolumeCheckBox);
        customVolumeSeekBar             = (SeekBar) findViewById(R.id.customVolumeSeekBar);

        //Advanced Tab UI elements
        RadioGroup linearLoadingThreadRadioGroup    = (RadioGroup) findViewById(R.id.linear_loading_thread_radio_group);
        RadioGroup smartLoadingThreadRadioGroup     = (RadioGroup) findViewById(R.id.smart_loading_thread_radio_group);
        RadioGroup ondemandLoadingThreadRadioGroup  = (RadioGroup) findViewById(R.id.ondemand_loading_thread_radio_group);
        RadioButton linearLoadingThreadButton0      = (RadioButton) findViewById(R.id.linear_loading_thread_0);
        RadioButton linearLoadingThreadButton1      = (RadioButton) findViewById(R.id.linear_loading_thread_1);
        RadioButton linearLoadingThreadButton2      = (RadioButton) findViewById(R.id.linear_loading_thread_2);
        RadioButton linearLoadingThreadButton3      = (RadioButton) findViewById(R.id.linear_loading_thread_3);
        RadioButton smartLoadingThreadButton0       = (RadioButton) findViewById(R.id.smart_loading_thread_0);
        RadioButton smartLoadingThreadButton1       = (RadioButton) findViewById(R.id.smart_loading_thread_1);
        RadioButton smartLoadingThreadButton2       = (RadioButton) findViewById(R.id.smart_loading_thread_2);
        RadioButton smartLoadingThreadButton3       = (RadioButton) findViewById(R.id.smart_loading_thread_3);
        RadioButton ondemandLoadingThreadButton1    = (RadioButton) findViewById(R.id.ondemand_loading_thread_1);
        RadioButton ondemandLoadingThreadButton2    = (RadioButton) findViewById(R.id.ondemand_loading_thread_2);
        RadioButton ondemandLoadingThreadButton3    = (RadioButton) findViewById(R.id.ondemand_loading_thread_3);
        RadioButton ondemandLoadingThreadButton4    = (RadioButton) findViewById(R.id.ondemand_loading_thread_4);
        Switch advSwitch1                           = (Switch) findViewById(R.id.adv_switch1);
        Switch advSwitch2                           = (Switch) findViewById(R.id.adv_switch2);
        Switch advSwitch3                           = (Switch) findViewById(R.id.adv_switch3);


        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*
         *            UI variables setup               *
         *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

        //Global settings UI elements
        tabHost.setup();

        //Display Tab UI elements
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Display");
        tabSpec.setContent(R.id.settings_tab_display);
        tabSpec.setIndicator(getString(R.string.display_tab_name));
        tabHost.addTab(tabSpec);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.skins_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skinsSpinner.setAdapter(adapter);

        //Sound Tab UI elements
        tabSpec = tabHost.newTabSpec("Sound");
        tabSpec.setContent(R.id.settings_tab_sound);
        tabSpec.setIndicator(getString(R.string.sound_tab_name));
        tabHost.addTab(tabSpec);

        customVolumeSeekBar.setMax(CUSTOM_VOLUME_MAX_DEFAULT);

        //Advanced Tab UI elements
        tabSpec = tabHost.newTabSpec("Advanced");
        tabSpec.setContent(R.id.settings_tab_advanced);
        tabSpec.setIndicator(getString(R.string.advanced_tab_name));
        tabHost.addTab(tabSpec);


        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*
         *  Bind UI variables to listeners for events  *
         *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

        //Global settings UI elements
        acceptButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        //Display Tab UI elements
        showHelp.setOnCheckedChangeListener(this);
        skinsSpinner.setOnItemSelectedListener(this);

        //Sound Tab UI elements
        customVolumeCheckBox.setOnCheckedChangeListener(this);
        customVolumeSeekBar.setOnSeekBarChangeListener(this);

        //Advanced Tab UI elements
        linearLoadingThreadRadioGroup.setOnCheckedChangeListener(this);
        smartLoadingThreadRadioGroup.setOnCheckedChangeListener(this);
        ondemandLoadingThreadRadioGroup.setOnCheckedChangeListener(this);
        advSwitch1.setOnCheckedChangeListener(this);
        advSwitch2.setOnCheckedChangeListener(this);
        advSwitch3.setOnCheckedChangeListener(this);


        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*
         *   Retrieve settings from preferences file   *
         *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

        //Get saved settings from file
        SharedPreferences savedSettings = getSharedPreferences(SETTINGS_FILE_NAME, MODE_PRIVATE);

        //Display Tab UI elements
        skinId          = savedSettings.getInt("skinId", SKIN_ID_DEFAULT);
        showHelpValue   = savedSettings.getBoolean("showHelp", SHOW_HELP_DEFAULT);

        //Sound Tab UI elements
        customVolume        = savedSettings.getBoolean("customVolume", CUSTOM_VOLUME_DEFAULT);
        customVolumeValue   = savedSettings.getInt("customVolumeValue",  CUSTOM_VOLUME_VALUE_DEFAULT);

        //Advanced Tab UI elements
        linearLoadingThread     = savedSettings.getInt("linearLoadingThread", LINEAR_LOADING_THREAD_DEFAULT);
        smartLoadingThread      = savedSettings.getInt("smartLoadingThread", SMART_LOADING_THREAD_DEFAULT);
        ondemandLoadingThread   = savedSettings.getInt("ondemandLoadingThread", ONDEMAND_LOADING_THREAD_DEFAULT);
        advSwitch1Value       = savedSettings.getBoolean("advSwitch1", advSwitch1_DEFAULT);
        advSwitch2Value       = savedSettings.getBoolean("advSwitch2", advSwitch2_DEFAULT);
        advSwitch3Value       = savedSettings.getBoolean("advSwitch3", advSwitch3_DEFAULT);


        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*
         * Set settings UI states from retrieved values *
         *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

        //Display Tab UI elements
        showHelp.setChecked(showHelpValue);
        skinsSpinner.setSelection(skinId);

        //Sound Tab UI elements
        customVolumeCheckBox.setChecked(customVolume);
        customVolumeSeekBar.setProgress(customVolumeValue);
        customVolumeSeekBar.setEnabled(customVolume);

        //Advanced Tab UI elements
        switch(linearLoadingThread) {
            case(0): linearLoadingThreadButton0.setChecked(true); break;
            case(1): linearLoadingThreadButton1.setChecked(true); break;
            case(2): linearLoadingThreadButton2.setChecked(true); break;
            case(3): linearLoadingThreadButton3.setChecked(true); break;
        }
        switch(smartLoadingThread) {
            case(0): smartLoadingThreadButton0.setChecked(true); break;
            case(1): smartLoadingThreadButton1.setChecked(true); break;
            case(2): smartLoadingThreadButton2.setChecked(true); break;
            case(3): smartLoadingThreadButton3.setChecked(true); break;
        }
        switch(ondemandLoadingThread) {
            case(1): ondemandLoadingThreadButton1.setChecked(true); break;
            case(2): ondemandLoadingThreadButton2.setChecked(true); break;
            case(3): ondemandLoadingThreadButton3.setChecked(true); break;
            case(4): ondemandLoadingThreadButton4.setChecked(true); break;
        }
        advSwitch1.setChecked(advSwitch1Value);
        advSwitch2.setChecked(advSwitch2Value);
        advSwitch3.setChecked(advSwitch3Value);
    }


    /*****************************************************************************************
     * ====================================[ UI LISTENERS ]================================= *
     *****************************************************************************************/

    public void onClick(View view) {

        //If the Accept button was clicked.
        if (view == findViewById(R.id.settings_accept_button)) {

            //Setup preferences file and editor.
            SharedPreferences toBeSavedSettings = getSharedPreferences(SETTINGS_FILE_NAME, MODE_PRIVATE);
            SharedPreferences.Editor toBeSavedSettingsEditor = toBeSavedSettings.edit();

            //Save all settings to preferences file.
            toBeSavedSettingsEditor.putInt("linearLoadingThread", linearLoadingThread);
            toBeSavedSettingsEditor.putInt("smartLoadingThread", smartLoadingThread);
            toBeSavedSettingsEditor.putInt("ondemandLoadingThread", ondemandLoadingThread);
            toBeSavedSettingsEditor.putBoolean("advSwitch1", advSwitch1Value);
            toBeSavedSettingsEditor.putBoolean("advSwitch2", advSwitch2Value);
            toBeSavedSettingsEditor.putBoolean("advSwitch3", advSwitch3Value);
            toBeSavedSettingsEditor.putInt("skinId", skinId);
            toBeSavedSettingsEditor.putBoolean("customVolume", customVolume);
            toBeSavedSettingsEditor.putInt("customVolumeValue", customVolumeValue);
            toBeSavedSettingsEditor.putBoolean("showHelp", showHelpValue);

            //Commit changes to preferences file.
            toBeSavedSettingsEditor.apply();

            //Save dynamic settings in an intent.
            Intent intent = new Intent();
            intent.putExtra("skinId", skinId);
            intent.putExtra("customVolume", customVolume);
            intent.putExtra("customVolumeValue", customVolumeValue);

            //Set result to 1 (settings changes), send intent to MainActivity with dynamic settings and destroy this activity.
            setResult(1, intent);
            finish();
        }

        //If the Cancel button was clicked.
        if (view == findViewById(R.id.settings_cancel_button)) {

            //Set result to 0 (no changes) and destroy this activity
            setResult(0);
            finish();
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
        if(compoundButton == findViewById(R.id.adv_switch1)) {
            advSwitch1Value = state;
        }
        if(compoundButton == findViewById(R.id.adv_switch2)) {
            advSwitch2Value = state;
        }
        if(compoundButton == findViewById(R.id.adv_switch3)) {
            advSwitch3Value = state;
        }
        if(compoundButton == findViewById(R.id.customVolumeCheckBox)) {
            customVolume = state;
            customVolumeSeekBar.setEnabled(customVolume);
        }
        if(compoundButton == findViewById(R.id.showHelpCheckBox)) {
            showHelpValue = state;
        }
    }

    public void onCheckedChanged(RadioGroup radioGroup, int itemId) {
        if(radioGroup == findViewById(R.id.linear_loading_thread_radio_group)) {
            if(itemId == radioGroup.getChildAt(0).getId()) {
                linearLoadingThread = 0;
            }
            if(itemId == radioGroup.getChildAt(1).getId()) {
                linearLoadingThread = 1;
            }
            if(itemId == radioGroup.getChildAt(2).getId()) {
                linearLoadingThread = 2;
            }
            if(itemId == radioGroup.getChildAt(3).getId()) {
                linearLoadingThread = 3;
            }
        }
        if(radioGroup == findViewById(R.id.smart_loading_thread_radio_group)) {
            if(itemId == radioGroup.getChildAt(0).getId()) {
                smartLoadingThread = 0;
            }
            if(itemId == radioGroup.getChildAt(1).getId()) {
                smartLoadingThread = 1;
            }
            if(itemId == radioGroup.getChildAt(2).getId()) {
                smartLoadingThread = 2;
            }
            if(itemId == radioGroup.getChildAt(3).getId()) {
                smartLoadingThread = 3;
            }
        }
        if(radioGroup == findViewById(R.id.ondemand_loading_thread_radio_group)) {
            if(itemId == radioGroup.getChildAt(0).getId()) {
                ondemandLoadingThread = 1;
            }
            if(itemId == radioGroup.getChildAt(1).getId()) {
                ondemandLoadingThread = 2;
            }
            if(itemId == radioGroup.getChildAt(2).getId()) {
                ondemandLoadingThread = 3;
            }
            if(itemId == radioGroup.getChildAt(3).getId()) {
                ondemandLoadingThread = 4;
            }
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //parent.getItemAtPosition(position);
        skinId = position;
    }

    public void onNothingSelected(AdapterView<?> parent) {
        //Nothing to do...
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        //Nothing to do...
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar == findViewById(R.id.customVolumeSeekBar)) {
            customVolumeValue = progress;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        //Nothing to do...
    }
}

