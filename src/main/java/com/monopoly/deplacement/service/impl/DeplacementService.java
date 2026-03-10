package com.monopoly.deplacement.service.impl;

import com.monopoly.deplacement.model.Deplacement;
import com.monopoly.joueur.model.Joueur;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.plateau.model.CasePlateau;
import com.monopoly.deplacement.service.IDeplacementService;
import org.springframework.stereotype.Service;

import static com.monopoly.plateau.Constantes.SALAIRE_CASE_DEPART;

@Service
public class DeplacementService implements IDeplacementService {

    @Override
    public Deplacement deplacer(Joueur joueur, LancerDes lancerDes) {
        CasePlateau destination = calculerDestination(joueur, lancerDes);
        CasePlateau positionInitiale = joueur.caseJoueur();
        joueur.setCaseJoueur(destination);
        return new Deplacement(positionInitiale, destination);
    }

    @Override
    public Deplacement deplacer(Joueur joueur, CasePlateau destination) {
        CasePlateau positionInitiale = joueur.caseJoueur();
        joueur.setCaseJoueur(destination);
        return new Deplacement(positionInitiale, destination);
    }

    @Override
    public void toucherSalaireSiPassageCaseDepart(Joueur joueur, Deplacement deplacementJoueur) {
        if (doitJoueurToucherSalaireCaseDepart(deplacementJoueur)) {
            joueur.recevoirArgent(SALAIRE_CASE_DEPART);
        }
    }

    private static CasePlateau calculerDestination(Joueur joueur, LancerDes lancerDes) {
        int positionActuelle = joueur.caseJoueur().positionSurPlateau();
        int valeurLancerDes = lancerDes.somme();
        int nouvellePosition = (positionActuelle + valeurLancerDes) % CasePlateau.values().length;
        return CasePlateau.depuisPosition(nouvellePosition);
    }

    private static boolean doitJoueurToucherSalaireCaseDepart(Deplacement deplacement) {
        return deplacement.caseDArrivee().positionSurPlateau() <= deplacement.caseDeDepart().positionSurPlateau();
    }

}
