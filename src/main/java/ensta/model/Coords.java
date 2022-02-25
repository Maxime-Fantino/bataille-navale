package ensta.model;

import java.util.Random;

public class Coords{
    private int x;
    private int y;

    public Coords(){
        this.x = 0;
        this.y = 0;
    }

    public Coords(int x, int y){
        this.x = x;
        this.y = y;
    }

    /* recopie */
    public Coords(Coords coords){
        this.x = coords.x;
        this.y = coords.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoords(Coords coords){
        this.x = coords.x;
        this.y = coords.y;
    }

    public boolean isInBoard(int size){
        return (this.x < size && this.y < size);
    }

    public static Coords randomCoords(int size){
        Random rd = new Random();
        return (new Coords(rd.nextInt(size), rd.nextInt(size))); 
    }
}