package com.monopoly.plateau.model.proprietes;

import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.plateau.model.CasePlateau;

public enum CartesProprietesNonConstructibles implements CartePropriete {
    ELECTRICITE(
            CasePlateau.ELECTRICITE,
            Groupe.COMPAGNIE,
            150
    ),
    EAU(
            CasePlateau.EAU,
            Groupe.COMPAGNIE,
            150
    ),
    GARE_MONTPARNASSE(
            CasePlateau.GARE_MONTPARNASSE,
            Groupe.GARE,
            200
    ),
    GARE_DE_LYON(
            CasePlateau.GARE_DE_LYON,
            Groupe.GARE,
            200
    ),
    GARE_DU_NORD(
            CasePlateau.GARE_DU_NORD,
            Groupe.GARE,
            200
    ),
    GARE_SAINT_LAZARE(
            CasePlateau.GARE_SAINT_LAZARE,
            Groupe.GARE,
            200
    );

    private final CasePlateau casePlateau;
    private final Groupe groupe;
    private final int valeur;

    CartesProprietesNonConstructibles(CasePlateau casePlateau, Groupe groupe, int valeur) {
        this.casePlateau = casePlateau;
        this.groupe = groupe;
        this.valeur = valeur;
    }

    @Override
    public int valeur() {
        return valeur;
    }

    @Override
    public Groupe groupe() {
        return groupe;
    }

    @Override
    public CasePlateau casePlateau() {
        return casePlateau;
    }

    public int calculerLoyerCompagnie(int nombreDeProprietesDuGroupePossedees, LancerDes lancerDes) {
        if (nombreDeProprietesDuGroupePossedees == 1) {
            return lancerDes.somme() * 4;
        } else if (nombreDeProprietesDuGroupePossedees == 2) {
            return lancerDes.somme() * 10;
        } else {
            throw new IllegalStateException("Le nombre de propriétés du groupe Compagnie possédées doit être de 1 ou 2");
        }
    }

    public int calculerLoyerGare(int nombreDeProprietesDuGroupePossedees) {
        return (int) Math.pow(2, nombreDeProprietesDuGroupePossedees - 1) * 25;
    }
}
