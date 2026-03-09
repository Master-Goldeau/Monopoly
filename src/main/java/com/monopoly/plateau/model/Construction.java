package com.monopoly.plateau.model;


public record Construction(int maisons, boolean hotel) {
    public Construction {
        if (hotel && maisons != 0) {
            throw new IllegalArgumentException("Si hotel=true, maisons doit être 0");
        }
        if (!hotel && (maisons < 0 || maisons > 4)) {
            throw new IllegalArgumentException("Maisons doit être entre 0 et 4");
        }
    }

    public static final Construction TERRAIN_NU = new Construction(0, false);
    public static final Construction UNE_MAISON = new Construction(1, false);
    public static final Construction DEUX_MAISONS = new Construction(2, false);
    public static final Construction TROIS_MAISONS = new Construction(3, false);
    public static final Construction QUATRE_MAISONS = new Construction(4, false);
    public static final Construction HOTEL = new Construction(0, true);
}
