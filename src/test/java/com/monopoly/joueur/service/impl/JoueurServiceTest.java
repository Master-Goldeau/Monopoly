package com.monopoly.joueur.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.model.Pion;
import com.monopoly.lancer.service.modele.Des;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.partie.service.impl.PartieService;
import com.monopoly.plateau.constantes.Case;
import com.monopoly.plateau.pioche.service.impl.PiochableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class JoueurServiceTest {

    @Mock
    PiochableService piochableServiceMock;

    @Spy
    PartieService partieServiceSpy = new PartieService(piochableServiceMock);

    @InjectMocks
    JoueurService joueurService;

    public static Stream<Arguments> JeuDeDonneesPositionnelles() {
        return Stream.of(
                Arguments.of(
                        new Joueur(Pion.CANON),
                        new LancerDes(new Des(4), new Des(3)), // 7
                        Case.CHANCE_7
                ),
                Arguments.of(
                        new Joueur(Case.RUE_DE_LA_PAIX),
                        new LancerDes(new Des(1), new Des(1)), // 2
                        Case.BELLEVILLE
                ),
                Arguments.of(
                        new Joueur(Case.LUXE),
                        new LancerDes(new Des(2), new Des(2)), // 4
                        Case.CAISSE_COMMUNAUTE_2
                ),
                Arguments.of(
                        new Joueur(Case.LUXE),
                        new LancerDes(new Des(1), new Des(1)), // 2
                        Case.DEPART
                )
        );
    }

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
        joueurService.deplacer(joueur, destination);

        //Then
        assertThat(joueur.getCaseJoueur()).isEqualTo(positionAttendue);
    }



    @ParameterizedTest
    @MethodSource("JeuDeDonneesPositionnelles")
    void getDestinationApresLancer_devrait_retourner_la_case_attendue(Joueur joueur, LancerDes lancerDes, Case positionAttendue) {
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
        joueurService.deplacer(joueur, Case.ALLER_EN_PRISON);
        // Then
        assertThat(joueur.getCaseJoueur()).isEqualTo(Case.SIMPLE_VISITE_PRISON);
    }

//    @Test
//    void quand_un_joueur_arrive_sur_une_case_chance_il_pioche_et_l_effet_de_la_carte_est_applique() {
//        // Given
//        Joueur joueur = Mockito.spy(new Joueur(Pion.BROUETTE));
//
//        Partie partie = partieServiceSpy.initialiserPartie(new ArrayDeque<>(List.of(joueur)));
//        Piochable cartePiochee = CartesChance.AVENUE_HENRI_MARTIN;
//        LancerDes valeurLancerDes = new LancerDes(new Des(4),new Des(3));
//        given(partieServiceSpy.getPartieEnCours()).willReturn(partie);
//        given(piochableServiceMock.piocher(any())).willReturn(cartePiochee);
//        given(joueurService.lancerDesEtGererDoublesConsecutifs(any())).willReturn(valeurLancerDes);
//
//        // When
//        joueurService.jouerTour(joueur);
//
//        // Then
//        then(piochableServiceMock).should(times(1)).piocher(partie.piocheCartesChance());
//    }

}
