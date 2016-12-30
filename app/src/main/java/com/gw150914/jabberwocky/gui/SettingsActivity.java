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

    public final static String SETTINGS_FILE_NAME = "Settings";
    public final static int LINEAR_LOADING_THREAD_DEFAULT = 1;
    public final static int SMART_LOADING_THREAD_DEFAULT = 1;
    public final static int ONDEMAND_LOADING_THREAD_DEFAULT = 2;
    public final static int SKIN_ID_DEFAULT = 0;
    public final static boolean ADV_SWITCH1_DEFAULT = false;
    public final static boolean ADV_SWITCH2_DEFAULT = false;
    public final static boolean ADV_SWITCH3_DEFAULT = false;
    public final static boolean CUSTOM_VOLUME_DEFAULT = false;
    public final static int CUSTOM_VOLUME_VALUE_DEFAULT = 100;
    public final static int CUSTOM_VOLUME_MAX_DEFAULT = 100;

    int skinId;
    int linearLoadingThread;
    int smartLoadingThread;
    int ondemandLoadingThread;
    boolean customVolume;
    int customVolumeValue;
    boolean adv_switch1_value, adv_switch2_value, adv_switch3_value;
    int linearLoadingThreadButton0Id, linearLoadingThreadButton1Id, linearLoadingThreadButton2Id, linearLoadingThreadButton3Id;
    int smartLoadingThreadButton0Id, smartLoadingThreadButton1Id, smartLoadingThreadButton2Id, smartLoadingThreadButton3Id;
    int ondemandLoadingThreadButton1Id, ondemandLoadingThreadButton2Id, ondemandLoadingThreadButton3Id, ondemandLoadingThreadButton4Id;
    RadioButton linearLoadingThreadButton0, linearLoadingThreadButton1, linearLoadingThreadButton2, linearLoadingThreadButton3;
    RadioButton smartLoadingThreadButton0, smartLoadingThreadButton1, smartLoadingThreadButton2, smartLoadingThreadButton3;
    RadioButton ondemandLoadingThreadButton1, ondemandLoadingThreadButton2, ondemandLoadingThreadButton3, ondemandLoadingThreadButton4;
    RadioGroup linearLoadingThreadRadioGroup;
    RadioGroup smartLoadingThreadRadioGroup;
    RadioGroup ondemandLoadingThreadRadioGroup;
    Switch adv_switch1, adv_switch2, adv_switch3;
    CheckBox customVolumeCheckBox;
    SeekBar customVolumeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Tab root
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

        Spinner skinsSpinner = (Spinner) findViewById(R.id.skins_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.skins_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skinsSpinner.setAdapter(adapter);
        skinsSpinner.setOnItemSelectedListener(this);

        Button acceptButton = (Button) findViewById(R.id.settings_accept_button);
        Button cancelButton = (Button) findViewById(R.id.settings_cancel_button);

        acceptButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        customVolumeCheckBox    = (CheckBox) findViewById(R.id.customVolumeCheckBox);
        customVolumeSeekBar     = (SeekBar) findViewById(R.id.customVolumeSeekBar);

        customVolumeCheckBox.setOnCheckedChangeListener(this);
        customVolumeSeekBar.setOnSeekBarChangeListener(this);

        linearLoadingThreadButton0      = (RadioButton) findViewById(R.id.linear_loading_thread_0);
        linearLoadingThreadButton1      = (RadioButton) findViewById(R.id.linear_loading_thread_1);
        linearLoadingThreadButton2      = (RadioButton) findViewById(R.id.linear_loading_thread_2);
        linearLoadingThreadButton3      = (RadioButton) findViewById(R.id.linear_loading_thread_3);
        linearLoadingThreadRadioGroup   = (RadioGroup) findViewById(R.id.linear_loading_thread_radio_group);

        smartLoadingThreadButton0      = (RadioButton) findViewById(R.id.smart_loading_thread_0);
        smartLoadingThreadButton1      = (RadioButton) findViewById(R.id.smart_loading_thread_1);
        smartLoadingThreadButton2      = (RadioButton) findViewById(R.id.smart_loading_thread_2);
        smartLoadingThreadButton3      = (RadioButton) findViewById(R.id.smart_loading_thread_3);
        smartLoadingThreadRadioGroup   = (RadioGroup) findViewById(R.id.smart_loading_thread_radio_group);

        ondemandLoadingThreadButton1      = (RadioButton) findViewById(R.id.ondemand_loading_thread_1);
        ondemandLoadingThreadButton2      = (RadioButton) findViewById(R.id.ondemand_loading_thread_2);
        ondemandLoadingThreadButton3      = (RadioButton) findViewById(R.id.ondemand_loading_thread_3);
        ondemandLoadingThreadButton4      = (RadioButton) findViewById(R.id.ondemand_loading_thread_4);
        ondemandLoadingThreadRadioGroup   = (RadioGroup) findViewById(R.id.ondemand_loading_thread_radio_group);

        linearLoadingThreadButton0Id = linearLoadingThreadButton0.getId();
        linearLoadingThreadButton1Id = linearLoadingThreadButton1.getId();
        linearLoadingThreadButton2Id = linearLoadingThreadButton2.getId();
        linearLoadingThreadButton3Id = linearLoadingThreadButton3.getId();
        linearLoadingThreadRadioGroup.setOnCheckedChangeListener(this);

        smartLoadingThreadButton0Id = smartLoadingThreadButton0.getId();
        smartLoadingThreadButton1Id = smartLoadingThreadButton1.getId();
        smartLoadingThreadButton2Id = smartLoadingThreadButton2.getId();
        smartLoadingThreadButton3Id = smartLoadingThreadButton3.getId();
        smartLoadingThreadRadioGroup.setOnCheckedChangeListener(this);

        ondemandLoadingThreadButton1Id = ondemandLoadingThreadButton1.getId();
        ondemandLoadingThreadButton2Id = ondemandLoadingThreadButton2.getId();
        ondemandLoadingThreadButton3Id = ondemandLoadingThreadButton3.getId();
        ondemandLoadingThreadButton4Id = ondemandLoadingThreadButton4.getId();
        ondemandLoadingThreadRadioGroup.setOnCheckedChangeListener(this);

        adv_switch1 = (Switch) findViewById(R.id.adv_switch1);
        adv_switch2 = (Switch) findViewById(R.id.adv_switch2);
        adv_switch3 = (Switch) findViewById(R.id.adv_switch3);

        adv_switch1.setOnCheckedChangeListener(this);
        adv_switch2.setOnCheckedChangeListener(this);
        adv_switch3.setOnCheckedChangeListener(this);

        //Restore preferences
        SharedPreferences settings = getSharedPreferences(SETTINGS_FILE_NAME, MODE_PRIVATE);
        
        linearLoadingThread = settings.getInt("linearLoadingThread", LINEAR_LOADING_THREAD_DEFAULT);
        smartLoadingThread = settings.getInt("smartLoadingThread", SMART_LOADING_THREAD_DEFAULT);
        ondemandLoadingThread = settings.getInt("ondemandLoadingThread", ONDEMAND_LOADING_THREAD_DEFAULT);

        adv_switch1_value = settings.getBoolean("advSwitch1", ADV_SWITCH1_DEFAULT);
        adv_switch2_value = settings.getBoolean("advSwitch2", ADV_SWITCH2_DEFAULT);
        adv_switch3_value = settings.getBoolean("advSwitch3", ADV_SWITCH3_DEFAULT);

        skinId = settings.getInt("skinId", SKIN_ID_DEFAULT);

        customVolume = settings.getBoolean("customVolume", CUSTOM_VOLUME_DEFAULT);
        customVolumeValue = settings.getInt("customVolumeValue",  CUSTOM_VOLUME_VALUE_DEFAULT);
        
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

        if(adv_switch1_value) {
            adv_switch1.setChecked(true);
        }
        else {
            adv_switch1.setChecked(false);
        }
        if(adv_switch2_value) {
            adv_switch2.setChecked(true);
        }
        else {
            adv_switch2.setChecked(false);
        }
        if(adv_switch3_value) {
            adv_switch3.setChecked(true);
        }
        else {
            adv_switch3.setChecked(false);
        }

        skinsSpinner.setSelection(skinId);

        customVolumeCheckBox.setChecked(customVolume);
        customVolumeSeekBar.setMax(CUSTOM_VOLUME_MAX_DEFAULT);
        customVolumeSeekBar.setProgress(customVolumeValue);
        customVolumeSeekBar.setEnabled(customVolume);

        System.out.println("DEBUG: SEEKBAR: " + customVolumeSeekBar.getProgress());

        /*
        switch (skinId) {
            case(0):
                skinsSpinner.setSelection(0);
        }
        */
    }

    public void onClick(View view) {

        if (view == findViewById(R.id.settings_accept_button)) {

            //Save all settings in internal storage.
            SharedPreferences settings = getSharedPreferences(SETTINGS_FILE_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("linearLoadingThread", linearLoadingThread);
            editor.putInt("smartLoadingThread", smartLoadingThread);
            editor.putInt("ondemandLoadingThread", ondemandLoadingThread);
            editor.putBoolean("advSwitch1", adv_switch1_value);
            editor.putBoolean("advSwitch2", adv_switch2_value);
            editor.putBoolean("advSwitch3", adv_switch3_value);
            editor.putInt("skinId", skinId);
            editor.putBoolean("customVolume", customVolume);
            editor.putInt("customVolumeValue", customVolumeValue);

            editor.apply();

            //Pass dynamic settings back to MainActivity.
            Intent intent = new Intent();
            intent.putExtra("skinId", skinId);
            intent.putExtra("customVolume", customVolume);
            intent.putExtra("customVolumeValue", customVolumeValue);
            setResult(1, intent);
            finish();
        }
        if (view == findViewById(R.id.settings_cancel_button)) {
            setResult(0);
            finish();
        }
    }
    public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
        if(compoundButton == findViewById(R.id.adv_switch1)) {
            adv_switch1_value = state;
        }
        if(compoundButton == findViewById(R.id.adv_switch3)) {
            adv_switch2_value = state;
        }
        if(compoundButton == findViewById(R.id.adv_switch3)) {
            adv_switch3_value = state;
        }
        if(compoundButton == findViewById(R.id.customVolumeCheckBox)) {
            customVolume = state;
            customVolumeSeekBar.setEnabled(customVolume);
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
        if(radioGroup == findViewById(R.id.smart_loading_thread_radio_group)) {
            System.out.println("DEBUG: Checked ID: " + item);
            if(item == smartLoadingThreadButton0Id) {
                smartLoadingThread = 0;
            }
            if(item == smartLoadingThreadButton1Id) {
                smartLoadingThread = 1;
            }
            if(item == smartLoadingThreadButton2Id) {
                smartLoadingThread = 2;
            }
            if(item == smartLoadingThreadButton3Id) {
                smartLoadingThread = 3;
            }
        }
        if(radioGroup == findViewById(R.id.ondemand_loading_thread_radio_group)) {
            System.out.println("DEBUG: Checked ID: " + item);
            if(item == ondemandLoadingThreadButton1Id) {
                ondemandLoadingThread = 1;
            }
            if(item == ondemandLoadingThreadButton2Id) {
                ondemandLoadingThread = 2;
            }
            if(item == ondemandLoadingThreadButton3Id) {
                ondemandLoadingThread = 3;
            }
            if(item == ondemandLoadingThreadButton4Id) {
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

    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar == findViewById(R.id.customVolumeSeekBar)) {
            customVolumeValue = progress;
            System.out.println("DEBUG: SEEKBAR: " + customVolumeSeekBar.getProgress());
        }
    }
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
}

