package com.monopoly.plateau;

import com.monopoly.plateau.model.CasePlateau;

import java.util.List;

public final class Constantes {

    public static final int NOMBRE_DOUBLES_CONSECUTIFS_POUR_PRISON = 3;
    public static final int SOMME_DE_DEPART = 1500;
    public static final int SALAIRE_CASE_DEPART = 200;
    public static final int TAXE_LUXE = 100;
    public static final int IMPOTS_REVENUS = 200;

    public static final int NOMBRE_TOTAL_DE_CARTE_CHANCE_OU_CAISSE_DE_COMMUNAUTE = 16;

    public static final int NOMBRE_CASES_CHANCE_OU_CAISSE_DE_COMMUNAUTE = 3;

    public static final List<CasePlateau> PLATEAU = List.of(
            CasePlateau.DEPART, CasePlateau.BELLEVILLE, CasePlateau.CAISSE_COMMUNAUTE_2, CasePlateau.LECOURBE, CasePlateau.IMPOTS,
            CasePlateau.GARE_MONTPARNASSE, CasePlateau.VAUGIRARD, CasePlateau.CHANCE_7, CasePlateau.COURCELLES, CasePlateau.REPUBLIQUE,
            CasePlateau.SIMPLE_VISITE_PRISON, CasePlateau.VILLETTE, CasePlateau.ELECTRICITE, CasePlateau.NEUILLY, CasePlateau.PARADIS,
            CasePlateau.GARE_DE_LYON, CasePlateau.MOZART, CasePlateau.CAISSE_COMMUNAUTE_17, CasePlateau.SAINT_MICHEL, CasePlateau.PIGALLE,
            CasePlateau.PARC_GRATUIT, CasePlateau.MONTAIGNE, CasePlateau.CHANCE_22, CasePlateau.MALESHERBES, CasePlateau.HENRI_MARTIN,
            CasePlateau.GARE_DU_NORD, CasePlateau.SAINT_HONORE, CasePlateau.BOURSE, CasePlateau.EAU, CasePlateau.LA_FAYETTE,
            CasePlateau.ALLER_EN_PRISON, CasePlateau.BRETEUIL, CasePlateau.FOCH, CasePlateau.CAISSE_COMMUNAUTE_33, CasePlateau.CAPUCINES,
            CasePlateau.GARE_SAINT_LAZARE, CasePlateau.CHANCE_36, CasePlateau.CHAMPS_ELYSEES, CasePlateau.LUXE, CasePlateau.RUE_DE_LA_PAIX
    );

    private Constantes() {}
}
