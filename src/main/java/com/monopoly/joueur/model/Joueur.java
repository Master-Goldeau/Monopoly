package com.monopoly.joueur.model;

import com.monopoly.plateau.model.CasePlateau;
import com.monopoly.proprietes.model.Groupe;
import com.monopoly.proprietes.model.Propriete;

import java.util.List;
import java.util.Map;

import static com.monopoly.plateau.Constantes.NOMBRE_DOUBLES_CONSECUTIFS_POUR_PRISON;
import static com.monopoly.plateau.Constantes.SOMME_DE_DEPART;

public class Joueur {
    private final Pion pion;
    private CasePlateau casePlateauJoueur;
    private int doubleConsecutifs;
    private int argent;
    private Map<Groupe, List<Propriete>> proprietes;
    private boolean possedeCarteLiberePrison;
    private boolean estEnPrison;
    private int nombreDeToursEnPrison;

    // Constructeurs
    public Joueur(Pion pion) {
        this.casePlateauJoueur = CasePlateau.DEPART;
        this.doubleConsecutifs = 0;
        this.argent = SOMME_DE_DEPART;
        this.possedeCarteLiberePrison = false;
        this.pion = pion;
        this.estEnPrison = false;
        this.nombreDeToursEnPrison = 0;
    }

    //Constructeur pour les TU uniquement
    public Joueur(CasePlateau position) {
        this.casePlateauJoueur = position;
        this.doubleConsecutifs = 0;
        this.argent = SOMME_DE_DEPART;
        this.possedeCarteLiberePrison = false;
        this.pion = Pion.CHAUSSURE;
    }

    // Getters et Setters
    public Pion pion() {
        return pion;
    }

    public CasePlateau caseJoueur() {
        return casePlateauJoueur;
    }

    public int position() {
        return this.casePlateauJoueur.positionSurPlateau();
    }

    public void setCaseJoueur(CasePlateau casePlateauJoueur) {
        this.casePlateauJoueur = casePlateauJoueur;
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

    public boolean aCarteLiberePrison() {
        return this.possedeCarteLiberePrison;
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


    public void recevoirArgent(int montant) {
        this.argent = this.argent + montant;
    }

    public void payer(int montant) {
        this.argent = this.argent - montant;
    }

    public boolean peutRejouer() {
        return this.doubleConsecutifs > 0 &&
               this.doubleConsecutifs < NOMBRE_DOUBLES_CONSECUTIFS_POUR_PRISON;
    }

    public void allerEnPrison() {
        setEstEnPrison(true);
        setCaseJoueur(CasePlateau.SIMPLE_VISITE_PRISON);
    }
}
