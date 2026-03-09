package com.monopoly.plateau.model.proprietes;

public enum Groupe {
    MARRON(2),
    GARE(4),
    BLEU_CIEL(3),
    VIOLET(3),
    COMPAGNIE(2),
    ORANGE(3),
    ROUGE(3),
    JAUNE(3),
    VERT(3),
    BLEU(2);

    private final int nombreDeProprietes;

    Groupe(int nombreDeProprietes) {
        this.nombreDeProprietes = nombreDeProprietes;
    }

    public int nombreDeProprietesDansLeGroupe() {
        return this.nombreDeProprietes;
    }
}
