package com.monopoly.lancer.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.lancer.service.ILancersService;
import com.monopoly.lancer.service.modele.Des;
import com.monopoly.lancer.service.modele.LancerDes;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

import static com.monopoly.plateau.Constantes.NOMBRE_MAXIMUM_DE_TOUR_EN_PRISON;

@Service
public class LancersService implements ILancersService {

    private final Supplier<Des> desSupplier;

    public LancersService() {
        this(Des::lancer);
    }

    public LancersService(Supplier<Des> desSupplier) {
        this.desSupplier = desSupplier;
    }

    @Override
    public LancerDes lancerDesEtGererDoublesConsecutifs(Joueur joueur) {
        LancerDes resultat = lancerDeuxDesSix();
        gererDouble(joueur, resultat);
        return resultat;
    }

    private LancerDes lancerDeuxDesSix() {
        return new LancerDes(desSupplier.get(), desSupplier.get());
    }

    private void gererDouble(Joueur joueur, LancerDes resultat) {
        if (resultat.estUnDouble()) {
            if (joueur.estEnPrison()) {
                // Si le joueur est en prison et fait un double,
                // il sort de prison mais ne peut pas rejouer.
                joueur.sortirDePrison();
            } else {
                incrementerDoublesConsecutifs(joueur);
                if (joueur.aTroisDoublesConsecutifs()) {
                    resetDoublesConsecutifs(joueur);
                    joueur.allerEnPrison();
                }
            }
        } else {
            resetDoublesConsecutifs(joueur);
            if(joueur.estEnPrison()){
                joueur.setNombreDeToursEnPrison(joueur.nombreDeToursEnPrison() +1);
                if(joueur.nombreDeToursEnPrison() == NOMBRE_MAXIMUM_DE_TOUR_EN_PRISON){
                    joueur.payer(50);
                    joueur.sortirDePrison();
                }
            }
        }
    }

    private static void incrementerDoublesConsecutifs(Joueur joueur) {
        joueur.setDoubleConsecutifs(joueur.doubleConsecutifs() + 1);
    }

    private static void resetDoublesConsecutifs(Joueur joueur) {
        joueur.setDoubleConsecutifs(0);
    }


}
