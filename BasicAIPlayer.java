import java.util.*;

public class BasicAIPlayer extends Player{
  public BasicAIPlayer(int thePlayerNum){
    super("Basic", thePlayerNum);
  }
  
  public int playChip(GameBoard board){
    int c = board.getNumColumns();
    List<Integer> validCols = board.getValidColumnsAsIntList();
    for(int i : validCols){
      GameBoard gb = board.copyBoard();
      int r = gb.getRowForCol(i);
      gb.changeSlot(r,i,this.playerNum);
      if(gb.getWinner() == playerNum){
        return i;
      }
      gb.changeSlot(r,i,1);
      if(gb.getWinner() == 1){
        return i;
      }
    }
    int randInd = (int)(Math.random() * validCols.size());
    return validCols.remove(randInd);
  }
  
  
}