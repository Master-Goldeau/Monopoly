package com.monopoly.plateau.pioche.model;

import com.monopoly.exception.MessagesErreur;
import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;
import com.monopoly.plateau.constantes.CasePlateau;

import java.util.Optional;

public record ValeurEffetCarteChanceOuCaisseDeCommunaute(Object valeur) {

    private enum ValeurEffetACalculer {
        DEFINIR_ANNIVERSAIRE,
        DEFINIR_CASE_APRES_RECUL_TROIS_CASES,
        DEFINIR_GARE_LA_PLUS_PROCHE,
        DEFINIR_MONTANT_REPARATIONS,
        DEFINIR_SERVICE_PUBLIC_LE_PLUS_PROCHE
    }

    // Variables statiques en haut du record, selon la convention Java
    public static final ValeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_ANNIVERSAIRE =
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(ValeurEffetACalculer.DEFINIR_ANNIVERSAIRE);

    public static final ValeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_CASE_APRES_RECUL_TROIS_CASES =
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(ValeurEffetACalculer.DEFINIR_CASE_APRES_RECUL_TROIS_CASES);

    public static final ValeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_GARE_LA_PLUS_PROCHE =
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(ValeurEffetACalculer.DEFINIR_GARE_LA_PLUS_PROCHE);

    public static final ValeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_MONTANT_REPARATIONS =
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(ValeurEffetACalculer.DEFINIR_MONTANT_REPARATIONS);

    public static final ValeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_SERVICE_PUBLIC_LE_PLUS_PROCHE =
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(ValeurEffetACalculer.DEFINIR_SERVICE_PUBLIC_LE_PLUS_PROCHE);

    public ValeurEffetCarteChanceOuCaisseDeCommunaute {
        verifierTypeValeur(valeur);
    }

    public CasePlateau definirDestination(Joueur joueur) {
        if (this.equals(DEFINIR_SERVICE_PUBLIC_LE_PLUS_PROCHE)) {
            return definirProchainServicePublic(joueur);
        }
        if (this.equals(DEFINIR_GARE_LA_PLUS_PROCHE)) {
            return trouverProchaineGare(joueur);
        }
        if (this.equals(DEFINIR_CASE_APRES_RECUL_TROIS_CASES)) {
            return reculerDeTroisCases(joueur);
        }
        // Pour les autres cas, on suppose que la valeur est déjà une Case
        return commeDestination();
    }

    public static CasePlateau definirProchainServicePublic(Joueur joueurSurCaseChance) {
        return switch (joueurSurCaseChance.caseJoueur()) {
            case CHANCE_7, CHANCE_36 -> CasePlateau.ELECTRICITE;
            case CHANCE_22 -> CasePlateau.EAU;
            default -> throw new IllegalArgumentException(MessagesErreur.CASE_NON_CHANCE);
        };
    }

    public static CasePlateau trouverProchaineGare(Joueur joueurSurCaseChance) {
        return switch (joueurSurCaseChance.caseJoueur()) {
            case CHANCE_7 -> CasePlateau.GARE_DE_LYON;
            case CHANCE_36 -> CasePlateau.GARE_MONTPARNASSE;
            case CHANCE_22 -> CasePlateau.GARE_DU_NORD;
            default -> throw new IllegalArgumentException(MessagesErreur.CASE_NON_CHANCE);
        };
    }

    public static CasePlateau reculerDeTroisCases(Joueur joueurSurCaseChance) {
        return switch (joueurSurCaseChance.caseJoueur()) {
            case CHANCE_7 -> CasePlateau.IMPOTS;
            case CHANCE_22 -> CasePlateau.PIGALLE;
            case CHANCE_36 -> CasePlateau.CAISSE_COMMUNAUTE_33;
            default -> throw new IllegalArgumentException(MessagesErreur.CASE_NON_CHANCE);
        };
    }

    private static void verifierTypeValeur(Object valeur) {
        if (nEstNiValeurEffetACalculerNiCaseNiInteger(valeur)) {
            throw new IllegalArgumentException(MessagesErreur.ERREUR_TYPE_VALEUR_EFFET_CARTE);
        }
    }


    private static boolean nEstNiValeurEffetACalculerNiCaseNiInteger(Object valeur) {
        return !(valeur instanceof ValeurEffetACalculer || valeur instanceof CasePlateau || valeur instanceof Integer);
    }

    public static int calculerValeurReparation(Partie partie, Joueur joueur) {
        return 0; //TODO Utiliser la case du joueur pour savoir si tarifs Chance ou CaisseDeCommunaute
    }


    public int commeMontant() {
        return Optional.ofNullable(valeur)
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .orElseThrow(() -> new IllegalArgumentException(MessagesErreur.ERREUR_TYPE_INTEGER));
    }

    public CasePlateau commeDestination() {
        return Optional.ofNullable(valeur)
                .filter(CasePlateau.class::isInstance)
                .map(CasePlateau.class::cast)
                .orElseThrow(() -> new IllegalArgumentException(MessagesErreur.ERREUR_TYPE_CASE));
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }
}
