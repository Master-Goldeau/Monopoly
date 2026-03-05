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

  Scénario: Un joueur lance les dés et arrive sur la case aller en prison
    Étant donné un joueur sur la case 19
    Quand il lance les dés et obtient 6 et 5
    Alors il doit se déplacer sur la case 30
    Et il doit aller en prison

  Scénario: Un joueur qui fait trois doubles d'affilée va en prison
    Étant donné un joueur sur la case 0
    Quand il lance les dés et obtient 4 et 4
    Alors il doit se déplacer sur la case 8
    Et il lance les dés et obtient 2 et 2
    Alors il doit se déplacer sur la case 12
    Et il lance les dés et obtient 1 et 1
    Alors il doit aller en prison

  Plan du scénario: un joueur arrive sur une case de pioche, pioche une carte et son effet est appliqué
    Étant donné un joueur sur la case <caseJoueur> avec <argent> euros
    Quand il lance les dés et obtient <valeurDes1> et <valeurDes2>
    Alors il doit se déplacer sur la case <caseArrivee>
    Et il doit piocher une carte "<typeCarte>"
    Et la carte piochée est "<typeCarte>" "<cartePiochee>"
    Et l'effet de la carte doit être appliqué
    Et il doit se déplacer sur la case <caseFinale>
    Et il doit avoir <argentFinal>

    Exemples:
      | caseJoueur | valeurDes1 | valeurDes2 | caseArrivee | typeCarte | cartePiochee        | caseFinale | argent | argentFinal |
      | 19         | 2          | 1          | 22          | CHANCE    | BOULEVARD_VILLETTE  | 11         | 1500   | 1700        |
      | 2          | 3          | 2          | 7           | CHANCE    | RUE_DE_LA_PAIX      | 39         | 94     | 94          |
      | 34         | 1          | 1          | 36          | CHANCE    | AVENUE_HENRI_MARTIN | 24         | 1500   | 1700        |
      | 36         | 6          | 5          | 7           | CHANCE    | SERVICE_PUBLIC      | 12         | 300    | 500         |
      | 17         | 4          | 1          | 22          | CHANCE    | SERVICE_PUBLIC      | 28         | 200    | 200         |
      | 29         | 5          | 2          | 36          | CHANCE    | SERVICE_PUBLIC      | 12         | 1000   | 1200        |
      | 34         | 1          | 1          | 36          | CHANCE    | CASE_DEPART         | 0          | 342    | 542         |
      | 34         | 1          | 1          | 36          | CHANCE    | ALLER_EN_PRISON     | 10         | 648    | 648         |
