package com.phodiep.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phodiep on 3/13/16.
 */
public class Player {

    private String name;
    private List<Card> hand;

    public Player(String name)
    {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public void addCardToHand(Card card)
    {
        this.hand.add(card);
    }

    public void clearHand()
    {
        this.hand.clear();
    }

    public List<Card> getHand()
    {
        return this.hand;
    }

    public String getName()
    {
        return this.name;
    }
}
