package com.monopoly.plateau.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.constantes.CasePlateau;
import com.monopoly.plateau.service.IDeplacementService;
import org.springframework.stereotype.Service;

@Service
public class DeplacementService implements IDeplacementService {

    private final CaseService caseService;

    public DeplacementService(CaseService caseService) {
        this.caseService = caseService;
    }

    @Override
    public void deplacer(Joueur joueur, CasePlateau destination) {
        if (CasePlateau.ALLER_EN_PRISON == destination) {
            allerEnPrison(joueur);
            return;
        }
        caseService.toucherSalaireSiPassageCaseDepart(joueur, destination);
        caseService.appliquerEffetDestination(joueur, destination);

        joueur.setCaseJoueur(destination);
    }

    @Override
    public void allerEnPrison(Joueur joueur) {
        joueur.setEstEnPrison(true);
        joueur.setCaseJoueur(CasePlateau.SIMPLE_VISITE_PRISON);
    }
}
