package com.monopoly.plateau.pioche.model;

import com.monopoly.plateau.constantes.CasePlateau;

import static com.monopoly.plateau.pioche.model.ValeurEffetCarteChanceOuCaisseDeCommunaute.DEFINIR_CASE_APRES_RECUL_TROIS_CASES;
import static com.monopoly.plateau.pioche.model.ValeurEffetCarteChanceOuCaisseDeCommunaute.DEFINIR_GARE_LA_PLUS_PROCHE;
import static com.monopoly.plateau.pioche.model.ValeurEffetCarteChanceOuCaisseDeCommunaute.DEFINIR_SERVICE_PUBLIC_LE_PLUS_PROCHE;

public enum CartesChance implements Piochable {
    DIVIDENDE(
            ActionCarte.BENEFICE,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(50),
            """
                    La banque vous verse un dividende de 50€.
                    """
    ),
    CASE_DEPART(
            ActionCarte.DEPLACER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(CasePlateau.DEPART),
            """
                    Avancez jusqu'à la case Départ.
                    Recevez 200€.
                    """
    ),
    ALLER_EN_PRISON(
            ActionCarte.PRISON,
            null,
            """
                    Allez en prison.
                    Allez tout droit en prison
                    
                    Ne passez pas par la case Départ.
                    Ne recevez pas 200€.
                    """
    ),
    SERVICE_PUBLIC(
            ActionCarte.DEPLACER,
            DEFINIR_SERVICE_PUBLIC_LE_PLUS_PROCHE,
            """
                    Avancez Jusqu'au prochain service public.
                    S'il n'appartient à personne vous pouvez l'acheter à la banque.
                    S'il appartient à un autre joueur, lancez les dés et payez 10 fois le montant du résultat au propriétaire.
                    Si vous passez par la case Départ, recevez 200€.
                    """
    ),
    AVENUE_HENRI_MARTIN(
            ActionCarte.DEPLACER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(CasePlateau.HENRI_MARTIN),
            """
                    Avancez jusqu'à l’avenue Henri-Martin.
                    Si vous passez par la case Départ, recevez 200€.
                    """
    ),
    PRET_IMMOBILIER(
            ActionCarte.BENEFICE,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(150),
            """
                    Votre prêt immobilier rapporte.
                    Recevez 150€.
                    """
    ),
    GARE_MONTPARNASSE(
            ActionCarte.DEPLACER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(CasePlateau.GARE_MONTPARNASSE),
            """
                    Rendez-vous à la gare Montparnasse.
                    Si vous passez par la case Départ, recevez 200€.
                    """
    ),
    RUE_DE_LA_PAIX(
            ActionCarte.DEPLACER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(CasePlateau.RUE_DE_LA_PAIX),
            """
                    Avancez jusqu'à la rue de la Paix.
                    """
    ),
    REPARATIONS(
            ActionCarte.PAYER,
            ValeurEffetCarteChanceOuCaisseDeCommunaute.DEFINIR_MONTANT_REPARATIONS, //TODO
            """
                    Faites des réparations sur toutes vos propriétés.
                    Pour chaque maison payez 25€.
                    Pour chaque hôtel payez 100€.
                    """
    ),
    LIBERE_PRISON(
            ActionCarte.CONSERVER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(0),
            """
                    Vous êtes libéré de prison.
                    Cette carte peut être conservée jusqu’à ce qu’elle soit utilisée ou vendue.
                    """
    ),
    BOULEVARD_VILLETTE(
            ActionCarte.DEPLACER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(CasePlateau.VILLETTE),
            """
                    Avancez jusqu’à la case Boulevard de la Villette.
                    """
    ),
    PRESIDENT_CONSEIL(
            ActionCarte.PAYER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(50), //TODO
            """
                    Vous avez été élu président du conseil d’administration.
                    Payez 50€ à chaque joueur.
                    """
    ),
    RECULEZ(
            ActionCarte.DEPLACER,
            DEFINIR_CASE_APRES_RECUL_TROIS_CASES,
            """
                    Reculez de trois cases
                    """
    ),
    AMENDE_EXCES_VITESSE(
            ActionCarte.BENEFICE,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(15),
            """
                    Amende pour excès de vitesse.
                    Payez 15€.
                    """
    ),
    PROCHAINE_GARE(
            ActionCarte.DEPLACER,
            DEFINIR_GARE_LA_PLUS_PROCHE,
            """
                    Avancez jusqu’à la prochaine gare.
                    Si elle n'appartient à personne vous pouvez l'acheter à la banque.
                    Si elle appartient à un joueur, payez au propriétaire le double du loyer auquel il a droit.
                    Si vous passez par la case Départ, recevez 200€.
                    """
    );

    // Variables d'instance
    private final ActionCarte actionCarte;
    private final ValeurEffetCarteChanceOuCaisseDeCommunaute valeurEffet;
    private final String description;

    // Constructeur
    CartesChance(ActionCarte actionCarte, ValeurEffetCarteChanceOuCaisseDeCommunaute valeurEffet, String description) {
        this.actionCarte = actionCarte;
        this.valeurEffet = valeurEffet;
        this.description = description;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public TypePiochable typePiochable() {
        return TypePiochable.CHANCE;
    }

    @Override
    public ActionCarte actionCarte() {
        return this.actionCarte;
    }

    @Override
    public ValeurEffetCarteChanceOuCaisseDeCommunaute valeurEffet() {
        return this.valeurEffet;
    }
}
