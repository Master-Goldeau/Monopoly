package com.monopoly.joueur.model;

import com.monopoly.plateau.constantes.Case;

import static com.monopoly.plateau.Constantes.NOMBRE_DOUBLES_CONSECUTIFS_POUR_PRISON;
import static com.monopoly.plateau.Constantes.SOMME_DE_DEPART;

public class Joueur {
    private final Pion pion;
    private Case caseJoueur;
    private int doubleConsecutifs;
    private int argent;
    private boolean possedeCarteLiberePrison;
    private boolean estEnPrison;
    private int nombreDeToursEnPrison;

    // Constructeurs
    public Joueur(Pion pion) {
        this.caseJoueur = Case.DEPART;
        this.doubleConsecutifs = 0;
        this.argent = SOMME_DE_DEPART;
        this.possedeCarteLiberePrison = false;
        this.pion = pion;
        this.estEnPrison = false;
        this.nombreDeToursEnPrison = 0;
    }

    //Constructeur pour les TU uniquement
    public Joueur(Case position) {
        this.caseJoueur = position;
        this.doubleConsecutifs = 0;
        this.argent = SOMME_DE_DEPART;
        this.possedeCarteLiberePrison = false;
        this.pion = Pion.CHAUSSURE;
    }

    // Getters et Setters
    public Pion pion() {
        return pion;
    }

    public Case caseJoueur() {
        return caseJoueur;
    }

    public int position() {
        return this.caseJoueur.positionSurPlateau();
    }

    public void setCaseJoueur(Case caseJoueur) {
        this.caseJoueur = caseJoueur;
    }

    public int doubleConsecutifs() {
        return doubleConsecutifs;
    }

    public void setDoubleConsecutifs(int doubleConsecutifs) {
        this.doubleConsecutifs = doubleConsecutifs;
    }

    public boolean aTroisDoublesConsecutifs() {
        return this.doubleConsecutifs == NOMBRE_DOUBLES_CONSECUTIFS_POUR_PRISON;
    }

    public int argent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public void setPossedeCarteLiberePrison(boolean possedeCarteLiberePrison) {
        this.possedeCarteLiberePrison = possedeCarteLiberePrison;
    }

    public boolean estEnPrison() {
        return this.estEnPrison;
    }

    public void setEstEnPrison(boolean estEnPrison) {
        this.estEnPrison = estEnPrison;
    }

    public int nombreDeToursEnPrison() {
        return nombreDeToursEnPrison;
    }

    public void setNombreDeToursEnPrison(int nombreDeToursEnPrison) {
        this.nombreDeToursEnPrison = nombreDeToursEnPrison;
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

    public boolean peutRejouer() {
        return !this.estEnPrison && this.doubleConsecutifs > 0 &&
               this.doubleConsecutifs < NOMBRE_DOUBLES_CONSECUTIFS_POUR_PRISON;
    }
}
