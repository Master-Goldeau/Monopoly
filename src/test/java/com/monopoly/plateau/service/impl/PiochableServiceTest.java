package com.monopoly.plateau.service.impl;

import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.service.IPiochableService;
import com.monopoly.plateau.pioche.service.impl.PiochableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PiochableServiceTest {

    private final IPiochableService piochableService = new PiochableService();

    public static Stream<Arguments> ClassesPiochables() {
        return Stream.of(
                Arguments.of(CartesChance.class)
                //Arguments.of(CartesCaisseDeCommunaute.class)
        );
    }

    @ParameterizedTest
    @MethodSource("ClassesPiochables")
    void initialiser_pioche_devrait_retourner_un_set_de_16_piochables(Class<? extends Piochable> classePiochable) {
        //Given
        List<Piochable> cartesAttendues = Arrays.asList(CartesChance.values());

        //When
        List<Piochable> resultat = piochableService.initialiserPioche(classePiochable);

        //Then
        assertThat(resultat)
                .hasSize(16)
                .containsExactlyInAnyOrderElementsOf(cartesAttendues);
    }

    @Test
    void initialiser_pioche_devrait_retourner_un_set_de_piochables_dans_un_ordre_different_a_chaque_initialisation() {
        //Given
        //When
        List<Piochable> resultat1 = piochableService.initialiserPioche(CartesChance.class);
        List<Piochable> resultat2 = piochableService.initialiserPioche(CartesChance.class);

        //Then
        assertThat(resultat1).isNotEqualTo(resultat2);
    }

    @Test
    void piocherCartePiochable_devrait_positionner_la_premiere_carte_de_la_liste_en_derniere_position_avant_de_la_retourner() {
        //Given
        List<Piochable> piocheChance = List.of(
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
                CartesChance.PRESIDENT_CONSEIL

        );
        Piochable cartePiocheeAttendue = piocheChance.get(0);
        List<Piochable> piocheAttendue = List.of(
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
        );


        //When
        Piochable cartePiochee = piochableService.piocher(piocheChance);

        //Then
        assertThat(cartePiochee).isEqualTo(cartePiocheeAttendue);
        assertThat(piocheChance).isEqualTo(piocheAttendue);
    }
}
