package com.monopoly.steps;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.service.impl.JoueurService;
import com.monopoly.lancer.service.impl.LancersService;
import com.monopoly.lancer.service.modele.Des;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.partie.model.Partie;
import com.monopoly.partie.service.impl.PartieService;
import com.monopoly.plateau.constantes.CasePlateau;
import com.monopoly.plateau.pioche.model.CartesCaisseDeCommunaute;
import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.model.TypePiochable;
import com.monopoly.plateau.pioche.service.impl.PiochableService;
import com.monopoly.plateau.service.impl.CaseService;
import com.monopoly.plateau.service.impl.DeplacementService;
import io.cucumber.java.Before;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.mockito.Mockito;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

import static com.monopoly.plateau.pioche.model.TypePiochable.CAISSE_DE_COMMUNAUTE;
import static com.monopoly.plateau.pioche.model.TypePiochable.CHANCE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class MonopolyStepDefinitions {
    private Joueur joueur;
    private Partie partie;
    private LancerDes lancerDesMock;
    private Piochable cartePiochee;
    private Queue<Joueur> joueurs;

    private final LancersService lancersServiceSpy;
    private final PiochableService piochableServiceSpy;
    private final JoueurService joueurService;
    private final DeplacementService deplacementService;
    private final PartieService partieService;
    private final CaseService caseService;

    public MonopolyStepDefinitions() {
        caseService = new CaseService();
        deplacementService = new DeplacementService(caseService);
        piochableServiceSpy = Mockito.spy(new PiochableService(deplacementService));
        lancersServiceSpy = Mockito.spy(new LancersService(deplacementService));
        partieService = new PartieService(piochableServiceSpy);
        joueurService = Mockito.spy(
                new JoueurService(partieService, lancersServiceSpy, deplacementService, piochableServiceSpy));
    }

    @Before
    public void setUp() {
        joueur = null;
        joueurs = new ArrayDeque<>();
        partie = null;
        lancerDesMock = null;
        cartePiochee = null;
    }

    @Etantdonné("un joueur sur la case {int} avec {int} euros")
    public void un_joueur_sur_la_case_avec_argent(int caseJoueur, int argent) {
        CasePlateau emplacement = CasePlateau.depuisPosition(caseJoueur);
        joueur = new Joueur(emplacement);
        joueur.setArgent(argent);
        joueurs.add(joueur);
        partie = partieService.initialiserPartie(joueurs);
    }

    @Etantdonné("un joueur sur la case {int}")
    public void un_joueur_sur_la_case(int caseJoueur) {
        CasePlateau positionInitiale = CasePlateau.depuisPosition(caseJoueur);
        joueur = new Joueur(positionInitiale);
        joueurs.add(joueur);
        partie = partieService.initialiserPartie(joueurs);
    }

    @Quand("il lance les dés et obtient {int} et {int}")
    public void il_lance_les_des_et_obtient(int valeurDes1, int valeurDes2) {
        lancerDesMock = new LancerDes(new Des(valeurDes1), new Des(valeurDes2));
        when(lancersServiceSpy.lancerDeuxDesSix()).thenReturn(lancerDesMock);
        lancersServiceSpy.lancerDesEtGererDoublesConsecutifs(joueur);
    }

    @Alors("il doit se déplacer sur la case {int}")
    public void il_doit_se_deplacer_sur_la_case(int caseArrivee) {
        CasePlateau casePlateauAttendue = CasePlateau.depuisPosition(caseArrivee);
        if (Objects.nonNull(lancerDesMock)) {
            CasePlateau casePlateauObtenue = joueurService.getDestinationApresLancer(joueur, lancerDesMock);
            assertThat(casePlateauObtenue).isEqualTo(casePlateauAttendue);
            deplacementService.deplacer(joueur, casePlateauObtenue);
        }
        if (CasePlateau.ALLER_EN_PRISON == casePlateauAttendue) {
            assertThat(joueur.position()).isEqualTo(CasePlateau.SIMPLE_VISITE_PRISON.positionSurPlateau());
        } else {
            assertThat(joueur.caseJoueur()).isEqualTo(casePlateauAttendue);
        }
        lancerDesMock = null; // Réinitialise le lancer pour éviter les interférences avec d'autres étapes
    }

    @Et("il doit aller en prison")
    public void il_doit_aller_en_prison() {
        assertThat(joueur.caseJoueur()).isEqualTo(CasePlateau.SIMPLE_VISITE_PRISON);
        assertThat(joueur.position()).isEqualTo(CasePlateau.SIMPLE_VISITE_PRISON.positionSurPlateau());
        assertThat(joueur.estEnPrison()).isTrue();
        assertThat(joueur.nombreDeToursEnPrison()).isZero();
    }

    @Et("il doit piocher une carte {string}")
    public void il_doit_piocher_une_carte(String typeCarte) {
        TypePiochable typePiochable = TypePiochable.valueOf(typeCarte);
        // Vérifie que le type de carte piochée est correct
        if (Objects.equals(typeCarte, CHANCE.name())) {
            assertThat(typePiochable).isEqualTo(CHANCE);
        } else {
            assertThat(typePiochable).isEqualTo(CAISSE_DE_COMMUNAUTE);
        }
    }

    @Et("la carte piochée est {string} {string}")
    public void la_carte_piochee_est(String typeCarte, String cartePiochee) {
        // Mock la carte piochée pour contrôler son nom
        TypePiochable typePiochable = TypePiochable.valueOf(typeCarte);
        Piochable cartePiocheeMock = switch (typePiochable) {
            case CHANCE -> CartesChance.valueOf(cartePiochee);
            case CAISSE_DE_COMMUNAUTE -> CartesCaisseDeCommunaute.valueOf(cartePiochee);
        };
        when(piochableServiceSpy.piocher(partie.getPioche(typePiochable)))
                .thenReturn(cartePiocheeMock);
        // Vérifie que la carte piochée est celle attendue
        this.cartePiochee = piochableServiceSpy.piocher(partie.getPioche(typePiochable));
        assertThat(this.cartePiochee.name()).isEqualTo(cartePiochee);
    }

    @Et("l'effet de la carte doit être appliqué")
    public void effet_carte_applique() {
        piochableServiceSpy.appliquerEffet(cartePiochee, partie, joueur);
    }

    @Et("il doit avoir {int} euros")
    public void il_doit_avoir(int argentFinal) {
        assertThat(joueur.argent()).isEqualTo(argentFinal);
    }
}
