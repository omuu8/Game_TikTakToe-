package Models;

import Exceptions.DuplicateSymbolException;
import Exceptions.MoreThanOneBotException;
import Exceptions.PlayersCntDimensionsMismatchException;
import Strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;

    private Board board;

    private List<Move> moves;

    private Player winner;
    private int nxtMovePlayerIdx;
    private GameState gameState;
    public static Builder getBuilder(){
        return new Builder();
    }

    private boolean checkWinner(Board board,Move move){
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }
    public void makeMove(){
        Player currMovePlayer = players.get(nxtMovePlayerIdx);
        System.out.println("It is "+currMovePlayer.getName()+"'s turn");
        Move move = currMovePlayer.makeMove(board);
        System.out.println("It is "+currMovePlayer.getName()+"'has made a move at row: "+move.getCell().getRow()
        +", column: "+move.getCell().getCol()+".");
        if(!validateMove(move)){
            System.out.println("Invalid Move please try again");
            return;
        }
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cellToUpdate = board.getBoard().get(row).get(col);
        cellToUpdate.setCellState(CellState.FILLED);
        cellToUpdate.setPlayer(currMovePlayer);
        Move finalMove = new Move(cellToUpdate,currMovePlayer);
        moves.add(finalMove);

        nxtMovePlayerIdx += 1;
        nxtMovePlayerIdx %= players.size();

        if(checkWinner(board,finalMove)){
            gameState = GameState.WIN;
            winner = currMovePlayer;
        }
        if(moves.size() == this.board.getSize()*this.board.getSize()){
            gameState = GameState.DRAW;

        }
    }

    public void undo(){
        if(moves.size() == 0){
            System.out.println("No move to undo");
            return;
        }
        Move lastMove = moves.get(moves.size()-1);

        moves.remove(lastMove);

        Cell cell = lastMove.getCell();
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);
        for(WinningStrategy winningStrategy : winningStrategies){
            winningStrategy.handleUndo(board,lastMove);
        }
        nxtMovePlayerIdx -= 1;
        nxtMovePlayerIdx += (nxtMovePlayerIdx+players.size())%players.size();
    }
    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return true;
        }
        if(row >= board.getSize() && col >= board.getSize()){
            return false;
        }
        return false;
    }
    private Game(int dimensions,List<Player> players,List<WinningStrategy> winningStrategies){
        this.nxtMovePlayerIdx = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.players = players;

        this.winningStrategies = winningStrategies;
        this.board = new Board(dimensions);
    }
    public void printBoard(){
        board.printBoard();
    }


    private List<WinningStrategy> winningStrategies;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextMovePlayerIndex() {
        return nxtMovePlayerIdx;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nxtMovePlayerIdx = nextMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        private int dimensions;
        public Builder(){
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimensions = 0;
        }
        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }
        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
        private void validateBotCnt() throws MoreThanOneBotException{
            int botCnt = 0;
            for(Player p : players){
                if(p.getPlayerType().equals(PlayerType.BOT)){
                    botCnt+=1;
                }
            }
            if(botCnt > 1){
                throw new MoreThanOneBotException();
            }

        }
        private void validateDimensionsAndPlayersCnt() throws PlayersCntDimensionsMismatchException{

            if(players.size() != dimensions-1){
                throw new PlayersCntDimensionsMismatchException();
            }

        }
        private void validateUniqueSymbolsForPlayers() throws DuplicateSymbolException{

            Map<Character,Integer> symbolCnt = new HashMap<>();
            for(Player p : players){
                if(!symbolCnt.containsKey(p.getSymbol().getaChar())){
                    symbolCnt.put(p.getSymbol().getaChar(),0);
                }
                symbolCnt.put(p.getSymbol().getaChar(),symbolCnt.get(p.getSymbol().getaChar()) +1);
                if(symbolCnt.get(p.getSymbol().getaChar()) > 1){
                    throw new DuplicateSymbolException();
                }
            }

        }
        private void validate() throws DuplicateSymbolException, PlayersCntDimensionsMismatchException, MoreThanOneBotException {
            // validate method has become Monster method
            // Also method was private so we need to add try and catch block also if return type of validate methods is boolean
            validateBotCnt();
            validateDimensionsAndPlayersCnt();
            validateUniqueSymbolsForPlayers();
        }
        public Game build() throws DuplicateSymbolException, PlayersCntDimensionsMismatchException, MoreThanOneBotException {
            validate();

            return new Game(dimensions,players,winningStrategies);
        }

    }
}
