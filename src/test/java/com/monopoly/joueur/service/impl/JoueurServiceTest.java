package com.monopoly.joueur.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JoueurServiceTest {

//    @Spy
//    DeplacementService deplacementServiceSpy = new DeplacementService();
//
//    @Spy
//    PiochableService piochableServiceSpy = new PiochableService(deplacementServiceSpy);
//
//    @Spy
//    PartieService partieServiceSpy = new PartieService(piochableServiceSpy);

//    @Spy
//    CaseService caseServiceSpy = new CaseService(partieServiceSpy, piochableServiceSpy);
//
//    @Spy
//    LancersService lancersServiceSpy = new LancersService(deplacementServiceSpy);
//
//    @InjectMocks
//    JoueurService joueurService;





//    @Disabled("Test de statistiques de calcul des fréquences de passage sur les cases.")
//    @Test
//    void statistiquesDePassageSurChaqueCase() {
//        long nbTours = 5000000; // 12^7, pour de bonnes stats
//        Joueur joueur = new Joueur(Pion.CHAUSSURE);
//        Queue<Joueur> joueurs = new ArrayDeque<>();
//        joueurs.add(joueur);
//        partieServiceSpy.initialiserPartie(joueurs);
//        Map<Integer, Long> passages = new HashMap<>();
//        for (long i = 0L; i < nbTours; i++) {
//            joueurService.jouerTour(joueur); // Utilise la méthode complète
//            passages.merge(joueur.position(), 1L, Long::sum);
//            if (i % 1_000_000 == 0 && i != 0) {
//                System.out.println("Tour en cours : " + i + "/" + nbTours);
//            }
//        }
//        System.out.println("Statistiques de passage sur chaque case après " + nbTours + " tours (triées décroissant) :");
//        passages.entrySet().stream()
//                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
//                .forEach(entry -> {
//                    int i = entry.getKey();
//                    long count = entry.getValue();
//                    String nomCase = PLATEAU.get(i).name();
//                    double pourCent = 100.0 * count / nbTours;
//                    System.out.printf("Case %2d _%-19s_ : %.2f/100%%n", i, nomCase, pourCent);
//                });
//        // Ordre attendu des indices de case (du plus fréquent au moins fréquent)
//        var ordreAttendu = java.util.List.of(
//            10, 24, 19, 0, 25, 15, 5, 18, 21, 20, 28, 16, 26, 11, 23, 32, 17, 31, 27, 34, 39, 29, 12, 13, 14, 35, 8, 33, 9, 4, 6, 38, 3, 37, 1, 2, 22, 7, 36
//        );
//        // Récupérer l'ordre réel après tri
//        var ordreReel = passages.entrySet().stream()
//            .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
//            .map(java.util.Map.Entry::getKey)
//            .toList();
//        System.out.println("Le joueur a : " + joueur.argent());
//
//        // Classement par groupe (utilisation du champ Groupe de Case)
//        Map<String, Long> passagesParGroupe = new HashMap<>();
//        for (Case c : Case.values()) {
//            String nomGroupe = c.groupe().map(Enum::name).orElse("SANS_GROUPE");
//            long nbPassages = passages.getOrDefault(c.positionSurPlateau(), 0L);
//            passagesParGroupe.merge(nomGroupe, nbPassages, Long::sum);
//        }
//        System.out.println("\nClassement des groupes de cases (par nombre de passages décroissant) :");
//        passagesParGroupe.entrySet().stream()
//            .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
//            .forEach(entry -> {
//                String groupe = entry.getKey();
//                long total = entry.getValue();
//                double pourCent = 100.0 * total / nbTours;
//                System.out.printf("%-15s : %d passages (%.2f%%)%n", groupe, total, pourCent);
//            });
//        // Assert sur l'ordre uniquement
//        Assertions.assertThat(ordreReel).isEqualTo(ordreAttendu);
//    }

}
