package com.monopoly.partie.model;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.pioche.model.Piochable;

import java.util.Queue;

import static com.monopoly.plateau.Constantes.CAISSE_DE_COMMUNAUTE;
import static com.monopoly.plateau.Constantes.CHANCE;

public record Partie(
        Joueur joueurCourant,
        Queue<Joueur> joueurs,
        Queue<Piochable> piocheCartesChance,
        Queue<Piochable> piocheCartesCaisseDeCommunaute
) {

    public Queue<Piochable> getPioche(String casePiochable) {
        return switch (casePiochable) {
            case CHANCE -> piocheCartesChance;
            case CAISSE_DE_COMMUNAUTE -> piocheCartesCaisseDeCommunaute;
            default -> throw new IllegalArgumentException("Case piochable inconnue : " + casePiochable);
        };
    }
}
