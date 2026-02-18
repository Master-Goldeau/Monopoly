package com.monopoly.plateau.modele.constantes;

import static com.monopoly.plateau.modele.constantes.TypeCase.*;

public enum Case {
    DEPART("Départ", RECEVOIR, 200, null),

    CAISSE_COMMUNAUTE("Caisse de communauté", PIOCHER, null, null),
    CHANCE("Chance", PIOCHER, null, null),

    IMPOTS("Impôts sur le revenu", PAYER, -200, null),


    SIMPLE_VISITE_PRISON("Simple visite / Prison", AUCUN_OU_PRISON, null, null),
    PARC_GRATUIT("Parc Gratuit", DEPLACEMENT, null, null),

    ALLER_EN_PRISON("Aller en prison", DEPLACEMENT, null, null),
    LUXE("Taxe de luxe", PAYER, -100, null),

    BELLEVILLE("Boulevard de Belleville", PROPRIETE, 60, Groupe.MARRON),
    LECOURBE("Rue Lecourbe", PROPRIETE, 60, Groupe.MARRON),

    VAUGIRARD("Rue de Vaugirard", PROPRIETE, 100, Groupe.BLEU_CIEL),
    COURCELLES("Rue de Courcelles", PROPRIETE, 100, Groupe.BLEU_CIEL),
    REPUBLIQUE("Avenue de la République", PROPRIETE, 120, Groupe.BLEU_CIEL),

    VILLETTE("Boulevard de la Villette", PROPRIETE, 140, Groupe.VIOLET),
    NEUILLY("Avenue de Neuilly", PROPRIETE, 140, Groupe.VIOLET),
    PARADIS("Rue de Paradis", PROPRIETE, 160, Groupe.VIOLET),

    MOZART("Avenue Mozart", PROPRIETE, 180, Groupe.ORANGE),
    SAINT_MICHEL("Boulevard Saint-Michel", PROPRIETE, 180, Groupe.ORANGE),
    PIGALLE("Place Pigalle", PROPRIETE, 200, Groupe.ORANGE),

    MONTAIGNE("Avenue Montaigne", PROPRIETE, 220, Groupe.ROUGE),
    MALESHERBES("Boulevard Malesherbes", PROPRIETE, 220, Groupe.ROUGE),
    HENRI_MARTIN("Avenue Henri Martin", PROPRIETE, 240, Groupe.ROUGE),

    SAINT_HONORE("Faubourg Saint-Honoré", PROPRIETE, 260, Groupe.JAUNE),
    BOURSE("Place de la Bourse", PROPRIETE, 260, Groupe.JAUNE),
    LA_FAYETTE("Rue la Fayette", PROPRIETE, 280, Groupe.JAUNE),

    BRETEUIL("Avenue de Breteuil", PROPRIETE, 300, Groupe.VERT),
    FOCH("Avenue Foch", PROPRIETE, 300, Groupe.VERT),
    CAPUCINES("Boulevard des Capucines", PROPRIETE, 320, Groupe.VERT),

    CHAMPS_ELYSEES("Avenue des Champs-Élysées", PROPRIETE, 350, Groupe.BLEU),
    PAIX("Rue de la Paix", PROPRIETE, 400, Groupe.BLEU),

    GARE_MONTPARNASSE("Gare Montparnasse", PROPRIETE, 200, Groupe.GARE),
    GARE_DU_NORD("Gare du Nord", PROPRIETE, 200, Groupe.GARE),
    GARE_DE_LYON("Gare de Lyon", PROPRIETE, 200, Groupe.GARE),
    GARE_SAINT_LAZARE("Gare Saint-Lazare", PROPRIETE, 200, Groupe.GARE),

    ELECTRICITE("Compagnie d'électricité", PROPRIETE, 150, Groupe.COMPAGNIE),
    EAU("Compagnie des eaux", PROPRIETE, 150, Groupe.COMPAGNIE)
    ;
    private final String nom;
    private final TypeCase typeCase;
    private final Integer montant;
    private final Groupe groupe;

    Case(String nom, TypeCase typeCase, Integer montant, Groupe groupe) {
        this.nom = nom;
        this.typeCase = typeCase;
        this.montant = montant;
        this.groupe = groupe;
    }
}
