package com.monopoly.lancer.service;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.lancer.service.modele.LancerDes;

public interface ILancersService {
    /**
     * Lance deux dés à six faces et retourne le résultat sous forme d'un lancer de deux dés.
     * Gère également les doubles consécutifs : si le joueur fait un double, il peut rejouer.
     * Cependant, s'il fait trois doubles consécutifs, il doit aller en prison.
     *
     * @return un LancerDes.
     */
    LancerDes lancerDesEtGererDoublesConsecutifs(Joueur joueur);
}
