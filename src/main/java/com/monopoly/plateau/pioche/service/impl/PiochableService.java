package com.monopoly.plateau.pioche.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.partie.model.Partie;
import com.monopoly.plateau.constantes.CasePlateau;
import com.monopoly.plateau.pioche.model.CartesChance;
import com.monopoly.plateau.pioche.model.Piochable;
import com.monopoly.plateau.pioche.service.IPiochableService;
import com.monopoly.plateau.service.IDeplacementService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Queue;

@Service
public class PiochableService implements IPiochableService {

    private final IDeplacementService deplacementService;

    public PiochableService(IDeplacementService deplacementService) {
        this.deplacementService = deplacementService;
    }

    @Override
    public Queue<Piochable> initialiserPioche(Class<? extends Piochable> classePiochable) {
        ArrayList<Piochable> valeurs = new ArrayList<>(Arrays.asList(classePiochable.getEnumConstants()));
        if (Objects.equals(CartesChance.class, classePiochable)) {
            ajouterDeuxiemeCarteProchaineGareDansPiocheChance(valeurs);
        }
        Collections.shuffle(valeurs);
        return new ArrayDeque<>(valeurs);
    }

    @Override
    public Piochable piocher(@NotNull Queue<Piochable> pioche) {
        Piochable cartePiochee = pioche.poll(); //Retourne la première carte de la pioche et la supprime de celle-ci
        pioche.offer(cartePiochee); //Ajoute la carte piochée à la fin de la pioche pour qu'elle puisse être piochée à nouveau plus tard
        return cartePiochee;
    }

    // Méthodes
    @Override
    public void appliquerEffet(Piochable cartePiochee, Partie partieEnCours, Joueur joueur) {
        switch (cartePiochee.actionCarte()) {
            case BENEFICE -> joueur.recevoirArgent(cartePiochee.valeurEffet().commeMontant());
            case PAYER -> joueur.payer(cartePiochee.valeurEffet().commeMontant());
            case PRISON -> joueur.allerEnPrison();
            case DEPLACER -> definirDestinationEtDeplacer(cartePiochee, joueur);
            case CONSERVER -> joueur.setPossedeCarteLiberePrison(true);
        }
    }

    private void definirDestinationEtDeplacer(Piochable cartePiochee, Joueur joueur) {
        CasePlateau destination = cartePiochee.valeurEffet().definirDestination(joueur);
        deplacementService.deplacerEtAppliquerEffetCase(joueur, destination);
    }

    private static void ajouterDeuxiemeCarteProchaineGareDansPiocheChance(ArrayList<Piochable> valeurs) {
        valeurs.add(CartesChance.PROCHAINE_GARE);
    }


}
