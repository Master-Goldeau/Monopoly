package com.monopoly.plateau.pioche.service.impl;

import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.service.IPiochableService;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Queue;

@Service
public class PiochableService implements IPiochableService {

    @Override
    public Queue<Piochable> initialiserPioche(Class<? extends Piochable> classePiochable) {
        ArrayList<Piochable> valeurs = new ArrayList<>(Arrays.asList(classePiochable.getEnumConstants()));
        if (Objects.equals(CartesChance.class, classePiochable)) {
            ajouterDeuxiemeCarteProchaineGareDansPiocheChance(valeurs);
        }
        Collections.shuffle(valeurs);
        return new ArrayDeque<>(valeurs);
    }

    @Override
    public Piochable piocher(Queue<Piochable> pioche) {
        Piochable cartePiochee = pioche.poll(); //Retourne la première carte de la pioche et la supprime de celle-ci
        pioche.offer(cartePiochee); //Ajoute la carte piochée à la fin de la pioche pour qu'elle puisse être piochée à nouveau plus tard
        return cartePiochee;
    }

    private static void ajouterDeuxiemeCarteProchaineGareDansPiocheChance(ArrayList<Piochable> valeurs) {
        valeurs.add(CartesChance.PROCHAINE_GARE);
    }


}
