package com.monopoly.deplacement.service.impl;

import com.monopoly.deplacement.model.Deplacement;
import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.model.Pion;
import com.monopoly.lancer.service.modele.Des;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.plateau.model.CasePlateau;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeplacementServiceTest {

    DeplacementService deplacementService = new DeplacementService();

    private static Stream<Arguments> JeuDeDonneesPositionnelles() {
        return Stream.of(
                Arguments.of(
                        new Joueur(Pion.CANON),
                        new LancerDes(new Des(4), new Des(3)), // 7
                        new Deplacement(CasePlateau.DEPART, CasePlateau.CHANCE_7)
                ),
                Arguments.of(
                        new Joueur(CasePlateau.RUE_DE_LA_PAIX),
                        new LancerDes(new Des(1), new Des(1)), // 2
                        new Deplacement(CasePlateau.RUE_DE_LA_PAIX, CasePlateau.BELLEVILLE)
                ),
                Arguments.of(
                        new Joueur(CasePlateau.LUXE),
                        new LancerDes(new Des(2), new Des(2)), // 4
                        new Deplacement(CasePlateau.LUXE, CasePlateau.CAISSE_COMMUNAUTE_2)
                ),
                Arguments.of(
                        new Joueur(CasePlateau.LUXE),
                        new LancerDes(new Des(1), new Des(1)), // 2
                        new Deplacement(CasePlateau.LUXE, CasePlateau.DEPART)
                )
        );
    }

    private static Stream<Arguments> deplacementVersUneCase() {
        return Stream.of(
                Arguments.of(
                        new Joueur(Pion.CHAUSSURE),
                        CasePlateau.LECOURBE,
                        CasePlateau.LECOURBE
                ),
                Arguments.of(
                        new Joueur(Pion.CHAUSSURE),
                        CasePlateau.RUE_DE_LA_PAIX,
                        CasePlateau.RUE_DE_LA_PAIX
                ),
                Arguments.of(
                        new Joueur(Pion.CHAUSSURE),
                        CasePlateau.GARE_MONTPARNASSE,
                        CasePlateau.GARE_MONTPARNASSE
                )
        );
    }


    @ParameterizedTest
    @MethodSource("JeuDeDonneesPositionnelles")
    void getDestinationApresLancer_devrait_retourner_la_case_attendue(Joueur joueur, LancerDes lancerDes, Deplacement deplacementAttendu) {
        //Given
        //When
        Deplacement deplacementObtenu = deplacementService.deplacer(joueur, lancerDes);

        //Then
        assertThat(deplacementObtenu).isEqualTo(deplacementAttendu);
    }

    @ParameterizedTest
    @MethodSource("deplacementVersUneCase")
    void un_jouer_qui_recoit_l_ordre_de_se_rendre_sur_une_case_donnee_devrait_se_deplacer_sur_cette_case(Joueur joueur, CasePlateau destination, CasePlateau positionAttendue) {
        //Given

        //When
        deplacementService.deplacer(joueur, destination);

        //Then
        assertThat(joueur.caseJoueur()).isEqualTo(positionAttendue);
    }
}
