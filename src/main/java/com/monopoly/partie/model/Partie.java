package com.monopoly.partie.model;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.model.TypePiochable;

import java.util.Queue;

public record Partie(
        Joueur joueurCourant,
        Queue<Joueur> joueurs,
        Queue<Piochable> piocheCartesChance,
        Queue<Piochable> piocheCartesCaisseDeCommunaute
) {

    public Queue<Piochable> getPioche(TypePiochable casePiochable) {
        return switch (casePiochable) {
            case CHANCE -> piocheCartesChance;
            case CAISSE_DE_COMMUNAUTE -> piocheCartesCaisseDeCommunaute;
        };
    }
}
