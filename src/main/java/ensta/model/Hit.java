package ensta.model;
import java.util.NoSuchElementException;

public enum Hit {
    MISS(-1, "manqué"),
    STRIKE(-2, "touché"),
    DESTROYER(2, "Destroyer"),
    SUBMARINE(3, "Sous-marin"),
    BATTLESHIP(4, "Battle ship"),
    CARRIER(5, "Carrier");

    /* ***
     * Attributs
     */
    private int value;
    private String label;

    /* ***
     * Constructeur
     */
    Hit(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    /* ***
     * Méthodes
     */
    public static Hit fromInt(int value) {
        for (Hit hit : Hit.values()) {
            if (hit.value == value) {
                return hit;
            }
        }
        throw new NoSuchElementException("no enum for value " + value);
    }

    public String toString() {
        return this.label;
    }
};
