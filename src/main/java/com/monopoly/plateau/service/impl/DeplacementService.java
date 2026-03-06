package com.monopoly.plateau.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.constantes.Case;
import com.monopoly.plateau.service.IDeplacementService;
import org.springframework.stereotype.Service;

import static com.monopoly.plateau.Constantes.SALAIRE_CASE_DEPART;

@Service
public class DeplacementService implements IDeplacementService {
    @Override
    public void deplacer(Joueur joueur, Case destination) {
        if (Case.ALLER_EN_PRISON == destination) {
            allerEnPrison(joueur);
            return;
        }
        if(doitJoueurToucherSalaireCaseDepart(joueur, destination.positionSurPlateau())){
            joueur.recevoirArgent(SALAIRE_CASE_DEPART);
        }
        joueur.setCaseJoueur(destination);
    }

    private static boolean doitJoueurToucherSalaireCaseDepart(Joueur joueur, int positionArrivee) {
        return !joueur.estEnPrison() &&
                positionArrivee <= joueur.position();
    }

    @Override
    public void allerEnPrison(Joueur joueur) {
        joueur.setEstEnPrison(true);
        joueur.setCaseJoueur(Case.SIMPLE_VISITE_PRISON);
    }
}
