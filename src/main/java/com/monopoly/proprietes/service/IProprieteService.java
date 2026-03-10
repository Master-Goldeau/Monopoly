package com.monopoly.proprietes.service;

import com.monopoly.proprietes.model.cartes.CartePropriete;

public interface IProprieteService {
    int valeurHypotheque(CartePropriete cartePropriete);
    int valeurLeveeHypotheque(CartePropriete cartePropriete);
}
