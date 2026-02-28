package com.monopoly.plateau.pioche.service;

import com.monopoly.plateau.pioche.model.Piochable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPiochableService {

    List<Piochable> initialiserPioche(Class<? extends Piochable> classePiochable);

    Piochable piocher(List<Piochable> pioche);
}
