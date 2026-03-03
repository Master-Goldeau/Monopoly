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
    void obtenirnomCase_devrait_retourner_le_nom_de_la_case() {
        //Given
        int position = 3;
        Case caseAttendue = Case.LECOURBE;
        //When
        Case resultat = PLATEAU.get(position);
        //Then
        assertThat(resultat).isEqualTo(caseAttendue);
    }

}
