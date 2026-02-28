package com.monopoly.partie.service;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;

import java.util.List;

public interface IPartieService {

    Partie initialiserPartie(List<Joueur> joueurs);
}
