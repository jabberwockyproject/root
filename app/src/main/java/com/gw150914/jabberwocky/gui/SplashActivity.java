package com.gw150914.jabberwocky.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.gw150914.jabberwocky.R;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

/*
LETTRE A MENECEE. Epicure
**
Quand donc nous disons que le plaisir est notre but ultime, le plaisir que nous
avons en vue est caractérisé par l'absence de souffrances corporelles et de troubles de l'âme.
Ce ne sont pas les beuveries et les orgies continuelles, [...] qui engendrent une vie heureuse,
mais la raison vigilante, qui recherche minutieusement les motifs de ce qu'il faut choisir et de ce qu'il faut éviter,
et qui rejettent les fausses opinions grâce auxquelles le plus grand trouble s'empare des âmes.
De tout cela, la sagesse est le principe et le plus grand des biens.
C'est pourquoi elle est même plus précieuse que la philosophie, car elle est la source de toutes les autres vertus,
puisqu'elle nous enseigne qu'on ne peut pas être heureux sans être sage, honnête et juste sans être sage,
ni honnête et juste sans être heureux. Les vertus, en effet, ne font qu'un avec la vie heureuse,
et celle-ci est inséparable d'elles.
*/

}
