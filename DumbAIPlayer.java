import java.util.*;

public class DumbAIPlayer extends Player{
  public DumbAIPlayer(int thePlayerNumber){
    super("Dummy", thePlayerNumber);
  }
  
  public int playChip(GameBoard board){
    if(board.isFull()){
      return -1;
    }
    List<Integer> validCols = new ArrayList<Integer>();
    validCols = board.getValidColumnsAsIntList();
    int randInd = (int)(Math.random() * validCols.size());
    return validCols.remove(randInd);
  }
}