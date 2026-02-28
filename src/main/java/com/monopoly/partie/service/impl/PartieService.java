package com.monopoly.partie.service.impl;

import com.monopoly.partie.model.Partie;
import com.monopoly.partie.service.IPartieService;
import com.monopoly.plateau.pioche.service.IPiochableService;

public class PartieService implements IPartieService {

    private final IPiochableService piochableService;

    public PartieService(IPiochableService piochableService) {
        this.piochableService = piochableService;
    }


    @Override
    public Partie initialiserPartie(int nombreDeJoueurs) {
        return null;
    }

}
