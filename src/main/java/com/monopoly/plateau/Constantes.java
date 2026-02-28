package com.monopoly.plateau;

import com.monopoly.plateau.constantes.Case;

import java.util.List;

public final class Constantes {

    public static final String CAISSE_DE_COMMUNAUTE = "Caisse de communauté";
    public static final String CHANCE = "Chance";

    public static final int NOMBRE_DOUBLES_CONSECUTIFS_POUR_PRISON = 3;
    public static final int SOMME_DE_DEPART = 1500;

    public static final int NOMBRE_TOTAL_DE_CARTE_CHANCE_OU_CAISSE_DE_COMMUNAUTE = 16;

    public static final int NOMBRE_CASES_CHANCE_OU_CAISSE_DE_COMMUNAUTE = 3;

    public static final List<Case> PLATEAU = List.of(
            Case.DEPART, Case.BELLEVILLE, Case.CAISSE_COMMUNAUTE_2, Case.LECOURBE, Case.IMPOTS,
            Case.GARE_MONTPARNASSE, Case.VAUGIRARD, Case.CHANCE_7, Case.COURCELLES, Case.REPUBLIQUE,
            Case.SIMPLE_VISITE_PRISON, Case.VILLETTE, Case.ELECTRICITE, Case.NEUILLY, Case.PARADIS,
            Case.GARE_DE_LYON, Case.MOZART, Case.CAISSE_COMMUNAUTE_17, Case.SAINT_MICHEL, Case.PIGALLE,
            Case.PARC_GRATUIT, Case.MONTAIGNE, Case.CHANCE_22, Case.MALESHERBES, Case.HENRI_MARTIN,
            Case.GARE_DU_NORD, Case.SAINT_HONORE, Case.BOURSE, Case.EAU, Case.LA_FAYETTE,
            Case.ALLER_EN_PRISON, Case.BRETEUIL, Case.FOCH, Case.CAISSE_COMMUNAUTE_33, Case.CAPUCINES,
            Case.GARE_SAINT_LAZARE, Case.CHANCE_36, Case.CHAMPS_ELYSEES, Case.LUXE, Case.RUE_DE_LA_PAIX
    );

    private Constantes() {}
}
