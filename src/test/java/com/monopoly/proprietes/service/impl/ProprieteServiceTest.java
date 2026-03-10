package com.monopoly.proprietes.service.impl;

import com.monopoly.proprietes.model.cartes.CartePropriete;
import com.monopoly.proprietes.model.cartes.CartesProprieteConstructibles;
import com.monopoly.proprietes.model.cartes.CartesProprietesNonConstructibles;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProprieteServiceTest {

    ProprieteService proprieteService = new ProprieteService();

    static Stream<CartePropriete> toutesLesCartesPropriete() {
        return Stream.concat(
            Stream.of(CartesProprieteConstructibles.values()),
            Stream.of(CartesProprietesNonConstructibles.values())
        );
    }

    @ParameterizedTest
    @MethodSource("toutesLesCartesPropriete")
    void valeurHypotheque(CartePropriete cartePropriete) {
        //Given
        int valeurAttendue = cartePropriete.valeur() /2;
        //When
        int valeurObtenue = proprieteService.valeurHypotheque(cartePropriete);

        //Then
        assertThat(valeurObtenue).isEqualTo(valeurAttendue);
    }

    @ParameterizedTest
    @MethodSource("toutesLesCartesPropriete")
    void valeurLeveeHypotheque(CartePropriete cartePropriete) {
        //Given
        int valeurAttendue = (cartePropriete.valeur()/2) + (cartePropriete.valeur()/10);
        //When
        int valeurObtenue = proprieteService.valeurLeveeHypotheque(cartePropriete);
        //Then
        assertThat(valeurAttendue).isEqualTo(valeurObtenue);
    }

}
