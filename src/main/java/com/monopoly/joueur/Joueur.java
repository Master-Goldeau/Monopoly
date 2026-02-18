package com.monopoly.joueur;

import com.monopoly.lancer.service.modele.Des;
import com.monopoly.plateau.modele.constantes.Case;

import java.util.Objects;

import static com.monopoly.plateau.modele.Constantes.*;

public class Joueur {
    private int positionPlateau;
    private int doubleConsecutifs;

    public Joueur(int positionPlateau) {
        this.positionPlateau = positionPlateau;
    }

    public Joueur() {
        this.positionPlateau = 0;
    }

    public int lancerDes() {
        int valeurDes1 = Des.lancer().valeur();
        int valeurDes2 = Des.lancer().valeur();
        if (valeurDes1 == valeurDes2) {
            this.doubleConsecutifs++;
        } else {
            this.doubleConsecutifs = 0;
        }
        return valeurDes1 + valeurDes2;
    }

    public void deplacer(int valeurLancerDes) {
        if (NOMBRE_DOUBLES_CONSECUTIFS_POUR_PRISON == this.doubleConsecutifs) {
            this.positionPlateau = PLATEAU.indexOf(Case.SIMPLE_VISITE_PRISON);
            this.doubleConsecutifs = 0;
        } else {
            this.positionPlateau = (this.positionPlateau + valeurLancerDes) % PLATEAU.size();
        }
    }

    public void deplacer(Case destination) {
        if (Objects.equals(Case.ALLER_EN_PRISON, destination)) {
            this.positionPlateau = PLATEAU.indexOf(Case.SIMPLE_VISITE_PRISON);
        } else {
            this.positionPlateau = PLATEAU.indexOf(destination);
        }
    }

    public int getPositionPlateau() {
        return this.positionPlateau;
    }


}
