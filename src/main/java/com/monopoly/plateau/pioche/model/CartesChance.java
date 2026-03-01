package com.monopoly.plateau.pioche.model;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;
import com.monopoly.plateau.constantes.Case;

import java.util.Objects;

public enum CartesChance implements Piochable {
    DIVIDENDE(ActionCarte.BENEFICE, new ValeurEffetCarteChanceOuCaisseDeCommunaute(50), "La banque vous verse un dividende de 50€."),
    AVANCEZ_CASE_DEPART(ActionCarte.DEPLACEMENT, new ValeurEffetCarteChanceOuCaisseDeCommunaute(Case.DEPART), "Avancez jusqu'à la case Départ. Recevez 200€."),
    ALLER_EN_PRISON(ActionCarte.DEPLACEMENT, new ValeurEffetCarteChanceOuCaisseDeCommunaute(Case.SIMPLE_VISITE_PRISON), "Allez en prison.\nAllez tout droit en prison\n\nNe passez pas par la case Départ.\nNe recevez pas 200€."),
    SERVICE_PUBLIC(
            ActionCarte.DEPLACEMENT,
            ValeurEffetCarteChanceOuCaisseDeCommunaute.DEFINIR_SERVICE_PUBLIC_LE_PLUS_PROCHE,
            "Avancez Jusqu'au prochain service public.\nS'il n'appartient à personne vous pouvez l'acheter à la banque.\nS'il appartient à un autre joueur, lancez les dés et payez 10 fois le montant du résultat au propriétaire. Si vous passez par la case Départ, recevez 200€."
    ),
    AVENUE_HENRI_MARTIN(ActionCarte.DEPLACEMENT, new ValeurEffetCarteChanceOuCaisseDeCommunaute(Case.HENRI_MARTIN), "Avances jusqu'à l’avenue Henri-Martin. Si vous passez par la case Départ, recevez 200€."),
    PRET_IMMOBILIER(ActionCarte.BENEFICE, new ValeurEffetCarteChanceOuCaisseDeCommunaute(150), "Votre prêt immobilier rapporte. Recevez 150€."),
    GARE_MONTPARNASSE(ActionCarte.DEPLACEMENT, new ValeurEffetCarteChanceOuCaisseDeCommunaute(Case.GARE_MONTPARNASSE), "Rendez-vous à la gare Montparnasse. Si vous passez par la case Départ, recevez 200€."),
    RUE_DE_LA_PAIX(ActionCarte.DEPLACEMENT, new ValeurEffetCarteChanceOuCaisseDeCommunaute(Case.RUE_DE_LA_PAIX), "Avancez jusqu'à la rue de la Paix."),
    REPARATIONS(ActionCarte.PAYER, new ValeurEffetCarteChanceOuCaisseDeCommunaute(25), "Faites des réparations sur toutes vos propriétés.\n Pour chaque maison payez 25€.\n Pour chaque hôtel payez 100€."),
    LIBERE_PRISON(ActionCarte.CONSERVER, new ValeurEffetCarteChanceOuCaisseDeCommunaute(null), "Vous êtes libéré de prison. Cette carte peut être conservée jusqu’à ce qu’elle soit utilisée ou vendue."),
    AVANCEZ_BOULEVARD_VILLETTE(ActionCarte.DEPLACEMENT, new ValeurEffetCarteChanceOuCaisseDeCommunaute(Case.VILLETTE), "Avancez jusqu’à la case Boulevard de la Villette."),
    PRESIDENT_CONSEIL(ActionCarte.PAYER, new ValeurEffetCarteChanceOuCaisseDeCommunaute(50), "Vous avez été élu président du conseil d’administration : payez 50€ à chaque joueur."),
    RECULEZ(ActionCarte.DEPLACEMENT, null, "Reculez de trois cases"),
    AMENDE_EXCES_VITESSE(ActionCarte.BENEFICE, new ValeurEffetCarteChanceOuCaisseDeCommunaute(15), "Amende pour excès de vitesse : payez 15€."),
    PROCHAINE_GARE(ActionCarte.DEPLACEMENT, null, "Avancez jusqu’à la prochaine gare.\nSi elle n'appartient à personne vous pouvez l'acheter à la banque. Si elle appartient à un joueur, payez au propriétaire le double du loyer auquel il a droit. Si vous passez par la case Départ, recevez 200€.");

    private final ActionCarte actionCarte;
    private final ValeurEffetCarteChanceOuCaisseDeCommunaute valeurEffet;
    private final String description;

    CartesChance(ActionCarte actionCarte, ValeurEffetCarteChanceOuCaisseDeCommunaute valeurEffet, String description) {
        this.actionCarte = actionCarte;
        this.valeurEffet = valeurEffet;
        this.description = description;
    }

    @Override
    public void appliquerEffet(Partie partieEnCours, Joueur joueur) {
        switch (actionCarte) {
            case BENEFICE -> joueur.recevoirArgent(this.valeurEffet.commeMontant());
            case PAYER -> joueur.payer(this.valeurEffet.commeMontant());
            case DEPLACEMENT -> definirDestination(joueur);

            case CONSERVER -> joueur.setPossedeCarteLiberePrison(true);
        }
    }

    private void definirDestination(Joueur joueur) {
        if (Objects.equals(this.valeurEffet, ValeurEffetCarteChanceOuCaisseDeCommunaute.DEFINIR_SERVICE_PUBLIC_LE_PLUS_PROCHE)) {
            joueur.deplacer(ValeurEffetCarteChanceOuCaisseDeCommunaute.definirProchainServicePublic(joueur).commeDestination());
        } else {
            joueur.deplacer(this.valeurEffet.commeDestination());
        }
    }
}
