package com.monopoly.lancer.controleur;

import com.monopoly.lancer.service.ILancersService;
import com.monopoly.lancer.service.modele.Des;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lancers")
public class LancersControleur {

    private final ILancersService lancersService;

    public LancersControleur(ILancersService lancersService) {
        this.lancersService = lancersService;
    }

    @GetMapping()
    public ResponseEntity<@NonNull List<Integer>> lancerDeuxDes() {
        var resultat = lancersService.lancerDeuxDesSix();
        return ResponseEntity.ok(resultat.stream().map(Des::valeur).toList());
    }

}
