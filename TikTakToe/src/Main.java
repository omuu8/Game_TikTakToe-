import Controllers.GameController;
import Exceptions.DuplicateSymbolException;
import Exceptions.MoreThanOneBotException;
import Exceptions.PlayersCntDimensionsMismatchException;
import Models.*;
import Strategies.winningStrategies.ColWinningStrategy;
import Strategies.winningStrategies.DiagonalWinningStrategy;
import Strategies.winningStrategies.RowWinningStrategy;
import Strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DuplicateSymbolException, PlayersCntDimensionsMismatchException, MoreThanOneBotException {

        Scanner sc = new Scanner(System.in);

        GameController gameController = new GameController();
            try {
                int dimensions = 3;
                List<Player> players = new ArrayList<>();
                players.add(new Player(1L, "Omkar", new Symbol('X'), PlayerType.HUMAN));
                players.add(new Bot(2L, "GPT", new Symbol('O'), BotDifficultyLevel.HARD));
                System.out.println(players);

                List<WinningStrategy> winningStrategies = List.of(
                        new RowWinningStrategy(),
                        new ColWinningStrategy(),
                        new DiagonalWinningStrategy()
                );

                Game game = gameController.startGame(dimensions, players, winningStrategies);

                while (gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
                    //while game is in progress
                    //1) will print the board
                    //2) x's turn
                    //3) ask to makeMove
                    gameController.printBoard(game);

                    System.out.println("Does anyone wants to undo? (y/n)");

                    String undoAns = sc.next();

                    if (undoAns.equalsIgnoreCase("y")) {
                        gameController.undo(game);
                        continue;
                    }

                    // I will merge whose turn and ask to makeMove because we will ask the human player that which move to make and bot dont need it
                    gameController.makeMove(game);
                }
                System.out.println("Game is finished");
                GameState state = gameController.checkState(game);

                if (state.equals(GameState.DRAW)) {
                    System.out.println("It is a draw");
                } else {
                    System.out.println("Winner is " + gameController.getWinner(game).getName());
                }
            } catch (Exception e) {
                throw e;
            }

    }
}