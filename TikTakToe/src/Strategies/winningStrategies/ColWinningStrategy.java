package Strategies.winningStrategies;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy{

    //in each col we need to check the count of symbols
    // for each col we will store count of every symbol in that particular col
    private final Map<Integer,HashMap<Symbol,Integer>> counts = new HashMap<>();
    //<ColNo,Symbol,Count>
    public boolean checkWinner(Board board, Move move){

        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!counts.containsKey(col)){
            counts.put(col,new HashMap<>());
        }

        HashMap<Symbol,Integer> colMap = counts.get(col);
        if(!colMap.containsKey(symbol)){
            colMap.put(symbol,0);
        }
        colMap.put(symbol,colMap.get(symbol)+1);

        if(colMap.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        HashMap<Symbol,Integer> colMap = counts.get(col);

        colMap.put(symbol,colMap.get(symbol)-1);
    }
}
