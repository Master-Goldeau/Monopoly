package com.monopoly.joueur.model;

import com.monopoly.plateau.constantes.Case;

import static com.monopoly.plateau.Constantes.NOMBRE_DOUBLES_CONSECUTIFS_POUR_PRISON;
import static com.monopoly.plateau.Constantes.SOMME_DE_DEPART;

public class Joueur {
    private Pion pion;
    private Case caseJoueur;
    private int doubleConsecutifs;
    private int argent;
    private boolean possedeCarteLiberePrison;

    // Constructeurs
    public Joueur(Pion pion) {
        this.caseJoueur = Case.DEPART;
        this.doubleConsecutifs = 0;
        this.argent = SOMME_DE_DEPART;
        this.possedeCarteLiberePrison = false;
        this.pion = pion;
    }

    //Constructeur pour les TU uniquement
    public Joueur(Case position){
        this.caseJoueur = position;
        this.doubleConsecutifs = 0;
        this.argent = SOMME_DE_DEPART;
        this.possedeCarteLiberePrison = false;
        this.pion = Pion.CHAUSSURE;
    }

    // Getters et Setters
    public Pion getPion() {
        return pion;
    }

    public Case getCaseJoueur() {
        return caseJoueur;
    }

    public int getPosition() {
        return this.caseJoueur.getPositionSurPlateau();
    }

    public void setCaseJoueur(Case caseJoueur) {
        this.caseJoueur = caseJoueur;
    }

    public int getDoubleConsecutifs() {
        return doubleConsecutifs;
    }

    public void setDoubleConsecutifs(int doubleConsecutifs) {
        this.doubleConsecutifs = doubleConsecutifs;
    }

    public boolean aTroisDoublesConsecutifs() {
        return this.doubleConsecutifs == NOMBRE_DOUBLES_CONSECUTIFS_POUR_PRISON;
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public boolean isPossedeCarteLiberePrison() {
        return possedeCarteLiberePrison;
    }

    public void setPossedeCarteLiberePrison(boolean possedeCarteLiberePrison) {
        this.possedeCarteLiberePrison = possedeCarteLiberePrison;
    }

    // Méthodes métier
    public boolean aCarteLiberePrison() {
        return this.possedeCarteLiberePrison;
    }

    public void recevoirArgent(int montant) {
        this.argent = this.argent + montant;
    }

    public void payer(int montant) {
        this.argent = this.argent - montant;
    }

    public void deplacer(Case caseDestination) {
        this.caseJoueur = caseDestination;
    }
}
