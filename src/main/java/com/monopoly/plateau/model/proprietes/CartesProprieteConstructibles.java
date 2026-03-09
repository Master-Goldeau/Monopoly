package com.monopoly.plateau.model.proprietes;

import com.monopoly.plateau.model.CasePlateau;

import java.util.Map;

public enum CartesProprieteConstructibles implements CartePropriete{
    BELLEVILLE(
            CasePlateau.BELLEVILLE,
            Groupe.MARRON,
            60,
            Map.of(
                    Construction.TERRAIN_NU, 2,
                    Construction.UNE_MAISON, 10,
                    Construction.DEUX_MAISONS, 30,
                    Construction.TROIS_MAISONS, 90,
                    Construction.QUATRE_MAISONS, 160,
                    Construction.HOTEL, 250
            ),
            50
    ),
    LECOURBE(
            CasePlateau.LECOURBE,
            Groupe.MARRON,
            60,
            Map.of(
                    Construction.TERRAIN_NU, 4,
                    Construction.UNE_MAISON, 20,
                    Construction.DEUX_MAISONS, 60,
                    Construction.TROIS_MAISONS, 180,
                    Construction.QUATRE_MAISONS, 320,
                    Construction.HOTEL, 450
            ),
            50
    ),
    VAUGIRARD(
            CasePlateau.VAUGIRARD,
            Groupe.BLEU_CIEL,
            100,
            Map.of(
                    Construction.TERRAIN_NU, 6,
                    Construction.UNE_MAISON, 30,
                    Construction.DEUX_MAISONS, 90,
                    Construction.TROIS_MAISONS, 270,
                    Construction.QUATRE_MAISONS, 400,
                    Construction.HOTEL, 550
            ),
            50
    ),
    COURCELLES(
            CasePlateau.COURCELLES,
            Groupe.BLEU_CIEL,
            100,
            Map.of(
                    Construction.TERRAIN_NU, 6,
                    Construction.UNE_MAISON, 30,
                    Construction.DEUX_MAISONS, 90,
                    Construction.TROIS_MAISONS, 270,
                    Construction.QUATRE_MAISONS, 400,
                    Construction.HOTEL, 550
            ),
            50
    ),
    REPUBLIQUE(
            CasePlateau.REPUBLIQUE,
            Groupe.BLEU_CIEL,
            120,
            Map.of(
                    Construction.TERRAIN_NU, 8,
                    Construction.UNE_MAISON, 40,
                    Construction.DEUX_MAISONS, 100,
                    Construction.TROIS_MAISONS, 300,
                    Construction.QUATRE_MAISONS, 450,
                    Construction.HOTEL, 600
            ),
            50
    ),
    VILLETTE(
            CasePlateau.VILLETTE,
            Groupe.VIOLET,
            140,
            Map.of(
                    Construction.TERRAIN_NU, 10,
                    Construction.UNE_MAISON, 50,
                    Construction.DEUX_MAISONS, 150,
                    Construction.TROIS_MAISONS, 450,
                    Construction.QUATRE_MAISONS, 625,
                    Construction.HOTEL, 750
            ),
            100
    ),
    NEUILLY(
            CasePlateau.NEUILLY,
            Groupe.VIOLET,
            140,
            Map.of(
                    Construction.TERRAIN_NU, 10,
                    Construction.UNE_MAISON, 50,
                    Construction.DEUX_MAISONS, 150,
                    Construction.TROIS_MAISONS, 450,
                    Construction.QUATRE_MAISONS, 625,
                    Construction.HOTEL, 750
            ),
            100
    ),
    PARADIS(
            CasePlateau.PARADIS,
            Groupe.VIOLET,
            160,
            Map.of(
                    Construction.TERRAIN_NU, 12,
                    Construction.UNE_MAISON, 60,
                    Construction.DEUX_MAISONS, 180,
                    Construction.TROIS_MAISONS, 500,
                    Construction.QUATRE_MAISONS, 700,
                    Construction.HOTEL, 900
            ),
            100
    ),
    MOZART(
            CasePlateau.MOZART,
            Groupe.ORANGE,
            180,
            Map.of(
                    Construction.TERRAIN_NU, 14,
                    Construction.UNE_MAISON, 70,
                    Construction.DEUX_MAISONS, 200,
                    Construction.TROIS_MAISONS, 550,
                    Construction.QUATRE_MAISONS, 750,
                    Construction.HOTEL, 950
            ),
            100
    ),
    SAINT_MICHEL(
            CasePlateau.SAINT_MICHEL,
            Groupe.ORANGE,
            180,
            Map.of(
                    Construction.TERRAIN_NU, 14,
                    Construction.UNE_MAISON, 70,
                    Construction.DEUX_MAISONS, 200,
                    Construction.TROIS_MAISONS, 550,
                    Construction.QUATRE_MAISONS, 750,
                    Construction.HOTEL, 950
            ),
            100
    ),
    PIGALLE(
            CasePlateau.PIGALLE,
            Groupe.ORANGE,
            200,
            Map.of(
                    Construction.TERRAIN_NU, 16,
                    Construction.UNE_MAISON, 80,
                    Construction.DEUX_MAISONS, 220,
                    Construction.TROIS_MAISONS, 600,
                    Construction.QUATRE_MAISONS, 800,
                    Construction.HOTEL, 1000
            ),
            100
    ),
    MATIGNON(
            CasePlateau.MATIGNON,
            Groupe.ROUGE,
            220,
            Map.of(
                    Construction.TERRAIN_NU, 18,
                    Construction.UNE_MAISON, 90,
                    Construction.DEUX_MAISONS, 250,
                    Construction.TROIS_MAISONS, 700,
                    Construction.QUATRE_MAISONS, 875,
                    Construction.HOTEL, 1050
            ),
            150
    ),
    MALESHERBES(
            CasePlateau.MALESHERBES,
            Groupe.ROUGE,
            220,
            Map.of(
                    Construction.TERRAIN_NU, 18,
                    Construction.UNE_MAISON, 90,
                    Construction.DEUX_MAISONS, 250,
                    Construction.TROIS_MAISONS, 700,
                    Construction.QUATRE_MAISONS, 875,
                    Construction.HOTEL, 1050
            ),
            150
    ),
    HENRI_MARTIN(
            CasePlateau.HENRI_MARTIN,
            Groupe.ROUGE,
            240,
            Map.of(
                    Construction.TERRAIN_NU, 20,
                    Construction.UNE_MAISON, 100,
                    Construction.DEUX_MAISONS, 300,
                    Construction.TROIS_MAISONS, 750,
                    Construction.QUATRE_MAISONS, 925,
                    Construction.HOTEL, 1100
            ),
            150
    ),
    SAINT_HONORE(
            CasePlateau.SAINT_HONORE,
            Groupe.JAUNE,
            260,
            Map.of(
                    Construction.TERRAIN_NU, 22,
                    Construction.UNE_MAISON, 110,
                    Construction.DEUX_MAISONS, 330,
                    Construction.TROIS_MAISONS, 800,
                    Construction.QUATRE_MAISONS, 975,
                    Construction.HOTEL, 1150
            ),
            150
    ),
    BOURSE(
            CasePlateau.BOURSE,
            Groupe.JAUNE,
            260,
            Map.of(
                    Construction.TERRAIN_NU, 22,
                    Construction.UNE_MAISON, 110,
                    Construction.DEUX_MAISONS, 330,
                    Construction.TROIS_MAISONS, 800,
                    Construction.QUATRE_MAISONS, 975,
                    Construction.HOTEL, 1150
            ),
            150
    ),
    LA_FAYETTE(
            CasePlateau.LA_FAYETTE,
            Groupe.JAUNE,
            280,
            Map.of(
                    Construction.TERRAIN_NU, 24,
                    Construction.UNE_MAISON, 120,
                    Construction.DEUX_MAISONS, 360,
                    Construction.TROIS_MAISONS, 850,
                    Construction.QUATRE_MAISONS, 1025,
                    Construction.HOTEL, 1200
            ),
            150
    ),
    BRETEUIL(
            CasePlateau.BRETEUIL,
            Groupe.VERT,
            300,
            Map.of(
                    Construction.TERRAIN_NU, 26,
                    Construction.UNE_MAISON, 130,
                    Construction.DEUX_MAISONS, 390,
                    Construction.TROIS_MAISONS, 900,
                    Construction.QUATRE_MAISONS, 1100,
                    Construction.HOTEL, 1275
            ),
            200
    ),
    FOCH(
            CasePlateau.FOCH,
            Groupe.VERT,
            300,
            Map.of(
                    Construction.TERRAIN_NU, 26,
                    Construction.UNE_MAISON, 130,
                    Construction.DEUX_MAISONS, 390,
                    Construction.TROIS_MAISONS, 900,
                    Construction.QUATRE_MAISONS, 1100,
                    Construction.HOTEL, 1275
            ),
            200
    ),
    CAPUCINES(
            CasePlateau.CAPUCINES,
            Groupe.VERT,
            320,
            Map.of(
                    Construction.TERRAIN_NU, 28,
                    Construction.UNE_MAISON, 150,
                    Construction.DEUX_MAISONS, 450,
                    Construction.TROIS_MAISONS, 1000,
                    Construction.QUATRE_MAISONS, 1200,
                    Construction.HOTEL, 1400
            ),
            200
    ),
    CHAMPS_ELYSES(
            CasePlateau.CHAMPS_ELYSEES,
            Groupe.BLEU,
            350,
            Map.of(
                    Construction.TERRAIN_NU, 35,
                    Construction.UNE_MAISON, 175,
                    Construction.DEUX_MAISONS, 500,
                    Construction.TROIS_MAISONS, 1100,
                    Construction.QUATRE_MAISONS, 1300,
                    Construction.HOTEL, 1500
            ),
            200
    ),
    RUE_DE_LA_PAIX(
            CasePlateau.RUE_DE_LA_PAIX,
            Groupe.BLEU,
            400,
            Map.of(
                    Construction.TERRAIN_NU, 50,
                    Construction.UNE_MAISON, 200,
                    Construction.DEUX_MAISONS, 600,
                    Construction.TROIS_MAISONS, 1400,
                    Construction.QUATRE_MAISONS, 1700,
                    Construction.HOTEL, 2000
            ),
            200
    );

    private final CasePlateau casePlateau;
    private final Groupe groupe;
    private final int valeur;
    private final Map<Construction, Integer> loyers;
    private final int prixConstructions;

    CartesProprieteConstructibles(CasePlateau casePlateau, Groupe groupe, int valeur, Map<Construction, Integer> loyers, int prixConstructions) {
        this.casePlateau = casePlateau;
        this.groupe = groupe;
        this.valeur = valeur;
        this.loyers = loyers;
        this.prixConstructions = prixConstructions;
    }

    @Override
    public int valeur() {
        return valeur;
    }

    @Override
    public Groupe groupe() {
        return groupe;
    }

    @Override
    public CasePlateau casePlateau() {
        return casePlateau;
    }

    public Integer loyer(Construction Constructions) {
        return loyers.get(Constructions);
    }

    public int getPrixConstructions() {
        return prixConstructions;
    }
}
