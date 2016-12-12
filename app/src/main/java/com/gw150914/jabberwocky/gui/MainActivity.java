package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends Activity implements View.OnClickListener,View.OnLongClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {


    /*****************************************************************************************
     * ===================================[ PRIVATE FIELDS ]================================ *
     *****************************************************************************************/

    private boolean thread1JobDone, thread2JobDone, thread3JobDone, thread11JobDone, thread12JobDone, thread13JobDone, loadingDone;
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
    private ProgressBar progressBar;
    private SoundPool soundPool;
    private Context appContext;
    private Handler loadingHandler;
    private ThreadPoolExecutor threadPoolExec;


    /*****************************************************************************************
     * ===================================[ NESTED CLASSES ]================================ *
     *****************************************************************************************/

    //This classes is designed to load a set of sounds in memory in a separate thread.
    private class LoadThread1 implements Runnable {

        private Message message;

        LoadThread1() {
            message = new Message();
            message.arg1 = 1;   //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {
            soundAndreaPasLa = new Sound("Andrea pas la",soundPool.load(appContext,R.raw.andrea_pas_la,1));
            soundAttention01 = new Sound("Attention - 01",soundPool.load(appContext,R.raw.attention01,1));
            soundAttention02 = new Sound("Attention - 02",soundPool.load(appContext,R.raw.attention02,1));
            soundAttention03 = new Sound("Attention - 03",soundPool.load(appContext,R.raw.attention03,1));
            soundAttention04 = new Sound("Attention - 04",soundPool.load(appContext,R.raw.attention04,1));
            soundAttention05 = new Sound("Attention - 05",soundPool.load(appContext,R.raw.attention05,1));
            soundAttention06 = new Sound("Attention - 06",soundPool.load(appContext,R.raw.attention06,1));
            soundAucunRapport = new Sound("Aucun rapport",soundPool.load(appContext,R.raw.aucun_rapport,1));
            soundBonneIdee = new Sound("Bonne idée",soundPool.load(appContext,R.raw.bonne_idee,1));
            soundCalomnie = new Sound("Calomnie",soundPool.load(appContext,R.raw.calomnie,1));
            soundChinois01 = new Sound("Chinois - 01",soundPool.load(appContext,R.raw.chinois01,1));
            soundChinois02 = new Sound("Chinois - 02",soundPool.load(appContext,R.raw.chinois02,1));
            soundCokeVachementBath = new Sound("La Coke c'est vachement bath",soundPool.load(appContext,R.raw.coke_vachement_bath,1));
            soundComprendsPas = new Sound("Comprends pas",soundPool.load(appContext,R.raw.comprends_pas,1));
            soundCracheBeaucoup = new Sound("Crache beaucoup",soundPool.load(appContext,R.raw.crache_beaucoup,1));
            soundDebandade = new Sound("Débandade",soundPool.load(appContext,R.raw.debandade,1));
            soundDefection = new Sound("Défection",soundPool.load(appContext,R.raw.defection,1));
            soundEmbarrassant = new Sound("Embarrasant",soundPool.load(appContext,R.raw.embarrassant,1));
            soundExigeReponse = new Sound("J'exige une réponse",soundPool.load(appContext,R.raw.exige_reponse,1));
            soundFaux = new Sound("Faux !",soundPool.load(appContext,R.raw.faux,1));
            soundFoutLaRage = new Sound("Fout la rage",soundPool.load(appContext,R.raw.fout_la_rage,1));
            soundGrosGourdin = new Sound("Gros gourdin",soundPool.load(appContext,R.raw.gros_gourdin,1));
            soundGrosseBlague = new Sound("Grosse blague",soundPool.load(appContext,R.raw.grosse_blague,1));
            soundHabile = new Sound("Habile",soundPool.load(appContext,R.raw.habile,1));
            soundHallucine = new Sound("Hallucine",soundPool.load(appContext,R.raw.hallucine,1));
            soundHumour = new Sound("Humour",soundPool.load(appContext,R.raw.humour,1));
            soundIncomprehensible = new Sound("Incompréhensible",soundPool.load(appContext,R.raw.incomprehensible,1));

            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    //This classes is designed to load a set of sounds in memory in a separate thread.
    private class LoadThread2 implements Runnable {

        private Message message;

        LoadThread2() {
            message = new Message();
            message.arg1 = 2;   //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {
            soundInteressePas = new Sound("Interesse pas",soundPool.load(appContext,R.raw.interesse_pas,1));
            soundLeGitan = new Sound("Ha le Gitan",soundPool.load(appContext,R.raw.le_gitan,1));
            soundMachiavellique = new Sound("Machiavellique",soundPool.load(appContext,R.raw.machiavellique,1));
            soundMagnerLeCul = new Sound("Magner le cul",soundPool.load(appContext,R.raw.magner_le_cul,1));
            soundMaitreMichel = new Sound("Maitre Michel",soundPool.load(appContext,R.raw.maitre_michel,1));
            soundMalentendu = new Sound("C'est un malentendu",soundPool.load(appContext,R.raw.malentendu,1));
            soundMarcheBien = new Sound("Ca marche bien",soundPool.load(appContext,R.raw.marche_bien,1));
            soundMeSensSeul = new Sound("Me sens seul",soundPool.load(appContext,R.raw.me_sens_seul,1));
            soundMethTropDeLaBalle01 = new Sound("La meth c'est trop de la balle - 01",soundPool.load(appContext,R.raw.meth_trop_de_la_balle,1));
            soundMethTropDeLaBalle02 = new Sound("La meth c'est trop de la balle - 02",soundPool.load(appContext,R.raw.meth_trop_de_la_balle02,1));
            soundMistake = new Sound("Mistake",soundPool.load(appContext,R.raw.mistake,1));
            soundNemrod = new Sound("Nemrod",soundPool.load(appContext,R.raw.nemrod,1));
            soundNoFuckingBalls = new Sound("No Fucking Balls",soundPool.load(appContext,R.raw.no_fucking_balls,1));
            soundNouveaute = new Sound("Nouveaute",soundPool.load(appContext,R.raw.nouveaute,1));
            soundOhOui = new Sound("Oh oui",soundPool.load(appContext,R.raw.oh_oui,1));
            soundOnSEmmerde = new Sound("On s'emmerde",soundPool.load(appContext,R.raw.on_s_emmerde,1));
            soundOsef = new Sound("Osef",soundPool.load(appContext,R.raw.osef,1));
            soundPasCool = new Sound("Pas Cool",soundPool.load(appContext,R.raw.pas_cool,1));
            soundPasDrole = new Sound("Pas Drole",soundPool.load(appContext,R.raw.pas_drole,1));
            soundPlaisanterie01 = new Sound("Plaisanterie - 01",soundPool.load(appContext,R.raw.plaisanterie01,1));
            soundPlaisanterie02 = new Sound("Plaisanterie - 02",soundPool.load(appContext,R.raw.plaisanterie02,1));
            soundPouleMouillee = new Sound("Poule mouillée",soundPool.load(appContext,R.raw.poule_mouillee,1));
            soundPourquoi = new Sound("Pourquoi",soundPool.load(appContext,R.raw.pourquoi,1));
            soundPqEmergency = new Sound("PQ Emergency",soundPool.load(appContext,R.raw.pq_emergency,1));
            soundPqIncroyable = new Sound("PQ Incroyable",soundPool.load(appContext,R.raw.pq_incroyable,1));
            soundPqReche = new Sound("Ce PQ est un peu reche",soundPool.load(appContext,R.raw.pq_reche,1));
            soundPqTropDoux = new Sound("PQ trop doux",soundPool.load(appContext,R.raw.pq_trop_doux,1));

            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    //This classes is designed to load a set of sounds in memory in a separate thread.
    private class LoadThread3 implements Runnable {

        private Message message;

        LoadThread3() {
            message = new Message();
            message.arg1 = 3;   //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {
            soundPqTropManque = new Sound("PQ Trop manqué",soundPool.load(appContext,R.raw.pq_trop_manque,1));
            soundPrejudice = new Sound("Prejudice",soundPool.load(appContext,R.raw.prejudice,1));
            soundPrevoyant = new Sound("Prevoyant",soundPool.load(appContext,R.raw.prevoyant,1));
            soundPrisPropreJeu = new Sound("Pris propre jeu",soundPool.load(appContext,R.raw.pris_propre_jeu,1));
            soundPtitZizi = new Sound("Ptit zizi",soundPool.load(appContext,R.raw.ptit_zizi,1));
            soundPtiteBite = new Sound("Ptite bite",soundPool.load(appContext,R.raw.ptite_bite,1));
            soundPueDuCul = new Sound("Pue du cul",soundPool.load(appContext,R.raw.pue_du_cul,1));
            soundQueSePasseTIl = new Sound("Que se passe t'il ",soundPool.load(appContext,R.raw.que_ce_passe_t_il,1));
            soundQuelqueSorte = new Sound("Quelque Sorte",soundPool.load(appContext,R.raw.quelque_sorte,1));
            soundQuiEstLa = new Sound("Qui est la",soundPool.load(appContext,R.raw.qui_est_la,1));
            soundQuoi01 = new Sound("Quoi - 01",soundPool.load(appContext,R.raw.quoi,1));
            soundQuoi02 = new Sound("Quoi - 02",soundPool.load(appContext,R.raw.quoi02,1));
            soundQuoi03 = new Sound("Quoi - 03",soundPool.load(appContext,R.raw.quoi03,1));
            soundSante = new Sound("Sante",soundPool.load(appContext,R.raw.sante,1));
            soundScandaleux = new Sound("Scandaleux",soundPool.load(appContext,R.raw.scandaleux,1));
            soundSuperBaise = new Sound("Super Baise",soundPool.load(appContext,R.raw.super_baise,1));
            soundSuperSpirituel = new Sound("Super Spirituel",soundPool.load(appContext,R.raw.super_spirituel,1));
            soundTrahison = new Sound("Trahison",soundPool.load(appContext,R.raw.trahison,1));
            soundTripleEpaisseur = new Sound("Triple epaisseur",soundPool.load(appContext,R.raw.triple_epaisseur,1));
            soundTropPlaisir = new Sound("Trop plaisir",soundPool.load(appContext,R.raw.trop_plaisir,1));
            soundTrucDeMazo = new Sound("Truc de mazo",soundPool.load(appContext,R.raw.truc_de_mazo,1));
            soundTrueStory = new Sound("True Story",soundPool.load(appContext,R.raw.true_story,1));
            soundVachementBath = new Sound("Vachement bath",soundPool.load(appContext,R.raw.vachement_bath,1));
            soundViens01 = new Sound("Viens - 01",soundPool.load(appContext,R.raw.viens01,1));
            soundViens02 = new Sound("Viens - 02",soundPool.load(appContext,R.raw.viens02,1));
            soundVieuxMan = new Sound("Vieux man",soundPool.load(appContext,R.raw.vieux_man,1));
            soundVoirMaBite = new Sound("Voir ma bite",soundPool.load(appContext,R.raw.voir_ma_bite,1));
            soundVrai = new Sound("Vrai",soundPool.load(appContext,R.raw.vrai,1));

            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    //This classes is designed to setup the theme "All"
    private class SetThread1 implements Runnable {

        private Message message;

        SetThread1() {
            message = new Message();
            message.arg1 = 11;  //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {
            themeAll.addSound(soundAndreaPasLa);
            themeAll.addSound(soundAttention01);
            themeAll.addSound(soundAttention02);
            themeAll.addSound(soundAttention03);
            themeAll.addSound(soundAttention04);
            themeAll.addSound(soundAttention05);
            themeAll.addSound(soundAttention06);
            themeAll.addSound(soundAucunRapport);
            themeAll.addSound(soundBonneIdee);
            themeAll.addSound(soundCalomnie);
            themeAll.addSound(soundChinois01);
            themeAll.addSound(soundChinois02);
            themeAll.addSound(soundCokeVachementBath);
            themeAll.addSound(soundComprendsPas);
            themeAll.addSound(soundCracheBeaucoup);
            themeAll.addSound(soundDebandade);
            themeAll.addSound(soundDefection);
            themeAll.addSound(soundEmbarrassant);
            themeAll.addSound(soundExigeReponse);
            themeAll.addSound(soundFaux);
            themeAll.addSound(soundFoutLaRage);
            themeAll.addSound(soundGrosGourdin);
            themeAll.addSound(soundGrosseBlague);
            themeAll.addSound(soundHabile);
            themeAll.addSound(soundHallucine);
            themeAll.addSound(soundHumour);
            themeAll.addSound(soundIncomprehensible);
            themeAll.addSound(soundInteressePas);
            themeAll.addSound(soundLeGitan);
            themeAll.addSound(soundMachiavellique);
            themeAll.addSound(soundMagnerLeCul);
            themeAll.addSound(soundMaitreMichel);
            themeAll.addSound(soundMalentendu);
            themeAll.addSound(soundMarcheBien);
            themeAll.addSound(soundMeSensSeul);
            themeAll.addSound(soundMethTropDeLaBalle01);
            themeAll.addSound(soundMethTropDeLaBalle02);
            themeAll.addSound(soundMistake);
            themeAll.addSound(soundNemrod);
            themeAll.addSound(soundNoFuckingBalls);
            themeAll.addSound(soundNouveaute);
            themeAll.addSound(soundOhOui);
            themeAll.addSound(soundOnSEmmerde);
            themeAll.addSound(soundOsef);
            themeAll.addSound(soundPasCool);
            themeAll.addSound(soundPasDrole);
            themeAll.addSound(soundPlaisanterie01);
            themeAll.addSound(soundPlaisanterie02);
            themeAll.addSound(soundPouleMouillee);
            themeAll.addSound(soundPourquoi);
            themeAll.addSound(soundPqEmergency);
            themeAll.addSound(soundPqIncroyable);
            themeAll.addSound(soundPqReche);
            themeAll.addSound(soundPqTropDoux);
            themeAll.addSound(soundPqTropManque);
            themeAll.addSound(soundPrejudice);
            themeAll.addSound(soundPrevoyant);
            themeAll.addSound(soundPrisPropreJeu);
            themeAll.addSound(soundPtiteBite);
            themeAll.addSound(soundPtitZizi);
            themeAll.addSound(soundPueDuCul);
            themeAll.addSound(soundQueSePasseTIl);
            themeAll.addSound(soundQuelqueSorte);
            themeAll.addSound(soundQuiEstLa);
            themeAll.addSound(soundQuoi01);
            themeAll.addSound(soundQuoi02);
            themeAll.addSound(soundQuoi03);
            themeAll.addSound(soundSante);
            themeAll.addSound(soundScandaleux);
            themeAll.addSound(soundSuperBaise);
            themeAll.addSound(soundSuperSpirituel);
            themeAll.addSound(soundTrahison);
            themeAll.addSound(soundTripleEpaisseur);
            themeAll.addSound(soundTropPlaisir);
            themeAll.addSound(soundTrucDeMazo);
            themeAll.addSound(soundTrueStory);
            themeAll.addSound(soundVachementBath);
            themeAll.addSound(soundViens01);
            themeAll.addSound(soundViens02);
            themeAll.addSound(soundVieuxMan);
            themeAll.addSound(soundVoirMaBite);
            themeAll.addSound(soundVrai);

            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    //This classes is designed to setup all themes but "All" and "Favorites"
    private class SetThread2 implements Runnable {

        private Message message;

        SetThread2() {
            message = new Message();
            message.arg1 = 12;  //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {
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

            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    //This classes is designed to setup the theme "Favorites"
    private class SetThread3 implements Runnable {

        private Message message;

        SetThread3() {
            message = new Message();
            message.arg1 = 13;  //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {

            //TODO: Read saved favorites sound list from file and setup themeFav from it.

            //Send a message to handler with the finished flag set
            message.arg2 = 1;
            loadingHandler.sendMessage(message);
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
        Button searchButton = (Button) findViewById(R.id.search_button);
        Button themeButton = (Button) findViewById(R.id.theme_button);
        Button randomButton = (Button) findViewById(R.id.random_button);
        Button settingsButton = (Button) findViewById(R.id.settings_button);
        soundListDisplay = (ListView) findViewById(R.id.sound_List_Display);
        currentThemeTextView = (TextView) findViewById(R.id.current_theme_display);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //For each object we created above, designate event listeners.
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
                            case (1):
                                thread1JobDone = true;
                                break; //thread #1 has finished its job.
                            case (2):
                                thread2JobDone = true;
                                break; //thread #2 has finished its job.
                            case (3):
                                thread3JobDone = true;
                                break; //thread #3 has finished its job.
                            case (11):
                                thread11JobDone = true;
                                break;//thread #11 has finished its job.
                            case (12):
                                thread12JobDone = true;
                                break;//thread #12 has finished its job.
                            case (13):
                                thread13JobDone = true;
                                break;//thread #13 has finished its job.
                        }

                        //If all Loading threads are finished, then start the set Thread.
                        if(thread1JobDone && thread2JobDone && thread3JobDone && !loadingDone) {

                            //Instantiate all settings threads.
                            SetThread1 setThread1 = new SetThread1();
                            SetThread2 setThread2 = new SetThread2();
                            SetThread3 setThread3 = new SetThread3();

                            //Submit the 3 setting threads to the thread pool.
                            threadPoolExec.submit(setThread1);
                            threadPoolExec.submit(setThread2);
                            threadPoolExec.submit(setThread3);

                            //Set loadingDone to true to prevent restarting setting threads later on.
                            loadingDone = true;
                        }

                        //Once Themes are fully set update themeEngine, UI and then fragments.
                        if(thread11JobDone && thread12JobDone && thread13JobDone) {

                            //Add themes to the theme engine. WARNING: themeAll MUST be 1st, themeFav MUST be 2nd.
                            themeEngine.addTheme(themeAll);
                            themeEngine.addTheme(themeFav);
                            themeEngine.addTheme(themeTaunt);
                            themeEngine.addTheme(themePq);

                            //Update UI
                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.sound_list, themeEngine.getCurrentTheme().getSoundNameList());
                            soundListDisplay.setAdapter(adapter);
                            currentThemeTextView.setText(themeEngine.getCurrentThemeString(appContext));

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
            thread1JobDone = false;
            thread2JobDone = false;
            thread3JobDone = false;
            thread11JobDone = false;
            thread12JobDone = false;
            loadingDone = false;

            //Instantiate the sound engine.
            soundEngine = new SoundEngine((AudioManager) this.getSystemService(Context.AUDIO_SERVICE));
            themeEngine = new ThemeEngine();

            //Instantiate Themes.
            themeAll = new Theme("All");
            themeFav = new Theme("Favorites");
            themePq = new Theme("PQ");
            themeTaunt = new Theme("Taunt");

            //Save the soundEngine sound pool now to avoid multiple soundEngine calls in threads.
            soundPool = soundEngine.getSoundPool();

            //Instantiate all loading threads.
            LoadThread1 loadingThread1 = new LoadThread1();
            LoadThread2 loadingThread2 = new LoadThread2();
            LoadThread3 loadingThread3 = new LoadThread3();

            //Submit the 3 loading threads to the thread pool.
            threadPoolExec.submit(loadingThread1);
            threadPoolExec.submit(loadingThread2);
            threadPoolExec.submit(loadingThread3);
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
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // store the data in the fragment
        soundEngineFragment.setData(soundEngine);
        themeEngineFragment.setData(themeEngine);
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

            /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            @ I/O TESTING CODE | I/O TESTING CODE | I/O TESTING CODE | I/O TESTING CODE @
            @ I/O TESTING CODE | I/O TESTING CODE | I/O TESTING CODE | I/O TESTING CODE @
            @ I/O TESTING CODE | I/O TESTING CODE | I/O TESTING CODE | I/O TESTING CODE @
            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

            String filename = "la_rage_file";   //testing file name to be written/read
            String string = "LA MAXI #";        //testing data to be written/read
            byte[] inputBuffer = new byte[50];  //testing input buffer for READ operations

            /******************
             * WRITE I/O TEST *
             ******************/
            try {
                FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(string.getBytes());
                outputStream.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            /*****************
             * READ I/O TEST *
             *****************/
            try {

                FileInputStream inputStream = openFileInput(filename);
                inputStream.read(inputBuffer);
                inputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            /*********************
             * DISPLAY READ DATA *
             *********************/

            //Convert input buffer (array of bytes) to a string
            String byteArrayToString = new String(inputBuffer);

            //Create a minimalist dialog with DEBUG title and read data as body message.
            AlertDialog.Builder debug_dialog = new AlertDialog.Builder(MainActivity.this);
            debug_dialog.setTitle("DEBUG");
            debug_dialog.setMessage(byteArrayToString);
            debug_dialog.create();
            debug_dialog.show();

            /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            @ END OF I/O TESTING CODE | END OF I/O TESTING CODE | END OF I/O TESTING CODE @
            @ END OF I/O TESTING CODE | END OF I/O TESTING CODE | END OF I/O TESTING CODE @
            @ END OF I/O TESTING CODE | END OF I/O TESTING CODE | END OF I/O TESTING CODE @
            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        }

        //Theme button was clicked
        if(findViewById(R.id.theme_button) == v) {
            AlertDialog.Builder choice_dialog = new AlertDialog.Builder(MainActivity.this);
            choice_dialog.setTitle(getString(R.string.choose_theme));
            choice_dialog.setItems(themeEngine.getThemeNameList(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Set current active theme to the chosen theme.
                    themeEngine.setCurrentTheme(themeEngine.getThemeList()[which]);

                    //Update UI accordingly to the new current active theme.
                    soundListDisplay.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.sound_list,themeEngine.getCurrentTheme().getSoundNameList()));
                    currentThemeTextView.setText(themeEngine.getCurrentThemeString(appContext));

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

        //Setting button was clicked
        if(findViewById(R.id.settings_button) == v) {

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
}