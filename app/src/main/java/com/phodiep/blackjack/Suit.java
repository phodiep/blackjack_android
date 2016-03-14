package com.phodiep.blackjack;

/**
 * Created by phodiep on 3/13/16.
 */
public enum Suit {
    HEART("Heart", Color.RED),
    CLUB("Club", Color.BLACK),
    DIAMOND("Diamond", Color.RED),
    SPADE("Spade", Color.BLACK);

    private enum Color {
        BLACK,
        RED
    }

    private Color color;
    private String display;

    private Suit(String display, Color color)
    {
        this.display = display;
        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }

    public String getDisplay()
    {
        return this.display;
    }
}
