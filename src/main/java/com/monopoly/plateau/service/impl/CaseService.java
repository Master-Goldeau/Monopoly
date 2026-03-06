package com.monopoly.plateau.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;
import com.monopoly.partie.service.IPartieService;
import com.monopoly.plateau.constantes.CasePlateau;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.model.TypePiochable;
import com.monopoly.plateau.pioche.service.IPiochableService;
import com.monopoly.plateau.service.ICaseService;
import org.springframework.stereotype.Service;

import static com.monopoly.plateau.Constantes.SALAIRE_CASE_DEPART;

@Service
public class CaseService implements ICaseService {

    private final IPartieService partieService;
    private final IPiochableService piochableService;

    public CaseService(IPartieService partieService, IPiochableService piochableService) {
        this.partieService = partieService;
        this.piochableService = piochableService;
    }

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
            case PIOCHER:
                piocherCarteEtAppliquerEffet(
                        partieService.getPartieEnCours(),
                        joueur,
                        TypePiochable.depuisNom(destination.nom())
                );
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

    private void piocherCarteEtAppliquerEffet(Partie partie, Joueur joueur, TypePiochable casePiochable) {
        Piochable cartePiochee = piocherCarte(partie, casePiochable);
        appliquerEffet(cartePiochee, partie, joueur);
    }

    private Piochable piocherCarte(Partie partie, TypePiochable typePioche) {
        return piochableService.piocher(partie.getPioche(typePioche));
    }

    private void appliquerEffet(Piochable cartePiochee, Partie partie, Joueur joueur) {
        piochableService.appliquerEffet(cartePiochee, partie, joueur);
    }
}

