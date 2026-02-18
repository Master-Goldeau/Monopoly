package com.monopoly.joueur;


import com.monopoly.lancer.service.modele.Des;
import com.monopoly.plateau.modele.constantes.Case;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.monopoly.plateau.modele.Constantes.PLATEAU;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class JoueurTest {

    @Mock
    private Des desMock;

    @InjectMocks
    Joueur joueur;

    public static Stream<Arguments> JeuDeDonneesPositionnelles() {
        return Stream.of(
                Arguments.of(
                        new Joueur(),
                        7,
                        7
                ),
                Arguments.of(
                        new Joueur(39),
                        2,
                        1
                ),
                Arguments.of(
                        new Joueur(38),
                        4,
                        2
                ),
                Arguments.of(
                        new Joueur( 38),
                        2,
                        0
                ),
                Arguments.of(
                        new Joueur( 7),
                        -3,
                        4
                )
        );
    }

    public static Stream<Arguments> deplacementVersUneCase() {
        return Stream.of(
                Arguments.of(
                        new Joueur(),
                        Case.LECOURBE,
                        PLATEAU.indexOf(Case.LECOURBE)
                ),
                Arguments.of(
                        new Joueur(),
                        Case.PAIX,
                        PLATEAU.indexOf(Case.PAIX)
                ),
                Arguments.of(
                        new Joueur(),
                        Case.GARE_MONTPARNASSE,
                        PLATEAU.indexOf(Case.GARE_MONTPARNASSE)
                ),
                Arguments.of(
                        new Joueur(),
                        Case.ALLER_EN_PRISON,
                        PLATEAU.indexOf(Case.SIMPLE_VISITE_PRISON)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("JeuDeDonneesPositionnelles")
    void un_joueur_devrait_se_deplacer_du_nombre_de_case_donnes(Joueur joueur, int lancerDes, int positionAttendue) {
        //Given
        //When
        joueur.deplacer(lancerDes);

        //Then
        assertThat(joueur.getPositionPlateau()).isEqualTo(positionAttendue);
    }

    @ParameterizedTest
    @MethodSource("deplacementVersUneCase")
    void un_jouer_qui_recoit_l_ordre_de_se_rendre_sur_une_case_donnee_devrait_se_deplacer_sur_cette_case(Joueur joueur, Case destination, int positionAttendue) {
        //Given
        //When
        joueur.deplacer(destination);

        //Then
        assertThat(joueur.getPositionPlateau()).isEqualTo(positionAttendue);
    }

    @Test
    void un_joueur_qui_lance_trois_doubles_de_suite_va_en_prison() {
        // Given
        try (MockedStatic<Des> mocked = Mockito.mockStatic(Des.class)) {
            mocked.when(Des::lancer).thenReturn(new Des(6));
            // When
            int lancer1 = joueur.lancerDes();
            joueur.deplacer(lancer1);
            int lancer2 = joueur.lancerDes();
            joueur.deplacer(lancer2);
            int lancer3 = joueur.lancerDes();
            joueur.deplacer(lancer3);

            // Then
            // Le joueur fait trois doubles six de suite, il doit être envoyé en prison
            assertThat(lancer1).isEqualTo(12);
            assertThat(lancer2).isEqualTo(12);
            assertThat(lancer3).isEqualTo(12);
            assertThat(joueur.getPositionPlateau()).isEqualTo(PLATEAU.indexOf(Case.SIMPLE_VISITE_PRISON));
        }
    }



}