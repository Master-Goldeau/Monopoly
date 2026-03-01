package com.monopoly.plateau.pioche.service;

import com.monopoly.plateau.pioche.model.Piochable;

import java.util.Queue;

public interface IPiochableService {

    Queue<Piochable> initialiserPioche(Class<? extends Piochable> classePiochable);

    Piochable piocher(Queue<Piochable> pioche);
}
