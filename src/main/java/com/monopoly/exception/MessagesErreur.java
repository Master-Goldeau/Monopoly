package com.monopoly.exception;

public final class MessagesErreur {

    public static final String ERREUR_TYPE_VALEUR_EFFET_CARTE = "La valeur d'un effet d'une carte piochable doit être de type Case, Integer ou ValeurEffetACalculer.";
    public static final String ERREUR_TYPE_INTEGER = "La valeur n'est pas de type Integer.class";
    public static final String ERREUR_TYPE_CASE = "La valeur n'est pas de type Case.class";

    public static final String CASE_NON_CHANCE = "La case fournie n'est pas une case Chance.";

    public static String AUCUNE_CASE_TROUVEE(int i) {
        return String.format("Aucune case trouvée à la position %d", i);
    }

    private MessagesErreur() {}
}
