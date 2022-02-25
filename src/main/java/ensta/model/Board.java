package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.model.ship.ShipState;
import ensta.model.ship.NullShip;
import ensta.util.Orientation;
import ensta.util.ColorUtil;

/*
import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import javax.crypto.spec.ChaCha20ParameterSpec;
*/
public class Board implements IBoard
{

  private static final int DEFAULT_SIZE = 5;
  private String name;
  private int size;
  private ShipState[][] tableauNom;
  private Boolean[][] tableauCoup;


  public Board(String name)
  {
    this.name = name;
	  this.size = DEFAULT_SIZE;
    
    this.tableauNom = new ShipState[size][size];
    this.tableauCoup = new Boolean[size][size];
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        this.tableauNom[i][j] = new ShipState();
        this.tableauCoup[i][j] = false;
      }
    }
  }

  
  public Board(String name, int size)
  {
    this.name = name;
	  if(size > 26){
		  this.size = 26;
		  System.out.println("Taille limitée à 26");
	  } else {
		  this.size = size;
	  }
    
    this.tableauNom = new ShipState[size][size];
    this.tableauCoup = new Boolean[size][size];
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        this.tableauNom[i][j] = new ShipState();
        this.tableauCoup[i][j] = false;
      }
    }
  }



  public void printAll() {
    // lettres
	  String horizontal = "   ";
	  for(int i=65; i<65+size; ++i){
		  horizontal += (char)i + " ";
  	}
	
	  System.out.println("NAVIRES :");
	  System.out.println(horizontal);
	  for(int l=0; l<size; ++l){
		  System.out.print(l);
			  if(l>=10){
				  System.out.print(" ");
			  } else {
			  System.out.print("  ");
			  }
		  for(int c=0; c<size-1; ++c){
			  System.out.print(this.tableauNom[c][l] + " ");
		  }
		  System.out.println(this.tableauNom[size-1][l]);
	  }

	  System.out.println("");

	  System.out.println("FRAPPES :");
	  System.out.println(horizontal);
	  for(int l=0; l<size; ++l){
		  System.out.print(l);
			  if(l>=10){
				  System.out.print(" ");
			  } else {
			  System.out.print("  ");
			  }
		  for(int c=0; c<size; ++c){
			  if(this.tableauCoup[c][l]){
				  if(tableauNom[c][l].isStruck()){
            System.out.print(ColorUtil.colorize("x ", ColorUtil.Color.RED));
          } else {
            System.out.print("x ");
          }
			  } else {
				  System.out.print(". ");
			  }
        // saut de ligne à la fin
        if(c == size-1){
          System.out.println("");
        }
		  }
	  }
    System.out.println("");
  }


  // print seulement le tableau des coups
  public void printCoups() {
    // lettres
	  String horizontal = "   ";
	  for(int i=65; i<65+size; ++i){
		  horizontal += (char)i + " ";
  	}

	  System.out.println("FRAPPES :");
	  System.out.println(horizontal);
	  for(int l=0; l<size; ++l){
		  System.out.print(l);
			  if(l>=10){
				  System.out.print(" ");
			  } else {
			  System.out.print("  ");
			  }
		  for(int c=0; c<size; ++c){
			  if(this.tableauCoup[c][l]){
				  if(tableauNom[c][l].isStruck()){
            System.out.print(ColorUtil.colorize("x ", ColorUtil.Color.RED));
          } else {
            System.out.print("x ");
          }
			  } else {
				  System.out.print(". ");
			  }
        // saut de ligne à la fin
        if(c == size-1){
          System.out.println("");
        }
		  }
	  }
    System.out.println("");
  }

  
  public int getSize(){
    return size;
  }



  public boolean putShip(AbstractShip ship, Coords coords) {
    if(ship.getOrientation() == Orientation.NORTH){
      if(!canPutShip(ship, coords)){
        System.out.println("Coordonnées invalides !");
        return false;
      } else {
        for(int i=0; i<ship.getLength(); ++i){
          (tableauNom[coords.getX()][coords.getY() - i]).setShip(ship);
          (tableauNom[coords.getX()][coords.getY() - i]).setStruck(false);
        }
        return true;
      }
    } else if(ship.getOrientation() == Orientation.SOUTH){
      if(!canPutShip(ship, coords)){
        System.out.println("Coordonnées invalides !");
        return false;
      } else {
        for(int i=0; i<ship.getLength(); ++i){
          (tableauNom[coords.getX()][coords.getY() + i]).setShip(ship);
          (tableauNom[coords.getX()][coords.getY() + i]).setStruck(false);
        }
        return true;
      }
    } else if(ship.getOrientation() == Orientation.EAST){
      if(!canPutShip(ship, coords)){
        System.out.println("Coordonnées invalides !");
        return false;
      } else {
        for(int i=0; i<ship.getLength(); ++i){
          (tableauNom[coords.getX() + i][coords.getY()]).setShip(ship);
          (tableauNom[coords.getX() + i][coords.getY()]).setStruck(false);
        }
        return true;
      }
    } else if(ship.getOrientation() == Orientation.WEST){
      if(!canPutShip(ship, coords)){
        System.out.println("Coordonnées invalides !");
        return false;
      } else {
        for(int i=0; i<ship.getLength(); ++i){
          (tableauNom[coords.getX() - i][coords.getY()]).setShip(ship);
          (tableauNom[coords.getX() - i][coords.getY()]).setStruck(false);
        }
        return true;
      }
    }
    return false;
  }


  public boolean hasShip(Coords coords){
    return ((tableauNom[coords.getX()][coords.getY()]).getShip().getName() != "NullShip");
  }


  public boolean canPutShip(AbstractShip ship, Coords coords)
  {
    Orientation o = ship.getOrientation();
    int dx = 0, dy = 0;
    if (o == Orientation.EAST) {
      if (coords.getX() + ship.getLength() > this.size) {
        return false;
      }
      dx = 1;
    } else if (o == Orientation.SOUTH) {
      if (coords.getY() + ship.getLength() > this.size) {
        return false;
      }
      dy = 1;
    } else if (o == Orientation.NORTH) {
      if (coords.getY() + 1 - ship.getLength() < 0) {
        return false;
      }
      dy = -1;
    } else if (o == Orientation.WEST) {
      if (coords.getX() + 1 - ship.getLength() < 0) {
        return false;
      }
      dx = -1;
    }

    Coords iCoords = new Coords(coords);

    for (int i = 0; i < ship.getLength(); ++i) {
      if (this.hasShip(iCoords)) {
        return false;
      }
      iCoords.setX(iCoords.getX() + dx);
      iCoords.setY(iCoords.getY() + dy);
    }

    return true;
  }
  

  public Hit sendHit(int x, int y){

    if(this.tableauCoup[x][y]){
      System.out.println("Un coup a déjà été joué a cet endroit !");
      return Hit.MISS;
    } else {
      if(tableauNom[x][y].getShip().getName() == "NullShip"){
        return Hit.MISS;
      } else {
        tableauNom[x][y].setStruck(true);
        tableauNom[x][y].addStrike();
        if(tableauNom[x][y].getShip().getStrikeCount() == tableauNom[x][y].getShip().getLength()){
          return Hit.fromInt(tableauNom[x][y].getShip().getLength());
        }
        return Hit.STRIKE;
      }
    }  
  }

  public Boolean getHit(Coords coords){
    return tableauCoup[coords.getX()][coords.getY()];
  }

  public void setHit(boolean hit, Coords coords){
    tableauCoup[coords.getX()][coords.getY()] = hit;
  }
}
