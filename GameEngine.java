import java.util.*;

public class GameEngine{
  private Player[] players;
  private GameBoard board;
  
  public GameEngine(){
    board = new GameBoard();
    players = new Player[2];
  }
  
  public void start(){
    Scanner sc = new Scanner(System.in);
    boolean flag = true;
    int play = 0;
    while(flag){
      flag = false;
      System.out.println("1. Play Human vs. Human\n2. Play Human vs AI\n3. Play AI vs AI");
      play = sc.nextInt();
      if(play < 1 || play > 3){
        System.out.println("Please enter a valid number");
        sc.nextLine();
        flag = true;
      }
    }
    if(play == 1){
      this.initHumanVSHuman();
    }
    else if(play == 2){
      this.initHumanVSAI();
    } else{
      this.initAIVSAI();
    }
  }
  
  private void initHumanVSHuman(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Player 1");
    System.out.print("Name: ");
    String name = sc.nextLine();
    Player player1 = new HumanPlayer(name, 1);
    System.out.println("Player 2");
    System.out.print("Name: ");
    name = sc.nextLine();
    Player player2 = new HumanPlayer(name, 2);
    players[0] = player1;
    players[1] = player2;
    this.playGame();
  }
  
  private void initHumanVSAI(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Player 1");
    System.out.print("Name: ");
    String name = sc.nextLine();
    Player player1 = new HumanPlayer(name, 1);
    Player AI = this.createAI(2);
    players[0] = player1;
    players[1] = AI;
    this.playGame();
  }
  
  private void initAIVSAI(){
    Player AI1 = this.createAI(1);
    Player AI2 = this.createAI(2);
    players[0] = AI1;
    players[1] = AI2;
    this.playGame();
  }
  
  private Player createAI(int theNum){
    Scanner sc = new Scanner(System.in);
    boolean flag = true;
    int aiType = 0;
    Player ai;
    System.out.println("\nWhat AI would you like?");
    while(flag){
      flag = false;
      System.out.println("1. Dumb AI\n2. Basic AI\n3. Advanced AI");
      aiType = sc.nextInt();
      if(aiType < 1 || aiType > 3){
        System.out.println("Please enter a valid  AI number number");
        sc.nextLine();
        flag = true;
      }
    }
    if(aiType == 1){
      ai = new DumbAIPlayer(theNum); 
    }
    else if(aiType == 2){
      ai = new BasicAIPlayer(theNum);
    }
    else{
      ai = new AdvancedAIPlayer(theNum);
    }
    return ai;
  }
  private void playGame(){
    board.printBoard();
    while(board.getWinner() == -1 && !board.isFull()){
      int c = players[0].playChip(board);
      int r = board.getRowForCol(c);
      board.changeSlot(r,c,1);
      board.printBoard();
      if(board.getWinner() != -1 || board.isFull()){
        break;
      }
      c = players[1].playChip(board);
      r = board.getRowForCol(c);
      board.changeSlot(r,c,2);
      board.printBoard();
    }
    if(board.isFull()){
      System.out.println("Nobody won :(");
    }
    else if(board.getWinner() != -1){
      int win = board.getWinner();
      System.out.println(players[win-1].getName() + " won!");
    }
  }
  
  public void playGame(GameBoard gb){
    board = gb;
    this.initAIVSAI();
    this.playGame();
  }
}