package com.monopoly.plateau.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.constantes.CasePlateau;
import com.monopoly.plateau.service.ICaseService;
import com.monopoly.plateau.service.IDeplacementService;
import org.springframework.stereotype.Service;

@Service
public class DeplacementService implements IDeplacementService {

    private final ICaseService caseService;

    public DeplacementService(ICaseService caseService) {
        this.caseService = caseService;
    }

    @Override
    public void deplacerEtAppliquerEffetCase(Joueur joueur, CasePlateau destination) {
        if (CasePlateau.ALLER_EN_PRISON == destination) {
            joueur.allerEnPrison();
            return;
        }
        caseService.toucherSalaireSiPassageCaseDepart(joueur, destination);
        caseService.appliquerEffetDestination(joueur, destination);

        joueur.setCaseJoueur(destination);
    }

}
