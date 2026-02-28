package com.monopoly.partie.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;
import com.monopoly.partie.service.IPartieService;
import com.monopoly.plateau.pioche.model.CartesCaisseDeCommunaute;
import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.service.IPiochableService;

import java.util.List;

public class PartieService implements IPartieService {

    private final IPiochableService piochableService;

    public PartieService(IPiochableService piochableService) {
        this.piochableService = piochableService;
    }

    @Override
    public Partie initialiserPartie(List<Joueur> joueurs) {
        // Exemple d'initialisation basique
        return new Partie(
                joueurs.getFirst(),
                joueurs,
                piochableService.initialiserPioche(CartesChance.class),
                piochableService.initialiserPioche(CartesCaisseDeCommunaute.class)
        );
    }

}
