package com.monopoly.partie.model;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.pioche.model.Piochable;

import java.util.List;

import static com.monopoly.plateau.Constantes.CAISSE_DE_COMMUNAUTE;
import static com.monopoly.plateau.Constantes.CHANCE;

public record Partie(
        Joueur joueurCourant,
        List<Joueur> joueurs,
        List<Piochable> piocheCartesChance,
        List<Piochable> piocheCartesCaisseDeCommunaute
) {

    //TODO initialiser piocheChance devrait retourner 16 cartes avec deux ProchaineGare


    public List<Piochable> getPioche(String casePiochable) {
        return switch (casePiochable) {
            case CHANCE -> piocheCartesChance;
            case CAISSE_DE_COMMUNAUTE -> piocheCartesCaisseDeCommunaute;
            default -> throw new IllegalArgumentException("Case piochable inconnue : " + casePiochable);
        };
    }
}
