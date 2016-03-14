package com.phodiep.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by phodiep on 3/13/16.
 */
public class Blackjack {
    private Deck deck;
    private Player dealer;
    private Player[] players;
    private int currentPlayerIndex;


    public Blackjack()
    {
        this.createAndShuffleDeck();
        this.createPlayerVsComputer();

        // deal 2 cards per player to start
        this.dealCardsToAllPlayers(2);

        this.currentPlayerIndex = 0;
    }

    public Player[] getPlayers()
    {
        return  this.players;
    }

    public Player getCurrentPlayer()
    {
        return this.players[this.currentPlayerIndex];
    }

    private void dealCardsToAllPlayers(int num)
    {
        for (Player player : players)
        {
            for (int i = 0; i < num; i++)
            {
                player.addCardToHand(this.deck.dealCard());
            }
        }
    }


    public void dealCard(Player player)
    {
        player.addCardToHand(this.deck.dealCard());
    }

    private void createPlayerVsComputer()
    {
        this.players = new Player[2];

        Player p1 = new Player("Dealer");
        Player p2 = new Player("Player");

        this.dealer = p1;
        this.players[0] = p1;
        this.players[1] = p2;
    }

    public Player getDealer()
    {
        return this.dealer;
    }

    private void createAndShuffleDeck()
    {
        int i = 0;
        Card[] cards = new Card[Suit.values().length * Face.values().length];

        for (Suit suit : Suit.values())
        {
            for (Face face : Face.values())
            {
                cards[i++] = new Card(face, suit);
            }
        }

        this.deck = new Deck(cards, true);
    }

    public static String cardDisplayValue(Card card)
    {
        return card.getFace().getDisplayValue() + " " + card.getSuit().getDisplay();
    }

    public static List<Integer> getHandValues(List<Card> hand)
    {
        Iterator<Card> iter = hand.iterator();
        List<Integer> values = new ArrayList<>();
        values.addAll(Arrays.asList(iter.next().getFace().getValues()));

        while(iter.hasNext())
        {
            Card card = iter.next();

            Integer[] cardValues = card.getFace().getValues();

            List<Integer> tempValues = new ArrayList<>(values.size() * cardValues.length);

            for (Integer v : values)
            {
                for (Integer t : cardValues)
                {
                    tempValues.add(v + t);
                }
            }
            values = tempValues;
        }

        return values;
    }

}
