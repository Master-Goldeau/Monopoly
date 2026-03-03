package com.monopoly.plateau.pioche.model;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;
import com.monopoly.plateau.constantes.Case;

import java.util.Objects;
import java.util.Optional;

import static com.monopoly.exception.Messages.CASE_NON_CHANCE;
import static com.monopoly.exception.Messages.ERREUR_TYPE_CASE;
import static com.monopoly.exception.Messages.ERREUR_TYPE_INTEGER;
import static com.monopoly.exception.Messages.ERREUR_TYPE_VALEUR_EFFET_CARTE;

public record valeurEffetCarteChanceOuCaisseDeCommunaute(Object valeur) {
    // Variables statiques en haut du record, selon la convention Java
    public static final valeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_ANNIVERSAIRE = new valeurEffetCarteChanceOuCaisseDeCommunaute(null);
    public static final valeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_CASE_APRES_RECUL_TROIS_CASES = new valeurEffetCarteChanceOuCaisseDeCommunaute(null);
    public static final valeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_GARE_LA_PLUS_PROCHE = new valeurEffetCarteChanceOuCaisseDeCommunaute(null);
    public static final valeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_MONTANT_REPARATIONS = new valeurEffetCarteChanceOuCaisseDeCommunaute(null);
    public static final valeurEffetCarteChanceOuCaisseDeCommunaute DEFINIR_SERVICE_PUBLIC_LE_PLUS_PROCHE = new valeurEffetCarteChanceOuCaisseDeCommunaute(null);

    public valeurEffetCarteChanceOuCaisseDeCommunaute {
        verifierTypeValeur(valeur);
    }

    public static Case definirProchainServicePublic(Joueur joueurSurCaseChance) {
        return switch (joueurSurCaseChance.getCaseJoueur()) {
            case CHANCE_7, CHANCE_36 ->Case.ELECTRICITE;
            case CHANCE_22 -> Case.EAU;
            default -> throw new IllegalArgumentException(CASE_NON_CHANCE);
        };
    }

    public static Case trouverProchaineGare(Joueur joueurSurCaseChance) {
        return switch (joueurSurCaseChance.getCaseJoueur()) {
            case CHANCE_7 -> Case.GARE_DE_LYON;
            case CHANCE_36 -> Case.GARE_MONTPARNASSE;
            case CHANCE_22 -> Case.GARE_DU_NORD;
            default -> throw new IllegalArgumentException(CASE_NON_CHANCE);
        };
    }

    public static Case reculerDeTroisCases(Joueur joueurSurCaseChance) {
        return switch (joueurSurCaseChance.getCaseJoueur()) {
            case CHANCE_7 -> Case.IMPOTS;
            case CHANCE_22 -> Case.PIGALLE;
            case CHANCE_36 -> Case.CAISSE_COMMUNAUTE_33;
            default -> throw new IllegalArgumentException(CASE_NON_CHANCE);
        };
    }

    private static void verifierTypeValeur(Object valeur) {
        if (nEstNiNullNiCaseNiInteger(valeur)) {
            throw new IllegalArgumentException(ERREUR_TYPE_VALEUR_EFFET_CARTE);
        }
    }



    private static boolean nEstNiNullNiCaseNiInteger(Object valeur) {
        return !(Objects.isNull(valeur) || valeur instanceof Case || valeur instanceof Integer);
    }

    public static int calculerValeurReparation(Partie partie, TypePiochable typePiochable, Joueur joueur) {
        return 0; //TODO
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

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }
}
