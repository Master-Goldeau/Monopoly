package com.monopoly.partie.service;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;

import java.util.Queue;

public interface IPartieService {

    Partie initialiserPartie(Queue<Joueur> joueurs);
    Partie getPartieEnCours();
}
