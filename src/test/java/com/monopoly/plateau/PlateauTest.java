package com.monopoly.plateau;

import com.monopoly.plateau.model.CasePlateau;
import com.monopoly.plateau.pioche.model.TypePiochable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static com.monopoly.plateau.Constantes.PLATEAU;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PlateauTest {

    @Test
    void get_depuis_position_devrait_retourner_la_case_attendue() {
        //Given
        int position = 3;
        CasePlateau casePlateauAttendue = CasePlateau.LECOURBE;
        //When
        CasePlateau resultat = PLATEAU.get(position);
        //Then
        assertThat(resultat).isEqualTo(casePlateauAttendue);
    }

    @Test
    void le_plateau_devrait_avoir_3_cases_chance_et_trois_cartes_de_communaute() {
        //Given
        //When
        long nombreCasesChance = PLATEAU.stream()
                .filter(casePlateau -> Objects.equals(casePlateau.nom(),TypePiochable.CHANCE.name()))
                .count();
        long nombreCasesCaisseDeCommunaute = PLATEAU.stream()
                .filter(casePlateau -> Objects.equals(casePlateau.nom(), TypePiochable.CAISSE_DE_COMMUNAUTE.name()))
                .count();
        //Then
        assertThat(nombreCasesChance).isEqualTo(Constantes.NOMBRE_CASES_CHANCE_OU_CAISSE_DE_COMMUNAUTE);
        assertThat(nombreCasesCaisseDeCommunaute).isEqualTo(Constantes.NOMBRE_CASES_CHANCE_OU_CAISSE_DE_COMMUNAUTE);
    }

}
