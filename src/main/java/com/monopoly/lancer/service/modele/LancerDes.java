package com.monopoly.lancer.service.modele;

public record LancerDes(Des de1, Des de2) {

    public int getSomme() {
        return de1.valeur() + de2.valeur();
    }
    public boolean estUnDouble() {
        return de1.valeur() == de2.valeur();
    }
}
