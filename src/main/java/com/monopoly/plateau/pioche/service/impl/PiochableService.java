package com.monopoly.plateau.pioche.service.impl;

import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.service.IPiochableService;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Queue;

@Service
public class PiochableService implements IPiochableService {

    @Override
    public Queue<Piochable> initialiserPioche(Class<? extends Piochable> classePiochable) {
        Piochable[] valeurs = classePiochable.getEnumConstants();
        if (Objects.equals(CartesChance.class, classePiochable)) {
            valeurs = ajouterDeuxiemeCarteProchaineGareDansPiocheChance(valeurs);
        }
        Collections.shuffle(Arrays.asList(valeurs));
        return new ArrayDeque<>(Arrays.asList(valeurs));
    }

    @Override
    public Piochable piocher(Queue<Piochable> pioche) {
        Piochable cartePiochee =  pioche.peek();
        pioche.poll();
        pioche.offer(cartePiochee);
        return cartePiochee;
    }

    private static Piochable[] ajouterDeuxiemeCarteProchaineGareDansPiocheChance(Piochable[] valeurs) {
        Piochable[] valeursEtGare = Arrays.copyOf(valeurs, valeurs.length + 1);
        valeursEtGare[valeurs.length] = CartesChance.PROCHAINE_GARE;
        return valeursEtGare;
    }


}
