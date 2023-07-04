package Controllers;

import Exceptions.DuplicateSymbolException;
import Exceptions.MoreThanOneBotException;
import Exceptions.PlayersCntDimensionsMismatchException;
import Models.Game;
import Models.GameState;
import Models.Player;
import Strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimOfBoard, List<Player> players, List<WinningStrategy> winningStrategies) throws DuplicateSymbolException, PlayersCntDimensionsMismatchException, MoreThanOneBotException {

       return Game.getBuilder()
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .setDimensions(dimOfBoard)
                .build();

    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public void undo(Game game){
        game.undo();
    }
    public GameState checkState(Game game){
        return game.getGameState();
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void  printBoard(Game game){
        game.printBoard();
    }
}
