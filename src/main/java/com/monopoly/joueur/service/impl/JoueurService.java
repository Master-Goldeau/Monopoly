package com.monopoly.joueur.service.impl;

import com.monopoly.deplacement.model.Deplacement;
import com.monopoly.deplacement.service.impl.DeplacementService;
import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.service.IJoueurService;
import com.monopoly.lancer.service.ILancersService;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.plateau.model.CasePlateau;
import com.monopoly.plateau.service.ICaseService;
import org.springframework.stereotype.Service;

@Service
public class JoueurService implements IJoueurService {

    private final ILancersService lancersService;
    private final DeplacementService deplacementService;
    private final ICaseService caseService;

    public JoueurService(ILancersService lancersService,
                         DeplacementService deplacementService,
                         ICaseService caseService
    ) {
        this.lancersService = lancersService;
        this.deplacementService = deplacementService;
        this.caseService = caseService;
    }

    @Override
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
            Deplacement deplacement = deplacementService.deplacer(joueur, lancerDes);
            deplacementService.toucherSalaireSiPassageCaseDepart(joueur, deplacement);
            caseService.appliquerEffetDestination(joueur);

        } while (joueur.peutRejouer());
    }
}
