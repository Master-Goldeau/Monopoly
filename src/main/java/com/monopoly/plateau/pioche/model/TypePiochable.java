package com.monopoly.plateau.pioche.model;

public enum TypePiochable {
    CHANCE,
    CAISSE_DE_COMMUNAUTE;


    public static TypePiochable depuisNom(String nom) {
        for (TypePiochable type : TypePiochable.values()) {
            if (type.name().equalsIgnoreCase(nom)) {
                return type;
            }
        }
        throw new IllegalArgumentException("TypePiochable inconnu : " + nom);
    }
}

