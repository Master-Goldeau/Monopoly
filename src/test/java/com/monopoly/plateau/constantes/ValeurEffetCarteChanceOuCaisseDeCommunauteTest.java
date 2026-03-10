package com.monopoly.plateau.constantes;


import com.monopoly.joueur.model.Joueur;
import com.monopoly.plateau.model.CasePlateau;
import com.monopoly.plateau.pioche.model.ValeurEffetCarteChanceOuCaisseDeCommunaute;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.monopoly.exception.MessagesErreur.ERREUR_TYPE_VALEUR_EFFET_CARTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ValeurEffetCarteChanceOuCaisseDeCommunauteTest {

    public static Stream<Arguments> typesValeursEffetCarteChanceOuCaisseDeCommunauteValides() {
        return Stream.of(
                Arguments.of(
                        CasePlateau.ALLER_EN_PRISON
                ),
                Arguments.of(
                        100
                ),
                Arguments.of(
                        -50
                )
        );
    }

    public static Stream<Arguments> typesValeursEffetCarteChanceOuCaisseDeCommunauteInvalides() {
        return Stream.of(
                Arguments.of(
                        "ALLER_EN_PRISON"
                ),
                Arguments.of(
                        100L
                ),
                Arguments.of(
                        List.of("ALLER_EN_PRISON")
                )
        );
    }

    public static Stream<Arguments> joueurSurCaseChance() {
        return Stream.of(
                Arguments.of(new Joueur(CasePlateau.CHANCE_7), CasePlateau.ELECTRICITE),
                Arguments.of(new Joueur(CasePlateau.CHANCE_22), CasePlateau.EAU),
                Arguments.of(new Joueur(CasePlateau.CHANCE_36), CasePlateau.ELECTRICITE)
        );
    }

    @ParameterizedTest
    @MethodSource("typesValeursEffetCarteChanceOuCaisseDeCommunauteInvalides")
    void devrait_throw_exception_si_valeur_effet_carte_chance_ou_caisse_de_communaute_invalide(Object valeurEffetCarteChanceOuCaisseDeCommunauteInvalide) {
        //Given

        // When
        Throwable exception = catchThrowable(
                () -> new ValeurEffetCarteChanceOuCaisseDeCommunaute(valeurEffetCarteChanceOuCaisseDeCommunauteInvalide));

        //Then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERREUR_TYPE_VALEUR_EFFET_CARTE);
    }

    @ParameterizedTest
    @MethodSource("typesValeursEffetCarteChanceOuCaisseDeCommunauteValides")
    void devrait_creer_valeur_effet_carte_chance_ou_caisse_de_communaute_valide(Object valeur) {
        // Given
        //When
        ValeurEffetCarteChanceOuCaisseDeCommunaute valeurEffetCarteChanceOuCaisseDeCommunaute = new ValeurEffetCarteChanceOuCaisseDeCommunaute(valeur);
        //Then
        assertThat(valeurEffetCarteChanceOuCaisseDeCommunaute.valeur()).isEqualTo(valeur);
    }

    @ParameterizedTest
    @MethodSource("joueurSurCaseChance")
    void prochainServicePublic_devrait_retourer_le_prochain_service_public_DepuisCaseChance_selon_position_du_joueur(Joueur joueurSurCaseChance, CasePlateau resultatAttendu) {
        //Given
        //When
        CasePlateau resultat = ValeurEffetCarteChanceOuCaisseDeCommunaute.definirProchainServicePublic(joueurSurCaseChance);
        //Then
        AssertionsForClassTypes.assertThat(resultat).isEqualTo(resultatAttendu);
    }
}

