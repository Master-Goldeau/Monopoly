package com.monopoly.plateau.model.proprietes;

import com.monopoly.plateau.model.CasePlateau;

public interface CartePropriete {
    CasePlateau casePlateau();
    Groupe groupe();
    int valeur();
}
