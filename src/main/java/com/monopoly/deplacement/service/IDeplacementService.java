package com.monopoly.deplacement.service;

import com.monopoly.deplacement.model.Deplacement;
import com.monopoly.joueur.model.Joueur;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.plateau.model.CasePlateau;

public interface IDeplacementService {
    Deplacement deplacer(Joueur joueur, LancerDes lancerDes);
    Deplacement deplacer(Joueur joueur, CasePlateau destination);
    void toucherSalaireSiPassageCaseDepart(Joueur joueur, Deplacement deplacementJoueur);
}
