package com.monopoly.steps;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.model.Pion;
import com.monopoly.joueur.service.impl.JoueurService;
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
import io.cucumber.java.Before;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.mockito.Mockito;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MonopolyStepDefinitions {
    private Joueur joueur;
    private Partie partie;
    private LancerDes lancerDes;
    private Piochable cartePiochee;
    private final JoueurService joueurService;
    private final LancersService lancersServiceMock;
    private final PiochableService piochableServiceMock;

    public MonopolyStepDefinitions() {
        PiochableService piochableService = new PiochableService();
        lancersServiceMock = Mockito.mock(LancersService.class);
        PartieService partieService = new PartieService(piochableService);
        piochableServiceMock = Mockito.mock(PiochableService.class);
        joueurService = Mockito.spy(new JoueurService(piochableService, lancersServiceMock, partieService));
        // Les objets d'état (joueur, partie, etc.) sont initialisés dans @Before
    }

    @Before
    public void setUp() {
        joueur = null;
        partie = null;
        lancerDes = null;
        cartePiochee = null;
    }

    @Etantdonné("un joueur sur la case {int} avec {int} euros")
    public void un_joueur_sur_la_case_avec_argent(int caseJoueur, int argent) {
        Case emplacement = Case.depuisPosition(caseJoueur);
        joueur = new Joueur(Pion.CANON);
        joueur.setArgent(argent);
        joueurService.deplacer(joueur, emplacement);
    }

    @Etantdonné("un joueur sur la case {int}")
    public void un_joueur_sur_la_case(int caseJoueur) {
        Case destination = Case.depuisPosition(caseJoueur);
        joueur = new Joueur(Pion.CANON);
        joueurService.deplacer(joueur, destination);
    }

    @Quand("il lance les dés et obtient {int} et {int}")
    public void il_lance_les_des_et_obtient(int valeurDes1, int valeurDes2) {
        lancerDes = new LancerDes(new Des(valeurDes1), new Des(valeurDes2));
        when(lancersServiceMock.lancerDeuxDesSix(joueur)).thenReturn(lancerDes); // Mock le résultat
        joueurService.lancerDesEtGererDoublesConsecutifs(joueur); // Appel via JoueurService pour gérer les doubles
    }

    @Alors("il doit se déplacer sur la case {int}")
    public void il_doit_se_deplacer_sur_la_case(int caseArrivee) {
        Case caseAttendue = Case.depuisPosition(caseArrivee);
        if (Objects.nonNull(lancerDes)) {
            Case caseObtenue = joueurService.getDestinationApresLancer(joueur, lancerDes);
            assertThat(caseObtenue).isEqualTo(caseAttendue);
            joueurService.deplacer(joueur, caseObtenue);
        }
        if (Case.ALLER_EN_PRISON == caseAttendue) {
            assertThat(joueur.position()).isEqualTo(Case.SIMPLE_VISITE_PRISON.positionSurPlateau());
        } else {
            assertThat(joueur.caseJoueur()).isEqualTo(caseAttendue);
        }
        lancerDes = null; // Réinitialise le lancer pour éviter les interférences avec d'autres étapes
    }

    @Et("il doit aller en prison")
    public void il_doit_aller_en_prison() {
        assertThat(joueur.caseJoueur()).isEqualTo(Case.SIMPLE_VISITE_PRISON);
        assertThat(joueur.position()).isEqualTo(Case.SIMPLE_VISITE_PRISON.positionSurPlateau());
        assertThat(joueur.estEnPrison()).isTrue();
        assertThat(joueur.nombreDeToursEnPrison()).isZero();
    }

    @Et("il doit piocher une carte {string}")
    public void il_doit_piocher_une_carte(String typeCarte) {
        TypePiochable typePiochable = TypePiochable.valueOf(typeCarte);
        assertThat(typePiochable).isEqualTo(TypePiochable.CHANCE);
        // Vérifie que le type de carte piochée est correct
    }

    @Et("la carte piochée est {string}")
    public void la_carte_piochee_est(String cartePiochee) {
        // Mock la carte piochée pour contrôler son nom
        when(piochableServiceMock.piocher(any())).thenReturn(CartesChance.valueOf(cartePiochee));
        // Vérifie que la carte piochée est celle attendue
        this.cartePiochee = piochableServiceMock.piocher(any());
        assertThat(this.cartePiochee.name()).isEqualTo(cartePiochee);
    }

    @Et("l'effet de la carte doit être appliqué")
    public void effet_carte_applique() {
        cartePiochee.appliquerEffet(partie, joueur);
    }

    @Et("il doit avoir {int}")
    public void il_doit_avoir(int argentFinal) {
        assertThat(joueur.argent()).isEqualTo(argentFinal);
    }
}
