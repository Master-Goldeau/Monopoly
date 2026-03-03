package com.monopoly.lancer.service.impl;

import com.monopoly.joueur.model.Joueur;
import com.monopoly.lancer.service.ILancersService;
import com.monopoly.lancer.service.modele.Des;
import com.monopoly.lancer.service.modele.LancerDes;
import org.springframework.stereotype.Service;

import static com.monopoly.lancer.service.modele.Des.*;

@Service
public class LancersService implements ILancersService {
    @Override
    public LancerDes lancerDeuxDesSix(Joueur joueur) {
        Des de1 = lancer();
        Des de2 = lancer();
        return new LancerDes(de1, de2);
    }
}
