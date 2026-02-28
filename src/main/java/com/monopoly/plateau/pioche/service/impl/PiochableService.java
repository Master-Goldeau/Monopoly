package com.monopoly.plateau.pioche.service.impl;

import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.service.IPiochableService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;


public class PiochableService implements IPiochableService {

    @Override
    public ArrayList<Piochable> initialiserPioche(Class<? extends Piochable> classePiochable) {
        Piochable[] valeurs = classePiochable.getEnumConstants();
        if (Objects.equals(CartesChance.class, classePiochable)) {
            valeurs = ajouterDeuxiemeCarteProchaineGareDansPiocheChance(valeurs);
        }
        ArrayList<Piochable> liste = new ArrayList<>(Arrays.asList(valeurs));
        Collections.shuffle(liste);
        return liste;
    }

    @Override
    public Piochable piocher(ArrayList<Piochable> pioche) {
        Piochable cartePiochee =  pioche.getFirst();
        pioche.removeFirst();
        pioche.addLast(cartePiochee);
        return cartePiochee;
    }

    private static Piochable[] ajouterDeuxiemeCarteProchaineGareDansPiocheChance(Piochable[] valeurs) {
        Piochable[] valeursEtGare = Arrays.copyOf(valeurs, valeurs.length + 1);
        valeursEtGare[valeurs.length] = CartesChance.PROCHAINE_GARE;
        return valeursEtGare;
    }


}
