package com.monopoly.partie.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;
import com.monopoly.partie.service.IPartieService;
import com.monopoly.plateau.pioche.model.CartesCaisseDeCommunaute;
import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.service.IPiochableService;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class PartieService implements IPartieService {

    private Partie partieEnCours;
    private final IPiochableService piochableService;

    public PartieService(IPiochableService piochableService) {
        this.piochableService = piochableService;
    }

    @Override
    public Partie initialiserPartie(Queue<Joueur> joueurs) {
        this.partieEnCours = new Partie(
                joueurs.peek(),
                joueurs,
                piochableService.initialiserPioche(CartesChance.class),
                piochableService.initialiserPioche(CartesCaisseDeCommunaute.class)
        );
        return this.partieEnCours;
    }

    @Override
    public Partie getPartieEnCours() {
        return this.partieEnCours;
    }

}
