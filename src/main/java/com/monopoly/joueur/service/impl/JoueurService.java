package com.monopoly.joueur.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.lancer.service.modele.Des;
import com.monopoly.partie.model.Partie;
import com.monopoly.plateau.constantes.Case;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.service.IPiochableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.monopoly.plateau.Constantes.PLATEAU;
import static com.monopoly.plateau.Constantes.SALAIRE_CASE_DEPART;
import static com.monopoly.plateau.constantes.Case.allerEnPrison;

@Service
public class JoueurService {

    private final IPiochableService piochableService;

    public JoueurService(IPiochableService piochableService) {
        this.piochableService = piochableService;
    }

    public int lancerDes(Joueur joueur) {
        int valeurDes1 = Des.lancer().valeur();
        int valeurDes2 = Des.lancer().valeur();
        gererDoubleConsecutifs(joueur, valeurDes1, valeurDes2);
        return valeurDes1 + valeurDes2;
    }

    private static boolean vaJoueurPasserLaCaseDepart(Joueur joueur, int positionArrivee) {
        return positionArrivee < joueur.getPosition();
    }

    public Case getDestinationApresLancer(Joueur joueur, int valeurLancerDes) {
        int positionArrivee = (joueur.getPosition() + valeurLancerDes) % PLATEAU.size();
        if(joueur.aTroisDoublesConsecutifs()){
            resetDoublesConsecutifs(joueur);
            return allerEnPrison();
        }
        if (vaJoueurPasserLaCaseDepart(joueur, positionArrivee)) {
            joueur.recevoirArgent(SALAIRE_CASE_DEPART);
        }

        Case destination = Case.depuisPosition(positionArrivee);

        if(destination.doitPiocher()){
            //Piochable cartePiochee = piocherCarte(partie, destination.getNom());
            //cartePiochee.appliquerEffet();
        }

        if (Objects.equals(Case.ALLER_EN_PRISON, destination)) {
            return allerEnPrison();
        }
        return destination;
    }

    public Piochable piocherCarte(Partie partie, String casePiochable) {
        // À implémenter
        List<Piochable> pioche = partie.getPioche(casePiochable);
        //return piochableService.piocher(casePiochable);
        return null;
    }


    private static void gererDoubleConsecutifs(Joueur joueur, int valeurDes1, int valeurDes2) {
        if (valeurDes1 == valeurDes2) {
            joueur.setDoubleConsecutifs(joueur.getDoubleConsecutifs()+1);
        } else {
            resetDoublesConsecutifs(joueur);
        }
    }

    private static void resetDoublesConsecutifs(Joueur joueur) {
        joueur.setDoubleConsecutifs(0);
    }

}
