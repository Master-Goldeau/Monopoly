package com.monopoly.joueur.service;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.constantes.Case;
import com.monopoly.plateau.pioche.model.Piochable;

public interface IJoueurService {
    int lancerDes(Joueur joueur);
    Case getDestinationApresLancer(Joueur joueur, int valeurLancerDes);
    Piochable piocherCarte(String casePiochable);
}
