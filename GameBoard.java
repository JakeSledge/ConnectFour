import java.util.*;

public class GameBoard{
  private int[][] grid;
  public static final int DEFAULT_ROWS = 6;
  public static final int DEFUALT_COLUMNS = 7;
  
  public GameBoard(){
    //this constructor makes the defualt game board
    grid = new int[DEFAULT_ROWS][DEFUALT_COLUMNS];
    for(int r = 0; r < grid.length; r++){
      for(int c = 0; c < grid[0].length; c++){
        grid[r][c] = 0;
      }
    }
  }
  
  public GameBoard(int[][] chips){
    //this constructor effectively copies another game board
    grid = new int[chips.length][chips[0].length];
    for(int r = 0; r < chips.length; r++){
      for(int c = 0; c < chips[0].length; c++){
        grid[r][c] = chips[r][c];
      }
    }
  }
  
  public GameBoard(int rows, int cols){
    grid = new int[rows][cols];
  }
  
  public int[][] getGridCopy(){
    int[][] toRet = new int[grid.length][grid[0].length];
    for(int r = 0; r < grid.length; r++){
      for(int c = 0; c < grid[0].length; c++){
        toRet[r][c] = grid[r][c];
      }
    }
    return toRet;
  }
  public int getNumColumns(){
    return grid[0].length;
  }
  
  public int getNumRows(){
    return grid.length;
  }
  
  public void printBoard(){
    //this method formats and prints the current game board
    System.out.println(" ------------- ");
    for(int r = 0; r < grid.length; r++){
      System.out.print("|");
      for(int c = 0; c < grid[0].length; c++){
        if(c != 0){
          System.out.print(" ");
        }
        if(grid[r][c] == 0){
          System.out.print("_");
        }
        else if(grid[r][c] == 1){
          System.out.print("O");
        }
        else if(grid[r][c] == 2){
          System.out.print("X");
        }
      }
      System.out.println("|");
    }
    System.out.println(" ------------- ");
  }
  
  public GameBoard copyBoard(){
    GameBoard toRet = new GameBoard(this.grid);
    return toRet;
  }
  
  public String getRowAsString(int row){
    //this method creates a string of the desired row ex: "011012"
    String theRow = "";
    for(int i = 0; i < grid[row].length; i++){
      theRow += grid[row][i];
    }
    return theRow;
  }
  
  public String getColumnAsString(int col){
    String theCol = "";
    for(int r = 0; r < grid.length; r++){
      theCol += grid[r][col];
    }
    return theCol;
  }
  
  public String getUpDiagonalAsString(int r, int c){
    if(r + c < grid[0].length-1){
      r = r + c;
      c = 0;
    }
    else{
      int val = grid.length - r;
      c = c - val + 1;
      r = grid.length-1;
    }
    String theDiag = this.getUpDiagonalAsString(r,c,"");
    return theDiag;
  }
  
  public String getUpDiagonalAsString(int r, int c, String s){
    s += grid[r][c];
    if(r == 0 || c == grid[0].length-1){
      return s;
    }
    else{
      s = this.getUpDiagonalAsString(r-1,c+1, s);
      return s;
    }
  }
  
  public String getDownDiagonalAsString(int r, int c){
    if(r - c >= grid.length-grid[0].length){
      int val = grid.length-1-r;
      c = c + val;
      r = grid.length-1;
    }
    else{
      int val = grid[0].length - c;
      c = grid[0].length - 1;
      r = r + val;
    }
    String theDiag = this.getDownDiagonalAsString(r,c,"");
    return theDiag;
  }
  
  public String getDownDiagonalAsString(int r, int c, String s){
    s += grid[r][c];
    if(r==0 || c == 0){
      return s;
    }
    else{
      s = this.getDownDiagonalAsString(r-1,c-1,s);
      return s;
    }
  }
 
  public int getWinner(){
    int r = 0;
    int c = 0;
    String row;
    String col;
    String upDiag;
    String downDiag;
    while(r < grid.length){
      row = this.getRowAsString(r);
      col = this.getColumnAsString(c);
      upDiag = this.getUpDiagonalAsString(r,c);
      downDiag = this.getDownDiagonalAsString(r,c);
      int[] rowArr = checkWinner(row);
      int[] colArr = checkWinner(col);
      int[] upDiagArr = checkWinner(upDiag);
      int[] downDiagArr = checkWinner(downDiag);
      if(rowArr[0] == 1){
        return rowArr[1];
      }
      else if(colArr[0] == 1){
        return colArr[1];
      }
      else if(upDiagArr[0] == 1){
        return upDiagArr[1];
      }
      else if(downDiagArr[0] == 1){
        return downDiagArr[1];
      }
      if(c == grid[0].length-1){
        r++;
        c = 0;
      }
      else {
        c++;
      }
    }
    return -1;
  }
 
  public int[] checkWinner(String s){
    int[] toRet = new int[2];
    if(s.indexOf("1111") >= 0){
      toRet[0] = 1;
      toRet[1] = 1;
    }
    else if(s.indexOf("2222") >= 0){
      toRet[0] = 1;
      toRet[1] = 2;
    } else{
      toRet[0] = -1;
      toRet[1] = -1;
    }
    return toRet;
  }
  
  public void placeChipInColumn(int aPlayerNum, int col){
    int row = getRowForCol(col);
    changeSlot(row,col,aPlayerNum);
  }
  
  public void changeSlot(int r, int c, int val){
    if(!(val == 0 || val == 1 || val == 2)){
      System.out.println("Invalid Value");
      return;
    }
    grid[r][c] = val;
  }
  
  public int getRowForCol(int c){
    String col = this.getColumnAsString(c);
    col = GameBoard.reverse(col);
    int ind = col.indexOf("0");
    if(ind == -1){
      return -1;
    }
    else{
      return grid.length - 1 - ind;
    }
  }
  
  private static String reverse(String s){
    String[] Char = new String[s.length()];
    for(int i = 0; i < s.length(); i++){
      Char[i] = s.substring(i,i+1);
    }
    String temp = "";
    for(int i = 0; i < Char.length; i++){
      temp += Char[Char.length-1-i];
    }
    return temp;
  }
  
  public boolean isColumnFull(int c){
    if(getRowForCol(c) == -1){
      return true;
    }
    return false;
  }
  
  public boolean isFull(){
    int full = 0;
    for(int c = 0; c < grid[0].length; c++){
      if(this.isColumnFull(c)){
        full++;
      }
    }
    if(full == grid[0].length){return true;}
    return false;
  }
  
  public String getValidColumns(){
    String validCols = "";
    for(int c = 0; c < grid[0].length; c++){
      if(!this.isColumnFull(c)){
        validCols += c + ", ";
      }
    }
    return validCols.substring(0,validCols.length()-2);
  }
  
  public List getValidColumnsAsIntList(){
    List<Integer> validCols= new ArrayList<Integer>();
    for(int c = 0; c < grid[0].length; c++){
      if(!this.isColumnFull(c)){
        validCols.add(c);
      }
    }
    return validCols;
  }
}