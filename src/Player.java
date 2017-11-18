import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Racko Game
 *
 * @author aylee
 * @version 1.0
 */
public class Player {
    /**
     * Holds the cards that are in the draw pile.
     */
    private ArrayList<Integer> drawPile = new ArrayList<>();
    /**
     * Holds the cards that have been taken from the draw pile or from the player's hand and discarded.
     */
    private ArrayList<Integer> discardPile = new ArrayList<>();
    /**
     * Contains Player 1's cards.
     */
    private ArrayList<Integer> player1Cards = new ArrayList<>();
    /**
     * Constant containing the number of cards each player has.
     */
    private static final int NUM_CARDS = 10;
    /**
     * Scanner to read in input from the user.
     */
    private Scanner sc = new Scanner(System.in);
    /**
     * Remains false until the player gets a racko and wins the game.
     */
    private boolean racko = false;

    /**
     * Constructor calls shuffles the cards and then calls the dealCards function and then the printMenu function.
     */
    public Player() {

        //TODO: INHERIT FROM GAME CLASS AND THEN OVERRIDE DEAL CARDS, PRINT BOARD AND PRINT MENU ETC....
        //Make a deck class and inherit the deck

        for (int i = 0; i <= 60; i++) { drawPile.add(i); }
        Collections.shuffle(drawPile);
        dealCards();
        printMenu();
    }

    /**
     * Shows user the first card they are dealt and then lets them choose if they want it in the front or the back.
     * Then 9 more cards are drawn from the pile and put into the user's hand.
     */
    private void dealCards() {
        System.out.println("Your first card is a " + drawPile.get(0) + ".");
        System.out.println("1 - Place this card at the bottom (smallest number).");
        System.out.println("2 - Place this card at the top (biggest number).");
        String input = sc.nextLine();

        if (input.equals("1")) {
            for (int count = 0; count < NUM_CARDS; count++) {
                player1Cards.add(drawPile.get(0));
                drawPile.remove(0);
            }
        }
        else if (input.equals("2")) {
            for (int count = NUM_CARDS - 1; count >= 0; count--) {
                player1Cards.add(drawPile.get(count));
                drawPile.remove(count);
            }
        }
        else {
            System.out.println("Invalid input. Please enter a 1 or 2.\n");
            dealCards();
        }

        discardPile.add(drawPile.get(0));
        drawPile.remove(0);

        System.out.println();
    }

    /**
     * Prints the board out for the user.
     */
    private void printBoard() {
        System.out.println("Player 1 Cards:");
        for (int i = NUM_CARDS - 1; i >= 0; i--) {
            System.out.printf("%-2s -- " + player1Cards.get(i) + "\n", ((i+1)*5));
        }
    }

    /**
     * Asks the user where they would like to draw the card from.
     * Once they draw the card, the cardAction function is called.
     */
    private void printMenu() {
        if (racko) {
            System.out.println("RRRRRRACKO!!");
            printBoard();
            return;
        }
        printBoard();

        System.out.println("\nPlease draw a card.");
        System.out.println("1 - Take " + discardPile.get(discardPile.size() - 1) + " from the discard pile.");
        System.out.println("2 - Take a card from the draw pile.");

        String line = sc.nextLine();

        int card;
        if (line.equals("1")) {
            card = discardPile.get(discardPile.size() - 1);
            discardPile.remove(discardPile.size() - 1);
            cardAction(card);
        }
        else if (line.equals("2")) {
            card = drawPile.get(0);
            drawPile.remove(0);
            cardAction(card);
        }
        else {
            System.out.println("Invalid input. Please enter a 1 or 2.\n");
            printMenu();
        }
        System.out.println();
        printMenu();
    }

    /**
     * Asks the user if they want to place the card on the board or discard the card.
     * If they want to place the card, it calls the placeCard function.
     * If they want to discard the card, the card gets added to the discard pile.
     *
     * @param card the card they drew.
     */
    private void cardAction(int card) {
        System.out.println("\nYou took card " + card + " from the pile.");
        System.out.println("1 - Place the card on the board");
        System.out.println("2 - Discard the card");

        String line = sc.nextLine();

        if (line.equals("1")) {
            System.out.println();
            placeCard(card);
        }
        else if (line.equals("2")) {
            discardPile.add(card);
        }
        else {
            System.out.println("Invalid input. Please enter a 1 or a 2.\n");
            cardAction(card);
        }
        System.out.println();
    }

    /**
     * Gives the user options for where to place their card.
     * Once it's in place, the old card gets added to the discard pile.
     *
     * @param card the card they drew and need to place.
     */
    private void placeCard(int card) {
        printBoard();
        System.out.println("\nWhich slot (5, 10, 15, etc.) would you like card " + card + " to go in?");
        String input = sc.nextLine();

        int intInput = Integer.parseInt(input);

        if (intInput >= 5 && intInput <= 50 && intInput % 5 == 0) {
            int index = (intInput / 5) - 1;
            int oldCard = player1Cards.get(index);
            player1Cards.set(index, card);
            discardPile.add(oldCard);
        }
        else {
            System.out.println("Invalid input. Please choose a slot.\n");
            placeCard(card);
        }

        ArrayList<Integer> temp = new ArrayList<>(player1Cards);
        Collections.sort(temp);
        if (temp.equals(player1Cards)) {
            racko = true;
        }
    }

    /**
     * Makes a new instance of the Player class.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Player();
    }
}
