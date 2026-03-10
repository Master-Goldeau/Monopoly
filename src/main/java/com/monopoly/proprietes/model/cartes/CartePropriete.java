package com.monopoly.proprietes.model.cartes;

import com.monopoly.plateau.model.CasePlateau;
import com.monopoly.proprietes.model.Groupe;

public interface CartePropriete {
    CasePlateau casePlateau();
    Groupe groupe();
    int valeur();
}
