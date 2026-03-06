package com.monopoly.plateau.service;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.constantes.Case;

public interface IDeplacementService {
    void deplacer(Joueur joueur, Case destination);
    void allerEnPrison(Joueur joueur);
}
