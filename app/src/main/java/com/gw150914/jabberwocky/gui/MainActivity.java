package com.gw150914.jabberwocky.gui;

import android.app.Activity;
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
import com.gw150914.jabberwocky.core.Theme;
import com.gw150914.jabberwocky.core.Sound;
import com.gw150914.jabberwocky.core.SoundEngine;

import java.util.Random;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends Activity implements View.OnClickListener,View.OnLongClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    private ArrayAdapter<String> adapterAll, adapterFav;
    private Theme currentTheme, themeAll, themeFav;
    //private SaveFavoritesList saveFavoritesList;
    private ListView soundListDisplay;
    private TextView currentThemeTextView;
    private Sound soundAndreaPasLa, soundAttention01, soundAttention02, soundAttention03, soundAttention04, soundAttention05, soundAttention06, soundAucunRapport, soundBonneIdee, soundCalomnie, soundChinois01, soundChinois02, soundCokeVachementBath, soundComprendsPas, soundCracheBeaucoup, soundDebandade, soundDefection, soundEmbarrassant, soundExigeReponse, soundFaux, soundFoutLaRage, soundGrosGourdin, soundGrosseBlague, soundHabile, soundHallucine, soundHumour, soundIncomprehensible, soundInteressePas, soundLeGitan, soundMachiavellique, soundMagnerLeCul, soundMaitreMichel, soundMalentendu, soundMarcheBien, soundMeSensSeul, soundMethTropDeLaBalle01, soundMethTropDeLaBalle02, soundMistake, soundNemrod, soundNoFuckingBalls, soundNouveaute, soundOhOui, soundOnSEmmerde, soundOsef, soundPasCool, soundPasDrole, soundPlaisanterie01, soundPlaisanterie02, soundPouleMouillee, soundPourquoi, soundPqEmergency, soundPqIncroyable, soundPqReche, soundPqTropDoux, soundPqTropManque, soundPrejudice, soundPrevoyant, soundPrisPropreJeu, soundPtitZizi, soundPtiteBite, soundPueDuCul, soundQueSePasseTIl, soundQuelqueSorte, soundQuiEstLa, soundQuoi01, soundQuoi02, soundQuoi03, soundSante, soundScandaleux, soundSuperBaise, soundSuperSpirituel, soundTrahison, soundTripleEpaisseur, soundTropPlaisir, soundTrucDeMazo, soundTrueStory, soundVachementBath, soundViens01, soundViens02, soundVieuxMan, soundVoirMaBite, soundVrai;
    private SoundEngine soundEngine;
    private SoundPool soundPool;
    Random random = new Random();
    int rand = 0;
    private Context appContext;
    private ThreadPoolExecutor threadPoolExec;
    private Handler loadingHandler;
    private int thread1LoadedSounds;
    private int thread2LoadedSounds;
    private int thread3LoadedSounds;
    private final int thread1TotalSounds = 27;
    private final int thread2TotalSounds = 27;
    private final int thread3TotalSounds = 28;

    private class LoadThread1 implements Runnable {
        private Theme theme;
        private SoundPool pool;
        private Context context;
        private Message message;
        LoadThread1 (Theme theme, SoundPool pool, Context context){
            this.theme = theme;
            this.pool = pool;
            this.context = context;
            message = new Message();
            message.arg1 = 1;
            message.arg2 = 0;
        }
        public void run() {
            soundAndreaPasLa = new Sound("Andrea pas la",pool.load(context,R.raw.andrea_pas_la,1));
            soundAttention01 = new Sound("Attention - 01",pool.load(context,R.raw.attention01,1));
            soundAttention02 = new Sound("Attention - 02",pool.load(context,R.raw.attention02,1));
            soundAttention03 = new Sound("Attention - 03",pool.load(context,R.raw.attention03,1));
            soundAttention04 = new Sound("Attention - 04",pool.load(context,R.raw.attention04,1));
            soundAttention05 = new Sound("Attention - 05",pool.load(context,R.raw.attention05,1));
            soundAttention06 = new Sound("Attention - 06",pool.load(context,R.raw.attention06,1));
            soundAucunRapport = new Sound("Aucun rapport",pool.load(context,R.raw.aucun_rapport,1));
            soundBonneIdee = new Sound("Bonne idée",pool.load(context,R.raw.bonne_idee,1));
            soundCalomnie = new Sound("Calomnie",pool.load(context,R.raw.calomnie,1));
            soundChinois01 = new Sound("Chinois - 01",pool.load(context,R.raw.chinois01,1));
            soundChinois02 = new Sound("Chinois - 02",pool.load(context,R.raw.chinois02,1));
            soundCokeVachementBath = new Sound("La Coke c'est vachement bath",pool.load(context,R.raw.coke_vachement_bath,1));
            soundComprendsPas = new Sound("Comprends pas",pool.load(context,R.raw.comprends_pas,1));
            soundCracheBeaucoup = new Sound("Crache beaucoup",pool.load(context,R.raw.crache_beaucoup,1));
            soundDebandade = new Sound("Débandade",pool.load(context,R.raw.debandade,1));
            soundDefection = new Sound("Défection",pool.load(context,R.raw.defection,1));
            soundEmbarrassant = new Sound("Embarrasant",pool.load(context,R.raw.embarrassant,1));
            soundExigeReponse = new Sound("J'exige une réponse",pool.load(context,R.raw.exige_reponse,1));
            soundFaux = new Sound("Faux !",pool.load(context,R.raw.faux,1));
            soundFoutLaRage = new Sound("Fout la rage",pool.load(context,R.raw.fout_la_rage,1));
            soundGrosGourdin = new Sound("Gros gourdin",pool.load(context,R.raw.gros_gourdin,1));
            soundGrosseBlague = new Sound("Grosse blague",pool.load(context,R.raw.grosse_blague,1));
            soundHabile = new Sound("Habile",pool.load(context,R.raw.habile,1));
            soundHallucine = new Sound("Hallucine",pool.load(context,R.raw.hallucine,1));
            soundHumour = new Sound("Humour",pool.load(context,R.raw.humour,1));
            soundIncomprehensible = new Sound("Incompréhensible",pool.load(context,R.raw.incomprehensible,1));

            theme.addSound(soundAndreaPasLa);
            theme.addSound(soundAttention01);
            theme.addSound(soundAttention02);
            theme.addSound(soundAttention03);
            theme.addSound(soundAttention04);
            theme.addSound(soundAttention05);
            theme.addSound(soundAttention06);
            theme.addSound(soundAucunRapport);
            theme.addSound(soundBonneIdee);
            theme.addSound(soundCalomnie);
            theme.addSound(soundChinois01);
            theme.addSound(soundChinois02);
            theme.addSound(soundCokeVachementBath);
            theme.addSound(soundComprendsPas);
            theme.addSound(soundCracheBeaucoup);
            theme.addSound(soundDebandade);
            theme.addSound(soundDefection);
            theme.addSound(soundEmbarrassant);
            theme.addSound(soundExigeReponse);
            theme.addSound(soundFaux);
            theme.addSound(soundFoutLaRage);
            theme.addSound(soundGrosGourdin);
            theme.addSound(soundGrosseBlague);
            theme.addSound(soundHabile);
            theme.addSound(soundHallucine);
            theme.addSound(soundHumour);
            theme.addSound(soundIncomprehensible);

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }
    private class LoadThread2 implements Runnable {
        private Theme theme;
        private SoundPool pool;
        private Context context;
        private Message message;
        LoadThread2 (Theme theme, SoundPool pool, Context context){
            this.theme = theme;
            this.pool = pool;
            this.context = context;
            message = new Message();
            message.arg1 = 2;
            message.arg2 = 0;
        }
        public void run() {
            soundInteressePas = new Sound("Interesse pas",pool.load(context,R.raw.interesse_pas,1));
            soundLeGitan = new Sound("Ha le Gitan",pool.load(context,R.raw.le_gitan,1));
            soundMachiavellique = new Sound("Machiavellique",pool.load(context,R.raw.machiavellique,1));
            soundMagnerLeCul = new Sound("Magner le cul",pool.load(context,R.raw.magner_le_cul,1));
            soundMaitreMichel = new Sound("Maitre Michel",pool.load(context,R.raw.maitre_michel,1));
            soundMalentendu = new Sound("C'est un malentendu",pool.load(context,R.raw.malentendu,1));
            soundMarcheBien = new Sound("Ca marche bien",pool.load(context,R.raw.marche_bien,1));
            soundMeSensSeul = new Sound("Me sens seul",pool.load(context,R.raw.me_sens_seul,1));
            soundMethTropDeLaBalle01 = new Sound("La meth c'est trop de la balle - 01",pool.load(context,R.raw.meth_trop_de_la_balle,1));
            soundMethTropDeLaBalle02 = new Sound("La meth c'est trop de la balle - 02",pool.load(context,R.raw.meth_trop_de_la_balle02,1));
            soundMistake = new Sound("Mistake",pool.load(context,R.raw.mistake,1));
            soundNemrod = new Sound("Nemrod",pool.load(context,R.raw.nemrod,1));
            soundNoFuckingBalls = new Sound("No Fucking Balls",pool.load(context,R.raw.no_fucking_balls,1));
            soundNouveaute = new Sound("Nouveaute",pool.load(context,R.raw.nouveaute,1));
            soundOhOui = new Sound("Oh oui",pool.load(context,R.raw.oh_oui,1));
            soundOnSEmmerde = new Sound("On s'emmerde",pool.load(context,R.raw.on_s_emmerde,1));
            soundOsef = new Sound("Osef",pool.load(context,R.raw.osef,1));
            soundPasCool = new Sound("Pas Cool",pool.load(context,R.raw.pas_cool,1));
            soundPasDrole = new Sound("Pas Drole",pool.load(context,R.raw.pas_drole,1));
            soundPlaisanterie01 = new Sound("Plaisanterie - 01",pool.load(context,R.raw.plaisanterie01,1));
            soundPlaisanterie02 = new Sound("Plaisanterie - 02",pool.load(context,R.raw.plaisanterie02,1));
            soundPouleMouillee = new Sound("Poule mouillée",pool.load(context,R.raw.poule_mouillee,1));
            soundPourquoi = new Sound("Pourquoi",pool.load(context,R.raw.pourquoi,1));
            soundPqEmergency = new Sound("PQ Emergency",pool.load(context,R.raw.pq_emergency,1));
            soundPqIncroyable = new Sound("PQ Incroyable",pool.load(context,R.raw.pq_incroyable,1));
            soundPqReche = new Sound("Ce PQ est un peu reche",pool.load(context,R.raw.pq_reche,1));
            soundPqTropDoux = new Sound("PQ trop doux",pool.load(context,R.raw.pq_trop_doux,1));

            theme.addSound(soundInteressePas);
            theme.addSound(soundLeGitan);
            theme.addSound(soundMachiavellique);
            theme.addSound(soundMagnerLeCul);
            theme.addSound(soundMaitreMichel);
            theme.addSound(soundMalentendu);
            theme.addSound(soundMarcheBien);
            theme.addSound(soundMeSensSeul);
            theme.addSound(soundMethTropDeLaBalle01);
            theme.addSound(soundMethTropDeLaBalle02);
            theme.addSound(soundMistake);
            theme.addSound(soundNemrod);
            theme.addSound(soundNoFuckingBalls);
            theme.addSound(soundNouveaute);
            theme.addSound(soundOhOui);
            theme.addSound(soundOnSEmmerde);
            theme.addSound(soundOsef);
            theme.addSound(soundPasCool);
            theme.addSound(soundPasDrole);
            theme.addSound(soundPlaisanterie01);
            theme.addSound(soundPlaisanterie02);
            theme.addSound(soundPouleMouillee);
            theme.addSound(soundPourquoi);
            theme.addSound(soundPqEmergency);
            theme.addSound(soundPqIncroyable);
            theme.addSound(soundPqReche);
            theme.addSound(soundPqTropDoux);

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }
    private class LoadThread3 implements Runnable {
        private Theme theme;
        private SoundPool pool;
        private Context context;
        private Message message;
        LoadThread3 (Theme theme, SoundPool pool, Context context){
            this.theme = theme;
            this.pool = pool;
            this.context = context;
            message = new Message();
            message.arg1 = 3;
            message.arg2 = 0;
        }

        public void run() {
            soundPqTropManque = new Sound("PQ Trop manqué",pool.load(context,R.raw.pq_trop_manque,1));
            soundPrejudice = new Sound("Prejudice",pool.load(context,R.raw.prejudice,1));
            soundPrevoyant = new Sound("Prevoyant",pool.load(context,R.raw.prevoyant,1));
            soundPrisPropreJeu = new Sound("Pris propre jeu",pool.load(context,R.raw.pris_propre_jeu,1));
            soundPtitZizi = new Sound("Ptit zizi",pool.load(context,R.raw.ptit_zizi,1));
            soundPtiteBite = new Sound("Ptite bite",pool.load(context,R.raw.ptite_bite,1));
            soundPueDuCul = new Sound("Pue du cul",pool.load(context,R.raw.pue_du_cul,1));
            soundQueSePasseTIl = new Sound("Que se passe t'il ",pool.load(context,R.raw.que_ce_passe_t_il,1));
            soundQuelqueSorte = new Sound("Quelque Sorte",pool.load(context,R.raw.quelque_sorte,1));
            soundQuiEstLa = new Sound("Qui est la",pool.load(context,R.raw.qui_est_la,1));
            soundQuoi01 = new Sound("Quoi - 01",pool.load(context,R.raw.quoi,1));
            soundQuoi02 = new Sound("Quoi - 02",pool.load(context,R.raw.quoi02,1));
            soundQuoi03 = new Sound("Quoi - 03",pool.load(context,R.raw.quoi03,1));
            soundSante = new Sound("Sante",pool.load(context,R.raw.sante,1));
            soundScandaleux = new Sound("Scandaleux",pool.load(context,R.raw.scandaleux,1));
            soundSuperBaise = new Sound("Super Baise",pool.load(context,R.raw.super_baise,1));
            soundSuperSpirituel = new Sound("Super Spirituel",pool.load(context,R.raw.super_spirituel,1));
            soundTrahison = new Sound("Trahison",pool.load(context,R.raw.trahison,1));
            soundTripleEpaisseur = new Sound("Triple epaisseur",pool.load(context,R.raw.triple_epaisseur,1));
            soundTropPlaisir = new Sound("Trop plaisir",pool.load(context,R.raw.trop_plaisir,1));
            soundTrucDeMazo = new Sound("Truc de mazo",pool.load(context,R.raw.truc_de_mazo,1));
            soundTrueStory = new Sound("True Story",pool.load(context,R.raw.true_story,1));
            soundVachementBath = new Sound("Vachement bath",pool.load(context,R.raw.vachement_bath,1));
            soundViens01 = new Sound("Viens - 01",pool.load(context,R.raw.viens01,1));
            soundViens02 = new Sound("Viens - 02",pool.load(context,R.raw.viens02,1));
            soundVieuxMan = new Sound("Vieux man",pool.load(context,R.raw.vieux_man,1));
            soundVoirMaBite = new Sound("Voir ma bite",pool.load(context,R.raw.voir_ma_bite,1));
            soundVrai = new Sound("Vrai",pool.load(context,R.raw.vrai,1));

            theme.addSound(soundPqTropManque);
            theme.addSound(soundPrejudice);
            theme.addSound(soundPrevoyant);
            theme.addSound(soundPrisPropreJeu);
            theme.addSound(soundPtiteBite);
            theme.addSound(soundPtitZizi);
            theme.addSound(soundPueDuCul);
            theme.addSound(soundQueSePasseTIl);
            theme.addSound(soundQuelqueSorte);
            theme.addSound(soundQuiEstLa);
            theme.addSound(soundQuoi01);
            theme.addSound(soundQuoi02);
            theme.addSound(soundQuoi03);
            theme.addSound(soundSante);
            theme.addSound(soundScandaleux);
            theme.addSound(soundSuperBaise);
            theme.addSound(soundSuperSpirituel);
            theme.addSound(soundTrahison);
            theme.addSound(soundTripleEpaisseur);
            theme.addSound(soundTropPlaisir);
            theme.addSound(soundTrucDeMazo);
            theme.addSound(soundTrueStory);
            theme.addSound(soundVachementBath);
            theme.addSound(soundViens01);
            theme.addSound(soundViens02);
            theme.addSound(soundVieuxMan);
            theme.addSound(soundVoirMaBite);
            theme.addSound(soundVrai);

            message.arg2 = 1;
            loadingHandler.sendMessage(message);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingHandler = new Handler(Looper.getMainLooper()) {

            /*
             * handleMessage() defines the operations to perform when
             * the Handler receives a new Message to process.
             */
            @Override
            public void handleMessage(Message message) {
                if(message.arg2 == 1) {
                    adapterAll.notifyDataSetChanged();
                }
            }
        };

        threadPoolExec = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        threadPoolExec.prestartAllCoreThreads();

        thread1LoadedSounds = 0;
        thread2LoadedSounds = 0;
        thread3LoadedSounds = 0;

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


        //Instantiate the sound engine.
        soundEngine = new SoundEngine((AudioManager)this.getSystemService(Context.AUDIO_SERVICE));

        //Instantiate themes named themeAll and themeFav
        themeAll = new Theme("themeAll");
        themeFav = new Theme("themeFav");

        //Instantiate the saveFavorites class
        //saveFavoritesList = new SaveFavoritesList("saveFavorites");


/*
        /*
         * Create adapters for the ListView
         * ListView will be fed from the soundNameList field of corresponding theme.
         *
         * https://developer.android.com/reference/android/widget/ListView.html#setAdapter%28android.widget.ListAdapter%29
         */
        adapterAll = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,themeAll.getSoundNameList());
        adapterFav = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, themeFav.getSoundNameList());

        soundListDisplay.setAdapter(adapterAll);

        currentTheme = themeAll;
        currentThemeTextView.setText("Current theme is All");

        //Load all embedded sounds in memory and create Sound objects
        soundPool = soundEngine.getSoundPool();
        appContext = this.getApplicationContext();

        LoadThread1 loadingThread1 = new LoadThread1(currentTheme, soundPool, appContext);
        LoadThread2 loadingThread2 = new LoadThread2(currentTheme, soundPool, appContext);
        LoadThread3 loadingThread3 = new LoadThread3(currentTheme, soundPool, appContext);

        threadPoolExec.submit(loadingThread1);
        threadPoolExec.submit(loadingThread2);
        threadPoolExec.submit(loadingThread3);
        adapterAll.notifyDataSetChanged();

        //Sort list by alphabetical order
        currentTheme.sortSoundNameList();
        adapterAll.notifyDataSetChanged();

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
            //get random position
            rand = random.nextInt(currentTheme.getSoundsCount());
            //play associated sound in current theme
            soundEngine.playSound(currentTheme.getSound(rand).getSoundId());

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
            soundEngine.playSound(currentTheme.getSound(pos).getSoundId());
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
            else {
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