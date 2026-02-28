package com.monopoly.joueur.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.model.Pion;
import com.monopoly.lancer.service.modele.Des;
import com.monopoly.partie.model.Partie;
import com.monopoly.plateau.constantes.Case;
import com.monopoly.plateau.pioche.service.IPiochableService;
import org.assertj.core.api.Assertions;
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

import static com.monopoly.plateau.Constantes.PLATEAU;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class JoueurServiceTest {

    @Mock
    IPiochableService piochableService;

    @InjectMocks
    JoueurService joueurService;

    public static Stream<Arguments> JeuDeDonneesPositionnelles() {
        return Stream.of(
                Arguments.of(
                        new Joueur(Pion.CANON),
                        7,
                        Case.CHANCE_7
                ),
                Arguments.of(
                        new Joueur(Case.RUE_DE_LA_PAIX),
                        2,
                        Case.BELLEVILLE
                ),
                Arguments.of(
                        new Joueur(Case.LUXE),
                        4,
                        Case.CAISSE_COMMUNAUTE_2
                ),
                Arguments.of(
                        new Joueur( Case.LUXE),
                        2,
                        Case.DEPART
                ),
                Arguments.of(
                        new Joueur( Case.CHANCE_7),
                        -3,
                        Case.IMPOTS
                )
        );
    }
    @ParameterizedTest
    @MethodSource("JeuDeDonneesPositionnelles")
    void getDestination_ApresLancer_devrait_retourner_la_position_attendue(Joueur joueur, int lancerDes, Case positionAttendue) {
        //Given
        //When
        Case destinationObtenue = joueurService.getDestinationApresLancer(joueur, lancerDes);

        //Then
        assertThat(destinationObtenue).isEqualTo(positionAttendue);
    }

    @Test
    void un_joueur_qui_arrive_sur_la_case_aller_en_prison_devrait_aller_en_prison() {
        // Given
        Joueur joueur = new Joueur(Case.PARC_GRATUIT);
        // When
        joueur.deplacer(joueurService.getDestinationApresLancer(joueur, 10));
        // Then
        assertThat(joueur.getCaseJoueur()).isEqualTo(Case.SIMPLE_VISITE_PRISON);
    }

    @Test
    void un_joueur_qui_lance_trois_doubles_de_suite_va_en_prison() {
        // Given
        Joueur joueur = new Joueur(Case.EAU);
        try (MockedStatic<Des> mocked = Mockito.mockStatic(Des.class)) {
            mocked.when(Des::lancer).thenReturn(new Des(6));
            // When
            int lancer1 = joueurService.lancerDes(joueur);
            joueur.deplacer(joueurService.getDestinationApresLancer(joueur, lancer1));
            int lancer2 = joueurService.lancerDes(joueur);
            joueur.deplacer(joueurService.getDestinationApresLancer(joueur, lancer2));
            int lancer3 = joueurService.lancerDes(joueur);
            joueur.deplacer(joueurService.getDestinationApresLancer(joueur, lancer3));

            // Then
            // Le joueur fait trois doubles six de suite, il doit être envoyé en prison
            assertThat(lancer1).isEqualTo(12);
            assertThat(lancer2).isEqualTo(12);
            assertThat(lancer3).isEqualTo(12);
            assertThat(joueur.getCaseJoueur()).isEqualTo(Case.SIMPLE_VISITE_PRISON);
            assertThat(joueur.getDoubleConsecutifs()).isZero(); // Après trois doubles, le compteur doit être remis à zéro
        }
    }

    @Test
    void quand_un_joueur_arrive_sur_une_case_chance_il_pioche_et_l_effet_de_la_carte_est_applique() {
        // Given
        Joueur joueur =Mockito.spy(new Joueur(Pion.BROUETTE));
        //Partie partie = new Partie(1, joueur, List.of(joueur), null);
        //TODO faire PartieService d'abord;
        int positionCaseChance = 7;
        Case caseArrivee = PLATEAU.get(positionCaseChance);

        // When
        joueur.deplacer(joueurService.getDestinationApresLancer(joueur, positionCaseChance));

        // Then
        then(piochableService).should(times(1)).piocher(caseArrivee.getNom());
        Assertions.assertThat(caseArrivee).isEqualTo(Case.CHANCE_7);
    }

}
