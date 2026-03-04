package com.monopoly.plateau.constantes;

import java.util.Optional;

import static com.monopoly.plateau.constantes.TypeCase.*;
import static com.monopoly.plateau.pioche.model.TypePiochable.CAISSE_DE_COMMUNAUTE;
import static com.monopoly.plateau.pioche.model.TypePiochable.CHANCE;

public enum Case {

    DEPART("Départ", ARGENT, 200, null,0),
    BELLEVILLE("Boulevard de Belleville", PROPRIETE, 60, Groupe.MARRON,1),
    CAISSE_COMMUNAUTE_2(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, null, null,2),
    LECOURBE("Rue Lecourbe", PROPRIETE, 60, Groupe.MARRON,3),
    IMPOTS("Impôts sur le revenu", ARGENT, -200, null,4),

    GARE_MONTPARNASSE("Gare Montparnasse", PROPRIETE, 200, Groupe.GARE,5),
    VAUGIRARD("Rue de Vaugirard", PROPRIETE, 100, Groupe.BLEU_CIEL,6),
    CHANCE_7(CHANCE.name(), PIOCHER, null, null,7),
    COURCELLES("Rue de Courcelles", PROPRIETE, 100, Groupe.BLEU_CIEL,8),
    REPUBLIQUE("Avenue de la République", PROPRIETE, 120, Groupe.BLEU_CIEL,9),

    SIMPLE_VISITE_PRISON("Simple visite / Prison", AUCUN_OU_PRISON, null, null,10),
    VILLETTE("Boulevard de la Villette", PROPRIETE, 140, Groupe.VIOLET,11),
    ELECTRICITE("Compagnie d'électricité", PROPRIETE, 150, Groupe.COMPAGNIE, 12),
    NEUILLY("Avenue de Neuilly", PROPRIETE, 140, Groupe.VIOLET,13),
    PARADIS("Rue de Paradis", PROPRIETE, 160, Groupe.VIOLET,14),

    GARE_DE_LYON("Gare de Lyon", PROPRIETE, 200, Groupe.GARE,15),
    MOZART("Avenue Mozart", PROPRIETE, 180, Groupe.ORANGE,16),
    CAISSE_COMMUNAUTE_17(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, null, null,17),
    SAINT_MICHEL("Boulevard Saint-Michel", PROPRIETE, 180, Groupe.ORANGE,18),
    PIGALLE("Place Pigalle", PROPRIETE, 200, Groupe.ORANGE,19),

    PARC_GRATUIT("Parc Gratuit", NEUTRE, null, null,20),
    MONTAIGNE("Avenue Montaigne", PROPRIETE, 220, Groupe.ROUGE,21),
    CHANCE_22(CHANCE.name(), PIOCHER, null, null,22),
    MALESHERBES("Boulevard Malesherbes", PROPRIETE, 220, Groupe.ROUGE,23),
    HENRI_MARTIN("Avenue Henri Martin", PROPRIETE, 240, Groupe.ROUGE,24),

    GARE_DU_NORD("Gare du Nord", PROPRIETE, 200, Groupe.GARE,25),
    SAINT_HONORE("Faubourg Saint-Honoré", PROPRIETE, 260, Groupe.JAUNE,26),
    BOURSE("Place de la Bourse", PROPRIETE, 260, Groupe.JAUNE,27),
    EAU("Compagnie des eaux", PROPRIETE, 150, Groupe.COMPAGNIE,28),
    LA_FAYETTE("Rue la Fayette", PROPRIETE, 280, Groupe.JAUNE,29),

    ALLER_EN_PRISON("Aller en prison", DEPLACEMENT, null, null,30),
    BRETEUIL("Avenue de Breteuil", PROPRIETE, 300, Groupe.VERT, 31),
    FOCH("Avenue Foch", PROPRIETE, 300, Groupe.VERT,32),
    CAISSE_COMMUNAUTE_33(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, null, null,33),
    CAPUCINES("Boulevard des Capucines", PROPRIETE, 320, Groupe.VERT,34),

    GARE_SAINT_LAZARE("Gare Saint-Lazare", PROPRIETE, 200, Groupe.GARE,35),
    CHANCE_36(CHANCE.name(), PIOCHER, null, null,36),
    CHAMPS_ELYSEES("Avenue des Champs-Élysées", PROPRIETE, 350, Groupe.BLEU,37),
    LUXE("Taxe de luxe", ARGENT, -100, null,38),
    RUE_DE_LA_PAIX("Rue de la Paix", PROPRIETE, 400, Groupe.BLEU,39);


    private final String nom;
    private final TypeCase typeCase;
    private final Integer montant;
    private final Groupe groupe;
    private final int position;

    Case(String nom, TypeCase typeCase, Integer montant, Groupe groupe, int position) {
        this.nom = nom;
        this.typeCase = typeCase;
        this.montant = montant;
        this.groupe = groupe;
        this.position = position;
    }

    public static Case depuisPosition(int i) {
        return java.util.Arrays.stream(Case.values())
                .filter(c -> c.position == i)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Aucune case à la position " + i));
    }

    public int positionSurPlateau() {
        return position;
    }

    public boolean doitPiocher(){
        return typeCase == TypeCase.PIOCHER;
    }

    public String getNom() {
        return nom;
    }

    public Optional<Groupe> getGroupe() {
        return Optional.ofNullable(groupe);
    }

    public Optional<Integer> getMontant() {
        return Optional.ofNullable(montant);
    }
}
