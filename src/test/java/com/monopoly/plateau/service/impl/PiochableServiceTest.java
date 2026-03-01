package com.monopoly.plateau.service.impl;

import com.monopoly.plateau.pioche.model.CartesCaisseDeCommunaute;
import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.service.IPiochableService;
import com.monopoly.plateau.pioche.service.impl.PiochableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PiochableServiceTest {

    private final IPiochableService piochableService = new PiochableService();

    public static Stream<Arguments> classesPiochables() {
        Queue<Piochable> piocheCartesChance = new ArrayDeque<>(List.of(CartesChance.values()));
        piocheCartesChance.add(CartesChance.PROCHAINE_GARE);
        Queue<Piochable> piocheCartesCaisseDeCommunaute = new ArrayDeque<>(List.of(CartesCaisseDeCommunaute.values()));
        return Stream.of(
                Arguments.of(CartesChance.class, piocheCartesChance),
                Arguments.of(CartesCaisseDeCommunaute.class, piocheCartesCaisseDeCommunaute)
        );
    }

    @ParameterizedTest
    @MethodSource("classesPiochables")
    void initialiser_pioche_devrait_retourner_un_set_de_16_piochables(Class<? extends Piochable> classePiochable, Queue<Piochable> cartesAtendues) {
        //Given

        //When
        Queue<Piochable> resultat = piochableService.initialiserPioche(classePiochable);

        //Then
        assertThat(resultat)
                .hasSize(16)
                .containsExactlyInAnyOrderElementsOf(cartesAtendues);
    }

    @Test
    void initialiser_pioche_devrait_retourner_un_set_de_piochables_dans_un_ordre_different_a_chaque_initialisation() {
        //Given
        //When
        Queue<Piochable> resultat1 = piochableService.initialiserPioche(CartesChance.class);
        Queue<Piochable> resultat2 = piochableService.initialiserPioche(CartesChance.class);

        //Then
        assertThat(resultat1).isNotEqualTo(resultat2);
    }

    @Test
    void piocherCartePiochable_devrait_positionner_la_premiere_carte_de_la_liste_en_derniere_position_avant_de_la_retourner() {
        //Given
        Queue<Piochable> piocheChance = new ArrayDeque<>(List.of(
                CartesChance.RUE_DE_LA_PAIX,
                CartesChance.GARE_MONTPARNASSE,
                CartesChance.PRET_IMMOBILIER,
                CartesChance.AVENUE_HENRI_MARTIN,
                CartesChance.SERVICE_PUBLIC,
                CartesChance.ALLER_EN_PRISON,
                CartesChance.AVANCEZ_CASE_DEPART,
                CartesChance.DIVIDENDE,
                CartesChance.LIBERE_PRISON,
                CartesChance.REPARATIONS,
                CartesChance.AVANCEZ_BOULEVARD_VILLETTE,
                CartesChance.AMENDE_EXCES_VITESSE,
                CartesChance.PRESIDENT_CONSEIL)
        );

        Piochable cartePiocheeAttendue = piocheChance.element();
        Queue<Piochable> piocheAttendue = new ArrayDeque<>(List.of(
                CartesChance.GARE_MONTPARNASSE,
                CartesChance.PRET_IMMOBILIER,
                CartesChance.AVENUE_HENRI_MARTIN,
                CartesChance.SERVICE_PUBLIC,
                CartesChance.ALLER_EN_PRISON,
                CartesChance.AVANCEZ_CASE_DEPART,
                CartesChance.DIVIDENDE,
                CartesChance.LIBERE_PRISON,
                CartesChance.REPARATIONS,
                CartesChance.AVANCEZ_BOULEVARD_VILLETTE,
                CartesChance.AMENDE_EXCES_VITESSE,
                CartesChance.PRESIDENT_CONSEIL,
                CartesChance.RUE_DE_LA_PAIX
        ));

        //When
        Piochable cartePiochee = piochableService.piocher(piocheChance);

        //Then
        assertThat(cartePiochee).isEqualTo(cartePiocheeAttendue);
        assertThat(piocheChance).containsExactlyElementsOf(piocheAttendue);
    }

    @Test
    void piocher_16_fois_devrait_retourner_la_liste_initialisee() {
        //Given
        Queue<Piochable> cartesChance = piochableService.initialiserPioche(CartesChance.class);
        Queue<Piochable> ordreInitial = new ArrayDeque<>(cartesChance);

        //When
        for (int i = 0; i < 16; i++) {
            piochableService.piocher(cartesChance);
        }
        assertThat(cartesChance).containsAnyElementsOf(ordreInitial);
    }

    @Test
    void ordreInitialAleatoire() {
        //Given
        //When
        ArrayList<Piochable> pioche1 = new ArrayList<>(piochableService.initialiserPioche(CartesChance.class));
        ArrayList<Piochable> pioche2 = new ArrayList<>(piochableService.initialiserPioche(CartesChance.class));

        //Then
        assertThat(pioche1).containsExactlyInAnyOrderElementsOf(pioche2);
        assertThat(pioche1).isNotEqualTo(pioche2);
    }


}
