package com.monopoly.plateau.modele;

import com.monopoly.joueur.Joueur;
import com.monopoly.plateau.modele.constantes.Case;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static com.monopoly.plateau.modele.Constantes.PLATEAU;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PlateauTest {

    @Test
    void creerPlateau_devrait_retourner_une_liste_de_40_cases(){
        //Given
       final int nombreCasesPlateauMonopoly = 40;
        //When
        //Then
        assertThat(PLATEAU).hasSize(nombreCasesPlateauMonopoly);
        assertThat(PLATEAU.indexOf(Case.PAIX)).isEqualTo(39);
    }

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

    @Test
    void statistiquesDePassageSurChaqueCase() {
        long nbTours = 5159780352L; // 12^9, pour de bonnes stats
        Joueur joueur = new com.monopoly.joueur.Joueur();
        Map<Integer, Long> passages = new HashMap<>();
        for (long i = 0L; i < nbTours; i++) {
            int lancer = joueur.lancerDes();
            joueur.deplacer(lancer);
            int pos = joueur.getPositionPlateau();
            passages.merge(pos, 1L, Long::sum);
        }
        System.out.println("Statistiques de passage sur chaque case après " + nbTours + " tours (triées décroissant) :");
        passages.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .forEach(entry -> {
                    int i = entry.getKey();
                    long count = entry.getValue();
                    String nomCase = PLATEAU.get(i).name();
                    double pourCent = 100.0 * count / nbTours;
                    System.out.printf("Case %2d _%-19s_ : %.2f/100000000%n", i, nomCase, pourCent);
                });
    }

}