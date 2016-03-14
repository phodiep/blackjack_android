package com.phodiep.blackjack;

/**
 * Created by phodiep on 3/13/16.
 */
public class Card {
    private Suit suit;
    private Face face;

    public Card(Face face, Suit suit)
    {
        this.face = face;
        this.suit = suit;
    }

    public Suit getSuit()
    {
        return this.suit;
    }

    public Face getFace()
    {
        return  this.face;
    }

}
