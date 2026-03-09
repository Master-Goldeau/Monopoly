package com.monopoly.plateau.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;
import com.monopoly.partie.service.IPartieService;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.model.TypePiochable;
import com.monopoly.plateau.pioche.service.IPiochableService;
import com.monopoly.plateau.service.ICaseService;
import org.springframework.stereotype.Service;

import static com.monopoly.plateau.Constantes.IMPOTS_REVENUS;
import static com.monopoly.plateau.Constantes.SALAIRE_CASE_DEPART;
import static com.monopoly.plateau.Constantes.TAXE_LUXE;

@Service
public class CaseService implements ICaseService {

    private final IPartieService partieService;
    private final IPiochableService piochableService;

    public CaseService(IPartieService partieService, IPiochableService piochableService) {
        this.partieService = partieService;
        this.piochableService = piochableService;
    }

    @Override
    public void appliquerEffetDestination(Joueur joueur) {
        switch (joueur.caseJoueur().type()) {
            case ALLER_EN_PRISON:
                joueur.allerEnPrison();
                return;
            case BENEFICE:
                joueur.recevoirArgent(SALAIRE_CASE_DEPART);
            case PAIEMENT:
                payer(joueur);
                return;
            case PIOCHER:
                piocherCarteEtAppliquerEffet(
                        partieService.getPartieEnCours(),
                        joueur,
                        TypePiochable.depuisNom(joueur.caseJoueur().nom())
                );
                return;
            default:
                // TODO : gérer les autres types de cases
        }
    }

    private static void payer(Joueur joueur) {
        switch(joueur.caseJoueur()) {
            case LUXE -> joueur.payer(TAXE_LUXE);
            case IMPOTS -> joueur.payer(IMPOTS_REVENUS);
        }
    }

    private void piocherCarteEtAppliquerEffet(Partie partie, Joueur joueur, TypePiochable casePiochable) {
        Piochable cartePiochee = piochableService.piocher(partie.getPioche(casePiochable));
        piochableService.appliquerEffet(cartePiochee, partie, joueur);
    }
}

