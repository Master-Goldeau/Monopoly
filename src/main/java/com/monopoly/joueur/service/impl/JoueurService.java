package com.monopoly.joueur.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.service.IJoueurService;
import com.monopoly.lancer.service.ILancersService;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.partie.model.Partie;
import com.monopoly.partie.service.IPartieService;
import com.monopoly.plateau.constantes.Case;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.model.TypePiochable;
import com.monopoly.plateau.pioche.service.IPiochableService;
import org.springframework.stereotype.Service;

import static com.monopoly.plateau.Constantes.PLATEAU;
import static com.monopoly.plateau.Constantes.SALAIRE_CASE_DEPART;

@Service
public class JoueurService implements IJoueurService {

    private final IPiochableService piochableService;
    private final ILancersService lancersService;
    private final IPartieService partieService;

    public JoueurService(IPiochableService piochableService,
                         ILancersService lancersService,
                         IPartieService partieService) {
        this.piochableService = piochableService;
        this.lancersService = lancersService;
        this.partieService = partieService;
    }

    public LancerDes lancerDesEtGererDoublesConsecutifs(Joueur joueur) {
        LancerDes resultat = lancersService.lancerDeuxDesSix(joueur);
        gererDoubleConsecutifs(joueur, resultat);
        return resultat;
    }

    private void gererDoubleConsecutifs(Joueur joueur, LancerDes resultat) {
        if (resultat.estUnDouble()) {
            incrementerDoublesConsecutifs(joueur);
            if (joueur.aTroisDoublesConsecutifs()) {
                deplacer(joueur, allerEnPrison(joueur));
            }
        } else {
            resetDoublesConsecutifs(joueur);
        }
    }

    private static void incrementerDoublesConsecutifs(Joueur joueur) {
        joueur.setDoubleConsecutifs(joueur.doubleConsecutifs() + 1);
    }

    @Override
    public void deplacer(Joueur joueur, Case destination) {
        if (Case.ALLER_EN_PRISON == destination) {
            destination = allerEnPrison(joueur);
        }
        if(doitJoueurToucherSalaireCaseDepart(joueur, destination.positionSurPlateau())){
            joueur.recevoirArgent(SALAIRE_CASE_DEPART);
        }
        joueur.setCaseJoueur(destination);
    }

    private static Case allerEnPrison(Joueur joueur) {
        joueur.setEstEnPrison(true);
        return Case.SIMPLE_VISITE_PRISON;
    }

    private static boolean doitJoueurToucherSalaireCaseDepart(Joueur joueur, int positionArrivee) {
        return !joueur.estEnPrison() &&
                positionArrivee <= joueur.position();
    }

    public Case getDestinationApresLancer(Joueur joueur, LancerDes valeurLancerDes) {
        if (joueur.aTroisDoublesConsecutifs()) {
            return allerEnPrison(joueur);
        }
        int positionArrivee = (joueur.position() + valeurLancerDes.getSomme()) % PLATEAU.size();
        return Case.depuisPosition(positionArrivee);
    }

    public void piocherCarteEtAppliquerEffet(Partie partie, Joueur joueur, TypePiochable casePiochable) {
        piocherCarte(partie, casePiochable).appliquerEffet(partie, joueur);
    }

    private Piochable piocherCarte(Partie partie, TypePiochable typePioche) {
        return piochableService.piocher(partie.getPioche(typePioche));
    }

    private static void resetDoublesConsecutifs(Joueur joueur) {
        joueur.setDoubleConsecutifs(0);
    }

    /**
     * Méthode utilitaire interne pour encapsuler le déroulement complet d'un tour de joueur.
     * À utiliser uniquement pour des tests ou des traitements batch, jamais depuis le contrôleur/front.
     */
    void jouerTour(Joueur joueur) {
        do {
            LancerDes resultat = lancerDesEtGererDoublesConsecutifs(joueur);
            // Si le joueur n'est pas allé en prison, déplacer normalement
            if (Case.ALLER_EN_PRISON != joueur.caseJoueur()) {
                Case destination = getDestinationApresLancer(joueur, resultat);
                deplacer(joueur, destination);
                // Pioche si la case est piochable
                if (destination.doitPiocher()) {
                    piocherCarteEtAppliquerEffet(
                            partieService.getPartieEnCours(),
                            joueur,
                            TypePiochable.depuisNom(destination.getNom())
                    );
                }
            }
        } while (joueur.peutRejouer());
    }
}
