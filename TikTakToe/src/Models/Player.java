package Models;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private long id;
    private PlayerType playerType;
    private Scanner scanner;
    public Player(Long id,String name,Symbol symbol,PlayerType playerType){
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }
    public Move makeMove(Board board){
        System.out.println("Please tell row count you want to move (Starting from 0)");
        int row = scanner.nextInt();
        System.out.println("Please tell col count you want to move (Starting from 0)");
        int col = scanner.nextInt();
        // player is not allowed to cheat in the game by overwriting his symbol in other players move so we
        // will return the move and decide the game to handle it
        return new Move(new Cell(row,col),this);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
