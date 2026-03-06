package com.monopoly.plateau.constantes;

import com.monopoly.exception.MessagesErreur;
import com.monopoly.plateau.Constantes;

import static com.monopoly.plateau.constantes.TypeCase.AUCUN_OU_PRISON;
import static com.monopoly.plateau.constantes.TypeCase.BENEFICE;
import static com.monopoly.plateau.constantes.TypeCase.DEPLACEMENT;
import static com.monopoly.plateau.constantes.TypeCase.NEUTRE;
import static com.monopoly.plateau.constantes.TypeCase.PAIEMENT;
import static com.monopoly.plateau.constantes.TypeCase.PIOCHER;
import static com.monopoly.plateau.constantes.TypeCase.PROPRIETE;
import static com.monopoly.plateau.pioche.model.TypePiochable.CAISSE_DE_COMMUNAUTE;
import static com.monopoly.plateau.pioche.model.TypePiochable.CHANCE;

public enum CasePlateau {

    DEPART("Départ", BENEFICE, Constantes.SALAIRE_CASE_DEPART, 0),
    BELLEVILLE("Boulevard de Belleville", PROPRIETE, 60, 1),
    CAISSE_COMMUNAUTE_2(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, null, 2),
    LECOURBE("Rue Lecourbe", PROPRIETE, 60, 3),
    IMPOTS("Impôts sur le revenu", PAIEMENT, Constantes.IMPOTS_REVENUS, 4),

    GARE_MONTPARNASSE("Gare Montparnasse", PROPRIETE, 200, 5),
    VAUGIRARD("Rue de Vaugirard", PROPRIETE, 100, 6),
    CHANCE_7(CHANCE.name(), PIOCHER, null, 7),
    COURCELLES("Rue de Courcelles", PROPRIETE, 100, 8),
    REPUBLIQUE("Avenue de la République", PROPRIETE, 120, 9),

    SIMPLE_VISITE_PRISON("Simple visite / Prison", AUCUN_OU_PRISON, null, 10),
    VILLETTE("Boulevard de la Villette", PROPRIETE, 140, 11),
    ELECTRICITE("Compagnie d'électricité", PROPRIETE, 150, 12),
    NEUILLY("Avenue de Neuilly", PROPRIETE, 140, 13),
    PARADIS("Rue de Paradis", PROPRIETE, 160, 14),

    GARE_DE_LYON("Gare de Lyon", PROPRIETE, 200, 15),
    MOZART("Avenue Mozart", PROPRIETE, 180, 16),
    CAISSE_COMMUNAUTE_17(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, null, 17),
    SAINT_MICHEL("Boulevard Saint-Michel", PROPRIETE, 180, 18),
    PIGALLE("Place Pigalle", PROPRIETE, 200, 19),

    PARC_GRATUIT("Parc Gratuit", NEUTRE, null, 20),
    MONTAIGNE("Avenue Montaigne", PROPRIETE, 220, 21),
    CHANCE_22(CHANCE.name(), PIOCHER, null, 22),
    MALESHERBES("Boulevard Malesherbes", PROPRIETE, 220, 23),
    HENRI_MARTIN("Avenue Henri Martin", PROPRIETE, 240, 24),

    GARE_DU_NORD("Gare du Nord", PROPRIETE, 200, 25),
    SAINT_HONORE("Faubourg Saint-Honoré", PROPRIETE, 260, 26),
    BOURSE("Place de la Bourse", PROPRIETE, 260, 27),
    EAU("Compagnie des eaux", PROPRIETE, 150, 28),
    LA_FAYETTE("Rue la Fayette", PROPRIETE, 280, 29),

    ALLER_EN_PRISON("Aller en prison", DEPLACEMENT, null, 30),
    BRETEUIL("Avenue de Breteuil", PROPRIETE, 300, 31),
    FOCH("Avenue Foch", PROPRIETE, 300, 32),
    CAISSE_COMMUNAUTE_33(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, null, 33),
    CAPUCINES("Boulevard des Capucines", PROPRIETE, 320, 34),

    GARE_SAINT_LAZARE("Gare Saint-Lazare", PROPRIETE, 200, 35),
    CHANCE_36(CHANCE.name(), PIOCHER, null, 36),
    CHAMPS_ELYSEES("Avenue des Champs-Élysées", PROPRIETE, 350, 37),
    LUXE("Taxe de luxe", PAIEMENT, Constantes.TAXE_LUXE, 38),
    RUE_DE_LA_PAIX("Rue de la Paix", PROPRIETE, 400, 39);


    private final String nom;
    private final TypeCase typeCase;
    private final Integer montant;
    private final int position;

    CasePlateau(String nom, TypeCase typeCase, Integer montant, int position) {
        this.nom = nom;
        this.typeCase = typeCase;
        this.montant = montant;
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

    public boolean doitPiocher(){
        return typeCase == TypeCase.PIOCHER;
    }

    public String nom() {
        return nom;
    }

    public int montant() {
        return montant;
    }
}
