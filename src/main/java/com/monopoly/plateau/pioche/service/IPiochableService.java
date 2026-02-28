package com.monopoly.plateau.pioche.service;

import com.monopoly.plateau.pioche.model.Piochable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface IPiochableService {

    ArrayList<Piochable> initialiserPioche(Class<? extends Piochable> classePiochable);

    Piochable piocher(ArrayList<Piochable> pioche);
}
