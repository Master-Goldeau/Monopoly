package com.monopoly.plateau.model;

import com.monopoly.exception.MessagesErreur;

import static com.monopoly.plateau.model.TypeCase.AUCUN_OU_PRISON;
import static com.monopoly.plateau.model.TypeCase.BENEFICE;
import static com.monopoly.plateau.model.TypeCase.NEUTRE;
import static com.monopoly.plateau.model.TypeCase.PAIEMENT;
import static com.monopoly.plateau.model.TypeCase.PIOCHER;
import static com.monopoly.plateau.model.TypeCase.PROPRIETE;
import static com.monopoly.plateau.pioche.model.TypePiochable.CAISSE_DE_COMMUNAUTE;
import static com.monopoly.plateau.pioche.model.TypePiochable.CHANCE;

public enum CasePlateau {

    DEPART("Départ", BENEFICE, 0),
    BELLEVILLE("Boulevard de Belleville", PROPRIETE, 1),
    CAISSE_COMMUNAUTE_2(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, 2),
    LECOURBE("Rue Lecourbe", PROPRIETE, 3),
    IMPOTS("Impôts sur le revenu", PAIEMENT, 4),

    GARE_MONTPARNASSE("Gare Montparnasse", PROPRIETE, 5),
    VAUGIRARD("Rue de Vaugirard", PROPRIETE, 6),
    CHANCE_7(CHANCE.name(), PIOCHER, 7),
    COURCELLES("Rue de Courcelles", PROPRIETE, 8),
    REPUBLIQUE("Avenue de la République", PROPRIETE, 9),

    SIMPLE_VISITE_PRISON("Simple visite / Prison", AUCUN_OU_PRISON, 10),
    VILLETTE("Boulevard de la Villette", PROPRIETE, 11),
    ELECTRICITE("Compagnie d'électricité", PROPRIETE, 12),
    NEUILLY("Avenue de Neuilly", PROPRIETE, 13),
    PARADIS("Rue de Paradis", PROPRIETE, 14),

    GARE_DE_LYON("Gare de Lyon", PROPRIETE, 15),
    MOZART("Avenue Mozart", PROPRIETE, 16),
    CAISSE_COMMUNAUTE_17(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, 17),
    SAINT_MICHEL("Boulevard Saint-Michel", PROPRIETE, 18),
    PIGALLE("Place Pigalle", PROPRIETE, 19),

    PARC_GRATUIT("Parc Gratuit", NEUTRE, 20),
    MATIGNON("Avenue Montaigne", PROPRIETE, 21),
    CHANCE_22(CHANCE.name(), PIOCHER, 22),
    MALESHERBES("Boulevard Malesherbes", PROPRIETE, 23),
    HENRI_MARTIN("Avenue Henri Martin", PROPRIETE, 24),

    GARE_DU_NORD("Gare du Nord", PROPRIETE, 25),
    SAINT_HONORE("Faubourg Saint-Honoré", PROPRIETE, 26),
    BOURSE("Place de la Bourse", PROPRIETE, 27),
    EAU("Compagnie des eaux", PROPRIETE, 28),
    LA_FAYETTE("Rue la Fayette", PROPRIETE, 29),

    ALLER_EN_PRISON("Aller en prison", TypeCase.ALLER_EN_PRISON, 30),
    BRETEUIL("Avenue de Breteuil", PROPRIETE, 31),
    FOCH("Avenue Foch", PROPRIETE, 32),
    CAISSE_COMMUNAUTE_33(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, 33),
    CAPUCINES("Boulevard des Capucines", PROPRIETE, 34),

    GARE_SAINT_LAZARE("Gare Saint-Lazare", PROPRIETE, 35),
    CHANCE_36(CHANCE.name(), PIOCHER, 36),
    CHAMPS_ELYSEES("Avenue des Champs-Élysées", PROPRIETE, 37),
    LUXE("Taxe de luxe", PAIEMENT, 38),
    RUE_DE_LA_PAIX("Rue de la Paix", PROPRIETE, 39);


    private final String nom;
    private final TypeCase typeCase;
    private final int position;

    CasePlateau(String nom, TypeCase typeCase, int position) {
        this.nom = nom;
        this.typeCase = typeCase;
        this.position = position;
    }

    public static CasePlateau depuisPosition(int i) {
        return java.util.Arrays.stream(CasePlateau.values())
                .filter(c -> c.position == i)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessagesErreur.AUCUNE_CASE_TROUVEE(i)));
    }

    public TypeCase type() {
        return typeCase;
    }

    public int positionSurPlateau() {
        return position;
    }

    public String nom() {
        return nom;
    }
}
