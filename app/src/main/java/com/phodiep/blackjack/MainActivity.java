package com.phodiep.blackjack;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


public class MainActivity extends ActionBarActivity {

    private Blackjack game;
    private TextView dealerName;
    private TextView dealerCards;
    private TextView dealerTotals;
    private TextView playerName;
    private TextView playerCards;
    private TextView playerTotals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.game = new Blackjack();

        this.dealerName = (TextView)this.findViewById(R.id.dealer);
        this.playerName = (TextView)this.findViewById(R.id.player);

        this.dealerName.setText(game.getPlayers()[0].getName());
        this.playerName.setText(game.getPlayers()[1].getName());

        this.dealerCards = (TextView)this.findViewById(R.id.dealerCards);
        this.dealerTotals = (TextView)this.findViewById(R.id.dealerTotals);
        this.playerCards = (TextView)this.findViewById(R.id.playerCards);
        this.playerTotals = (TextView)this.findViewById(R.id.playerTotals);
        updateTextViews();
        checkForBlackJack();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static String handDisplayValue(List<Card> hand)
    {
        StringBuilder sb = new StringBuilder();

        Iterator<Card> iter = hand.iterator();

        sb.append(Blackjack.cardDisplayValue(iter.next()));

        while(iter.hasNext())
        {
            sb.append(", ");
            sb.append(Blackjack.cardDisplayValue(iter.next()));
        }

        return sb.toString();
    }

    public void clickHit(View view) {
        game.dealCard(game.getPlayers()[1]);
        updateTextViews();

        //continueDealingToDealer();
        String bust = checkPlayersForBust();
        if (bust != null)
        {
            Log.e("debug", "before alert(bust)");
            alert(bust);
        }
    }

    public void clickStand(View view)
    {
        if (continueDealingToDealer()) {
            clickStand(view);
            return;
        }

        String message = checkPlayersForBust();

        if (message == null)
        {
            message = compareAllPlayerValues();
        }

        alert(message);

    }

    private boolean continueDealingToDealer()
    {
        if (!atLeast17(Blackjack.getHandValues(game.getDealer().getHand()))) {
            game.dealCard(game.getDealer());
            updateTextViews();
            return true;
        }

        return false;

    }

    private String compareAllPlayerValues()
    {
        Integer value = 0;
        String name = "";

        for (Player player : game.getPlayers())
        {
            for (Integer v : Blackjack.getHandValues(player.getHand()))
            {
                if (v <= 21 && value < v)
                {
                    value = v;
                    name = player.getName();
                }
            }
        }

        return name + " wins with " + value.toString();

    }

    private boolean atLeast17(List<Integer> values)
    {
        for (Integer v : values)
        {
            if (v >= 17) {
                return true;
            }
        }
        return false;
    }

    private void updateTextViews()
    {
        this.dealerCards.setText(handDisplayValue(game.getDealer().getHand()));
        this.dealerTotals.setText(Blackjack.getHandValues(game.getDealer().getHand()).toString());

        this.playerCards.setText(handDisplayValue(game.getPlayers()[1].getHand()));
        this.playerTotals.setText(Blackjack.getHandValues(game.getPlayers()[1].getHand()).toString());
    }

    private void checkForBlackJack()
    {
        List<Integer> playerValues = Blackjack.getHandValues(game.getPlayers()[1].getHand());
        if (playerValues.contains(10) && playerValues.contains(21)) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Blackjack!!!");
            builder1.setCancelable(false);

            builder1.setPositiveButton(
                    "I won!!!",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    private boolean checkForBust(Player player)
    {
        Integer min = 0;
        for (Integer v : Blackjack.getHandValues(player.getHand())) {
            if (min == 0)
                min = v;
            else if (v < min)
                min = v;
        }
        return min > 21;
    }

    private String checkPlayersForBust()
    {
        for (Player player : game.getPlayers())
        {
            if (checkForBust(player))
            {
                return player.getName() + " goes bust!";

            }
        }
        return null;
    }

    private void alert(String message)
    {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(message);
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "New Round",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        newRound();
                        dialog.cancel();

                    }
                });

        builder1.setNegativeButton(
                "Done Playing",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void newRound()
    {
        for (Player player : game.getPlayers())
        {
            player.clearHand();
            game.dealCard(player);
            game.dealCard(player);
        }

        updateTextViews();

    }
}
