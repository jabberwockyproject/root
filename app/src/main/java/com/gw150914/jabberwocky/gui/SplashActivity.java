package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.gw150914.jabberwocky.R;
import com.gw150914.jabberwocky.core.Sound;
import com.gw150914.jabberwocky.core.SoundEngine;
import com.gw150914.jabberwocky.core.Theme;
import com.gw150914.jabberwocky.core.ThemeEngine;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SplashActivity extends Activity {

    private Theme themeAll, themeFav, themePq, themeTaunt;
    private Sound soundAndreaPasLa, soundAttention01, soundAttention02, soundAttention03, soundAttention04, soundAttention05, soundAttention06, soundAucunRapport, soundBonneIdee, soundCalomnie, soundChinois01, soundChinois02, soundCokeVachementBath, soundComprendsPas, soundCracheBeaucoup, soundDebandade, soundDefection, soundEmbarrassant, soundExigeReponse, soundFaux, soundFoutLaRage, soundGrosGourdin, soundGrosseBlague, soundHabile, soundHallucine, soundHumour, soundIncomprehensible, soundInteressePas, soundLeGitan, soundMachiavellique, soundMagnerLeCul, soundMaitreMichel, soundMalentendu, soundMarcheBien, soundMeSensSeul, soundMethTropDeLaBalle01, soundMethTropDeLaBalle02, soundMistake, soundNemrod, soundNoFuckingBalls, soundNouveaute, soundOhOui, soundOnSEmmerde, soundOsef, soundPasCool, soundPasDrole, soundPlaisanterie01, soundPlaisanterie02, soundPouleMouillee, soundPourquoi, soundPqEmergency, soundPqIncroyable, soundPqReche, soundPqTropDoux, soundPqTropManque, soundPrejudice, soundPrevoyant, soundPrisPropreJeu, soundPtitZizi, soundPtiteBite, soundPueDuCul, soundQueSePasseTIl, soundQuelqueSorte, soundQuiEstLa, soundQuoi01, soundQuoi02, soundQuoi03, soundSante, soundScandaleux, soundSuperBaise, soundSuperSpirituel, soundTrahison, soundTripleEpaisseur, soundTropPlaisir, soundTrucDeMazo, soundTrueStory, soundVachementBath, soundViens01, soundViens02, soundVieuxMan, soundVoirMaBite, soundVrai;
    private SoundPool soundPool;
    private Context appContext;
    private ThreadPoolExecutor threadPoolExec;
    private Handler loadingHandler;
    private int thread1LoadedSounds; //unused ATM
    private int thread2LoadedSounds; //unused ATM
    private int thread3LoadedSounds; //unused ATM
    private final int thread1TotalSounds = 27; //unused ATM
    private final int thread2TotalSounds = 27; //unused ATM
    private final int thread3TotalSounds = 28; //unused ATM
    private boolean thread1JobDone, thread2JobDone, thread3JobDone, thread11JobDone, thread12JobDone, loadingDone;
    private SetThread1 setThread1;
    private SetThread2 setThread2;
    private boolean massSuicide;
    private TextView loadingDetails;
    ThemeEngine themeEngine;

    private class LoadThread1 implements Runnable {
        private Message message;
        LoadThread1 (){
            message = new Message();
            message.arg1 = 1;   //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }
        public void run() {
            soundAndreaPasLa = new Sound("Andrea pas la",soundPool.load(appContext,R.raw.andrea_pas_la,1)); ++thread1LoadedSounds;
            soundAttention01 = new Sound("Attention - 01",soundPool.load(appContext,R.raw.attention01,1)); ++thread1LoadedSounds;
            soundAttention02 = new Sound("Attention - 02",soundPool.load(appContext,R.raw.attention02,1)); ++thread1LoadedSounds;
            soundAttention03 = new Sound("Attention - 03",soundPool.load(appContext,R.raw.attention03,1)); ++thread1LoadedSounds;
            soundAttention04 = new Sound("Attention - 04",soundPool.load(appContext,R.raw.attention04,1)); ++thread1LoadedSounds;
            soundAttention05 = new Sound("Attention - 05",soundPool.load(appContext,R.raw.attention05,1)); ++thread1LoadedSounds;
            soundAttention06 = new Sound("Attention - 06",soundPool.load(appContext,R.raw.attention06,1)); ++thread1LoadedSounds;
            soundAucunRapport = new Sound("Aucun rapport",soundPool.load(appContext,R.raw.aucun_rapport,1)); ++thread1LoadedSounds;
            soundBonneIdee = new Sound("Bonne idée",soundPool.load(appContext,R.raw.bonne_idee,1)); ++thread1LoadedSounds;
            soundCalomnie = new Sound("Calomnie",soundPool.load(appContext,R.raw.calomnie,1)); ++thread1LoadedSounds;
            soundChinois01 = new Sound("Chinois - 01",soundPool.load(appContext,R.raw.chinois01,1)); ++thread1LoadedSounds;
            soundChinois02 = new Sound("Chinois - 02",soundPool.load(appContext,R.raw.chinois02,1)); ++thread1LoadedSounds;
            soundCokeVachementBath = new Sound("La Coke c'est vachement bath",soundPool.load(appContext,R.raw.coke_vachement_bath,1)); ++thread1LoadedSounds;
            soundComprendsPas = new Sound("Comprends pas",soundPool.load(appContext,R.raw.comprends_pas,1)); ++thread1LoadedSounds;
            soundCracheBeaucoup = new Sound("Crache beaucoup",soundPool.load(appContext,R.raw.crache_beaucoup,1)); ++thread1LoadedSounds;
            soundDebandade = new Sound("Débandade",soundPool.load(appContext,R.raw.debandade,1)); ++thread1LoadedSounds;
            soundDefection = new Sound("Défection",soundPool.load(appContext,R.raw.defection,1)); ++thread1LoadedSounds;
            soundEmbarrassant = new Sound("Embarrasant",soundPool.load(appContext,R.raw.embarrassant,1)); ++thread1LoadedSounds;
            soundExigeReponse = new Sound("J'exige une réponse",soundPool.load(appContext,R.raw.exige_reponse,1)); ++thread1LoadedSounds;
            soundFaux = new Sound("Faux !",soundPool.load(appContext,R.raw.faux,1)); ++thread1LoadedSounds;
            soundFoutLaRage = new Sound("Fout la rage",soundPool.load(appContext,R.raw.fout_la_rage,1)); ++thread1LoadedSounds;
            soundGrosGourdin = new Sound("Gros gourdin",soundPool.load(appContext,R.raw.gros_gourdin,1)); ++thread1LoadedSounds;
            soundGrosseBlague = new Sound("Grosse blague",soundPool.load(appContext,R.raw.grosse_blague,1)); ++thread1LoadedSounds;
            soundHabile = new Sound("Habile",soundPool.load(appContext,R.raw.habile,1)); ++thread1LoadedSounds;
            soundHallucine = new Sound("Hallucine",soundPool.load(appContext,R.raw.hallucine,1)); ++thread1LoadedSounds;
            soundHumour = new Sound("Humour",soundPool.load(appContext,R.raw.humour,1)); ++thread1LoadedSounds;
            soundIncomprehensible = new Sound("Incompréhensible",soundPool.load(appContext,R.raw.incomprehensible,1)); ++thread1LoadedSounds;

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    private class LoadThread2 implements Runnable {
        private Message message;
        LoadThread2 (){
            message = new Message();
            message.arg1 = 2;   //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }
        public void run() {
            soundInteressePas = new Sound("Interesse pas",soundPool.load(appContext,R.raw.interesse_pas,1)); ++thread2LoadedSounds;
            soundLeGitan = new Sound("Ha le Gitan",soundPool.load(appContext,R.raw.le_gitan,1)); ++thread2LoadedSounds;
            soundMachiavellique = new Sound("Machiavellique",soundPool.load(appContext,R.raw.machiavellique,1)); ++thread2LoadedSounds;
            soundMagnerLeCul = new Sound("Magner le cul",soundPool.load(appContext,R.raw.magner_le_cul,1)); ++thread2LoadedSounds;
            soundMaitreMichel = new Sound("Maitre Michel",soundPool.load(appContext,R.raw.maitre_michel,1)); ++thread2LoadedSounds;
            soundMalentendu = new Sound("C'est un malentendu",soundPool.load(appContext,R.raw.malentendu,1)); ++thread2LoadedSounds;
            soundMarcheBien = new Sound("Ca marche bien",soundPool.load(appContext,R.raw.marche_bien,1)); ++thread2LoadedSounds;
            soundMeSensSeul = new Sound("Me sens seul",soundPool.load(appContext,R.raw.me_sens_seul,1)); ++thread2LoadedSounds;
            soundMethTropDeLaBalle01 = new Sound("La meth c'est trop de la balle - 01",soundPool.load(appContext,R.raw.meth_trop_de_la_balle,1)); ++thread2LoadedSounds;
            soundMethTropDeLaBalle02 = new Sound("La meth c'est trop de la balle - 02",soundPool.load(appContext,R.raw.meth_trop_de_la_balle02,1)); ++thread2LoadedSounds;
            soundMistake = new Sound("Mistake",soundPool.load(appContext,R.raw.mistake,1)); ++thread2LoadedSounds;
            soundNemrod = new Sound("Nemrod",soundPool.load(appContext,R.raw.nemrod,1)); ++thread2LoadedSounds;
            soundNoFuckingBalls = new Sound("No Fucking Balls",soundPool.load(appContext,R.raw.no_fucking_balls,1)); ++thread2LoadedSounds;
            soundNouveaute = new Sound("Nouveaute",soundPool.load(appContext,R.raw.nouveaute,1)); ++thread2LoadedSounds;
            soundOhOui = new Sound("Oh oui",soundPool.load(appContext,R.raw.oh_oui,1)); ++thread2LoadedSounds;
            soundOnSEmmerde = new Sound("On s'emmerde",soundPool.load(appContext,R.raw.on_s_emmerde,1)); ++thread2LoadedSounds;
            soundOsef = new Sound("Osef",soundPool.load(appContext,R.raw.osef,1)); ++thread2LoadedSounds;
            soundPasCool = new Sound("Pas Cool",soundPool.load(appContext,R.raw.pas_cool,1)); ++thread2LoadedSounds;
            soundPasDrole = new Sound("Pas Drole",soundPool.load(appContext,R.raw.pas_drole,1)); ++thread2LoadedSounds;
            soundPlaisanterie01 = new Sound("Plaisanterie - 01",soundPool.load(appContext,R.raw.plaisanterie01,1)); ++thread2LoadedSounds;
            soundPlaisanterie02 = new Sound("Plaisanterie - 02",soundPool.load(appContext,R.raw.plaisanterie02,1)); ++thread2LoadedSounds;
            soundPouleMouillee = new Sound("Poule mouillée",soundPool.load(appContext,R.raw.poule_mouillee,1)); ++thread2LoadedSounds;
            soundPourquoi = new Sound("Pourquoi",soundPool.load(appContext,R.raw.pourquoi,1)); ++thread2LoadedSounds;
            soundPqEmergency = new Sound("PQ Emergency",soundPool.load(appContext,R.raw.pq_emergency,1)); ++thread2LoadedSounds;
            soundPqIncroyable = new Sound("PQ Incroyable",soundPool.load(appContext,R.raw.pq_incroyable,1)); ++thread2LoadedSounds;
            soundPqReche = new Sound("Ce PQ est un peu reche",soundPool.load(appContext,R.raw.pq_reche,1)); ++thread2LoadedSounds;
            soundPqTropDoux = new Sound("PQ trop doux",soundPool.load(appContext,R.raw.pq_trop_doux,1)); ++thread2LoadedSounds;

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    private class LoadThread3 implements Runnable {
        private Message message;
        LoadThread3 (){
            message = new Message();
            message.arg1 = 3;   //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {
            soundPqTropManque = new Sound("PQ Trop manqué",soundPool.load(appContext,R.raw.pq_trop_manque,1)); ++thread3LoadedSounds;
            soundPrejudice = new Sound("Prejudice",soundPool.load(appContext,R.raw.prejudice,1)); ++thread3LoadedSounds;
            soundPrevoyant = new Sound("Prevoyant",soundPool.load(appContext,R.raw.prevoyant,1)); ++thread3LoadedSounds;
            soundPrisPropreJeu = new Sound("Pris propre jeu",soundPool.load(appContext,R.raw.pris_propre_jeu,1)); ++thread3LoadedSounds;
            soundPtitZizi = new Sound("Ptit zizi",soundPool.load(appContext,R.raw.ptit_zizi,1)); ++thread3LoadedSounds;
            soundPtiteBite = new Sound("Ptite bite",soundPool.load(appContext,R.raw.ptite_bite,1)); ++thread3LoadedSounds;
            soundPueDuCul = new Sound("Pue du cul",soundPool.load(appContext,R.raw.pue_du_cul,1)); ++thread3LoadedSounds;
            soundQueSePasseTIl = new Sound("Que se passe t'il ",soundPool.load(appContext,R.raw.que_ce_passe_t_il,1)); ++thread3LoadedSounds;
            soundQuelqueSorte = new Sound("Quelque Sorte",soundPool.load(appContext,R.raw.quelque_sorte,1)); ++thread3LoadedSounds;
            soundQuiEstLa = new Sound("Qui est la",soundPool.load(appContext,R.raw.qui_est_la,1)); ++thread3LoadedSounds;
            soundQuoi01 = new Sound("Quoi - 01",soundPool.load(appContext,R.raw.quoi,1)); ++thread3LoadedSounds;
            soundQuoi02 = new Sound("Quoi - 02",soundPool.load(appContext,R.raw.quoi02,1)); ++thread3LoadedSounds;
            soundQuoi03 = new Sound("Quoi - 03",soundPool.load(appContext,R.raw.quoi03,1)); ++thread3LoadedSounds;
            soundSante = new Sound("Sante",soundPool.load(appContext,R.raw.sante,1)); ++thread3LoadedSounds;
            soundScandaleux = new Sound("Scandaleux",soundPool.load(appContext,R.raw.scandaleux,1)); ++thread3LoadedSounds;
            soundSuperBaise = new Sound("Super Baise",soundPool.load(appContext,R.raw.super_baise,1)); ++thread3LoadedSounds;
            soundSuperSpirituel = new Sound("Super Spirituel",soundPool.load(appContext,R.raw.super_spirituel,1)); ++thread3LoadedSounds;
            soundTrahison = new Sound("Trahison",soundPool.load(appContext,R.raw.trahison,1)); ++thread3LoadedSounds;
            soundTripleEpaisseur = new Sound("Triple epaisseur",soundPool.load(appContext,R.raw.triple_epaisseur,1)); ++thread3LoadedSounds;
            soundTropPlaisir = new Sound("Trop plaisir",soundPool.load(appContext,R.raw.trop_plaisir,1)); ++thread3LoadedSounds;
            soundTrucDeMazo = new Sound("Truc de mazo",soundPool.load(appContext,R.raw.truc_de_mazo,1)); ++thread3LoadedSounds;
            soundTrueStory = new Sound("True Story",soundPool.load(appContext,R.raw.true_story,1)); ++thread3LoadedSounds;
            soundVachementBath = new Sound("Vachement bath",soundPool.load(appContext,R.raw.vachement_bath,1)); ++thread3LoadedSounds;
            soundViens01 = new Sound("Viens - 01",soundPool.load(appContext,R.raw.viens01,1)); ++thread3LoadedSounds;
            soundViens02 = new Sound("Viens - 02",soundPool.load(appContext,R.raw.viens02,1)); ++thread3LoadedSounds;
            soundVieuxMan = new Sound("Vieux man",soundPool.load(appContext,R.raw.vieux_man,1)); ++thread3LoadedSounds;
            soundVoirMaBite = new Sound("Voir ma bite",soundPool.load(appContext,R.raw.voir_ma_bite,1)); ++thread3LoadedSounds;
            soundVrai = new Sound("Vrai",soundPool.load(appContext,R.raw.vrai,1)); ++thread3LoadedSounds;

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    private class SetThread1 implements Runnable {
        private Message message;
        SetThread1 (){
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
        SetThread2 (){
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

    private class ProgressThread1 implements Runnable {
        private Message message;
        ProgressThread1 (){
            message = new Message();
            message.arg1 = 21;  //thread id
            message.arg2 = 0;   //thread status (0=ongoing, 1=done)
        }

        public void run() {
            //TODO: Update message below loading bar. Show loaded sounds.
            /*
            while(!massSuicide){

            }
            */

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        threadPoolExec = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        threadPoolExec.prestartAllCoreThreads();

        SoundEngine soundEngine;

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
                    switch(message.arg1){
                        case(1): thread1JobDone = true; break; //thread #1 has finished its job. (loading)
                        case(2): thread2JobDone = true; break; //thread #2 has finished its job. (loading)
                        case(3): thread3JobDone = true; break; //thread #3 has finished its job. (loading)
                        case(11): thread11JobDone = true; break;//thread #11 has finished its job. (setting)
                        case(12): thread12JobDone = true; break;//thread #12 has finished its job. (setting)
                        case(21): thread12JobDone = true; break;//thread #21 has finished its job. (monitoring)
                    }

                    //If all Loading threads are finished, then start the set Thread.
                    //Finally, set loadingDone at true to avoid starting setThreads again later on.
                    if(thread1JobDone && thread2JobDone && thread3JobDone && !loadingDone){
                        threadPoolExec.submit(setThread1);
                        threadPoolExec.submit(setThread2);
                        loadingDone = true;
                    }

                    //Once setting threads are finished, setup themeEngine
                    if(thread11JobDone && thread12JobDone){
                        themeEngine.addTheme(themeAll);
                        themeEngine.addTheme(themeFav);
                        themeEngine.addTheme(themeTaunt);
                        themeEngine.addTheme(themePq);
                        massSuicide = true; //remaining threads should kill themselves starting from now

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
        massSuicide = false;

        loadingDetails = (TextView) findViewById(R.id.loadingDetailsTextView);

        //Instantiate engines.
        soundEngine = new SoundEngine((AudioManager)this.getSystemService(Context.AUDIO_SERVICE));
        themeEngine = new ThemeEngine();

        //Instantiate themes
        themeAll = new Theme("All");
        themeFav = new Theme("Favorites");
        themePq = new Theme("PQ");
        themeTaunt = new Theme("Taunt");

        soundPool = soundEngine.getSoundPool();
        appContext = this.getApplicationContext();

        //Instantiate loading threads
        LoadThread1 loadingThread1 = new LoadThread1();
        LoadThread2 loadingThread2 = new LoadThread2();
        LoadThread3 loadingThread3 = new LoadThread3();

        //Instantiate setting threads
        setThread1 = new SetThread1();
        setThread2 = new SetThread2();

        //Instantiate monitoring threads
        ProgressThread1 progressThread1 = new ProgressThread1();

        //submit loading threads to the thread pool
        threadPoolExec.submit(loadingThread1);
        threadPoolExec.submit(loadingThread2);
        threadPoolExec.submit(loadingThread3);

        //submit monitoring threads to the thread pool
        threadPoolExec.submit(progressThread1);
    }
}