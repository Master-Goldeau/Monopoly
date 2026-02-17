package com.monopoly.lancer.service.modele;

import java.util.concurrent.ThreadLocalRandom;

public record Des(
        int valeur
) {
    public Des {
        final int VALEUR_MAX = 6;
        final int VALEUR_MIN = 1;
        if (valeur < VALEUR_MIN || valeur > VALEUR_MAX) {
            throw new IllegalArgumentException("La valeur d'un dés doit être comprise entre 1 et 6.");
        }
    }

    public static Des lancer() {
        final int VALEUR_MINIMALE = 1;
        final int VALEUR_MAXIMALE_EXCLUE = 7;
        int valeur = ThreadLocalRandom.current().nextInt(VALEUR_MINIMALE, VALEUR_MAXIMALE_EXCLUE);
        return new Des(valeur);
    }
}
