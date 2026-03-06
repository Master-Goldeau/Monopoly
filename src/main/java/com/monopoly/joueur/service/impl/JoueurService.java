package com.monopoly.joueur.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.service.IJoueurService;
import com.monopoly.lancer.service.ILancersService;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.partie.service.IPartieService;
import com.monopoly.plateau.constantes.CasePlateau;
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
            if (CasePlateau.ALLER_EN_PRISON == joueur.caseJoueur()) {
                // TODO : gérer le cas quand le joueur est en prison :
                //  payer pour sortir, faire un double, ou utiliser carte
                //  Négocier avec d'autres joueurs
                //  Construire
                return;
            }
            CasePlateau destination = getDestinationApresLancer(joueur, lancerDes);
            deplacementService.deplacerEtAppliquerEffetCase(joueur, destination);

    } while(joueur.peutRejouer());
}

public CasePlateau getDestinationApresLancer(Joueur joueur, LancerDes valeurLancerDes) {
    int positionArrivee = (joueur.position() + valeurLancerDes.getSomme()) % PLATEAU.size();
    return CasePlateau.depuisPosition(positionArrivee);
}
}
