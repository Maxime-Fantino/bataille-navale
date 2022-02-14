package ensta.model.ship;

import ensta.util.Orientation;

public class AbstractShip{
    private Character label;
    private String name;
    private int size;
    private Orientation orientation;


    public AbstractShip(String name, Character label, int size, Orientation orientation){
        this.name = name;
        this.label = label;
        this.size = size;
        this.orientation = orientation;
    }


    public Character getLabel(){
        return this.label;
    }

    public String getName(){
        return this.name;
    }

    public int getLength(){
        return this.size;
    }

    public Orientation getOrientation(){
        return this.orientation;
    }

    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
    }  
}