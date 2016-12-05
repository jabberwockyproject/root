package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gw150914.jabberwocky.R;
import com.gw150914.jabberwocky.core.Theme;
import com.gw150914.jabberwocky.core.Sound;

public class MainActivity extends Activity implements View.OnClickListener,View.OnLongClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    private ArrayAdapter<String> adapterAll, adapterFav;
    private Theme currentTheme, themeAll, themeFav;
    private SoundPool soundPool;
    private ListView soundListDisplay;
    private TextView currentThemeTextView;
    private Sound soundAndreaPasLa, soundAucunRapport, soundDefection, soundFoutLaRage, soundGrosseBlague, soundHabile, soundHumour, soundLeGitan, soundMachiavellique, soundMagnerLeCul, soundMaitreMichel, soundMarcheBien, soundNoFuckingBalls, soundNouveaute, soundPasCool, soundPasDrole, soundPourquoi, soundPqReche, soundPrejudice, soundPtitZizi, soundPtiteBite, soundQueSePasseTIl, soundQuelqueSorte, soundQuoi, soundSante, soundScandaleux, soundSuperBaise, soundSuperSpirituel, soundTrahison, soundTropPlaisir, soundVieuxMan;

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
        currentThemeTextView = (TextView) findViewById(R.id.current_theme_display) ;

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

        //Load all embedded sounds in memory and create Sound objects
        soundAndreaPasLa = new Sound("Andrea pas la",soundPool.load(this.getApplicationContext(),R.raw.andrea_pas_la,1));
        soundAucunRapport = new Sound("Aucun rapport",soundPool.load(this.getApplicationContext(),R.raw.aucun_rapport,1));
        soundDefection = new Sound("Defection",soundPool.load(this.getApplicationContext(),R.raw.defection,1));
        soundFoutLaRage = new Sound("Fout la rage",soundPool.load(this.getApplicationContext(),R.raw.fout_la_rage,1));
        soundGrosseBlague = new Sound("Grosse blague",soundPool.load(this.getApplicationContext(),R.raw.grosse_blague,1));
        soundHabile = new Sound("Habile",soundPool.load(this.getApplicationContext(),R.raw.habile,1));
        soundHumour = new Sound("Humour",soundPool.load(this.getApplicationContext(),R.raw.humour,1));
        soundLeGitan = new Sound("Le Gitan",soundPool.load(this.getApplicationContext(),R.raw.le_gitan,1));
        soundMachiavellique = new Sound("Machiavellique",soundPool.load(this.getApplicationContext(),R.raw.machiavellique,1));
        soundMagnerLeCul = new Sound("Magner le cul",soundPool.load(this.getApplicationContext(),R.raw.magner_le_cul,1));
        soundMaitreMichel = new Sound("Maitre Michel",soundPool.load(this.getApplicationContext(),R.raw.maitre_michel,1));
        soundMarcheBien = new Sound("Marche bien",soundPool.load(this.getApplicationContext(),R.raw.marche_bien,1));
        soundNoFuckingBalls = new Sound("No Fucking Balls",soundPool.load(this.getApplicationContext(),R.raw.no_fucking_balls,1));
        soundNouveaute = new Sound("Nouveaute",soundPool.load(this.getApplicationContext(),R.raw.nouveaute,1));
        soundPasCool = new Sound("Pas Cool",soundPool.load(this.getApplicationContext(),R.raw.pas_cool,1));
        soundPasDrole = new Sound("Pas Drole",soundPool.load(this.getApplicationContext(),R.raw.pas_drole,1));
        soundPourquoi = new Sound("Pourquoi",soundPool.load(this.getApplicationContext(),R.raw.pourquoi,1));
        soundPqReche = new Sound("PQ Reche",soundPool.load(this.getApplicationContext(),R.raw.pq_reche,1));
        soundPrejudice = new Sound("Prejudice",soundPool.load(this.getApplicationContext(),R.raw.prejudice,1));
        soundPtitZizi = new Sound("Ptit zizi",soundPool.load(this.getApplicationContext(),R.raw.ptit_zizi,1));
        soundPtiteBite = new Sound("Ptite bite",soundPool.load(this.getApplicationContext(),R.raw.ptite_bite,1));
        soundQueSePasseTIl = new Sound("Que se passe t'il ",soundPool.load(this.getApplicationContext(),R.raw.que_ce_passe_t_il,1));
        soundQuelqueSorte = new Sound("Quelque Sorte",soundPool.load(this.getApplicationContext(),R.raw.quelque_sorte,1));
        soundQuoi = new Sound("Quoi",soundPool.load(this.getApplicationContext(),R.raw.quoi,1));
        soundSante = new Sound("Sante",soundPool.load(this.getApplicationContext(),R.raw.sante,1));
        soundScandaleux = new Sound("Scandaleux",soundPool.load(this.getApplicationContext(),R.raw.scandaleux,1));
        soundSuperBaise = new Sound("Super Baise",soundPool.load(this.getApplicationContext(),R.raw.super_baise,1));
        soundSuperSpirituel = new Sound("Super Spirituel",soundPool.load(this.getApplicationContext(),R.raw.super_spirituel,1));
        soundTrahison = new Sound("Trahison",soundPool.load(this.getApplicationContext(),R.raw.trahison,1));
        soundTropPlaisir = new Sound("Trop plaisir",soundPool.load(this.getApplicationContext(),R.raw.trop_plaisir,1));
        soundVieuxMan = new Sound("Vieux man",soundPool.load(this.getApplicationContext(),R.raw.vieux_man,1));

        //Below code is currently useless. Keep it for ref.
        /*
        soundPool.setOnLoadCompleteListener(
                new SoundPool.OnLoadCompleteListener(){
                    public void onLoadComplete(SoundPool soundPool, int soundId, int status) {
                        //nothing
                    }
                }
        );
        */

        //Add created sounds in theme All
        themeAll.addSound(soundAndreaPasLa);
        themeAll.addSound(soundAucunRapport);
        themeAll.addSound(soundDefection);
        themeAll.addSound(soundFoutLaRage);
        themeAll.addSound(soundGrosseBlague);
        themeAll.addSound(soundHabile);
        themeAll.addSound(soundHumour);
        themeAll.addSound(soundLeGitan);
        themeAll.addSound(soundMachiavellique);
        themeAll.addSound(soundMagnerLeCul);
        themeAll.addSound(soundMaitreMichel);
        themeAll.addSound(soundMarcheBien);
        themeAll.addSound(soundNoFuckingBalls);
        themeAll.addSound(soundNouveaute);
        themeAll.addSound(soundPasCool);
        themeAll.addSound(soundPasDrole);
        themeAll.addSound(soundPourquoi);
        themeAll.addSound(soundPqReche);
        themeAll.addSound(soundPrejudice);
        themeAll.addSound(soundPtitZizi);
        themeAll.addSound(soundPtiteBite);
        themeAll.addSound(soundQueSePasseTIl);
        themeAll.addSound(soundQuelqueSorte);
        themeAll.addSound(soundQuoi);
        themeAll.addSound(soundSante);
        themeAll.addSound(soundScandaleux);
        themeAll.addSound(soundSuperBaise);
        themeAll.addSound(soundSuperSpirituel);
        themeAll.addSound(soundTrahison);
        themeAll.addSound(soundTropPlaisir);
        themeAll.addSound(soundVieuxMan);

        /*
         * Create an adapter for the ListView
         * ListView will be fed from the soundNameList field of the themeAll theme.
         *
         * https://developer.android.com/reference/android/widget/ListView.html#setAdapter%28android.widget.ListAdapter%29
         */
        adapterAll = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,themeAll.getSoundNameList());
        soundListDisplay.setAdapter(adapterAll);
        currentTheme = themeAll;
        currentThemeTextView.setText("Current theme is All");

        /*Create an adapter for Favorites ListView
         *fed from soundNameList field of themeFav theme
         */
        adapterFav = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, themeFav.getSoundNameList());
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

        }
        if(findViewById(R.id.theme_button) == v) {
            if(soundListDisplay.getAdapter() == adapterAll) {
                soundListDisplay.setAdapter(adapterFav);
                currentTheme = themeFav;
                currentThemeTextView.setText("Current theme: Favorites");
            }
            else if(soundListDisplay.getAdapter() == adapterFav) {
                soundListDisplay.setAdapter(adapterAll);
                currentTheme = themeAll;
                currentThemeTextView.setText("Current theme: All");
            }
        }
        if(findViewById(R.id.random_button) == v) {

        }
        if(findViewById(R.id.settings_button) == v) {

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
            return true;
        }
        if(findViewById(R.id.theme_button) == v) {
            return true;
        }
        if(findViewById(R.id.random_button) == v) {
            return true;
        }
        if(findViewById(R.id.settings_button) == v) {
            return true;
        }
        return false;
    }

    /*
     * OnItemClick event handler.
     * Items in the ListView will use this method if pressed.
     * Will load and play the habile sound.
     * habile.mp3 is located in the res/raw folder.
     *
     * https://developer.android.com/reference/android/widget/AdapterView.OnItemClickListener.html
     */
    public void onItemClick(AdapterView parent, View v, int pos, long id) {
        if(findViewById(R.id.sound_List_Display) == parent) {
            AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float leftVolume = curVolume/maxVolume;
            float rightVolume = curVolume/maxVolume;
            int priority = 1;
            int loop = 0;
            float rate = 1f;
            soundPool.play(currentTheme.getSound(pos).getSoundId(), leftVolume, rightVolume, priority, loop, rate);
        }
    }

    /*
     * OnItemLongClick event handler.
     * Items in the ListView will use this method if pressed for a few seconds.
     * Will display a dialog box asking whether the user want to add the pressed
     * sound to favorites.
     * The return value is there to say whether we took care of the event or not.
     * If not, other event handlers might be fired. Has no implication for the moment.
     *
     * https://developer.android.com/reference/android/widget/AdapterView.OnItemLongClickListener.html
     */

    public boolean onItemLongClick(AdapterView parent, View v, final int pos, long id) {
        if(findViewById(R.id.sound_List_Display) == parent) {
            if (currentTheme != themeFav) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(getString(R.string.dialog_title));
                alertDialog.setMessage(currentTheme.getSoundNameList().get(pos));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                themeFav.addSound(currentTheme.getSound(pos));
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            }
            else{
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(getString(R.string.dialog_remove_from_fav));
                alertDialog.setMessage(currentTheme.getSoundNameList().get(pos));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                themeFav.removeSound(currentTheme.getSound(pos));
                                adapterFav.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            }
        }
        return false;
    }
}