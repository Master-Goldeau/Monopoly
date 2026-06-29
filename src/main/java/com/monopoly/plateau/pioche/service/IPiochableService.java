package com.monopoly.plateau.pioche.service;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;
import com.monopoly.plateau.pioche.model.Piochable;

import java.util.Queue;

public interface IPiochableService {

    Queue<Piochable> initialiserPioche(Class<? extends Piochable> classePiochable);

    Piochable piocher(Queue<Piochable> pioche);

    void appliquerEffet(Piochable cartePiochee, Partie partieEnCours, Joueur joueur);

    void remettreCarteDansPioche(Piochable carteUtilisee, Partie partieEnCours);
}
