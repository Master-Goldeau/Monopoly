# language: fr

Fonctionnalité: Effet des cases du plateau sur le joueur

  Scénario: Un joueur lance les dés et arrive sur la case aller en prison
    Étant donné un joueur sur la case 19
    Quand il lance les dés et obtient 6 et 5
    Alors il doit se déplacer sur la case 30
    Et il doit aller en prison

  Scénario: Un joueur lance les dès et arrive sur la case Taxe de luxe
    Étant donné un joueur sur la case 35 avec 639 euros
    Quand il lance les dés et obtient 2 et 1
    Alors il doit se déplacer sur la case 38
    Et il doit avoir 539 euros

  Plan du scénario: un joueur arrive sur une case de pioche, pioche une carte et son effet est appliqué
    Étant donné un joueur sur la case <caseJoueur> avec <argent> euros
    Quand il lance les dés et obtient <valeurDes1> et <valeurDes2>
    Alors il doit se déplacer sur la case <caseArrivee>
    Et il doit piocher une carte "<typeCarte>"
    Et la carte piochée est "<typeCarte>" "<cartePiochee>"
    Et l'effet de la carte doit être appliqué
    Et il doit se déplacer sur la case <caseFinale>
    Et il doit avoir <argentFinal> euros

    Exemples:
      | caseJoueur | valeurDes1 | valeurDes2 | caseArrivee | typeCarte            | cartePiochee            | caseFinale | argent | argentFinal |
      | 19         | 2          | 1          | 22          | CHANCE               | BOULEVARD_VILLETTE      | 11         | 1500   | 1700        |
      | 2          | 3          | 2          | 7           | CHANCE               | RUE_DE_LA_PAIX          | 39         | 94     | 94          |
      | 34         | 1          | 1          | 36          | CHANCE               | AVENUE_HENRI_MARTIN     | 24         | 1500   | 1700        |
      | 36         | 6          | 5          | 7           | CHANCE               | SERVICE_PUBLIC          | 12         | 300    | 500         |
      | 17         | 4          | 1          | 22          | CHANCE               | SERVICE_PUBLIC          | 28         | 200    | 200         |
      | 29         | 5          | 2          | 36          | CHANCE               | SERVICE_PUBLIC          | 12         | 1000   | 1200        |
      | 34         | 1          | 1          | 36          | CHANCE               | CASE_DEPART             | 0          | 342    | 542         |
      | 34         | 1          | 1          | 36          | CHANCE               | ALLER_EN_PRISON         | 10         | 648    | 648         |
      | 34         | 1          | 1          | 36          | CHANCE               | DIVIDENDE               | 36         | 648    | 698         |
      | 2          | 3          | 2          | 7           | CHANCE               | PRET_IMMOBILIER         | 7          | 94     | 244         |
      | 34         | 1          | 1          | 36          | CHANCE               | GARE_MONTPARNASSE       | 5          | 648    | 848         |
      | 34         | 1          | 1          | 36          | CHANCE               | AMENDE_EXCES_VITESSE    | 36         | 648    | 663         |
      | 36         | 6          | 5          | 7           | CHANCE               | PROCHAINE_GARE          | 15         | 300    | 500         |
      | 17         | 4          | 1          | 22          | CHANCE               | PROCHAINE_GARE          | 25         | 200    | 200         |
      | 29         | 5          | 2          | 36          | CHANCE               | PROCHAINE_GARE          | 5          | 1000   | 1200        |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | CAISSE_DE_VACANCES      | 33         | 1000   | 1100        |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | ASSURANCE_VIE           | 33         | 1000   | 1100        |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | FRAIS_SCOLARITE         | 33         | 1000   | 950         |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | REMBOURSEMENT_IMPOTS    | 33         | 1000   | 1020        |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | HOPITAL                 | 33         | 1000   | 900         |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | ALLER_EN_PRISON         | 10         | 1000   | 1000        |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | HONORAIRES_CONSULTATION | 33         | 1000   | 1025        |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | VENTE_STOCK             | 33         | 1000   | 1050        |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | BEAUTE                  | 33         | 1000   | 1010        |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | AVANCEZ_CASE_DEPART     | 0          | 1000   | 1200        |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | ERREUR_BANQUE           | 33         | 1000   | 1200        |
      | 29         | 1          | 3          | 33          | CAISSE_DE_COMMUNAUTE | CHIOT                   | 33         | 1000   | 950         |
