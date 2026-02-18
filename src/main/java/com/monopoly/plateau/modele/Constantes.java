package com.monopoly.plateau.modele;

import com.monopoly.plateau.modele.constantes.Case;
import java.util.List;

public final class Constantes {

    public static final int NOMBRE_DOUBLES_CONSECUTIFS_POUR_PRISON = 3;

    public static final List<Case> PLATEAU = List.of(
            Case.DEPART, Case.BELLEVILLE, Case.CAISSE_COMMUNAUTE, Case.LECOURBE, Case.IMPOTS,
            Case.GARE_MONTPARNASSE, Case.VAUGIRARD, Case.CHANCE, Case.COURCELLES, Case.REPUBLIQUE,
            Case.SIMPLE_VISITE_PRISON, Case.VILLETTE, Case.ELECTRICITE, Case.NEUILLY, Case.PARADIS,
            Case.GARE_DE_LYON, Case.MOZART, Case.CAISSE_COMMUNAUTE, Case.SAINT_MICHEL, Case.PIGALLE,
            Case.PARC_GRATUIT, Case.MONTAIGNE, Case.CHANCE, Case.MALESHERBES, Case.HENRI_MARTIN,
            Case.GARE_DU_NORD, Case.SAINT_HONORE, Case.BOURSE, Case.EAU, Case.LA_FAYETTE,
            Case.ALLER_EN_PRISON, Case.BRETEUIL, Case.FOCH, Case.CAISSE_COMMUNAUTE, Case.CAPUCINES,
            Case.GARE_SAINT_LAZARE, Case.CHANCE, Case.CHAMPS_ELYSEES, Case.LUXE, Case.PAIX
    );

    private Constantes() {}
}
