import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Ship> listOfShips = new ArrayList<>();
    private int numOfGuesses = 0;
    private String[] listOfShipNames = {"Boaty McBoatyface", "SS Enterprise", "Shippy McShipface", "Sailboat", "Rowboat", "Dead boat", "Curiosity", "Spirit", "Sojourner", "Opportunity", "Apollo 11", "Hubble Space Telescope", "Deathboat", "Lifeboat", "International Space Station", "Queequeg", "Argo"};

    private void setupGame(int numShips) {
        for (int i = 0; i < numShips; i++) {
            Ship ship = new Ship(3, listOfShipNames[i]);
            listOfShips.add(ship);
        }
        System.out.println("Your goal is to sink " + listOfShips.size() + " ships. Good luck!");
    }

    private void startPlaying() {
        while (!listOfShips.isEmpty()) {
            String userGuess = getUserInput();
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "miss";
        String shipName = "";
        for (Ship ship : listOfShips) {
            result = ship.checkYourself(userGuess);
            if (result.equalsIgnoreCase("hit")) {
                shipName = ship.getName();
                break;
            } else if (result.equalsIgnoreCase("kill")) {
                listOfShips.remove(ship);
                shipName = ship.getName();
                break;
            }
        }
        System.out.println(result + " " + shipName);
    }

    private void finishGame() {
        System.out.println("All ships have been sunk!");
        if (numOfGuesses == 9) {
            System.out.println("Perfect! You sunk the ships in the minimum number of guesses!");
        } else if (numOfGuesses <= 18) {
            System.out.println("Pretty good!");
        } else {
            System.out.println("Took you long enough.");
        }
        System.out.println("It took you " + numOfGuesses + " guesses to sink all of the ships");
    }

    private String getUserInput() {
        System.out.println("Take a guess (e.g. a0) a-g, 0-6");
        return scan.nextLine();
    }

    public static void main(String[] args) {
        Scanner scones = new Scanner(System.in);
        Game game = new Game();
        System.out.println("How many ships? (number from 1 - 7)");
        int numShips = Integer.parseInt(scones.nextLine());
        game.setupGame(numShips);
        game.startPlaying();
    }
}
