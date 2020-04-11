import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ship {
    private ArrayList<String> locationCells;
    private String name;
    private ArrayList<String> takenCoords = new ArrayList<String>();
    /*public void setLocationCells(ArrayList<String> locations) {
        this.locationCells = locations;
    }*/

    public String checkYourself(String stringGuess) {
        int index = locationCells.indexOf(stringGuess);
        String result = "miss";
        if(index >= 0){
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = "kill";
            } else {
                result = "hit";
            }
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public Ship(int shipSize, String shipName) {
        this.locationCells = placeShip(shipSize);
        this.name = shipName;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int shipCount = 0;

    ArrayList<String> placeShip(int shipSize) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        String temp = null;
        int[] coords = new int[shipSize];
        int attempts = 0;
        boolean success = false;
        int location = 0;

        shipCount++;
        int incr = 1;
        if ((shipCount % 2) == 1) {
            incr = gridLength;
        }

        while (!success && attempts++ < 200) {
            location = (int) (Math.random() * gridSize);
            int x = 0;
            success = true;
            while (success && x < shipSize) {
                if (grid[location] == 0) {
                    coords[x++] = location;
                    location += incr;
                    if (location >= gridSize) {
                        success = false;
                    }
                    if (x > 0 && (location % gridLength == 0)) {
                        success = false;
                    }
                } else {
                    success = false;
                }
            }
        }
        int x = 0;
        int row = 0;
        int column = 0;
        while (x < shipSize) {
            grid[coords[x]] = 1;
            row = (int) (coords[x] / gridLength);
            column = coords[x] % gridLength;
            temp = String.valueOf(alphabet.charAt(column));
            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
        }
        for (String coordinate : alphaCells) {
            if (takenCoords.size() > 0) {
                for (int j = 0; j < takenCoords.size(); j++) {
                    if (coordinate == takenCoords.get(j)) {
                        alphaCells = placeShip(shipSize);
                        break;
                    }
                }
            }
        }
        for (String coordinate : alphaCells) {
            takenCoords.add(coordinate);
        }
        return alphaCells;
    }
}
