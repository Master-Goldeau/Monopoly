package com.monopoly.plateau.constantes;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CaseTest {


    public static Stream<Arguments> casesChance() {
        return Stream.of(
                Arguments.of(Case.CHANCE_7, Case.ELECTRICITE),
                Arguments.of(Case.CHANCE_22, Case.EAU),
                Arguments.of(Case.CHANCE_36, Case.ELECTRICITE)
        );
    }

    @ParameterizedTest
    @MethodSource("casesChance")
    void prochainServicePublic_devrait_retourer_le_prochain_service_public_DepuisCaseChance_selon_position_du_joueur(Case caseChance, Case resultatAttendu) {
        //Given
        //When
        Case resultat = Case.prochainServicePublicDepuisCaseChance(caseChance);
        //Then
        assertThat(resultat).isEqualTo(resultatAttendu);
    }
}
