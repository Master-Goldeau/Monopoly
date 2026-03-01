package com.monopoly.lancer.service;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.lancer.service.modele.LancerDes;

public interface ILancersService {
    /**
     * Lance deux dés à six faces et retourne le résultat sous forme d'un Set de dès.
     *
     * @return un LancerDes.
     */
    LancerDes lancerDeuxDesSix(Joueur joueur);
}
