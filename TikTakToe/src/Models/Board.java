package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;

    public Board(int dimensions){
        size = dimensions;
        board = new ArrayList<>();
        for(int i = 0;i<dimensions;i++){
            //added multiple arrays

            board.add(new ArrayList<>());
            for(int j = 0;j<dimensions;j++){
                board.get(i).add(new Cell(i,j));
            }
        }
    }
    public void printBoard(){
        for(List<Cell> row : board){
            for(Cell cell : row){

//                if(cell.getPlayer() == null){
//                    System.out.printf("| - |");
//                }else{
//                    System.out.printf("| "+cell.getPlayer().getSymbol().getaChar()+" |");
//                }
                //Instead of above if conditions we will use below method and move above code there
                cell.display();
            }
            System.out.printf("\n");
        }
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
