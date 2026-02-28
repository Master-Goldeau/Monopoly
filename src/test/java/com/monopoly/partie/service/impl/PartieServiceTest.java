package com.monopoly.partie.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.joueur.model.Pion;
import com.monopoly.partie.model.Partie;
import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.service.IPiochableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PartieServiceTest {

    @Mock
    IPiochableService piochableService;

    @InjectMocks
    PartieService partieService;

    @Test
    void initialiserPartie_devrait_initialiser_une_partie_avec_les_joueurs_et_les_pioches() {
        //Given
        Joueur joueur1 = new Joueur(Pion.CHAPEAU);

        //When
        Partie nouvellePartie = partieService.initialiserPartie();

        //Then

        then(piochableService).should(Mockito.times(1)).initialiserPioche(CartesChance.class);
        then(piochableService).should(Mockito.times(1)).initialiserPioche(CartesChance.class);
    }
}

