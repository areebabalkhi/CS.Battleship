import java.util.ArrayList;

public class Ship {
    private ArrayList<String> locationCells;
    private String name;
    private ArrayList<String> takenCoords = new ArrayList<>();
    /*public void setLocationCells(ArrayList<String> locations) {
        this.locationCells = locations;
    }*/
    private enum Status {
        MISS,
        HIT,
        KILL
    }
    public String checkYourself(String stringGuess) {
        int index = locationCells.indexOf(stringGuess);
        Status result = Status.MISS;
        if(index >= 0){
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = Status.KILL;
            } else {
                result = Status.HIT;
            }
        }
        return String.valueOf(result);
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
    private int gridLength = 10;
    private int shipCount = 0;

    ArrayList<String> placeShip(int shipSize) {
        ArrayList<String> alphaCells = new ArrayList<>();
        boolean success = false;

        shipCount++;
        boolean horizontal = true;
        if ((shipCount % 2) == 1) {
            System.out.println(shipCount);
            horizontal = false;
        }
        while (!success) {
            int row = (int) (Math.random() * gridLength);
            int column = (int) (Math.random() * gridLength);
            String temp = String.valueOf(alphabet.charAt(column)).concat(Integer.toString(row));
            alphaCells.add(temp);
            success = checkCoords(temp);
            int i = 0;
            int tempr = row;
            int tempc = column;
            while (success && i < shipSize) {
                if (!horizontal) {
                    temp = String.valueOf(alphabet.charAt(column + 1)).concat(Integer.toString(row));
                    tempc++;
                } else {
                    temp = String.valueOf(alphabet.charAt(column)).concat(Integer.toString(row + 1));
                    tempr++;
                }
                success = checkCoords(temp);
                if (success) {
                    if (tempr > gridLength || tempc > gridLength) {
                        success = false;
                    } else {
                        alphaCells.add(temp);
                    }
                }
                i++;
            }
        }
        return alphaCells;
    }

    private boolean checkCoords(String coord) {
        boolean out = true;
        for (int i = 0; i < takenCoords.size(); i++) {
            if (takenCoords.get(i).equalsIgnoreCase(coord)) {
                out = false;
            }
        }
        return out;
    }
}
