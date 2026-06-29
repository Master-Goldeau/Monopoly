# language: fr

Fonctionnalité: Choix d'un joueur en prison

  Scénario: Un joueur est en prison et choisit de payer pour sortir
    Étant donné un joueur au 0 tour en prison avec 450 euros
    Quand il choisit de payer pour sortir de prison
    Et il n'est plus prisonnier
    Alors il lance les dés et obtient 2 et 3
    Et il doit se déplacer sur la case 15
    Et il doit avoir 400 euros
    Et il ne peut pas rejouer
    Et il a 0 tours de prison au compteur

  Scénario: Un joueur est en prison et choisit de payer pour sortir et fait un double
    Étant donné un joueur au 2 tour en prison avec 450 euros
    Quand il choisit de payer pour sortir de prison
    Et il n'est plus prisonnier
    Alors il lance les dés et obtient 2 et 2
    Et il doit se déplacer sur la case 14
    Et il doit avoir 400 euros
    Et il peut rejouer
    Et il a 0 tours de prison au compteur

  Scénario: Un joueur est en prison et lance les dès pour sortir et fait un double
    Étant donné un joueur au 2 tour en prison avec 450 euros
    Quand il lance les dés et obtient 3 et 3
    Alors il n'est plus prisonnier
    Et il doit se déplacer sur la case 16
    Et il doit avoir 450 euros
    Et il ne peut pas rejouer
    Et il a 0 tours de prison au compteur

  Scénario: Un joueur est en prison et lance les dès pour sortir mais ne fait pas de double
    Étant donné un joueur au 1 tour en prison avec 450 euros
    Quand il lance les dés et obtient 2 et 3
    Alors il est toujours prisonnier
    Et il doit avoir 450 euros
    Et il a 2 tours de prison au compteur

  Scénario: Un joueur doit sortir de prison et payer 50 euros après un troisième tour en prison
    Étant donné un joueur au 2 tour en prison avec 450 euros
    Quand il lance les dés et obtient 6 et 1
    Alors il n'est plus prisonnier
    Et il doit se déplacer sur la case 17
    Et il doit avoir 400 euros
    Et il a 0 tours de prison au compteur

  Scénario: Un joueur utilise sa carte libéré de prison pour sortir de prison
    Étant donné un joueur au 2 tour en prison avec 450 euros et une carte "CHANCE" libéré de prison
    Quand il utilise sa carte vous êtes libéré de prison
    Alors il n'est plus prisonnier
    Et il lance les dés et obtient 6 et 1
    Et il doit se déplacer sur la case 17
    Et il doit avoir 450 euros
    Et il a 0 tours de prison au compteur
    Et il a 0 carte libéré de prison

#    Étant donné un joueur au 2 tour en prison avec 450 euros et 2 cartes libéré de prison
#    Quand il utilise sa carte vous êtes libéré de prison
#    Alors il n'est plus prisonnier
#    Et il lance les dés et obtient 6 et 1
#    Et il doit se déplacer sur la case 17
#    Et il doit avoir 450 euros
#    Et il a 0 tours de prison au compteur
#    Et il a 1 carte libéré de prison
