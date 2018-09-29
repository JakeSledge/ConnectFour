import java.util.*;

public class Main{
  public static void main(String[] args){
    GameBoard gb = new GameBoard();
    GameEngine ge = new GameEngine();
    AdvancedAIPlayer me = new AdvancedAIPlayer(1);
    AdvancedAIPlayer them = new AdvancedAIPlayer(2);
    initGrid(gb);
    ge.start();
    /*List<int[]> test = new ArrayList<int[]>();
    int[] a = {5,0};
    int[] b = {-1,1};
    int[] c = {0,2};
    int[] d = {-1,3};
    int[] e = {-2,4};
    test.add(a);
    test.add(b);
    test.add(c);
    test.add(d);
    test.add(e);
    int be = me.getScoreClosestToZero(test);
    System.out.println(be);
    System.out.println("Should be 2");*/
    //gb.printBoard();
    //int c = me.getScoreOfColumn(4,gb,0,them,1);
    //int c = me.playChip(gb);
    //System.out.println(c);
    //gb.placeChipInColumn(me.getPlayerNum(),c);
    //gb.printBoard();
    //ge.playGame(gb);
    //next class init a grid that is 3 or 4 slots from winning to perfect that. THEN move on to starting from the beggining
  }
  
  public static void initGrid(GameBoard gb){
    gb.changeSlot(5,0,1);
    gb.changeSlot(4,0,1);
    gb.changeSlot(5,2,1);
    gb.changeSlot(5,3,1);
    gb.changeSlot(4,3,1);
    for(int r = 0; r < 6; r++){
      if(r%2==0){
        gb.changeSlot(r,0,1);
        gb.changeSlot(r,1,1);
        gb.changeSlot(r,2,2);
        gb.changeSlot(r,3,2);
        gb.changeSlot(r,4,1);
        gb.changeSlot(r,5,1);
        gb.changeSlot(r,6,2);
      } else{
        gb.changeSlot(r,0,2);
        gb.changeSlot(r,1,2);
        gb.changeSlot(r,2,1);
        gb.changeSlot(r,3,1);
        gb.changeSlot(r,4,2);
        gb.changeSlot(r,5,2);
        gb.changeSlot(r,6,1);
      }
    }
    gb.changeSlot(0,4,0);
    gb.changeSlot(1,4,0);
    gb.changeSlot(2,4,0);
    gb.changeSlot(1,5,1);
    gb.changeSlot(2,4,2);
    gb.changeSlot(1,3,1);
    gb.changeSlot(1,6,1);
    gb.changeSlot(0,3,0);
    gb.changeSlot(2,4,0);
    gb.changeSlot(0,2,0);
    gb.changeSlot(0,1,0);
    gb.changeSlot(1,3,0);
    gb.changeSlot(1,2,0);
  }
}