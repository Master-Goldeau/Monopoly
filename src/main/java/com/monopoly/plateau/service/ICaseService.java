package com.monopoly.plateau.service;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.constantes.CasePlateau;

public interface ICaseService {

    void toucherSalaireSiPassageCaseDepart(Joueur joueur, CasePlateau destination);

    void appliquerEffetDestination(Joueur joueur, CasePlateau destination);
}
