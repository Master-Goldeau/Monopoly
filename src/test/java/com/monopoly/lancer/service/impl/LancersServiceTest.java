package com.monopoly.lancer.service.impl;

import com.monopoly.lancer.service.modele.Des;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.math.RoundingMode.HALF_UP;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class LancersServiceTest {

    @InjectMocks
    LancersService lancersService;
    private static final BigDecimal TOTAL_COMBINAISONS = BigDecimal.valueOf(36);
    private static final int SCALE = 10;
    private static final BigDecimal TOLERANCE = new BigDecimal("0.01"); // 5%
    private static final BigDecimal UN_SUR_36 = BigDecimal.ONE.divide(TOTAL_COMBINAISONS, SCALE, HALF_UP);
    private static final BigDecimal DEUX_SUR_36 = BigDecimal.valueOf(2).divide(TOTAL_COMBINAISONS, SCALE, HALF_UP);
    private static final BigDecimal TROIS_SUR_36 = BigDecimal.valueOf(3).divide(TOTAL_COMBINAISONS, SCALE, HALF_UP);
    private static final BigDecimal QUATRE_SUR_36 = BigDecimal.valueOf(4).divide(TOTAL_COMBINAISONS, SCALE, HALF_UP);
    private static final BigDecimal CINQ_SUR_36 = BigDecimal.valueOf(5).divide(TOTAL_COMBINAISONS, SCALE, HALF_UP);
    private static final BigDecimal SIX_SUR_36 = BigDecimal.valueOf(6).divide(TOTAL_COMBINAISONS, SCALE, HALF_UP);
    private static final Map<Integer, BigDecimal> PROBABILITES_THEORIQUES;
    static {
        PROBABILITES_THEORIQUES = new HashMap<>();
        PROBABILITES_THEORIQUES.put(2, UN_SUR_36);
        PROBABILITES_THEORIQUES.put(3, DEUX_SUR_36);
        PROBABILITES_THEORIQUES.put(4, TROIS_SUR_36);
        PROBABILITES_THEORIQUES.put(5, QUATRE_SUR_36);
        PROBABILITES_THEORIQUES.put(6, CINQ_SUR_36);
        PROBABILITES_THEORIQUES.put(7, SIX_SUR_36);
        PROBABILITES_THEORIQUES.put(8, CINQ_SUR_36);
        PROBABILITES_THEORIQUES.put(9, QUATRE_SUR_36);
        PROBABILITES_THEORIQUES.put(10, TROIS_SUR_36);
        PROBABILITES_THEORIQUES.put(11, DEUX_SUR_36);
        PROBABILITES_THEORIQUES.put(12, UN_SUR_36);
    }

    @Test
    void lancerDeuxDesSix_devrait_retourner_deux_des_avec_une_valeur_entre_1_et_6() {
        //Given
        int nbLancers = 20736;
        //When
        Stream.generate(() -> lancersService.lancerDeuxDesSix())
                .limit(nbLancers)
        //Then
                .forEach(resultatLancer -> assertThat(resultatLancer)
                        .hasSize(2)
                        .allSatisfy(de -> assertThat(de.valeur()).isBetween(1, 6)));
    }

    @Test
    void probabilitesSommeDesDeuxDes_respectees() {
        // Ce test vérifie la distribution de la somme des deux dés sur 12 puissance 6 lancers
        // Given
        int nbLancers = 2985984; // 12^6, pour une bonne approximation des probabilités théoriques
        Map<Integer, Integer> probabilitesObservees = new HashMap<>();
        // When
        Stream.generate(() -> lancersService.lancerDeuxDesSix())
            .limit(nbLancers)
            .forEach(resultatLancer -> enregistrerResultatLancer(resultatLancer, probabilitesObservees));
        // Then
        PROBABILITES_THEORIQUES.keySet().stream()
            .sorted()
            .forEach(somme -> {
                BigDecimal probaTheorique = PROBABILITES_THEORIQUES.get(somme);
                BigDecimal probaObservee = new BigDecimal(probabilitesObservees.getOrDefault(somme, 0)).divide(new BigDecimal(nbLancers), 10, HALF_UP);
                BigDecimal borneBasse = probaTheorique.subtract(probaTheorique.multiply(TOLERANCE));
                BigDecimal borneHaute = probaTheorique.add(probaTheorique.multiply(TOLERANCE));
                assertThat(probaObservee)
                    .as("Somme %d", somme)
                    .isBetween(borneBasse, borneHaute);
            });
    }

    private static void enregistrerResultatLancer(List<Des> des, Map<Integer, Integer> probabilitesObservees) {
        int sommeDesDeuxDes = des.stream().mapToInt(Des::valeur).sum();
        probabilitesObservees.merge(sommeDesDeuxDes, 1, Integer::sum);
    }
}
