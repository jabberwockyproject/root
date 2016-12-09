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

    private final int thread1TotalSounds = 27; //unused ATM
    private final int thread2TotalSounds = 27; //unused ATM
    private final int thread3TotalSounds = 28; //unused ATM
    private int thread1LoadedSounds; //unused ATM
    private int thread2LoadedSounds; //unused ATM
    private int thread3LoadedSounds; //unused ATM
    private boolean thread1JobDone, thread2JobDone, thread3JobDone, thread11JobDone, thread12JobDone, loadingDone;
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
    private SoundPool soundPool;
    private Context appContext;
    private Handler loadingHandler;
    private ThreadPoolExecutor threadPoolExec;
    private SetThread1 setThread1;
    private SetThread2 setThread2;


    /*****************************************************************************************
     * ===================================[ NESTED CLASSES ]================================ *
     *****************************************************************************************/

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

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

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

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

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

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

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

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

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

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }


    /****************************************************************************************
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~[ ACTIVITY STATE METHODS ] ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
     ****************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        soundEngineFragment = (SoundEngineFragment) fragmentManager.findFragmentByTag("soundEngine");
        themeEngineFragment = (ThemeEngineFragment) fragmentManager.findFragmentByTag("themeEngine");

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
        currentThemeTextView = (TextView) findViewById(R.id.current_theme_display);

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

        if(soundEngineFragment == null || themeEngineFragment == null) {
            threadPoolExec = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
            threadPoolExec.prestartAllCoreThreads();

            loadingHandler = new Handler(Looper.getMainLooper()) {

                /*
                 * handleMessage() defines the operations to perform when
                 * the Handler receives a new Message to process.
                 */
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
                        }

                        //If all Loading threads are finished, then start the set Thread.
                        //Finally, set loadingDone at true to avoid starting setThreads again later on.
                        if(thread1JobDone && thread2JobDone && thread3JobDone && !loadingDone) {
                            threadPoolExec.submit(setThread1);
                            threadPoolExec.submit(setThread2);
                            loadingDone = true;
                        }

                        //Once ThemeAll is fully set, update UI
                        if(thread11JobDone && thread12JobDone) {

                            themeEngine.addTheme(themeAll);
                            themeEngine.addTheme(themeFav);
                            themeEngine.addTheme(themeTaunt);
                            themeEngine.addTheme(themePq);

                            adapter.notifyDataSetChanged();

                            soundEngineFragment = new SoundEngineFragment();
                            themeEngineFragment = new ThemeEngineFragment();

                            fragmentManager.beginTransaction().add(soundEngineFragment, "soundEngine").commit();
                            fragmentManager.beginTransaction().add(themeEngineFragment, "themeEngine").commit();

                            soundEngineFragment.setData(soundEngine);
                            themeEngineFragment.setData(themeEngine);
                        }
                    }
                }
            };

            thread1LoadedSounds = 0; //unused ATM
            thread2LoadedSounds = 0; //unused ATM
            thread3LoadedSounds = 0; //unused ATM

            thread1JobDone = false;
            thread2JobDone = false;
            thread3JobDone = false;
            thread11JobDone = false;
            thread12JobDone = false;
            loadingDone = false;

            //Instantiate the sound engine.
            soundEngine = new SoundEngine((AudioManager) this.getSystemService(Context.AUDIO_SERVICE));
            themeEngine = new ThemeEngine();

            //Instantiate themes named themeAll and themeFav
            themeAll = new Theme("All");
            themeFav = new Theme("Favorites");
            themePq = new Theme("PQ");
            themeTaunt = new Theme("Taunt");

            /*
             * Create adapters for the ListView
             * ListView will be fed from the soundNameList field of corresponding theme.
             *
             * https://developer.android.com/reference/android/widget/ListView.html#setAdapter%28android.widget.ListAdapter%29
             */
            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, themeAll.getSoundNameList());
            soundListDisplay.setAdapter(adapter);

            //Load all embedded sounds in memory and create Sound objects
            soundPool = soundEngine.getSoundPool();
            appContext = this.getApplicationContext();

            LoadThread1 loadingThread1 = new LoadThread1();
            LoadThread2 loadingThread2 = new LoadThread2();
            LoadThread3 loadingThread3 = new LoadThread3();

            setThread1 = new SetThread1();
            setThread2 = new SetThread2();

            threadPoolExec.submit(loadingThread1);
            threadPoolExec.submit(loadingThread2);
            threadPoolExec.submit(loadingThread3);
        }

        else {
            soundEngine = soundEngineFragment.getData();
            themeEngine = themeEngineFragment.getData();

            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, themeEngine.getCurrentTheme().getSoundNameList());
            soundListDisplay.setAdapter(adapter);
        }
        currentThemeTextView.setText("Current theme: All");
    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        // store the data in the fragment
        soundEngineFragment.setData(soundEngine);
        themeEngineFragment.setData(themeEngine);
    }


    /*****************************************************************************************
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~[ UI LISTENERS ] ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
     *****************************************************************************************/

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

        // I/O TESTING CODE
        if(findViewById(R.id.search_button) == v) {

            String FILENAME = "la_rage_file";
            String string = "LA MAXI #";

            try {
                FileOutputStream outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                outputStream.write(string.getBytes());
                outputStream.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            byte[] inputBuffer = new byte[50];

            try {

                FileInputStream inputStream = openFileInput(FILENAME);
                inputStream.read(inputBuffer);
                inputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            String byteArrayToString = new String(inputBuffer);

            AlertDialog.Builder debug_dialog = new AlertDialog.Builder(MainActivity.this);
            debug_dialog.setTitle("DEBUG");
            debug_dialog.setMessage(byteArrayToString);
            debug_dialog.create();
            debug_dialog.show();
        }

        if(findViewById(R.id.theme_button) == v) {
            AlertDialog.Builder choice_dialog = new AlertDialog.Builder(MainActivity.this);
            choice_dialog.setTitle("Choose your theme, Bro !");
            choice_dialog.setItems(themeEngine.getThemeNameList(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Theme chosenTheme = themeEngine.getThemeList()[which];
                    soundListDisplay.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,chosenTheme.getSoundNameList()));
                    currentThemeTextView.setText("Current Theme: "+chosenTheme.getName());
                    themeEngine.setCurrentTheme(chosenTheme);
                    dialog.dismiss();
                }
            });
            choice_dialog.create();
            choice_dialog.show();
        }

        if(findViewById(R.id.random_button) == v) {
            if (themeEngine.getCurrentTheme().getSoundsCount() != 0) {
                soundEngine.playSound(themeEngine.getCurrentTheme().getRandomSound().getSoundId());
            }
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
     *
     * https://developer.android.com/reference/android/widget/AdapterView.OnItemClickListener.html
     */
    public void onItemClick(AdapterView parent, View v, int pos, long id) {
        if(findViewById(R.id.sound_List_Display) == parent) {
            soundEngine.playSound(themeEngine.getCurrentTheme().getSound(pos).getSoundId());
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
            if(themeEngine.getCurrentTheme() != themeEngine.getTheme(1)) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(getString(R.string.dialog_title));
                alertDialog.setMessage(themeEngine.getCurrentTheme().getSoundNameList().get(pos));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                themeEngine.getTheme(1).addSound(themeEngine.getCurrentTheme().getSound(pos));
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            }
            else {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(getString(R.string.dialog_remove_from_fav));
                alertDialog.setMessage(themeEngine.getCurrentTheme().getSoundNameList().get(pos));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
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