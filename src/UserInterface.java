import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserInterface {

    private ArrayList<Integer> heads = new ArrayList<>();
    private ArrayList<Integer> tails = new ArrayList<>();
    private int coinflips = 1;
    private Random randomNumber = new Random();
    private int headsWin = 0;
    private int tailsWin = 0;
    private int ties = 0;
    private int roundsPlayed = 1;
    private int coinTurns = 1;
    private String playerOne;
    private String playerTwo;
    

    public void menu() {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Enter name of player one: ");
            playerOne = scanner.nextLine();
            System.out.println("Enter name of player two: ");
            playerTwo = scanner.nextLine();

            while (true) {

                System.out.println(
                        "type 1. to toss a dice several times, or 2. for just one time, 3. to reset, 4. to change players and 5. to exit: ");

                int input = scanner.nextInt();

                switch (input) {
                    case 1:
                        System.out.println("How many turns do you want to play?");
                        int turns = scanner.nextInt();
                        while (coinTurns <= turns) {
                            start();
                            coinTurns++;
                        }
                        coinTurns = 1;
                        break;
                    case 2:
                        start();
                        break;
                    case 3:
                        clearAll();
                        break;
                    case 4:
                        newPlayers();
                    case 5:
                        return;

                }
            }
        }
    }

    public void start() {
        while (coinflips <= 100) {
            int coin = randomNumber.nextInt(2) + 1;

            if (coin == 1) {
                heads.add(coinflips);
            } else {
                tails.add(coinflips);
            }

            coinflips++;

        }
        count();
        roundsPlayed++;
    }

    public void count() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("** COIN TOSS **");

        System.out.println("Heads: " + heads.size() + " : Tails: " + tails.size());
        System.out.println("************");
        System.out.println("Heads: " + heads.size() + "\nHeads count: " + heads);
        System.out.println("***********");
        System.out.println("Tails: " + tails.size() + "\nTails count: " + tails + "\n");

        if (heads.size() > tails.size()) {
            System.out.println(playerOne + " is the winner!");
            headsWin++;
        } else if (heads.size() == tails.size()) {
            ties++;
            System.out.println("Tied!");
        } else {
            System.out.println(playerTwo + " is the winner!");
            tailsWin++;
        }

        System.out.println("\n*******");
        double total = headsWin + tailsWin + ties;
        System.out.println("Rounds played: " + roundsPlayed);
        System.out.println("\nTimes " + playerOne + " have won: " + headsWin + "   Percentage: "
                + (headsWin / total) * 100 + " %");
        System.out.println(
                "Times " + playerTwo + " have won: " + tailsWin + "   Percentage: " + (tailsWin / total) * 100 + " %");
        System.out.println("Tied rounds: " + ties + "\t      Percentage: " + (ties / total) * 100 + " %");
        if (headsWin > tailsWin) {
            System.out.println("\n" + playerOne + " won with: " + (headsWin - tailsWin) + " more won rounds! \n");
        } else if (headsWin == tailsWin) {
            System.out.println("\nIt's a tie!");
        } else {
            System.out.println("\n" + playerTwo + " won with: " + (tailsWin - headsWin) + " more won rounds! \n");
        }

        coinflips = 1;
        tails.clear();
        heads.clear();

    }

    public void clearAll() {
        headsWin = 0;
        tailsWin = 0;
        ties = 0;
        roundsPlayed = 1;
        coinTurns = 1;
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    public void newPlayers() {
        clearAll();
        menu();

    }

}