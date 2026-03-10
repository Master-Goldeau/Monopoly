package com.monopoly.proprietes.model;

public class Construction {
    private final int maisons;
    private final boolean hotel;

    private Construction(int maisons, boolean hotel) {
        this.maisons = maisons;
        this.hotel = hotel;
    }

    public static final Construction TERRAIN_NU = new Construction(0, false);
    public static final Construction UNE_MAISON = new Construction(1, false);
    public static final Construction DEUX_MAISONS = new Construction(2, false);
    public static final Construction TROIS_MAISONS = new Construction(3, false);
    public static final Construction QUATRE_MAISONS = new Construction(4, false);
    public static final Construction HOTEL = new Construction(0, true);
}
