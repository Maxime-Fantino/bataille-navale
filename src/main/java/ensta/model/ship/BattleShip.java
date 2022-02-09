package ensta.model.ship;

import ensta.util.Orientation;

public class BattleShip extends AbstractShip{
    BattleShip(String name, Character label, int size, Orientation orientation){
        super(name, label, size, orientation);
    }

    BattleShip(String name, Character label, int size){
        super(name, label, size, Orientation.WEST);
    }
}