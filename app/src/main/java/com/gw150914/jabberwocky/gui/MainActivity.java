package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    final int  soundLoadThread1StartIndex = 0;
    final int  soundLoadThread1EndIndex = 40;
    final int  soundLoadThread2StartIndex = 40;
    final int  soundLoadThread2EndIndex = 81;
    final int soundTotal = 81;

    private boolean thread0JobDone, thread1JobDone, thread2JobDone, thread10JobDone, thread11JobDone, soundInitDone, themeInitDone;
    private SoundEngine soundEngine;
    private ThemeEngine themeEngine;
    private Sound soundAndreaPasLa, soundAttention01, soundAttention02, soundAttention03, soundAttention04, soundAttention05, soundAttention06, soundAucunRapport, soundBonneIdee, soundCalomnie, soundChinois01, soundChinois02, soundCokeVachementBath, soundComprendsPas, soundCracheBeaucoup, soundDebandade, soundDefection, soundEmbarrassant, soundExigeReponse, soundFaux, soundFoutLaRage, soundGrosGourdin, soundGrosseBlague, soundHabile, soundHallucine, soundHumour, soundIncomprehensible, soundInteressePas, soundLeGitan, soundMachiavellique, soundMagnerLeCul, soundMaitreMichel, soundMalentendu, soundMarcheBien, soundMeSensSeul, soundMethTropDeLaBalle01, soundMethTropDeLaBalle02, soundMistake, soundNemrod, soundNoFuckingBalls, soundNouveaute, soundOhOui, soundOnSEmmerde, soundOsef, soundPasCool, soundPasDrole, soundPlaisanterie01, soundPlaisanterie02, soundPouleMouillee, soundPourquoi, soundPqEmergency, soundPqIncroyable, soundPqReche, soundPqTropDoux, soundPqTropManque, soundPrejudice, soundPrevoyant, soundPrisPropreJeu, soundPtitZizi, soundPtiteBite, soundPueDuCul, soundQueSePasseTIl, soundQuelqueSorte, soundQuiEstLa, soundQuoi01, soundQuoi02, soundQuoi03, soundSante, soundScandaleux, soundSuperBaise, soundSuperSpirituel, soundTrahison, soundTripleEpaisseur, soundTropPlaisir, soundTrucDeMazo, soundTrueStory, soundVachementBath, soundViens01, soundViens02, soundVieuxMan, soundVoirMaBite, soundVrai;
    private Theme themeAll, themeFav, themePq, themeTaunt;
    private SoundEngineFragment soundEngineFragment;
    private ThemeEngineFragment themeEngineFragment;
    private FragmentManager fragmentManager;
    private ArrayAdapter<String> adapter;
    private ListView soundListDisplay;
    private TextView currentThemeTextView;
    private TextView soundCountTextView;
    private TextView soundSpeedTextView;
    private ProgressBar progressBar;
    private SoundPool soundPool;
    private Context appContext;
    private Handler loadingHandler;
    private ThreadPoolExecutor threadPoolExec;

    Sound[] soundArray;


    /*****************************************************************************************
     * ===================================[ NESTED CLASSES ]================================ *
     *****************************************************************************************/

    //This classes is designed to load a set of sounds in memory in a separate thread.
    private class SoundInitThread implements Runnable {

        private Message message;

        SoundInitThread() {
            message = new Message();
            message.arg1 = 0;   //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {
            int index = 0;
            soundArray = new Sound[soundTotal+1];
            soundArray[index++] = new Sound("Andrea pas la",R.raw.andrea_pas_la);
            soundArray[index++] = new Sound("Attention - 01",R.raw.attention01);
            soundArray[index++] = new Sound("Attention - 02",R.raw.attention02);
            soundArray[index++] = new Sound("Attention - 03",R.raw.attention03);
            soundArray[index++] = new Sound("Attention - 04",R.raw.attention04);
            soundArray[index++] = new Sound("Attention - 05",R.raw.attention05);
            soundArray[index++] = new Sound("Attention - 06",R.raw.attention06);
            soundArray[index++] = new Sound("Aucun rapport",R.raw.aucun_rapport);
            soundArray[index++] = new Sound("Bonne idée",R.raw.bonne_idee);
            soundArray[index++] = new Sound("Calomnie",R.raw.calomnie);
            soundArray[index++] = new Sound("Chinois - 01",R.raw.chinois01);
            soundArray[index++] = new Sound("Chinois - 02",R.raw.chinois02);
            soundArray[index++] = new Sound("La Coke c'est vachement bath",R.raw.coke_vachement_bath);
            soundArray[index++] = new Sound("Comprends pas",R.raw.comprends_pas);
            soundArray[index++] = new Sound("Crache beaucoup",R.raw.crache_beaucoup);
            soundArray[index++] = new Sound("Débandade",R.raw.debandade);
            soundArray[index++] = new Sound("Défection",R.raw.defection);
            soundArray[index++] = new Sound("Embarrasant",R.raw.embarrassant);
            soundArray[index++] = new Sound("J'exige une réponse",R.raw.exige_reponse);
            soundArray[index++] = new Sound("Faux !",R.raw.faux);
            soundArray[index++] = new Sound("Fout la rage",R.raw.fout_la_rage);
            soundArray[index++] = new Sound("Gros gourdin",R.raw.gros_gourdin);
            soundArray[index++] = new Sound("Grosse blague",R.raw.grosse_blague);
            soundArray[index++] = new Sound("Habile",R.raw.habile);
            soundArray[index++] = new Sound("Hallucine",R.raw.hallucine);
            soundArray[index++] = new Sound("Humour",R.raw.humour);
            soundArray[index++] = new Sound("Incompréhensible",R.raw.incomprehensible);
            soundArray[index++] = new Sound("Interesse pas",R.raw.interesse_pas);
            soundArray[index++] = new Sound("Ha le Gitan",R.raw.le_gitan);
            soundArray[index++] = new Sound("Machiavellique",R.raw.machiavellique);
            soundArray[index++] = new Sound("Magner le cul",R.raw.magner_le_cul);
            soundArray[index++] = new Sound("Maitre Michel",R.raw.maitre_michel);
            soundArray[index++] = new Sound("C'est un malentendu",R.raw.malentendu);
            soundArray[index++] = new Sound("Ca marche bien",R.raw.marche_bien);
            soundArray[index++] = new Sound("Me sens seul",R.raw.me_sens_seul);
            soundArray[index++] = new Sound("La meth c'est trop de la balle - 01",R.raw.meth_trop_de_la_balle);
            soundArray[index++] = new Sound("La meth c'est trop de la balle - 02",R.raw.meth_trop_de_la_balle02);
            soundArray[index++] = new Sound("Mistake",R.raw.mistake);
            soundArray[index++] = new Sound("Nemrod",R.raw.nemrod);
            soundArray[index++] = new Sound("No Fucking Balls",R.raw.no_fucking_balls);
            soundArray[index++] = new Sound("Nouveaute",R.raw.nouveaute);
            soundArray[index++] = new Sound("Oh oui",R.raw.oh_oui);
            soundArray[index++] = new Sound("On s'emmerde",R.raw.on_s_emmerde);
            soundArray[index++] = new Sound("Osef",R.raw.osef);
            soundArray[index++] = new Sound("Pas Cool",R.raw.pas_cool);
            soundArray[index++] = new Sound("Pas Drole",R.raw.pas_drole);
            soundArray[index++] = new Sound("Plaisanterie - 01",R.raw.plaisanterie01);
            soundArray[index++] = new Sound("Plaisanterie - 02",R.raw.plaisanterie02);
            soundArray[index++] = new Sound("Poule mouillée",R.raw.poule_mouillee);
            soundArray[index++] = new Sound("Pourquoi",R.raw.pourquoi);
            soundArray[index++] = new Sound("PQ Emergency",R.raw.pq_emergency);
            soundArray[index++] = new Sound("PQ Incroyable",R.raw.pq_incroyable);
            soundArray[index++] = new Sound("Ce PQ est un peu reche",R.raw.pq_reche);
            soundArray[index++] = new Sound("PQ trop doux",R.raw.pq_trop_doux);
            soundArray[index++] = new Sound("PQ Trop manqué",R.raw.pq_trop_manque);
            soundArray[index++] = new Sound("Prejudice",R.raw.prejudice);
            soundArray[index++] = new Sound("Prevoyant",R.raw.prevoyant);
            soundArray[index++] = new Sound("Pris propre jeu",R.raw.pris_propre_jeu);
            soundArray[index++] = new Sound("Ptit zizi",R.raw.ptit_zizi);
            soundArray[index++] = new Sound("Ptite bite",R.raw.ptite_bite);
            soundArray[index++] = new Sound("Pue du cul",R.raw.pue_du_cul);
            soundArray[index++] = new Sound("Que se passe t'il ",R.raw.que_ce_passe_t_il);
            soundArray[index++] = new Sound("Quelque Sorte",R.raw.quelque_sorte);
            soundArray[index++] = new Sound("Qui est la",R.raw.qui_est_la);
            soundArray[index++] = new Sound("Quoi - 01",R.raw.quoi);
            soundArray[index++] = new Sound("Quoi - 02",R.raw.quoi02);
            soundArray[index++] = new Sound("Quoi - 03",R.raw.quoi03);
            soundArray[index++] = new Sound("Sante",R.raw.sante);
            soundArray[index++] = new Sound("Scandaleux",R.raw.scandaleux);
            soundArray[index++] = new Sound("Super Baise",R.raw.super_baise);
            soundArray[index++] = new Sound("Super Spirituel",R.raw.super_spirituel);
            soundArray[index++] = new Sound("Trahison",R.raw.trahison);
            soundArray[index++] = new Sound("Triple epaisseur",R.raw.triple_epaisseur);
            soundArray[index++] = new Sound("Trop plaisir",R.raw.trop_plaisir);
            soundArray[index++] = new Sound("Truc de mazo",R.raw.truc_de_mazo);
            soundArray[index++] = new Sound("True Story",R.raw.true_story);
            soundArray[index++] = new Sound("Vachement bath",R.raw.vachement_bath);
            soundArray[index++] = new Sound("Viens - 01",R.raw.viens01);
            soundArray[index++] = new Sound("Viens - 02",R.raw.viens02);
            soundArray[index++] = new Sound("Vieux man",R.raw.vieux_man);
            soundArray[index++] = new Sound("Voir ma bite",R.raw.voir_ma_bite);
            soundArray[index++] = new Sound("Vrai",R.raw.vrai);

            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    //This classes is designed to load a set of sounds in memory in a separate thread.
    private class SoundLoadThread1 implements Runnable {

        private Message message;

        SoundLoadThread1() {
            message = new Message();
            message.arg1 = 1;   //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {

            for(int index = soundLoadThread1StartIndex; index < soundLoadThread1EndIndex; ++index) {
                soundArray[index].setSoundId(soundPool.load(appContext, soundArray[index].getResId(), 1));
            }

            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    //This class is designed to load a set of sounds in memory in a separate thread.
    private class SoundLoadThread2 implements Runnable {

        private Message message;

        SoundLoadThread2() {
            message = new Message();
            message.arg1 = 2;   //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {

            for(int index = soundLoadThread2StartIndex; index < soundLoadThread2EndIndex; ++index) {
                soundArray[index].setSoundId(soundPool.load(appContext, soundArray[index].getResId(), 1));
            }

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

            for(int index = 0; index < soundTotal; ++index) {
                themeAll.addSound(soundArray[index]);
            }

            /*
            themeTaunt.addSound(soundInteressePas);
            themeTaunt.addSound(soundLeGitan);
            themeTaunt.addSound(soundMagnerLeCul);
            themeTaunt.addSound(soundNoFuckingBalls);
            themeTaunt.addSound(soundOnSEmmerde);
            themeTaunt.addSound(soundOsef);
            themeTaunt.addSound(soundPasDrole);
            themeTaunt.addSound(soundPouleMouillee);
            themeTaunt.addSound(soundPtiteBite);
            themeTaunt.addSound(soundPtitZizi);
            themeTaunt.addSound(soundVieuxMan);

            themePq.addSound(soundPqEmergency);
            themePq.addSound(soundPqIncroyable);
            themePq.addSound(soundPqReche);
            themePq.addSound(soundPqTropDoux);
            themePq.addSound(soundPqTropManque);
            themePq.addSound(soundTripleEpaisseur);
            */

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


            try {
                //FileReader reads the file
                fileReader = new FileReader(saveFile);

                //Pass stream to BufferedReader
                buffRead = new BufferedReader(fileReader);

                for (soundRead = buffRead.readLine(); soundRead != null; soundRead = buffRead.readLine()){
                    System.out.println(soundRead);
                    soundPosition = themeAll.getSoundNameList().indexOf(soundRead);
                    themeFav.addSound(themeAll.getSound(soundPosition));
                    System.out.println("Sound " + soundPosition + " loaded OK");
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
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

        //Create an object for each view we need to listen to so we can manipulate them in the code.
        ImageButton searchButton    = (ImageButton) findViewById(R.id.search_button);
        ImageButton themeButton     = (ImageButton) findViewById(R.id.theme_button);
        ImageButton randomButton    = (ImageButton) findViewById(R.id.random_button);
        ImageButton settingsButton  = (ImageButton) findViewById(R.id.settings_button);
        ImageButton slowButton     = (ImageButton) findViewById(R.id.slow_button);
        ImageButton fastButton    = (ImageButton) findViewById(R.id.fast_button);
        ImageButton loopButton  = (ImageButton) findViewById(R.id.loop_button);
        soundListDisplay            = (ListView) findViewById(R.id.sound_List_Display);
        currentThemeTextView        = (TextView) findViewById(R.id.current_theme_display);
        soundCountTextView          = (TextView) findViewById(R.id.sound_count_display);
        soundSpeedTextView          = (TextView) findViewById(R.id.sound_speed);
        progressBar                 = (ProgressBar) findViewById(R.id.progress_bar);

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

        //If either of the engine fragment is null, a full initialization/loading is required.
        if(soundEngineFragment == null || themeEngineFragment == null) {

            //Remove the sound ViewList and show the loading circle.
            soundListDisplay.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            //Create a thread pool with a fixed 3 thread slots. Pre-start all threads immediately.
            threadPoolExec = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
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

                            //Instantiate all settings threads.

                            SoundLoadThread1 soundLoadThread1 = new SoundLoadThread1();
                            SoundLoadThread2 soundLoadThread2 = new SoundLoadThread2();
                            ThemeInitThread themeinitThread = new ThemeInitThread();
                            FavLoadThread favLoadThread = new FavLoadThread();

                            //Submit the 3 setting threads to the thread pool.
                            threadPoolExec.submit(soundLoadThread1);
                            threadPoolExec.submit(soundLoadThread2);
                            threadPoolExec.submit(themeinitThread);
                            //threadPoolExec.submit(favLoadThread);

                            //Set loadingDone to true to prevent restarting setting threads later on.
                            soundInitDone = true;
                        }

                        //Once Themes are fully set update themeEngine, UI and then fragments.
                        if(thread10JobDone && !themeInitDone) {

                            //Add themes to the theme engine. WARNING: themeAll MUST be 1st, themeFav MUST be 2nd.
                            themeEngine.addTheme(themeAll);
                            //themeEngine.addTheme(themeFav);
                            //themeEngine.addTheme(themeTaunt);
                            //themeEngine.addTheme(themePq);

                            //Update UI
                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.sound_list, themeEngine.getCurrentTheme().getSoundNameList());
                            soundListDisplay.setAdapter(adapter);
                            currentThemeTextView.setText(themeEngine.getCurrentThemeString(appContext));
                            soundSpeedTextView.setText("x" + new DecimalFormat("#.##").format(soundEngine.getRate()));
                            soundCountTextView.setText(Integer.toString(themeEngine.getTheme(0).getSoundsCount()) + " " + getString(R.string.sound_count));

                            //Remove the loading circle and show the sound ViewList.
                            progressBar.setVisibility(View.GONE);
                            soundListDisplay.setVisibility(View.VISIBLE);

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
                    }
                }
            };

            //Nothing is done/finished at this point
            thread0JobDone = false;
            thread1JobDone = false;
            thread2JobDone = false;
            thread10JobDone = false;
            thread11JobDone = false;
            soundInitDone = false;
            themeInitDone = false;

            //Instantiate the sound engine.
            soundEngine = new SoundEngine((AudioManager) this.getSystemService(Context.AUDIO_SERVICE));
            themeEngine = new ThemeEngine();

            //Instantiate Themes.
            themeAll = new Theme(getString(R.string.theme_all_name));
            themeFav = new Theme(getString(R.string.theme_favorites_name));
            themePq = new Theme("PQ");
            themeTaunt = new Theme("Taunt");

            //Save the soundEngine sound pool now to avoid multiple soundEngine calls in threads.
            soundPool = soundEngine.getSoundPool();

            //Instantiate all loading threads.
            SoundInitThread loadingThread1 = new SoundInitThread();

            //Submit the 3 loading threads to the thread pool.
            threadPoolExec.submit(loadingThread1);
        }

        //If both engine fragments are available, retrieve both engine from fragments and do minimal work.
        else {

            //Remove the loading circle and show the sound ViewList.
            progressBar.setVisibility(View.GONE);
            soundListDisplay.setVisibility(View.VISIBLE);

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
    public void onDestroy() {
        super.onDestroy();
        // store the data in the fragment
        soundEngineFragment.setData(soundEngine);
        themeEngineFragment.setData(themeEngine);


        //save Favorites list in a file in the app's internal storage
        FileWriter writer;
        BufferedWriter buffWrite;
        File saveFile = new File(appContext.getFilesDir(), "saved_fav.txt");
        int soundIndex;
        String soundBuffer;

        try {
            if(!saveFile.exists()) {
                saveFile.createNewFile();
                System.out.println(saveFile + "file created");
            }
            writer = new FileWriter(saveFile);
            buffWrite = new BufferedWriter(writer);

            for(soundIndex = 0; soundIndex < themeFav.getSoundsCount(); soundIndex++){
                soundBuffer = themeFav.getSound(soundIndex).getName();
                buffWrite.write(soundBuffer);
                System.out.println(soundBuffer);
                buffWrite.newLine();
                buffWrite.flush();
                System.out.println("Line " + soundIndex + " OK");

            }
            System.out.println("Fav list saved");



        } catch (IOException ioe) {
            ioe.printStackTrace();
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
                sound.setSoundId(soundEngine.getSoundPool().load(appContext, sound.getResId(), 1));
            }
            soundEngine.playSound(themeEngine.getCurrentTheme().getSound(pos).getSoundId());
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