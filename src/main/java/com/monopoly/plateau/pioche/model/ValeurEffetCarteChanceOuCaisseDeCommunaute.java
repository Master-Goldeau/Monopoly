package com.monopoly.plateau.pioche.model;

import com.monopoly.plateau.constantes.Case;

import java.util.Objects;
import java.util.Optional;

import static com.monopoly.exception.Messages.ERREUR_TYPE_CASE;
import static com.monopoly.exception.Messages.ERREUR_TYPE_INTEGER;
import static com.monopoly.exception.Messages.ERREUR_TYPE_VALEUR_EFFET_CARTE;

public record ValeurEffetCarteChanceOuCaisseDeCommunaute(Object valeur) {

    public static final ValeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_SERVICE_PUBLIC_LE_PLUS_PROCHE = new ValeurEffetCarteChanceOuCaisseDeCommunaute(null);
    public static final ValeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_ANNIVERSAIRE = new ValeurEffetCarteChanceOuCaisseDeCommunaute(null);

    public ValeurEffetCarteChanceOuCaisseDeCommunaute {
        verifierTypeValeur(valeur);
    }

    private static void verifierTypeValeur(Object valeur) {
        if (nEstNiNullNiCaseNiInteger(valeur)) {
            throw new IllegalArgumentException(ERREUR_TYPE_VALEUR_EFFET_CARTE);
        }
    }

    private static boolean nEstNiNullNiCaseNiInteger(Object valeur) {
        return !(Objects.isNull(valeur) || valeur instanceof Case || valeur instanceof Integer);
    }

    public int commeMontant() {
        return Optional.of(valeur)
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .orElseThrow(() -> new IllegalArgumentException(ERREUR_TYPE_INTEGER));
    }

    public Case commeDestination() {
        return Optional.of(valeur)
                .filter(Case.class::isInstance)
                .map(Case.class::cast)
                .orElseThrow(() -> new IllegalArgumentException(ERREUR_TYPE_CASE));
    }
}



