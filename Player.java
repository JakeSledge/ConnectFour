public abstract class Player{
  protected String name;
  protected int playerNum;
  
  public Player(String theName, int thePlayerNum){
    name = theName;
    playerNum = thePlayerNum;
  }
  
  public abstract int playChip(GameBoard board);
  //this will be overriten in every subclass
  
  public String getName(){
    return name;
  }
  
  public int getPlayerNum(){
    return playerNum;
  }
}