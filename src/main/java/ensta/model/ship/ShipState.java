package ensta.model.ship;

import ensta.util.ColorUtil;
import ensta.model.ship.NullShip;

public class ShipState{
    AbstractShip ship;
    boolean struck;

    public ShipState(){
        this.ship = new NullShip();
        this.struck = false;
    }

    public ShipState(AbstractShip ship){
        this.ship = ship;
        this.struck = false;
    }
    
    public boolean isStruck(){
        return this.struck;
    }

    public void setStruck(boolean struck){
        this.struck = struck;
    }

    public void addStrike(){
        this.struck = true;
        if(this.ship.strikeCount < this.ship.size){
            this.ship.strikeCount++;
        }
    }

    public boolean isSunk(){
        return ship.isSunk();
    }

    public String toString(){
        //passage en String
        String res = "" + this.ship.label;
        if(struck){ res = ColorUtil.colorize(res, ColorUtil.Color.RED); }
        return res;
    }

    public AbstractShip getShip(){
        return this.ship;
    }

    public void setShip(AbstractShip ship){
        this.ship = ship;
    }
}