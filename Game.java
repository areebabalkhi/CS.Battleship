import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Ship> listOfShips = new ArrayList<>();
    private int numOfGuesses = 0;
    private String[] listOfShipNames = {"Boaty McBoatyface", "SS Enterprise", "Shippy McShipface", "Sailboat", "Rowboat", "Dead boat", "Curiosity", "Spirit", "Sojourner", "Opportunity", "Apollo 11", "Hubble Space Telescope", "Deathboat", "Lifeboat", "International Space Station", "Queequeg", "Argo"};
    //private int numShips = 0;

    public void setupGame(int numShips) {
        for (int i = 0; i < numShips; i++) {
            Ship ship = new Ship(3, listOfShipNames[i]);
            listOfShips.add(ship);
        }
        //this.numShips = numShips;
        System.out.println("Your goal is to sink " + listOfShips.size() + " ships. Good luck!");
    }

    public void startPlaying() {
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
        if (numOfGuesses == listOfShips.size() * 3) {
            System.out.println("Perfect! You sunk the ships in the minimum number of guesses!");
        } else if (numOfGuesses <= listOfShips.size() * 4) {
            System.out.println("Pretty good!");
        } else {
            System.out.println("Took you long enough.");
        }
        System.out.println("It took you " + numOfGuesses + " guesses to sink all of the ships");
    }

    private String getUserInput() {
        System.out.println("Take a guess (e.g. a0) a-j, 0-9");
        return scan.nextLine();
    }


}
