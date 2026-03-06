package com.monopoly.plateau.service;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.constantes.CasePlateau;

public interface IDeplacementService {
    void deplacer(Joueur joueur, CasePlateau destination);
    void allerEnPrison(Joueur joueur);
}
