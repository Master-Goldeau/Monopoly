package com.monopoly.lancer.service;

import com.monopoly.lancer.service.modele.Des;

import java.util.List;

public interface ILancersService {
    /**
     * Lance deux dés à six faces et retourne le résultat sous forme d'un Set de dès.
     *
     * @return un Set de dès contenant exactement deux dés.
     */
    List<Des> lancerDeuxDesSix();
}
