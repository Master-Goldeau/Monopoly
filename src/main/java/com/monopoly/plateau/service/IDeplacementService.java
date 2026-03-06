package com.monopoly.plateau.service;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.constantes.CasePlateau;

public interface IDeplacementService {
    void deplacerEtAppliquerEffetCase(Joueur joueur, CasePlateau destination);
}
