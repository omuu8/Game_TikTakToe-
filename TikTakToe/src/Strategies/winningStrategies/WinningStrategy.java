package Strategies.winningStrategies;

import Models.Board;
import Models.Move;

public interface WinningStrategy {

    boolean checkWinner(Board board, Move move);

    // Here we are handling the undo using kuch kuch hota hai method
    void handleUndo(Board board, Move move);


}
