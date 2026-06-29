package com.monopoly.joueur.service.impl;

import com.monopoly.deplacement.model.Deplacement;
import com.monopoly.deplacement.service.IDeplacementService;
import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.service.IJoueurService;
import com.monopoly.lancer.service.ILancersService;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.partie.service.IPartieService;
import com.monopoly.partie.service.impl.PartieService;
import com.monopoly.plateau.model.CasePlateau;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.service.IPiochableService;
import com.monopoly.plateau.pioche.service.impl.PiochableService;
import com.monopoly.plateau.service.ICaseService;
import org.springframework.stereotype.Service;

@Service
public class JoueurService implements IJoueurService {

    private final ILancersService lancersService;
    private final IDeplacementService deplacementService;
    private final ICaseService caseService;
    private final IPartieService partieService;
    private final IPiochableService piochableService;


    public JoueurService(ILancersService lancersService,
                         IDeplacementService deplacementService,
                         ICaseService caseService,
                         IPiochableService piochableService,
                         IPartieService partieService) {
        this.lancersService = lancersService;
        this.deplacementService = deplacementService;
        this.caseService = caseService;
        this.piochableService = piochableService;
        this.partieService = partieService;
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

    @Override
    public void payerPourSortirDePrison(Joueur joueur) {
        joueur.payer(50);
        joueur.sortirDePrison();
    }

    @Override
    public void sortirDePrisonAvecCarte(Joueur joueur) {
        if(joueur.possedeCarteLiberePrison()){
            Piochable carteUtilisee = joueur.utiliserCarteLiberePrison();
            piochableService.remettreCarteDansPioche(carteUtilisee, partieService.getPartieEnCours());
            joueur.sortirDePrison();
        }
    }
}
