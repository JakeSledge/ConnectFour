import java.util.*;

public class AdvancedAIPlayer extends Player {
  private final static int[] looks = {3,3,3,4,4,4,5,5,7,8,8,8,9,9,9,10,10,10,10,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11};
  private int moves;
  public AdvancedAIPlayer(int thePlayerNum){
    super("Advanced", thePlayerNum);
    moves = 0;
  }
  
  public boolean isEqual(AdvancedAIPlayer other){
    if(other.getPlayerNum() == this.playerNum){
      return true;
    } return false;
  }
  
  public int playChip(GameBoard board){
    int theirNum;
    if(this.playerNum == 1){
      theirNum = 2;
    }
    else{
      theirNum = 1;
    }
    AdvancedAIPlayer against = new AdvancedAIPlayer(theirNum);
    Scanner sc = new Scanner(System.in);
    //System.out.println("How much lookahead?");
    //int look = sc.nextInt();
    //if(look > 50){
      int look = looks[moves];
    //}
    moves++;
    int col = this.playChip(board,against,look);
    return col;
  }
  
  public int playChip(GameBoard board, AdvancedAIPlayer against, int look){
    List<int[]> cols = new ArrayList<int[]>();
    int[] sco = new int[2];
    for(int i = 0; i < board.getNumColumns(); i++){
      if(board.isColumnFull(i)){
        continue;
      }
      sco[0] = this.getScoreOfColumn(i,board,0,against,look);
      System.out.println(sco[0] + " " + i);
      sco[1] = i;
      cols.add(sco);
    }
    return getScoreClosestToZero(cols);
  }
  
  public int getScoreOfColumn(int col, GameBoard board, int counter, AdvancedAIPlayer against, int look){
    if(counter==look || board.isFull()){
      return 700;
    }
    counter++;
    GameBoard gb = board.copyBoard();
    BasicAIPlayer basic = new BasicAIPlayer(against.getPlayerNum());
    int c = basic.playChip(gb);
    int r = gb.getRowForCol(c);
    gb.placeChipInColumn(this.playerNum, c);
    if(gb.getWinner() != -1 || gb.isFull()){
      if(gb.getWinner() == playerNum){
        return counter;
      }
      else if(gb.getWinner() != -1){
        return -1*counter*1000;
      }
      else if(gb.isFull()){
        return 700;
      }
    }
    gb.changeSlot(r,c,0);
    gb.placeChipInColumn(against.getPlayerNum(),c);
    System.out.println("Done");
    if(gb.getWinner() == against.getPlayerNum()){
      System.out.println("----------------------TEST BOARD------------------------");
      gb.printBoard();
      return counter-1;
    }
    gb.changeSlot(r,c,0);
    gb.placeChipInColumn(this.playerNum, c);
    c = basic.playChip(gb);
    gb.placeChipInColumn(against.getPlayerNum(), c);
    List<int[]> cols = new ArrayList<int[]>();
    int[] sco = new int[2];
    for(int i = 0; i < board.getNumColumns(); i++){
      if(board.isColumnFull(i)){
        continue;
      }
      sco[0] = this.getScoreOfColumn(i,gb,counter,against,look);
      sco[1] = i;
      cols.add(sco);
    }
    return getScoreClosestToZero(cols);
  }
  
  public static int getGreatestScoreCol(List<int[]> scores){
    if(scores.size() == 0){
      return -1;
    }
    int[] temp = scores.get(0);
    int highest = temp[0];
    int col = temp[1];
    for(int[] nums: scores){
      if(nums[0] > highest){
        col = nums[1];
        highest = nums[0];
      }
    }
    return col;
  }
  
  public static int getLeastScoreCol(List<int[]> scores){
    if(scores.size() == 0){
      return -1;
    }
    int[] temp = scores.get(0);
    int lowest = temp[0];
    int col = temp[1];
    for(int[] nums: scores){
      if(nums[0] < lowest){
        col = nums[1];
        lowest = nums[0];
      }
    }
    return col;
  }
  
  public static int getScoreClosestToZero(List<int[]> scores){
    if(scores.size() == 0){
      return -1;
    }
    int[] temp = scores.get(0);
    int closest = Math.abs(temp[0]);
    int col = temp[1];
    for(int[] nums : scores){
      if(Math.abs(nums[0]) < closest){
        closest = Math.abs(nums[0]);
        col = nums[1];
      }
    }
    return col;
  }
}