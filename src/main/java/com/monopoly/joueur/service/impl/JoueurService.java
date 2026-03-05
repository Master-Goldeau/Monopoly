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
import com.monopoly.plateau.service.impl.DeplacementService;
import org.springframework.stereotype.Service;

import static com.monopoly.plateau.Constantes.PLATEAU;

@Service
public class JoueurService implements IJoueurService {

    private final IPartieService partieService;
    private final ILancersService lancersService;
    private final DeplacementService deplacementService;
    private final IPiochableService piochableService;

    public JoueurService(IPartieService partieService,
                         ILancersService lancersService,
                         DeplacementService deplacementService,
                         IPiochableService piochableService
                         ) {
        this.partieService = partieService;
        this.lancersService = lancersService;
        this.deplacementService = deplacementService;
        this.piochableService = piochableService;
    }


    public void jouerTour(Joueur joueur) {
        do {
            LancerDes lancerDes = lancersService.lancerDesEtGererDoublesConsecutifs(joueur);
            // Si le joueur est allé en prison il ne peut plus se déplacer.
            if (Case.ALLER_EN_PRISON == joueur.caseJoueur()) {
                // TODO : gérer le cas quand le joueur est en prison :
                //  payer pour sortir, faire un double, ou utiliser carte
                //  Négocier avec d'autres joueurs
                //  Construire
                return;
            }
            Case destination = getDestinationApresLancer(joueur, lancerDes);
            deplacementService.deplacer(joueur, destination);
            // Pioche si la case est piochable
            if (destination.doitPiocher()) {
                piocherCarteEtAppliquerEffet(
                        partieService.getPartieEnCours(),
                        joueur,
                        TypePiochable.depuisNom(destination.nom())
                );
            }
    } while(joueur.peutRejouer());
}

public Case getDestinationApresLancer(Joueur joueur, LancerDes valeurLancerDes) {
    int positionArrivee = (joueur.position() + valeurLancerDes.getSomme()) % PLATEAU.size();
    return Case.depuisPosition(positionArrivee);
}

public void piocherCarteEtAppliquerEffet(Partie partie, Joueur joueur, TypePiochable casePiochable) {
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
