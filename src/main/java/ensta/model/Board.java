package ensta.model;
/*
import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import javax.crypto.spec.ChaCha20ParameterSpec;
*/
public class Board //implements IBoard
{

  private static final int DEFAULT_SIZE = 10;
  private String name;
  private int size;
  private Character[][] tableauNom;
  private Boolean[][] tableauCoup;

  public Board(String name, int size)
  {
    this.name = name;
	if(size > 26){
		this.size = 26;
		System.out.println("Taille limitée à 26");
	} else {
		this.size = size;
	}
    
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        this.tableauNom[i][j] = '.';
        this.tableauCoup[i][j] = false;
      }
    }
  }

  public Board(String name)
  {
    this.name = name;
    for (int i = 0; i < DEFAULT_SIZE; ++i) {
      for (int j = 0; j < DEFAULT_SIZE; ++j) {
        this.tableauNom[i][j] = '.';
        this.tableauCoup[i][j] = false;
      }
    }
  }

  public void print() {
	String horizontal = "   ";
	for(int i=65; i<65+size; ++i){
		horizontal += (char)i;
	}
	
	System.out.println("Navires :");
	System.out.println(horizontal);
	for(int i=0; i<size; ++i){
		System.out.print(i+1);
			if(i>=10){
				System.out.print(" ");
			} else {
			System.out.print("  ");
			}
		for(int j=0; j<size-1; ++j){
			System.out.print(this.tableauNom[i][j] + " ");
		}
		System.out.println(this.tableauNom[i][size-1]);
	}

	System.out.println("");

	System.out.println("Frappes :");
	System.out.println(horizontal);
	for(int i=0; i<size; ++i){
		System.out.print(i+1);
			if(i>=10){
				System.out.print(" ");
			} else {
			System.out.print("  ");
			}
		for(int j=0; j<size-1; ++j){
			if(this.tableauCoup[i][j]){
				System.out.print("x ");
			} else {
				System.out.print(". ");
			}
		}
		if(this.tableauCoup[i][size-1]){
			System.out.print("x ");
		} else {
			System.out.print(". ");
		}
	}
  }

  /*
  public boolean canPutShip(AbstractShip ship, Coords coords)
  {
    Orientation o = ship.getOrientation();
    int dx = 0, dy = 0;
    if (o == Orientation.EAST) {
      if (coords.getX() + ship.getLength() >= this.size) {
        return false;
      }
      dx = 1;
    } else if (o == Orientation.SOUTH) {
      if (coords.getY() + ship.getLength() >= this.size) {
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
  */
}
