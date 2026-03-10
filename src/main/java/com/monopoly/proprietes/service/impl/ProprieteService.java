package com.monopoly.proprietes.service.impl;

import com.monopoly.proprietes.model.cartes.CartePropriete;
import com.monopoly.proprietes.service.IProprieteService;

public class ProprieteService implements IProprieteService {

    @Override
    public int valeurHypotheque(CartePropriete cartePropriete) {
        return (int) (cartePropriete.valeur() * 0.5);
    }

    @Override
    public int valeurLeveeHypotheque(CartePropriete cartePropriete) {
        return (int) (cartePropriete.valeur() * 0.6);
    }

}
