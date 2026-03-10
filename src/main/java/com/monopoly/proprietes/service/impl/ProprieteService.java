package com.monopoly.proprietes.service.impl;

import com.monopoly.proprietes.model.Groupe;
import com.monopoly.proprietes.model.Propriete;
import com.monopoly.proprietes.service.IProprieteService;

import java.util.List;

public class ProprieteService implements IProprieteService {

    @Override
    public int valeurHypotheque(int valeurPropriete) {
        return (int) (valeurPropriete * 0.5);
    }

    @Override
    public int ValeurLeveeHypotheque(int valeurPropriete) {
        return (int) (valeurPropriete * 0.55);
    }

    @Override
    public boolean estGroupeComplet(Groupe groupe, List<Propriete> proprieteDUnMemeGroupe){
        return proprieteDUnMemeGroupe
                .stream()
                .filter(propriete -> propriete.groupe() == groupe)
                .count() == groupe.nombreDeProprietesDansLeGroupe();
    }
}
