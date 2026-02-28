package com.monopoly.plateau.pioche.service.impl;

import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.service.IPiochableService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class PiochableService implements IPiochableService {

    @Override
    public List<Piochable> initialiserPioche(Class<? extends Piochable> classePiochable) {
        Piochable[] valeurs = classePiochable.getEnumConstants();
        return melangerPioche(valeurs);
    }

    @Override
    public Piochable piocher(List<Piochable> pioche) {
        return null;
    }


    private List<Piochable> melangerPioche(Piochable[] pioche) {
        List<Piochable> liste = Arrays.asList(pioche);
        Collections.shuffle(liste);
        return liste;
    }



}
