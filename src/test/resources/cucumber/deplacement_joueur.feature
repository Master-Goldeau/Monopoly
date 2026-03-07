# language: fr

Fonctionnalité: Déplacement du joueur sur le plateau

  Plan du scénario: Un joueur lance les dés et avance
    Étant donné un joueur sur la case <caseJoueur>
    Quand il lance les dés et obtient <valeurDes1> et <valeurDes2>
    Alors il doit se déplacer sur la case <caseArrivee>

    Exemples:
      | caseJoueur | valeurDes1 | valeurDes2 | caseArrivee |
      | 0          | 5          | 3          | 8           |
      | 9          | 6          | 4          | 19          |
      | 31         | 2          | 6          | 39          |
      | 39         | 4          | 1          | 4           |


  Scénario: Un joueur qui fait trois doubles d'affilée va en prison
    Étant donné un joueur sur la case 0
    Quand il lance les dés et obtient 4 et 4
    Alors il doit se déplacer sur la case 8
    Et il peut rejouer
    Et il lance les dés et obtient 2 et 2
    Alors il doit se déplacer sur la case 12
    Et il peut rejouer
    Et il lance les dés et obtient 1 et 1
    Alors il doit être en prison
