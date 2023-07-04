package Strategies.winningStrategies;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{

    private final Map<Integer, HashMap<Symbol,Integer>> counts = new HashMap<>();
    //<ColNo,Symbol,Count>
    public boolean checkWinner(Board board, Move move){

        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!counts.containsKey(row)){
            counts.put(row,new HashMap<>());
        }

        HashMap<Symbol,Integer> rowMap = counts.get(row);
        if(!rowMap.containsKey(symbol)){
            rowMap.put(symbol,0);
        }
        rowMap.put(symbol,rowMap.get(symbol)+1);

        if(rowMap.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        HashMap<Symbol,Integer> rowMap = counts.get(row);

        rowMap.put(symbol,rowMap.get(symbol)-1);

    }
}
