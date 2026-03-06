package com.monopoly.plateau.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.constantes.CasePlateau;
import com.monopoly.plateau.service.ICaseService;
import org.springframework.stereotype.Service;

import static com.monopoly.plateau.Constantes.SALAIRE_CASE_DEPART;

@Service
public class CaseService implements ICaseService {

    @Override
    public void toucherSalaireSiPassageCaseDepart(Joueur joueur, CasePlateau destination) {
        if (doitJoueurToucherSalaireCaseDepart(joueur, destination.positionSurPlateau())) {
            joueur.recevoirArgent(SALAIRE_CASE_DEPART);
        }
    }

    @Override
    public void appliquerEffetDestination(Joueur joueur, CasePlateau destination) {
        switch (destination.type()) {
            case BENEFICE:
                joueur.recevoirArgent(SALAIRE_CASE_DEPART);
            case PAIEMENT:
                payer(joueur, destination);
                break;

            default:
                // TODO : gérer les autres types de cases
        }
    }

    private static void payer(Joueur joueur, CasePlateau destination) {
        joueur.payer(destination.montant());
    }

    private static boolean doitJoueurToucherSalaireCaseDepart(Joueur joueur, int positionArrivee) {
        return !joueur.estEnPrison() &&
                positionArrivee < joueur.position();
    }
}

