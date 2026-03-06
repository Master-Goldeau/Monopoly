package com.monopoly.plateau;

import com.monopoly.plateau.constantes.Case;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.monopoly.plateau.Constantes.PLATEAU;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PlateauTest {

    @Test
    void get_depuis_position_devrait_retourner_la_case_attendue() {
        //Given
        int position = 3;
        Case caseAttendue = Case.LECOURBE;
        //When
        Case resultat = PLATEAU.get(position);
        //Then
        assertThat(resultat).isEqualTo(caseAttendue);
    }

}
