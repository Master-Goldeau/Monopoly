package com.monopoly.plateau.pioche.model;

public sealed interface Piochable
        permits CartesChance, CartesCaisseDeCommunaute {
    String description();

    TypePiochable typePiochable();

    String name();

    ActionCarte actionCarte();

    ValeurEffetCarteChanceOuCaisseDeCommunaute valeurEffet();
}
