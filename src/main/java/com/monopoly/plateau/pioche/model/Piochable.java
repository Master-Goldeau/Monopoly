package com.monopoly.plateau.pioche.model;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;

public sealed interface Piochable
        permits CartesChance, CartesCaisseDeCommunaute {
    void appliquerEffet(Partie partieEnCours, Joueur joueur);
    String getDescription();
}
