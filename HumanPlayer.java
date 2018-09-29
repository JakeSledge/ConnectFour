import java.util.*;

public class HumanPlayer extends Player{
  public HumanPlayer(String theName, int thePlayerNum){
    super(theName, thePlayerNum);
  }
  
  public int playChip(GameBoard board){
    Scanner sc = new Scanner(System.in);
    String validCols = board.getValidColumns();
    boolean badCol = false;
    int passedCol = -1;
    while(!badCol){
      badCol = true;
      System.out.println(name + "'s turn");
      System.out.println("What column do you want to play a chip?\nValid cols are " + validCols);
      passedCol = sc.nextInt();
      sc.nextLine();
      String passed = "" + passedCol;
      if(validCols.indexOf(passed) == -1){
        System.out.println("Please enter a valid column");
        badCol = false;
      }
    }
    return passedCol;
  }
}