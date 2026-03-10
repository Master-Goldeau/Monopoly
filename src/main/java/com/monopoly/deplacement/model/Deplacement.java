package com.monopoly.deplacement.model;

import com.monopoly.plateau.model.CasePlateau;

public record Deplacement(
        CasePlateau caseDeDepart,
        CasePlateau caseDArrivee
) {
}
