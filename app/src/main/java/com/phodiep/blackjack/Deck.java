package com.phodiep.blackjack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by phodiep on 3/13/16.
 */
public class Deck {
    private Queue<Card> deck;

    public Deck(Card[] cards, boolean shuffle)
    {
        if (shuffle)
        {
            List<Card> temp = Arrays.asList(cards);
            Collections.shuffle(temp);
            this.deck = new LinkedList<>(temp);
        }
        else
            this.deck = new LinkedList<>(Arrays.asList(cards));
    }

    public Card dealCard()
    {
        return deck.poll();
    }
}
