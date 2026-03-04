package com.monopoly.joueur.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.model.Pion;
import com.monopoly.lancer.service.impl.LancersService;
import com.monopoly.lancer.service.modele.Des;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.partie.model.Partie;
import com.monopoly.partie.service.impl.PartieService;
import com.monopoly.plateau.constantes.Case;
import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.model.TypePiochable;
import com.monopoly.plateau.pioche.service.impl.PiochableService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Stream;

import static com.monopoly.plateau.Constantes.PLATEAU;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class JoueurServiceTest {

    @Spy
    PiochableService piochableServiceSpy = new PiochableService();

    @Spy
    PartieService partieServiceSpy = new PartieService(piochableServiceSpy);

    @Spy
    LancersService lancersServiceSpy = new LancersService();


    @InjectMocks
    JoueurService joueurService;

    public static Stream<Arguments> JeuDeDonneesPositionnelles() {
        return Stream.of(
                Arguments.of(
                        new Joueur(Pion.CANON),
                        new LancerDes(new Des(4), new Des(3)), // 7
                        Case.CHANCE_7
                ),
                Arguments.of(
                        new Joueur(Case.RUE_DE_LA_PAIX),
                        new LancerDes(new Des(1), new Des(1)), // 2
                        Case.BELLEVILLE
                ),
                Arguments.of(
                        new Joueur(Case.LUXE),
                        new LancerDes(new Des(2), new Des(2)), // 4
                        Case.CAISSE_COMMUNAUTE_2
                ),
                Arguments.of(
                        new Joueur(Case.LUXE),
                        new LancerDes(new Des(1), new Des(1)), // 2
                        Case.DEPART
                )
        );
    }

    public static Stream<Arguments> deplacementVersUneCase() {
        return Stream.of(
                Arguments.of(
                        new Joueur(Pion.CHAUSSURE),
                        Case.LECOURBE,
                        Case.LECOURBE
                ),
                Arguments.of(
                        new Joueur(Pion.CHAUSSURE),
                        Case.RUE_DE_LA_PAIX,
                        Case.RUE_DE_LA_PAIX
                ),
                Arguments.of(
                        new Joueur(Pion.CHAUSSURE),
                        Case.GARE_MONTPARNASSE,
                        Case.GARE_MONTPARNASSE
                )
        );
    }

    @ParameterizedTest
    @MethodSource("deplacementVersUneCase")
    void un_jouer_qui_recoit_l_ordre_de_se_rendre_sur_une_case_donnee_devrait_se_deplacer_sur_cette_case(Joueur joueur, Case destination, Case positionAttendue) {
        //Given

        //When
        joueurService.deplacer(joueur, destination);

        //Then
        assertThat(joueur.caseJoueur()).isEqualTo(positionAttendue);
    }



    @ParameterizedTest
    @MethodSource("JeuDeDonneesPositionnelles")
    void getDestinationApresLancer_devrait_retourner_la_case_attendue(Joueur joueur, LancerDes lancerDes, Case positionAttendue) {
        //Given
        //When
        Case destinationObtenue = joueurService.getDestinationApresLancer(joueur, lancerDes);

        //Then
        assertThat(destinationObtenue).isEqualTo(positionAttendue);
    }

    @Test
    void un_joueur_qui_arrive_sur_la_case_aller_en_prison_devrait_aller_en_prison() {
        // Given
        Joueur joueur = new Joueur(Case.PARC_GRATUIT);
        // When
        joueurService.deplacer(joueur, Case.ALLER_EN_PRISON);
        // Then
        assertThat(joueur.caseJoueur()).isEqualTo(Case.SIMPLE_VISITE_PRISON);
    }

    @Disabled("Test de statistiques de calcul des fréquences de passage sur les cases.")
    @Test
    void statistiquesDePassageSurChaqueCase() {
        long nbTours = 5000000; // 12^7, pour de bonnes stats
        Joueur joueur = new Joueur(Pion.CHAUSSURE);
        Queue<Joueur> joueurs = new ArrayDeque<>();
        joueurs.add(joueur);
        partieServiceSpy.initialiserPartie(joueurs);
        Map<Integer, Long> passages = new HashMap<>();
        for (long i = 0L; i < nbTours; i++) {
            joueurService.jouerTour(joueur); // Utilise la méthode complète
            passages.merge(joueur.position(), 1L, Long::sum);
            if (i % 1_000_000 == 0 && i != 0) {
                System.out.println("Tour en cours : " + i + "/" + nbTours);
            }
        }
        System.out.println("Statistiques de passage sur chaque case après " + nbTours + " tours (triées décroissant) :");
        passages.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .forEach(entry -> {
                    int i = entry.getKey();
                    long count = entry.getValue();
                    String nomCase = PLATEAU.get(i).name();
                    double pourCent = 100.0 * count / nbTours;
                    System.out.printf("Case %2d _%-19s_ : %.2f/100%%n", i, nomCase, pourCent);
                });
        // Ordre attendu des indices de case (du plus fréquent au moins fréquent)
        var ordreAttendu = java.util.List.of(
            10, 24, 19, 0, 25, 15, 5, 18, 21, 20, 28, 16, 26, 11, 23, 32, 17, 31, 27, 34, 39, 29, 12, 13, 14, 35, 8, 33, 9, 4, 6, 38, 3, 37, 1, 2, 22, 7, 36
        );
        // Récupérer l'ordre réel après tri
        var ordreReel = passages.entrySet().stream()
            .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
            .map(java.util.Map.Entry::getKey)
            .toList();
        System.out.println("Le joueur a : " + joueur.argent());

        // Classement par groupe (utilisation du champ Groupe de Case)
        Map<String, Long> passagesParGroupe = new HashMap<>();
        for (Case c : Case.values()) {
            String nomGroupe = c.getGroupe().map(Enum::name).orElse("SANS_GROUPE");
            long nbPassages = passages.getOrDefault(c.positionSurPlateau(), 0L);
            passagesParGroupe.merge(nomGroupe, nbPassages, Long::sum);
        }
        System.out.println("\nClassement des groupes de cases (par nombre de passages décroissant) :");
        passagesParGroupe.entrySet().stream()
            .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
            .forEach(entry -> {
                String groupe = entry.getKey();
                long total = entry.getValue();
                double pourCent = 100.0 * total / nbTours;
                System.out.printf("%-15s : %d passages (%.2f%%)%n", groupe, total, pourCent);
            });
        // Assert sur l'ordre uniquement
        Assertions.assertThat(ordreReel).isEqualTo(ordreAttendu);
    }

    @Test
    void quand_un_joueur_arrive_sur_une_case_chance_il_pioche_et_l_effet_de_la_carte_est_applique() {
        // Given
        Joueur joueur = new Joueur(Pion.BROUETTE);
        Queue<Joueur> joueurs = new ArrayDeque<>();
        joueurs.add(joueur);
        Partie partie = partieServiceSpy.initialiserPartie(joueurs);
        Piochable cartePiochee = CartesChance.AVENUE_HENRI_MARTIN;
        LancerDes valeurLancerDes = new LancerDes(new Des(4),new Des(3));
        BDDMockito.given(partieServiceSpy.getPartieEnCours())
                .willReturn(partie);
        BDDMockito.given(piochableServiceSpy.piocher(partie.getPioche(TypePiochable.CHANCE)))
                .willReturn(cartePiochee);
        BDDMockito.given(lancersServiceSpy.lancerDeuxDesSix(joueur))
                .willReturn(valeurLancerDes);

        // When
        joueurService.jouerTour(joueur);

        // Then
        then(piochableServiceSpy).should(times(1)).piocher(partie.piocheCartesChance());
        assertThat(joueur.caseJoueur()).isEqualTo(Case.HENRI_MARTIN);
    }

}
