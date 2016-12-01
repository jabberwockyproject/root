package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
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

    private ArrayAdapter<String> adapterAll, adapterFav;
    private Theme themeAll, themeFav;
    private SoundPool soundPool;
    private ListView soundListDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Create an object for each view we need to listen to. (so we can manipulate them in the code)
         * findViewById(R.id.NAME) looks in activity_main.xml file for the correct view
         * The lines findViewById(R.id.NAME) is seeking are android:id="@+id/NAME"
         */
        Button searchButton = (Button) findViewById(R.id.search_button);
        Button themeButton = (Button) findViewById(R.id.theme_button);
        Button randomButton = (Button) findViewById(R.id.random_button);
        Button settingsButton = (Button) findViewById(R.id.settings_button);
        soundListDisplay = (ListView) findViewById(R.id.sound_List_Display);

        /*
         * For each object we created above, designate event listeners.
         * Ex: searchButton.setOnClickListener(this); designate the MainActivity (this) as
         * listener for the OnClick event.
         * To make this work, MainActivity must implements the correct interface (ex: View.OnClickListener)
         * AND have a custom public void onClick(View) method.
         */
        searchButton.setOnClickListener(this);
        searchButton.setOnLongClickListener(this);
        themeButton.setOnClickListener(this);
        themeButton.setOnLongClickListener(this);
        randomButton.setOnClickListener(this);
        randomButton.setOnLongClickListener(this);
        settingsButton.setOnClickListener(this);
        settingsButton.setOnLongClickListener(this);
        soundListDisplay.setOnItemClickListener(this);
        soundListDisplay.setOnItemLongClickListener(this);

        /*
         * Instantiate a sound pool for playing sounds later on.
         * This pool should load all sounds in the future. (might require a scan)
         *
         * https://developer.android.com/reference/android/media/SoundPool.html
         */
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC,0);

        //Instantiate themes named themeAll and themeFav
        themeAll = new Theme("themeAll");
        themeFav = new Theme("themeFav");


        /*
         * Create an adapter for the ListView
         * ListView will be fed from the soundNameList field of the themeAll theme.
         *
         * https://developer.android.com/reference/android/widget/ListView.html#setAdapter%28android.widget.ListAdapter%29
         */
        adapterAll = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,themeAll.getSoundNameList());
        soundListDisplay.setAdapter(adapterAll);

        /*Create an adapter for Favorites ListView
        fed from soundNameList field of themeFav theme
         */
        adapterFav = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, themeFav.getSoundNameList());



        /*
         * Testing code.
         * Instantiate 20 Sound objects with generic names and then
         * add all these sounds name to the ListView.
         * Update the display at the end.
         */
        final int maxSound = 20;
        for (int i = 0; i < maxSound; i++){
            String soundNameSample = "Sample #" + Integer.toString(i);
            String soundNameFav = "Favorite #" + Integer.toString(i);
            themeAll.addSound(new Sound(soundNameSample));
            themeFav.addSound(new Sound(soundNameFav));
        }
        adapterAll.notifyDataSetChanged();
        adapterFav.notifyDataSetChanged();



    }

    /*
     * OnClick event handler.
     * All four buttons will use this method if pressed.
     * For the moment, the method check if the triggered view is
     * one of the known buttons, and add a word in the ListView.
     * Does nothing otherwise.
     *
     * https://developer.android.com/reference/android/view/View.OnClickListener.html
     */
    public void onClick(View v) {
        if(findViewById(R.id.search_button) == v) {
            themeAll.addSound(new Sound("search"));
            adapterAll.notifyDataSetChanged();
        }
        if(findViewById(R.id.theme_button) == v) {
            if(soundListDisplay.getAdapter() == adapterAll) {
                soundListDisplay.setAdapter(adapterFav);
            }
            else if(soundListDisplay.getAdapter() == adapterFav) {
                soundListDisplay.setAdapter(adapterAll);
                //TODO  improve code for multiple adapters
            }
        }
        if(findViewById(R.id.random_button) == v) {
            soundListDisplay.setAdapter(adapterAll);
        }
        if(findViewById(R.id.settings_button) == v) {
            themeFav.addSound(new Sound("settings"));
            adapterFav.notifyDataSetChanged();
        }
    }

    /*
     * OnLongClick event handler.
     * All four buttons will use this method if pressed for a few seconds.
     * For the moment, the method check if the triggered view is
     * one of the known buttons, and add a word in the ListView.
     * Does nothing otherwise
     * The return value is there to say whether we took care of the event or not.
     * If not, other event handlers might be fired. Has no implication for the moment.
     *
     * https://developer.android.com/reference/android/view/View.OnLongClickListener.html
     */
    public boolean onLongClick(View v) {
        if(findViewById(R.id.search_button) == v) {
            themeAll.addSound(new Sound("l-search"));
            adapterAll.notifyDataSetChanged();
            return true;
        }
        if(findViewById(R.id.theme_button) == v) {
            themeAll.addSound(new Sound("l-theme"));
            adapterAll.notifyDataSetChanged();
            return true;
        }
        if(findViewById(R.id.random_button) == v) {
            themeAll.addSound(new Sound("l-random"));
            adapterAll.notifyDataSetChanged();
            return true;
        }
        if(findViewById(R.id.settings_button) == v) {
            themeAll.addSound(new Sound("l-settings"));
            adapterAll.notifyDataSetChanged();
            return true;
        }
        return false;
    }

    /*
     * OnItemClick event handler.
     * Items in the ListView will use this method if pressed.
     * Add the pressed item position (in the list) to the ListView (testing/experimental purpose)
     * Will load and play the beep1.mp3 sound.
     * beep1.mp3 is located in the res/raw folder.
     *
     * https://developer.android.com/reference/android/widget/AdapterView.OnItemClickListener.html
     */
    public void onItemClick(AdapterView parent, View v, int pos, long id){
        if(findViewById(R.id.sound_List_Display) == parent) {
            themeAll.addSound(new Sound(Integer.toString(pos)));
            adapterAll.notifyDataSetChanged();

            //Sound play testing
            int sID = soundPool.load(this.getApplicationContext(),R.raw.beep1,1);
            soundPool.setOnLoadCompleteListener(
                    new SoundPool.OnLoadCompleteListener(){
                        public void onLoadComplete(SoundPool soundPool, int soundId, int status) {
                            AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                            float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                            float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                            float leftVolume = curVolume/maxVolume;
                            float rightVolume = curVolume/maxVolume;
                            int priority = 1;
                            int loop = 0;
                            float rate = 1f;
                            soundPool.play(soundId, leftVolume, rightVolume, priority, loop, rate);
                        }
                    }
            );
            //End of Sound play testing
        }
    }

    /*
     * OnItemLongClick event handler.
     * Items in the ListView will use this method if pressed for a few seconds.
     * Add the pressed item id to the ListView (testing/experimental purpose)
     * The return value is there to say whether we took care of the event or not.
     * If not, other event handlers might be fired. Has no implication for the moment.
     *
     * https://developer.android.com/reference/android/widget/AdapterView.OnItemLongClickListener.html
     */
    public boolean onItemLongClick(AdapterView parent, View v, int pos, long id){
        if(findViewById(R.id.sound_List_Display) == parent) {
            themeAll.addSound(new Sound(Long.toString(id)));
            adapterAll.notifyDataSetChanged();
            return true;
        }
        return false;
    }
}