package com.monopoly.lancer.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.lancer.service.ILancersService;
import com.monopoly.lancer.service.modele.Des;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.plateau.service.impl.DeplacementService;
import org.springframework.stereotype.Service;

@Service
public class LancersService implements ILancersService {

    private final DeplacementService deplacementService;

    public LancersService(DeplacementService deplacementService) {
        this.deplacementService = deplacementService;
    }

    @Override
    public LancerDes lancerDesEtGererDoublesConsecutifs(Joueur joueur) {
        LancerDes resultat = lancerDeuxDesSix();
        gererDoubleConsecutifs(joueur, resultat);
        return resultat;
    }

    //TODO : refactoriser pour que cette méthode reste privée.
    // Elle est actuellement publique pour pouvoir être mockée dans les tests, mais cela n'est pas idéal.
    public LancerDes lancerDeuxDesSix() {
        return new LancerDes(Des.lancer(), Des.lancer());
    }

    private void gererDoubleConsecutifs(Joueur joueur, LancerDes resultat) {
        if (resultat.estUnDouble()) {
            incrementerDoublesConsecutifs(joueur);
            if (joueur.aTroisDoublesConsecutifs()) {
                resetDoublesConsecutifs(joueur);
                deplacementService.allerEnPrison(joueur);
            }
        } else {
            resetDoublesConsecutifs(joueur);
        }
    }

    private static void incrementerDoublesConsecutifs(Joueur joueur) {
        joueur.setDoubleConsecutifs(joueur.doubleConsecutifs() + 1);
    }

    private static void resetDoublesConsecutifs(Joueur joueur) {
        joueur.setDoubleConsecutifs(0);
    }


}
