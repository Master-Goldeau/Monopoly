package com.monopoly.proprietes.model;

import com.monopoly.proprietes.model.cartes.CartesProprieteConstructibles;

public class Propriete {
    private CartesProprieteConstructibles cartePropriete;
    private Groupe groupe;
    private Construction construction;

    public Groupe groupe(){
        return groupe;
    }

    public CartesProprieteConstructibles carte() {
        return cartePropriete;
    }

    public Construction construction() {
        return construction;
    }

}
