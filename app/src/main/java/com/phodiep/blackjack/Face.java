package com.phodiep.blackjack;

/**
 * Created by phodiep on 3/13/16.
 */
public enum Face {
    TWO("2",new Integer[]{2}),
    THREE("3",new Integer[]{3}),
    FOUR("4",new Integer[]{4}),
    FIVE("5",new Integer[]{5}),
    SIX("6",new Integer[]{6}),
    SEVEN("7",new Integer[]{7}),
    EIGHT("8",new Integer[]{8}),
    NINE("9",new Integer[]{9}),
    TEN("10",new Integer[]{10}),
    JACK("J",new Integer[]{10}),
    QUEEN("Q",new Integer[]{10}),
    KING("K",new Integer[]{10}),
    ACE("A",new Integer[]{1,11});

    private final String displayValue;
    private final Integer[] values;

    private Face(String display, Integer[] values)
    {
        this.displayValue = display;
        this.values = values;
    }

    public String getDisplayValue()
    {
        return this.displayValue;
    }

    public Integer[] getValues()
    {
        return this.values;
    }
}

