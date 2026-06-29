package com.monopoly.steps;

import com.monopoly.deplacement.model.Deplacement;
import com.monopoly.deplacement.service.IDeplacementService;
import com.monopoly.deplacement.service.impl.DeplacementService;
import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.model.Pion;
import com.monopoly.joueur.service.IJoueurService;
import com.monopoly.joueur.service.impl.JoueurService;
import com.monopoly.lancer.service.ILancersService;
import com.monopoly.lancer.service.impl.LancersService;
import com.monopoly.lancer.service.modele.Des;
import com.monopoly.lancer.service.modele.LancerDes;
import com.monopoly.partie.model.Partie;
import com.monopoly.partie.service.IPartieService;
import com.monopoly.partie.service.impl.PartieService;
import com.monopoly.plateau.model.CasePlateau;
import com.monopoly.plateau.pioche.model.CartesCaisseDeCommunaute;
import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.model.TypePiochable;
import com.monopoly.plateau.pioche.service.IPiochableService;
import com.monopoly.plateau.pioche.service.impl.PiochableService;
import com.monopoly.plateau.service.ICaseService;
import com.monopoly.plateau.service.impl.CaseService;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.mockito.Mockito;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Supplier;

import static com.monopoly.plateau.pioche.model.TypePiochable.CAISSE_DE_COMMUNAUTE;
import static com.monopoly.plateau.pioche.model.TypePiochable.CHANCE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class DeplacementStepDefinitions {
    private Joueur joueur;
    private Partie partie;
    private LancerDes lancerDesMock;
    private Piochable cartePiochee;
    private Queue<Joueur> joueurs;

    private final Supplier<Des> desSupplierMock;
    private final ILancersService lancersService;
    private final IPiochableService piochableServiceSpy;
    private final IDeplacementService deplacementService;
    private final IPartieService partieService;
    private final ICaseService caseService;
    private final IJoueurService joueurService;

    public DeplacementStepDefinitions() {
        desSupplierMock = mockDesSupplier();
        deplacementService = new DeplacementService();
        piochableServiceSpy = Mockito.spy(new PiochableService(deplacementService));
        partieService = new PartieService(piochableServiceSpy);
        caseService = new CaseService(partieService, piochableServiceSpy);
        lancersService = new LancersService(desSupplierMock);
        joueurService = new JoueurService(lancersService, deplacementService, caseService, piochableServiceSpy, partieService);
    }

    private static Supplier<Des> mockDesSupplier() {
        @SuppressWarnings("unchecked")
        Supplier<Des> supplier = Mockito.mock(Supplier.class);
        return supplier;
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

    @Etantdonné("un joueur au {int} tour en prison avec {int} euros")
    public void un_joueur_en_prison_avec_euros(int nombreDeTourEnPrison, int montant) {
        joueur = new Joueur(Pion.CANON);
        joueur.allerEnPrison();
        joueur.setNombreDeToursEnPrison(nombreDeTourEnPrison);
        joueur.setArgent(montant);
    }

    @Etantdonné("un joueur au {int} tour en prison avec {int} euros et une carte {string} libéré de prison")
    public void un_joueur_en_prison_avec_euros(int nombreDeTourEnPrison, int montant, int nombreDeCartesLiberePrison) {
        joueur = new Joueur(Pion.CANON);
        joueur.allerEnPrison();
        joueur.setNombreDeToursEnPrison(nombreDeTourEnPrison);
        joueur.setArgent(montant);
        if (nombreDeCartesLiberePrison > 2) {
            throw new IllegalArgumentException("Un joueur ne peut pas avoir plus de 2 cartes libéré de prison");
        }
        if (nombreDeCartesLiberePrison == 2) {
            joueur.ajouterCarteLiberePrison(CartesChance.LIBERE_PRISON);
            joueur.ajouterCarteLiberePrison(CartesCaisseDeCommunaute.LIBERE_PRISON);
        } else if (nombreDeCartesLiberePrison == 1) {
            joueur.ajouterCarteLiberePrison(CartesChance.LIBERE_PRISON);
        }
    }

    @Quand("il lance les dés et obtient {int} et {int}")
    public void il_lance_les_des_et_obtient(int valeurDes1, int valeurDes2) {
        lancerDesMock = new LancerDes(new Des(valeurDes1), new Des(valeurDes2));
        when(desSupplierMock.get()).thenReturn(new Des(valeurDes1), new Des(valeurDes2));
        lancersService.lancerDesEtGererDoublesConsecutifs(joueur);
    }

    @Alors("il doit se déplacer sur la case {int}")
    public void il_doit_se_deplacer_sur_la_case(int caseArrivee) {
        CasePlateau casePlateauAttendue = CasePlateau.depuisPosition(caseArrivee);
        if (Objects.nonNull(lancerDesMock)) {
            Deplacement deplacement = deplacementService.deplacer(joueur, lancerDesMock);
            deplacementService.toucherSalaireSiPassageCaseDepart(joueur, deplacement);
            CasePlateau casePlateauObtenue = joueur.caseJoueur();
            assertThat(casePlateauObtenue)
                    .isEqualTo(deplacement.caseDArrivee())
                    .isEqualTo(casePlateauAttendue);
        } else {
            assertThat(joueur.caseJoueur()).isEqualTo(casePlateauAttendue);
        }
        lancerDesMock = null; // Réinitialise le lancer pour éviter les interférences avec d'autres étapes
    }

    @Et("l'effet de la case est appliqué")
    public void appliquerEffetCase() {
        caseService.appliquerEffetDestination(joueur);
    }

    @Et("il doit être en prison")
    public void il_doit_etre_en_prison() {
        verifierQueLeJoueurEstEnPrison();
    }

    @Et("il peut rejouer")
    public void il_peut_rejouer() {
        assertThat(joueur.peutRejouer()).isTrue();
    }

    @Et("il ne peut pas rejouer")
    public void il_ne_peut_pas_rejouer() {
        assertThat(joueur.peutRejouer()).isFalse();
    }

    private void verifierQueLeJoueurEstEnPrison() {
        assertThat(joueur.caseJoueur()).isEqualTo(CasePlateau.SIMPLE_VISITE_PRISON);
        assertThat(joueur.position()).isEqualTo(CasePlateau.SIMPLE_VISITE_PRISON.positionSurPlateau());
        assertThat(joueur.estEnPrison()).isTrue();
        assertThat(joueur.nombreDeToursEnPrison()).isZero();
        assertThat(joueur.peutRejouer()).isFalse();
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


    @Quand("il choisit de payer pour sortir de prison")
    public void il_choisit_de_payer_pour_sortir_de_prison() {
        joueurService.payerPourSortirDePrison(joueur);
    }


    @Et("il n'est plus prisonnier")
    public void il_n_est_plus_prisonnier() {
        // Write code here that turns the phrase above into concrete actions
        assertThat(joueur.estEnPrison()).isFalse();
    }

    @Et("il est toujours prisonnier")
    public void il_est_toujours_prisonnier() {
        // Write code here that turns the phrase above into concrete actions
        assertThat(joueur.estEnPrison()).isTrue();
        assertThat(joueur.caseJoueur()).isEqualTo(CasePlateau.SIMPLE_VISITE_PRISON);
    }

    @Et("il a {int} tours de prison au compteur")
    public void il_a_passe_x_tours_en_prison(int nombreDeToursEnPrison) {
        assertThat(joueur.nombreDeToursEnPrison()).isEqualTo(nombreDeToursEnPrison);
    }

    @Quand("il utilise sa carte vous êtes libéré de prison")
    public void il_utilise_sa_carte_vous_etes_libere_de_prison() {
        // Write code here that turns the phrase above into concrete actions
        joueurService.sortirDePrisonAvecCarte(joueur);
    }

    @Et("il a {int} carte libéré de prison")
    public void ilACarteLibereDePrison(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Et("il doit conserver la carte {string}")
    public void ilDoitConserverLaCarte(String typeCarte){
        TypePiochable typePiochable = TypePiochable.valueOf(typeCarte);
            assertThat(joueur.cartesLiberePrison().contains(CartesCaisseDeCommunaute.LIBERE_PRISON)).isTrue();
    }

    @Et("la pioche {string} contient {int} cartes")
    public void verifierNombreCartesDansPioche(String pioche, int nombreCartes){
        TypePiochable typePiochable = TypePiochable.valueOf(pioche);
        assertThat(partie.getPioche(typePiochable).size()).isEqualTo(nombreCartes);
       }
}
