package com.monopoly.proprietes.service;

import com.monopoly.proprietes.model.Groupe;
import com.monopoly.proprietes.model.Propriete;

import java.util.List;

public interface IProprieteService {
    int valeurHypotheque(int valeurPropriete);
    int ValeurLeveeHypotheque(int valeurPropriete);
    boolean estGroupeComplet(Groupe groupe, List<Propriete> proprieteDUnMemeGroupe);

}
