package ensta.model.ship;

import org.assertj.core.internal.Arrays;
import ensta.util.Orientation;

public class AbstractShip
{
  protected Character label;
  protected String name;
  protected int size;
  protected Orientation orientation;
  protected int strikeCount;

  public AbstractShip(String name,
                      Character label,
                      int size,
                      Orientation orientation)
  {
    this.name = name;
    this.label = label;
    this.size = size;
    this.orientation = orientation;
    this.strikeCount = 0;
  }

  public Character getLabel() { return this.label; }

  public String getName() { return this.name; }

  public int getLength() { return this.size; }

  public Orientation getOrientation() { return this.orientation; }

  public int getStrikeCount(){ return this.strikeCount; }

  public void setOrientation(Orientation orientation)
  {
    this.orientation = orientation;
  }

    public void addStrike() { this.strikeCount++; }

    public boolean isSunk() {
        String[] possibleNames = {"Destroyer", "Submarine", "BattleShp", "Carrier"};
        int[] sunkSizes = {2,3,4,5};
        boolean res = false;
        for(int i=0; i<4; ++i){
            if(this.name == possibleNames[i] && this.strikeCount == sunkSizes[i]){
                res = true;
            }
        }
        return res;
    }
}