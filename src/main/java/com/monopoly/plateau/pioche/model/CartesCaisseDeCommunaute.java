package com.monopoly.plateau.pioche.model;

import com.monopoly.plateau.model.CasePlateau;

import static com.monopoly.plateau.pioche.model.ValeurEffetCarteChanceOuCaisseDeCommunaute.DEFINIR_ANNIVERSAIRE;

public enum CartesCaisseDeCommunaute implements Piochable {
    CAISSE_DE_VACANCES(
            ActionCarte.BENEFICE,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(100),
            """
                    Votre caisse de vacances vous rapporte.
                    Recevez 100€.
                    """
    ),
    ASSURANCE_VIE(
            ActionCarte.BENEFICE,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(100),
            """
                    Votre assurance vie vous rapporte.
                    Recevez 100€.
                    """
    ),
    FRAIS_SCOLARITE(
            ActionCarte.PAYER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(50),
            """
                    Frais de scolarité.
                    Payez 50€.
                    """
    ),
    REMBOURSEMENT_IMPOTS(
            ActionCarte.BENEFICE,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(20),
            """
                    Remboursement des impôts sur le revenu.
                    Recevez 20€.
                    """
    ),
    HOPITAL(
            ActionCarte.PAYER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(100),
            """
                    Frais d'hôpital.
                    Payez 100€.
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
    HONORAIRES_CONSULTATION(
            ActionCarte.BENEFICE,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(25),
            """
                    Honoraires de consultation.
                    Recevez 25€.
                    """
    ),
    ANNIVERSAIRE(
            ActionCarte.BENEFICE,
            DEFINIR_ANNIVERSAIRE,
            """
                    C'est votre anniversaire.
                    Recevez 10€ de chaque joueur.
                    """
    ),
    HERITAGE(
            ActionCarte.BENEFICE,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(100),
            """
                    Vous recevez un héritage mystérieux.
                    Recevez 100€.
                    """
    ),
    VENTE_STOCK(
            ActionCarte.BENEFICE,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(50),
            """
                    La vente de votre stock vous rapporte.
                    Recevez 50€.
                    """
    ),
    BEAUTE(
            ActionCarte.BENEFICE,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(10),
            """
                    Vous avez gagné le deuxième prix du concours de beauté.
                    Recevez 10€.
                    """
    ),
    REPARATIONS(
            ActionCarte.PAYER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(-25),
            """
                    Vous êtes évalué pour des travaux de voirie.
                    Pour chaque maison que vous possédez payez 40€.
                    Pour chaque hôtel que vous possédez payez 115€.
                    """
    ),
    AVANCEZ_CASE_DEPART(
            ActionCarte.DEPLACER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(CasePlateau.DEPART),
            """
                    Avancez jusqu'à la case Départ.
                    Recevez 200€.
                    """
    ),
    ERREUR_BANQUE(
            ActionCarte.BENEFICE,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(200),
            """
                    Erreur de la banque en votre faveur.
                    Recevez 200€.
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
    CHIOT(
            ActionCarte.PAYER,
            new ValeurEffetCarteChanceOuCaisseDeCommunaute(50),
            """
                    Vous adoptez un chiot.
                    Payez 50€.
                    """
    );

    private final ActionCarte actionCarte;
    private final ValeurEffetCarteChanceOuCaisseDeCommunaute valeurEffet;
    private final String description;

    CartesCaisseDeCommunaute(ActionCarte actionCarte, ValeurEffetCarteChanceOuCaisseDeCommunaute valeurEffet, String description) {
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
        return TypePiochable.CAISSE_DE_COMMUNAUTE;
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
