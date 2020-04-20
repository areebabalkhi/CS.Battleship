import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scones = new Scanner(System.in);
        Game game = new Game();
        System.out.println("How many ships? (number from 1 - 7)");
        int numShips = Integer.parseInt(scones.nextLine());
        game.setupGame(numShips);
        game.startPlaying();
    }
}
