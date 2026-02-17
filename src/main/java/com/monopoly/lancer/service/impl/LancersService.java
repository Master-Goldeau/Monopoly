package com.monopoly.lancer.service.impl;

import com.monopoly.lancer.service.ILancersService;
import com.monopoly.lancer.service.modele.Des;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancersService implements ILancersService {
    @Override
    public List<Des> lancerDeuxDesSix() {
        return List.of(Des.lancer(), Des.lancer());
    }
}
