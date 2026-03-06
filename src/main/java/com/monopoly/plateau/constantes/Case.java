package com.monopoly.plateau.constantes;

import com.monopoly.exception.MessagesErreur;

import java.util.Optional;

import static com.monopoly.plateau.constantes.TypeCase.*;
import static com.monopoly.plateau.pioche.model.TypePiochable.CAISSE_DE_COMMUNAUTE;
import static com.monopoly.plateau.pioche.model.TypePiochable.CHANCE;

public enum Case {

    DEPART("Départ", BENEFICE, 200, Groupe.AUCUN,0),
    BELLEVILLE("Boulevard de Belleville", PROPRIETE, 60, Groupe.MARRON,1),
    CAISSE_COMMUNAUTE_2(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, null, Groupe.AUCUN,2),
    LECOURBE("Rue Lecourbe", PROPRIETE, 60, Groupe.MARRON,3),
    IMPOTS("Impôts sur le revenu", PAIEMENT, -200, Groupe.AUCUN,4),

    GARE_MONTPARNASSE("Gare Montparnasse", PROPRIETE, 200, Groupe.GARE,5),
    VAUGIRARD("Rue de Vaugirard", PROPRIETE, 100, Groupe.BLEU_CIEL,6),
    CHANCE_7(CHANCE.name(), PIOCHER, null, Groupe.AUCUN,7),
    COURCELLES("Rue de Courcelles", PROPRIETE, 100, Groupe.BLEU_CIEL,8),
    REPUBLIQUE("Avenue de la République", PROPRIETE, 120, Groupe.BLEU_CIEL,9),

    SIMPLE_VISITE_PRISON("Simple visite / Prison", AUCUN_OU_PRISON, null, Groupe.AUCUN,10),
    VILLETTE("Boulevard de la Villette", PROPRIETE, 140, Groupe.VIOLET,11),
    ELECTRICITE("Compagnie d'électricité", PROPRIETE, 150, Groupe.COMPAGNIE, 12),
    NEUILLY("Avenue de Neuilly", PROPRIETE, 140, Groupe.VIOLET,13),
    PARADIS("Rue de Paradis", PROPRIETE, 160, Groupe.VIOLET,14),

    GARE_DE_LYON("Gare de Lyon", PROPRIETE, 200, Groupe.GARE,15),
    MOZART("Avenue Mozart", PROPRIETE, 180, Groupe.ORANGE,16),
    CAISSE_COMMUNAUTE_17(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, null, Groupe.AUCUN,17),
    SAINT_MICHEL("Boulevard Saint-Michel", PROPRIETE, 180, Groupe.ORANGE,18),
    PIGALLE("Place Pigalle", PROPRIETE, 200, Groupe.ORANGE,19),

    PARC_GRATUIT("Parc Gratuit", NEUTRE, null, Groupe.AUCUN,20),
    MONTAIGNE("Avenue Montaigne", PROPRIETE, 220, Groupe.ROUGE,21),
    CHANCE_22(CHANCE.name(), PIOCHER, null, Groupe.AUCUN,22),
    MALESHERBES("Boulevard Malesherbes", PROPRIETE, 220, Groupe.ROUGE,23),
    HENRI_MARTIN("Avenue Henri Martin", PROPRIETE, 240, Groupe.ROUGE,24),

    GARE_DU_NORD("Gare du Nord", PROPRIETE, 200, Groupe.GARE,25),
    SAINT_HONORE("Faubourg Saint-Honoré", PROPRIETE, 260, Groupe.JAUNE,26),
    BOURSE("Place de la Bourse", PROPRIETE, 260, Groupe.JAUNE,27),
    EAU("Compagnie des eaux", PROPRIETE, 150, Groupe.COMPAGNIE,28),
    LA_FAYETTE("Rue la Fayette", PROPRIETE, 280, Groupe.JAUNE,29),

    ALLER_EN_PRISON("Aller en prison", DEPLACEMENT, null, Groupe.AUCUN,30),
    BRETEUIL("Avenue de Breteuil", PROPRIETE, 300, Groupe.VERT, 31),
    FOCH("Avenue Foch", PROPRIETE, 300, Groupe.VERT,32),
    CAISSE_COMMUNAUTE_33(CAISSE_DE_COMMUNAUTE.name(), PIOCHER, null, Groupe.AUCUN,33),
    CAPUCINES("Boulevard des Capucines", PROPRIETE, 320, Groupe.VERT,34),

    GARE_SAINT_LAZARE("Gare Saint-Lazare", PROPRIETE, 200, Groupe.GARE,35),
    CHANCE_36(CHANCE.name(), PIOCHER, null, Groupe.AUCUN,36),
    CHAMPS_ELYSEES("Avenue des Champs-Élysées", PROPRIETE, 350, Groupe.BLEU,37),
    LUXE("Taxe de luxe", PAIEMENT, -100, Groupe.AUCUN,38),
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
                .orElseThrow(() -> new IllegalArgumentException(MessagesErreur.AUCUNE_CASE_TROUVEE(i)));
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

    public Optional<Groupe> groupe() {
        return Optional.ofNullable(groupe);
    }

    public Optional<Integer> montant() {
        return Optional.ofNullable(montant);
    }
}
