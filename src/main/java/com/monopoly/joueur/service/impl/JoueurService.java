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
import static com.monopoly.plateau.constantes.Case.allerEnPrison;

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
        LancerDes resultat = lancerDes(joueur);
        gererDoubleConsecutifs(joueur, resultat);
        return resultat;
    }

    private LancerDes lancerDes(Joueur joueur) {
        return lancersService.lancerDeuxDesSix(joueur);
    }

    private void gererDoubleConsecutifs(Joueur joueur, LancerDes resultat) {
        if (resultat.estUnDouble()) {
            joueur.setDoubleConsecutifs(joueur.getDoubleConsecutifs() + 1);
            if (joueur.aTroisDoublesConsecutifs()) {
                deplacer(joueur, allerEnPrison());
            }
        } else {
            resetDoublesConsecutifs(joueur);
        }
    }

    @Override
    public void deplacer(Joueur joueur, Case destination) {
        if (Case.ALLER_EN_PRISON == destination) {
            destination = allerEnPrison();
        }
        joueur.setCaseJoueur(destination);
    }

    private static boolean vaJoueurSarreterOuPasserLaCaseDepart(Joueur joueur, int positionArrivee) {
        return Case.DEPART.getPositionSurPlateau() == joueur.getPosition()
                || positionArrivee < joueur.getPosition();
    } //TODO

    public Case getDestinationApresLancer(Joueur joueur, LancerDes valeurLancerDes) {
        if (joueur.aTroisDoublesConsecutifs()) {
            return allerEnPrison();
        }
        int positionArrivee = (joueur.getPosition() + valeurLancerDes.getSomme()) % PLATEAU.size();
//        if (vaJoueurSarreterOuPasserLaCaseDepart(joueur, positionArrivee)) {
//            joueur.recevoirArgent(SALAIRE_CASE_DEPART);
//        }

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
        boolean rejouer;
        do {
            LancerDes resultat = lancerDesEtGererDoublesConsecutifs(joueur);
            // Si le joueur n'est pas allé en prison, déplacer normalement
            if (Case.ALLER_EN_PRISON != joueur.getCaseJoueur()) {
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
            rejouer = joueur.peutRejouer();
        } while (rejouer);
    }
}
