package com.monopoly.joueur;


import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.model.Pion;
import com.monopoly.plateau.constantes.Case;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class JoueurTest {



    public static Stream<Arguments> deplacementVersUneCase() {
        return Stream.of(
                Arguments.of(
                        new Joueur(Pion.CHAUSSURE),
                        Case.LECOURBE,
                        Case.LECOURBE
                ),
                Arguments.of(
                        new Joueur(Pion.CHAUSSURE),
                        Case.RUE_DE_LA_PAIX,
                        Case.RUE_DE_LA_PAIX
                ),
                Arguments.of(
                        new Joueur(Pion.CHAUSSURE),
                        Case.GARE_MONTPARNASSE,
                        Case.GARE_MONTPARNASSE
                )
        );
    }

    @ParameterizedTest
    @MethodSource("deplacementVersUneCase")
    void un_jouer_qui_recoit_l_ordre_de_se_rendre_sur_une_case_donnee_devrait_se_deplacer_sur_cette_case(Joueur joueur, Case destination, Case positionAttendue) {
        //Given
        //When
        joueur.deplacer(destination);

        //Then
        assertThat(joueur.getCaseJoueur()).isEqualTo(positionAttendue);
    }




}
