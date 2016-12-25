package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gw150914.jabberwocky.R;
import com.gw150914.jabberwocky.core.SoundEngineFragment;
import com.gw150914.jabberwocky.core.Theme;
import com.gw150914.jabberwocky.core.Sound;
import com.gw150914.jabberwocky.core.SoundEngine;
import com.gw150914.jabberwocky.core.ThemeEngine;
import com.gw150914.jabberwocky.core.ThemeEngineFragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends Activity implements View.OnClickListener,View.OnLongClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {


    /*****************************************************************************************
     * ===================================[ PRIVATE FIELDS ]================================ *
     *****************************************************************************************/

    final int TOTAL_SOUND = 82;
    final int MAX_THREAD = 4;

    int linearLoadingThread, smartLoadingThread, ondemandLoadingThread;

    boolean thread0JobDone, thread1JobDone, thread2JobDone, thread10JobDone, thread11JobDone, soundInitDone, themeInitDone, soundLoadDone;
    SoundEngine soundEngine;
    ThemeEngine themeEngine;
    Theme themeAll, themeFav, themePq, themeTaunt;
    SoundEngineFragment soundEngineFragment;
    ThemeEngineFragment themeEngineFragment;
    FragmentManager fragmentManager;
    ArrayAdapter<String> adapter;
    ListView soundListDisplay;
    TextView currentThemeTextView;
    TextView soundCountTextView;
    TextView soundSpeedTextView;
    SoundPool soundPool;
    Context appContext;
    Handler loadingHandler;
    ThreadPoolExecutor threadPoolExec;
    Sound[] soundArray;


    /*****************************************************************************************
     * ===================================[ NESTED CLASSES ]================================ *
     *****************************************************************************************/

    //This classes is designed to init a set of sound objects in a separate thread.
    private class SoundInitThread implements Runnable {

        private Message message;

        SoundInitThread() {
            message = new Message();
            message.arg1 = 0;   //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {
            soundArray = new Sound[TOTAL_SOUND];
            soundArray[0] = new Sound("Andrea pas la",R.raw.andrea_pas_la);
            soundArray[1] = new Sound("Attention - 01",R.raw.attention01);
            soundArray[2] = new Sound("Attention - 02",R.raw.attention02);
            soundArray[3] = new Sound("Attention - 03",R.raw.attention03);
            soundArray[4] = new Sound("Attention - 04",R.raw.attention04);
            soundArray[5] = new Sound("Attention - 05",R.raw.attention05);
            soundArray[6] = new Sound("Attention - 06",R.raw.attention06);
            soundArray[7] = new Sound("Aucun rapport",R.raw.aucun_rapport);
            soundArray[8] = new Sound("Bonne idée",R.raw.bonne_idee);
            soundArray[9] = new Sound("Calomnie",R.raw.calomnie);
            soundArray[10] = new Sound("Chinois - 01",R.raw.chinois01);
            soundArray[11] = new Sound("Chinois - 02",R.raw.chinois02);
            soundArray[12] = new Sound("La Coke c'est vachement bath",R.raw.coke_vachement_bath);
            soundArray[13] = new Sound("Comprends pas",R.raw.comprends_pas);
            soundArray[14] = new Sound("Crache beaucoup",R.raw.crache_beaucoup);
            soundArray[15] = new Sound("Débandade",R.raw.debandade);
            soundArray[16] = new Sound("Défection",R.raw.defection);
            soundArray[17] = new Sound("Embarrasant",R.raw.embarrassant);
            soundArray[18] = new Sound("J'exige une réponse",R.raw.exige_reponse);
            soundArray[19] = new Sound("Faux !",R.raw.faux);
            soundArray[20] = new Sound("Fout la rage",R.raw.fout_la_rage);
            soundArray[21] = new Sound("Gros gourdin",R.raw.gros_gourdin);
            soundArray[22] = new Sound("Grosse blague",R.raw.grosse_blague);
            soundArray[23] = new Sound("Habile",R.raw.habile);
            soundArray[24] = new Sound("Hallucine",R.raw.hallucine);
            soundArray[25] = new Sound("Humour",R.raw.humour);
            soundArray[26] = new Sound("Incompréhensible",R.raw.incomprehensible);
            soundArray[27] = new Sound("Interesse pas",R.raw.interesse_pas);
            soundArray[28] = new Sound("Ha le Gitan",R.raw.le_gitan);
            soundArray[29] = new Sound("Machiavellique",R.raw.machiavellique);
            soundArray[30] = new Sound("Magner le cul",R.raw.magner_le_cul);
            soundArray[31] = new Sound("Maitre Michel",R.raw.maitre_michel);
            soundArray[32] = new Sound("C'est un malentendu",R.raw.malentendu);
            soundArray[33] = new Sound("Ca marche bien",R.raw.marche_bien);
            soundArray[34] = new Sound("Me sens seul",R.raw.me_sens_seul);
            soundArray[35] = new Sound("La meth c'est trop de la balle - 01",R.raw.meth_trop_de_la_balle);
            soundArray[36] = new Sound("La meth c'est trop de la balle - 02",R.raw.meth_trop_de_la_balle02);
            soundArray[37] = new Sound("Mistake",R.raw.mistake);
            soundArray[38] = new Sound("Nemrod",R.raw.nemrod);
            soundArray[39] = new Sound("No Fucking Balls",R.raw.no_fucking_balls);
            soundArray[40] = new Sound("Nouveaute",R.raw.nouveaute);
            soundArray[41] = new Sound("Oh oui",R.raw.oh_oui);
            soundArray[42] = new Sound("On s'emmerde",R.raw.on_s_emmerde);
            soundArray[43] = new Sound("Osef",R.raw.osef);
            soundArray[44] = new Sound("Pas Cool",R.raw.pas_cool);
            soundArray[45] = new Sound("Pas Drole",R.raw.pas_drole);
            soundArray[46] = new Sound("Plaisanterie - 01",R.raw.plaisanterie01);
            soundArray[47] = new Sound("Plaisanterie - 02",R.raw.plaisanterie02);
            soundArray[48] = new Sound("Poule mouillée",R.raw.poule_mouillee);
            soundArray[49] = new Sound("Pourquoi",R.raw.pourquoi);
            soundArray[50] = new Sound("PQ Emergency",R.raw.pq_emergency);
            soundArray[51] = new Sound("PQ Incroyable",R.raw.pq_incroyable);
            soundArray[52] = new Sound("Ce PQ est un peu reche",R.raw.pq_reche);
            soundArray[53] = new Sound("PQ trop doux",R.raw.pq_trop_doux);
            soundArray[54] = new Sound("PQ Trop manqué",R.raw.pq_trop_manque);
            soundArray[55] = new Sound("Prejudice",R.raw.prejudice);
            soundArray[56] = new Sound("Prevoyant",R.raw.prevoyant);
            soundArray[57] = new Sound("Pris propre jeu",R.raw.pris_propre_jeu);
            soundArray[58] = new Sound("Ptit zizi",R.raw.ptit_zizi);
            soundArray[59] = new Sound("Ptite bite",R.raw.ptite_bite);
            soundArray[60] = new Sound("Pue du cul",R.raw.pue_du_cul);
            soundArray[61] = new Sound("Que se passe t'il ",R.raw.que_ce_passe_t_il);
            soundArray[62] = new Sound("Quelque Sorte",R.raw.quelque_sorte);
            soundArray[63] = new Sound("Qui est la",R.raw.qui_est_la);
            soundArray[64] = new Sound("Quoi - 01",R.raw.quoi);
            soundArray[65] = new Sound("Quoi - 02",R.raw.quoi02);
            soundArray[66] = new Sound("Quoi - 03",R.raw.quoi03);
            soundArray[67] = new Sound("Sante",R.raw.sante);
            soundArray[68] = new Sound("Scandaleux",R.raw.scandaleux);
            soundArray[69] = new Sound("Super Baise",R.raw.super_baise);
            soundArray[70] = new Sound("Super Spirituel",R.raw.super_spirituel);
            soundArray[71] = new Sound("Trahison",R.raw.trahison);
            soundArray[72] = new Sound("Triple epaisseur",R.raw.triple_epaisseur);
            soundArray[73] = new Sound("Trop plaisir",R.raw.trop_plaisir);
            soundArray[74] = new Sound("Truc de mazo",R.raw.truc_de_mazo);
            soundArray[75] = new Sound("True Story",R.raw.true_story);
            soundArray[76] = new Sound("Vachement bath",R.raw.vachement_bath);
            soundArray[77] = new Sound("Viens - 01",R.raw.viens01);
            soundArray[78] = new Sound("Viens - 02",R.raw.viens02);
            soundArray[79] = new Sound("Vieux man",R.raw.vieux_man);
            soundArray[80] = new Sound("Voir ma bite",R.raw.voir_ma_bite);
            soundArray[81] = new Sound("Vrai",R.raw.vrai);

            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }


    //This classes is designed to load a set of sounds in memory in a separate thread.
    private class SoundLoadThread implements Runnable {

        private int start;
        private int end;
        private int modulo;
        private Message message;
        private Sound[] soundList;

        SoundLoadThread(Sound[] soundList, int start, int end, int modulo, int threadId) {

            message = new Message();    //message object to be sent to thread handler
            message.arg1 = threadId;    //thread id
            message.arg2 = 0;           //thread status (0=ongoing, 1=done)
            this.soundList = soundList; //the sound list to load in memory
            this.start = start;         //starting point in the sound list to load
            this.end = end;             //ending point in the sound list to load
            this.modulo = modulo;       //iteration modulo in the sound list to load
        }

        public void run() {

            System.out.println("LOADING THREAD");
            System.out.println("START: " + start);
            System.out.println("END: " + end);
            System.out.println("MODULO: " + modulo);
            System.out.println("ID: " + message.arg1);

            for(int index = start; index < end; index += modulo) {
                if(soundList[index].getSoundId() == 0) {
                    soundList[index].setSoundId(soundPool.load(appContext, soundList[index].getResId(), 1));
                }
            }

            System.out.println("DEBUG: Loading Thread finished: " + message.arg1);
            System.out.println("DEBUG: Loading Thread finished: " + message.arg1);
            System.out.println("DEBUG: Loading Thread finished: " + message.arg1);
            System.out.println("DEBUG: Loading Thread finished: " + message.arg1);

            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    //This class is designed to setup the theme "All"
    private class ThemeInitThread implements Runnable {

        private Message message;

        ThemeInitThread() {
            message = new Message();
            message.arg1 = 10;  //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {

            //Set theme all
            for(int index = 0; index < TOTAL_SOUND; ++index) {
                themeAll.addSound(soundArray[index]);
            }

            //Theme all is ready, theme fav loading can now start.
            threadPoolExec.submit(new FavLoadThread());

            //Set theme taunt
            themeTaunt.addSound(soundArray[27]);
            themeTaunt.addSound(soundArray[28]);
            themeTaunt.addSound(soundArray[30]);
            themeTaunt.addSound(soundArray[39]);
            themeTaunt.addSound(soundArray[42]);
            themeTaunt.addSound(soundArray[43]);
            themeTaunt.addSound(soundArray[45]);
            themeTaunt.addSound(soundArray[48]);
            themeTaunt.addSound(soundArray[58]);
            themeTaunt.addSound(soundArray[59]);
            themeTaunt.addSound(soundArray[79]);

            //Set theme pq
            themePq.addSound(soundArray[50]);
            themePq.addSound(soundArray[51]);
            themePq.addSound(soundArray[52]);
            themePq.addSound(soundArray[50]);
            themePq.addSound(soundArray[50]);
            themePq.addSound(soundArray[72]);

            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    //This class is designed to setup the theme "Favorites"
    private class FavLoadThread implements Runnable {

        private Message message;

        FavLoadThread() {
            message = new Message();
            message.arg1 = 11;  //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {

            File saveFile = new File(appContext.getFilesDir(), "saved_fav.txt");
            BufferedReader buffRead;
            FileReader fileReader;
            String soundRead;
            int soundPosition;

            if(saveFile.canRead()) {
                try {
                    //FileReader reads the file
                    fileReader = new FileReader(saveFile);

                    //Pass stream to BufferedReader
                    buffRead = new BufferedReader(fileReader);

                    for (soundRead = buffRead.readLine(); soundRead != null; soundRead = buffRead.readLine()) {
                        System.out.println(soundRead);
                        soundPosition = themeAll.getSoundNameList().indexOf(soundRead);
                        themeFav.addSound(themeAll.getSound(soundPosition));
                        System.out.println("Sound " + soundPosition + " loaded OK");
                    }
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
            System.out.println("fav list loaded");
        }
    }


    /*****************************************************************************************
     * ==============================[ ACTIVITY STATE METHODS ]============================= *
     *****************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(SettingsActivity.SETTINGS_FILE_NAME,MODE_PRIVATE);

        //Create an object for each view we need to listen to so we can manipulate them in the code.
        ImageButton searchButton    = (ImageButton) findViewById(R.id.search_button);
        ImageButton themeButton     = (ImageButton) findViewById(R.id.theme_button);
        ImageButton randomButton    = (ImageButton) findViewById(R.id.random_button);
        ImageButton settingsButton  = (ImageButton) findViewById(R.id.settings_button);
        ImageButton slowButton      = (ImageButton) findViewById(R.id.slow_button);
        ImageButton fastButton      = (ImageButton) findViewById(R.id.fast_button);
        ImageButton loopButton      = (ImageButton) findViewById(R.id.loop_button);
        soundListDisplay            = (ListView) findViewById(R.id.sound_List_Display);
        currentThemeTextView        = (TextView) findViewById(R.id.current_theme_display);
        soundCountTextView          = (TextView) findViewById(R.id.sound_count_display);
        soundSpeedTextView          = (TextView) findViewById(R.id.sound_speed);

        //For each object we created above, designate event listeners.
        searchButton.setOnClickListener(this);
        searchButton.setOnLongClickListener(this);
        themeButton.setOnClickListener(this);
        themeButton.setOnLongClickListener(this);
        randomButton.setOnClickListener(this);
        randomButton.setOnLongClickListener(this);
        settingsButton.setOnClickListener(this);
        settingsButton.setOnLongClickListener(this);
        slowButton.setOnClickListener(this);
        slowButton.setOnLongClickListener(this);
        fastButton.setOnClickListener(this);
        fastButton.setOnLongClickListener(this);
        loopButton.setOnClickListener(this);
        loopButton.setOnLongClickListener(this);
        soundListDisplay.setOnItemClickListener(this);
        soundListDisplay.setOnItemLongClickListener(this);

        //Save the app context so threads can get a context when needed.
        appContext = this.getApplicationContext();

        //Attempt to retrieve the two engines fragments in memory.
        fragmentManager = getFragmentManager();
        soundEngineFragment = (SoundEngineFragment) fragmentManager.findFragmentByTag("soundEngine");
        themeEngineFragment = (ThemeEngineFragment) fragmentManager.findFragmentByTag("themeEngine");

        if(soundEngineFragment == null) {
            System.out.println("DEBUG: Sound Engine is not available from fragments.");
        }
        else {
            System.out.println("DEBUG: Sound Engine is available from fragments.");
        }
        if(themeEngineFragment == null) {
            System.out.println("DEBUG: Theme Engine is not available from fragments.");
        }
        else {
            System.out.println("DEBUG: Theme Engine is available from fragments.");
        }

        //If either of the engine fragment is null, a full initialization/loading is required.
        if(soundEngineFragment == null || themeEngineFragment == null) {

            System.out.println("DEBUG: Engines not available from fragments.");

            linearLoadingThread = settings.getInt("linearLoadingThread", SettingsActivity.LINEAR_LOADING_THREAD_DEFAULT);
            smartLoadingThread = settings.getInt("smartLoadingThread", SettingsActivity.SMART_LOADING_THREAD_DEFAULT);
            ondemandLoadingThread = settings.getInt("ondemandLoadingThread", SettingsActivity.ONDEMAND_LOADING_THREAD_DEFAULT);

            //Create a thread pool with a fixed MAX_THREAD thread slots. Pre-start all threads immediately.
            threadPoolExec = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREAD);
            threadPoolExec.prestartAllCoreThreads();

            /********************************************************
             * Create a new handler for threads.                    *
             * This handler is bound to the UI thread so it can     *
             * manipulate UI elements (unlike threads)              *
             ********************************************************/
            loadingHandler = new Handler(Looper.getMainLooper()) {

                //Method used when the handler is receiving messages from threads.
                @Override
                public void handleMessage(Message message) {

                    //A thread has just finished its job.
                    if(message.arg2 == 1) {

                        //Check which thread sent this message
                        switch(message.arg1) {
                            case (0):
                                thread0JobDone = true;
                                break; //thread #0 has finished its job.
                            case (1):
                                thread1JobDone = true;
                                break; //thread #1 has finished its job.
                            case (2):
                                thread2JobDone = true;
                                break; //thread #2 has finished its job.
                            case (10):
                                thread10JobDone = true;
                                break; //thread #10 has finished its job.
                            case (11):
                                thread11JobDone = true;
                                break; //thread #11 has finished its job.
                        }

                        //If all Loading threads are finished, then start the set Thread.
                        if(thread0JobDone && !soundInitDone) {

                            //We do not want to go there anymore.
                            soundInitDone = true;

                            System.out.println("DEBUG: linearLoadingThread: " + linearLoadingThread);
                            System.out.println("DEBUG: linearLoadingThread: " + linearLoadingThread);
                            System.out.println("DEBUG: linearLoadingThread: " + linearLoadingThread);
                            System.out.println("DEBUG: linearLoadingThread: " + linearLoadingThread);
                            System.out.println("DEBUG: linearLoadingThread: " + linearLoadingThread);
                            System.out.println("DEBUG: linearLoadingThread: " + linearLoadingThread);

                            //Submit loading threads to the thread pool.
                            for(int loadingThreadId = 1; loadingThreadId <= linearLoadingThread; ++loadingThreadId) {
                                threadPoolExec.submit(new SoundLoadThread(soundArray, (loadingThreadId - 1), TOTAL_SOUND, linearLoadingThread , loadingThreadId ));
                            }

                            //Submit the Theme init thread to the thread pool.
                            threadPoolExec.submit(new ThemeInitThread());
                        }

                        //Once Themes are fully set update themeEngine, UI and then fragments.
                        if(thread10JobDone && !themeInitDone) {

                            //We do not want to go there anymore.
                            themeInitDone = true;

                            //Add themes to the theme engine. WARNING: themeAll MUST be 1st, themeFav MUST be 2nd.
                            themeEngine.addTheme(themeAll);
                            themeEngine.addTheme(themeFav);
                            themeEngine.addTheme(themeTaunt);
                            themeEngine.addTheme(themePq);

                            //Update UI
                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.sound_list, themeEngine.getCurrentTheme().getSoundNameList());
                            soundListDisplay.setAdapter(adapter);
                            currentThemeTextView.setText(themeEngine.getCurrentThemeString(appContext));
                            soundSpeedTextView.setText("x" + new DecimalFormat("#.##").format(soundEngine.getRate()));
                            soundCountTextView.setText(Integer.toString(themeEngine.getTheme(0).getSoundsCount()) + " " + getString(R.string.sound_count));

                            //Instantiate Fragments for the first time.
                            soundEngineFragment = new SoundEngineFragment();
                            themeEngineFragment = new ThemeEngineFragment();

                            //Setup both engine fragment with an unique id.
                            fragmentManager.beginTransaction().add(soundEngineFragment, "soundEngine").commit();
                            fragmentManager.beginTransaction().add(themeEngineFragment, "themeEngine").commit();

                            //Add data to both engine fragments.
                            soundEngineFragment.setData(soundEngine);
                            themeEngineFragment.setData(themeEngine);
                        }
                        if(thread1JobDone && thread2JobDone && !soundLoadDone) {
                            soundLoadDone = true;
                        }
                    }
                }
            };

            //Nothing is done/finished at this point
            thread0JobDone  = false;
            thread1JobDone  = false;
            thread2JobDone  = false;
            thread10JobDone = false;
            thread11JobDone = false;
            soundInitDone   = false;
            themeInitDone   = false;
            soundLoadDone   = false;

            //Instantiate the sound engine.
            soundEngine     = new SoundEngine((AudioManager) this.getSystemService(Context.AUDIO_SERVICE));
            themeEngine     = new ThemeEngine();

            //Instantiate Themes.
            themeAll        = new Theme(getString(R.string.theme_all_name));
            themeFav        = new Theme(getString(R.string.theme_favorites_name));
            themePq         = new Theme("PQ");
            themeTaunt      = new Theme("Taunt");

            //Save the soundEngine sound pool now to avoid multiple soundEngine calls in threads.
            soundPool = soundEngine.getSoundPool();

            //Set an onLoadComplete listener for the soundPool.
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                        public void onLoadComplete(SoundPool soundPool, int soundId, int status) {

                            int index = themeEngine.getTheme(0).getIndexBySoundId(soundId);
                            Sound sound;

                            if(index >= 0) {
                                sound = themeEngine.getTheme(0).getSound(index);
                                System.out.println("SOUND LOADED: ID=" + soundId + " | Index=" + index + " | name=" + sound.getName());
                                if(sound.getonDemandFlag()) {
                                    soundEngine.playSound(soundId);
                                    sound.setOnDemandFlag(false);
                                }
                            }
                        }
                    }
            );

            //Instantiate all loading threads.
            SoundInitThread loadingThread1 = new SoundInitThread();

            //Submit the 3 loading threads to the thread pool.
            threadPoolExec.submit(loadingThread1);
        }

        //If both engine fragments are available, retrieve both engine from fragments and do minimal work.
        else {

            System.out.println("DEBUG: Loading engines from fragments");

            //Retrieve engines from fragments.
            soundEngine = soundEngineFragment.getData();
            themeEngine = themeEngineFragment.getData();

            //Update UI
            currentThemeTextView.setText(themeEngine.getCurrentThemeString(this));
            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.sound_list, themeEngine.getCurrentTheme().getSoundNameList());
            soundListDisplay.setAdapter(adapter);
            soundCountTextView.setText(Integer.toString(themeEngine.getCurrentTheme().getSoundsCount()) + " " + getString(R.string.sound_count));
            soundSpeedTextView.setText("x" + new DecimalFormat("#.##").format(soundEngine.getRate()));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // store the data in the fragment
        soundEngineFragment.setData(soundEngine);
        themeEngineFragment.setData(themeEngine);


        //save Favorites list in a file in the app's internal storage
        FileWriter writer;
        BufferedWriter buffWrite;
        File saveFile = new File(appContext.getFilesDir(), "saved_fav.txt");
        int soundIndex;
        String soundBuffer;

        System.out.println("DEBUG: Trying to save favorites file");
        if(!saveFile.exists()) {
            try {
                if (!saveFile.exists()) {
                    saveFile.createNewFile();
                    System.out.println("DEBUG: File does not exist, creating it: " + saveFile);
                }
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        else {
            System.out.println("DEBUG: File already exist");
        }

        try {
            writer = new FileWriter(saveFile);
            buffWrite = new BufferedWriter(writer);

            for(soundIndex = 0; soundIndex < themeFav.getSoundsCount(); ++soundIndex){
                soundBuffer = themeFav.getSound(soundIndex).getName();
                buffWrite.write(soundBuffer);
                System.out.println(soundBuffer);
                buffWrite.newLine();
                buffWrite.flush();
                System.out.println("Line " + soundIndex + " OK");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1) {
            int linearLoadingThread = (int) data.getExtras().get("linearLoadingThread");
            System.out.println("DEBUG: User saved new changes:");
            System.out.println("DEBUG: linearLoadingThread: " + linearLoadingThread);
        }
        if(resultCode == 0) {
            System.out.println("DEBUG: User cancelled changes");
        }
    }



    /*****************************************************************************************
     * ====================================[ UI LISTENERS ]================================= *
     *****************************************************************************************/

    /***********************[ onClick ]**********************
     * OnClick event handler. All four buttons will use     *
     * this method if clicked. There is a check on which    *
     * button was clicked so the correct action is performed*
     ********************************************************/

    public void onClick(View v) {

        //Search button was clicked
        if(findViewById(R.id.search_button) == v) {
            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
            View searchView = layoutInflaterAndroid.inflate(R.layout.search_dialog, null);

            final AlertDialog searchDialog = new AlertDialog.Builder(this).create();
            searchDialog.setCanceledOnTouchOutside(false);
            final EditText userInput = (EditText) searchView.findViewById(R.id.userInputDialog);
            Button sendButton = (Button) searchView.findViewById(R.id.send_button);
            Button cancelButton = (Button) searchView.findViewById(R.id.cancel_button);
            final Theme themeSearch = new Theme("Last search");

            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int searchIndex;
                    //get text from EditText and store in a String
                    final String userSearch = userInput.getText().toString();
                    System.out.println(userSearch);


                     for(searchIndex = 0; searchIndex < themeAll.getSoundsCount(); searchIndex++) {
                           String buffSearch = themeAll.getSoundNameList().get(searchIndex);
                           if (buffSearch.toLowerCase().contains(userSearch.toLowerCase())) {
                               themeSearch.addSound(themeAll.getSound(searchIndex));
                           }
                      }

                    int nbResults = themeSearch.getSoundsCount();
                        if (nbResults != 0) {
                            searchDialog.dismiss();

                            //Set current active theme to the search results.
                            themeEngine.setCurrentTheme(themeSearch);

                            //Update UI accordingly to the new current active theme.
                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.sound_list, themeEngine.getCurrentTheme().getSoundNameList());
                            soundListDisplay.setAdapter(adapter);
                            currentThemeTextView.setText(themeEngine.getCurrentThemeString(appContext));
                            soundCountTextView.setText(Integer.toString(themeEngine.getCurrentTheme().getSoundsCount()) + " " + getString(R.string.sound_count));
                        }
                        else {
                            AlertDialog noResultsDialog = new AlertDialog.Builder(MainActivity.this).create();
                            noResultsDialog.setTitle(getString(R.string.no_results_dialog));
                            noResultsDialog.setCancelable(false);
                            noResultsDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, getString(R.string.rage_quit),
                                    new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface d, int which) {
                                    System.out.println("No results for " + userSearch);
                                    searchDialog.dismiss();
                                    d.dismiss();
                                }
                            });


                            noResultsDialog.show();
                        }
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    themeEngine.removeTheme(themeSearch);
                    searchDialog.cancel();
                }
            });
            searchDialog.setView(searchView);
            searchDialog.show();
        }


        //Theme button was clicked
        if(findViewById(R.id.theme_button) == v || findViewById(R.id.current_theme_display) == v) {
            AlertDialog.Builder choice_dialog = new AlertDialog.Builder(MainActivity.this);
            choice_dialog.setTitle(getString(R.string.choose_theme));
            choice_dialog.setItems(themeEngine.getThemeNameList(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Set current active theme to the chosen theme.
                    themeEngine.setCurrentTheme(themeEngine.getThemeList()[which]);

                    //Update UI accordingly to the new current active theme.
                    adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.sound_list,themeEngine.getCurrentTheme().getSoundNameList());
                    soundListDisplay.setAdapter(adapter);
                    currentThemeTextView.setText(themeEngine.getCurrentThemeString(appContext));
                    soundCountTextView.setText(Integer.toString(themeEngine.getCurrentTheme().getSoundsCount()) + " " + getString(R.string.sound_count));
                    if(!soundLoadDone) {
                        threadPoolExec.submit(new SoundLoadThread(themeEngine.getCurrentTheme().getSoundList(), 0, themeEngine.getCurrentTheme().getSoundsCount(), 1, -1));
                    }
                    dialog.dismiss();
                }
            });
            choice_dialog.create();
            choice_dialog.show();
        }

        //Random button was clicked
        if(findViewById(R.id.random_button) == v) {
            if (themeEngine.getCurrentTheme().getSoundsCount() != 0) {
                soundEngine.playSound(themeEngine.getCurrentTheme().getRandomSound().getSoundId());
            }
        }

        //Settings button was clicked
        if(findViewById(R.id.settings_button) == v) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, 0);
        }

        //Slow button was clicked
        if(findViewById(R.id.slow_button) == v) {
            float rate = soundEngine.getRate();
            if(rate > 0.5) {
                rate -= 0.1;
                soundEngine.setRate(rate);
                soundSpeedTextView.setText("x" + new DecimalFormat("#.##").format(rate));
            }
        }
        //Fast button was clicked
        if(findViewById(R.id.fast_button) == v) {
            float rate = soundEngine.getRate();
            if(rate < 2.0) {
                rate += 0.1;
                soundEngine.setRate(rate);
                soundSpeedTextView.setText("x" + new DecimalFormat("#.##").format(rate));
            }
        }
        //Loop button was clicked
        if(findViewById(R.id.loop_button) == v) {
            if(soundEngine.getLoop() == -1) {
                soundEngine.setLoop(0);
                soundEngine.stopAllLoopingStreams();
            }
            else {
                soundEngine.setLoop(-1);
            }

        }
    }

    /*********************[ onLongClick ]********************
     * OnLongClick event handler. All four buttons will use *
     * this method if clicked and held for a few seconds.   *
     * There is a check on which button was clicked so the  *
     * correct action is performed.                         *
     * Has no real uses for the moment.                     *
     ********************************************************/
    public boolean onLongClick(View v) {

        //Search button was clicked and held
        if(findViewById(R.id.search_button) == v) {
            return true;
        }

        //Theme button was clicked and held
        if(findViewById(R.id.theme_button) == v) {

            return true;
        }

        //Random button was clicked and held
        if(findViewById(R.id.random_button) == v) {
            return true;
        }

        //Setting button was clicked and held
        if(findViewById(R.id.settings_button) == v) {
            return true;
        }
        return false;
    }

    /*********************[ onItemClick ]********************
     * OnItemClick event handler. All elements in the       *
     * listView (sound list shown to the user) will use     *
     * this method if clicked.                              *
     * If the item parent is the sound listView, then the   *
     * correct sound will be played                         *
     ********************************************************/
    public void onItemClick(AdapterView parent, View v, int pos, long id) {
        if(findViewById(R.id.sound_List_Display) == parent) {
            Sound sound = themeEngine.getCurrentTheme().getSound(pos);
            if(sound.getSoundId() == 0) {
                sound.setOnDemandFlag(true);
                sound.setSoundId(soundEngine.getSoundPool().load(appContext, sound.getResId(), 1));
            }
            else {
                soundEngine.playSound(themeEngine.getCurrentTheme().getSound(pos).getSoundId());
            }
        }
    }

    /*******************[ onItemLongClick ]******************
     * OnItemLongClick event handler. All elements in the   *
     * listView (sound list shown to the user) will use     *
     * this method if clicked and held for a few seconds.   *
     * Will display a dialog box asking if the user want to *
     * add the pressed sound to favorites.                  *
     * The return value is there to say whether we took     *
     * care of the event or not.                            *
     * If not, other event handlers might be fired.         *
     ********************************************************/
    public boolean onItemLongClick(AdapterView parent, View v, final int pos, long id) {

        //Check if the item parent is the sound ViewList
        if(findViewById(R.id.sound_List_Display) == parent) {

            //Current active theme is NOT favorites. Create a "add to favorites" dialog.
            if(themeEngine.getCurrentTheme() != themeEngine.getTheme(1)) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(getString(R.string.dialog_add_to_fav));
                alertDialog.setMessage(themeEngine.getCurrentTheme().getSoundNameList().get(pos));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                //Add the sound to the favorites theme.
                                themeEngine.getTheme(1).addSound(themeEngine.getCurrentTheme().getSound(pos));

                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            }

            //Current active theme IS favorites. Create a "remove from favorites" dialog.
            else {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(getString(R.string.dialog_remove_from_fav));
                alertDialog.setMessage(themeEngine.getCurrentTheme().getSoundNameList().get(pos));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                //Remove the sound from the favorites theme and update UI.
                                themeEngine.getTheme(1).removeSound(themeEngine.getCurrentTheme().getSound(pos));
                                adapter.notifyDataSetChanged();

                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            }
        }
        return false;
    }


/*
MEDITATIONS X.XVIII. - M.Aurelius
**
Make it not any longer a matter of dispute or discourse,
what are the signs and proprieties of a good man,
but really and actually be such.
*/

}