package com.monopoly.joueur.service;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.plateau.constantes.Case;

public interface IJoueurService {
    LancerDes lancerDesEtGererDoublesConsecutifs(Joueur joueur);
    void deplacer(Joueur joueur, Case destination);
}
