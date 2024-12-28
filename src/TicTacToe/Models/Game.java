package TicTacToe.Models;

import TicTacToe.GameControllers.GameController;
import TicTacToe.Strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Player winner;
    private GameState gameState;
    private Board board;
    private Integer nextPlayer;
    private List<Move> moves;
    private List<WinningStrategy> winningStrategy;



    private Game(Builder builder) {
        board = new Board(builder.boardSize);
        this.players = builder.players;
        this.winningStrategy = builder.winningStrategies;
        this.nextPlayer = 0;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.gameState=GameState.ONGOING;
    }



    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Integer getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Integer nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<WinningStrategy> getWinningStrategy() {
        return winningStrategy;
    }

    public void setWinningStrategy(List<WinningStrategy> winningStrategy) {
        this.winningStrategy = winningStrategy;
    }

    public void displayBoard(){
        board.display();

    }
    private boolean validateMove(Move move){
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();


        if(row<0 || row>board.getSize()-1 || col<0 || col>board.getSize()-1){
            return false;
        }
        return board.getGrid().get(row).get(col).getCellState().equals(CellState.EMPTY);

    }
    public void updateGameState(Move move,Player cuurPlayer){
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        Cell cellToChange=board.getGrid().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setSymbol(cuurPlayer.getSymbol());
        move.setCell(cellToChange);
        move.setPlayer(cuurPlayer);

        moves.add(move);
        nextPlayer++;
        nextPlayer%=players.size();
    }
    private boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy: winningStrategy){
            if(winningStrategy.winner(board,move)){
                return true;
            }
        }
        return false;
    }

    public void makeMove(){
        //current player for move
        Player cuurPlayer=players.get(getNextPlayer());
        System.out.println("its "+cuurPlayer.getName()+"'s turn. Please make your move.");
        Move move=cuurPlayer.makeMove(board);

        if(!validateMove(move)){
            System.out.println("Invalid move. Please try again.");
            return;
        }
        updateGameState(move,cuurPlayer);
        if(checkWinner(move)){
            winner=cuurPlayer;
            gameState=GameState.SUCCESS;
        }
        // if total moves reached to total cell in grid then it is a draw
        else if(moves.size()==board.getSize() * board.getSize()){
            gameState=GameState.DRAW;
        }
    }
    public void undo(){
        if(moves.size()==0){
            System.out.println("No more moves to undo.");
            return;
        }

        Move lastMove=moves.get(moves.size()-1);
        moves.remove(moves.size()-1);
        lastMove.getCell().setCellState(CellState.EMPTY);
        lastMove.getCell().setSymbol(null);
        nextPlayer--;
        nextPlayer=(nextPlayer+players.size())%players.size(); //to handle negative mode

        for(WinningStrategy winningStrategy: winningStrategy){
            winningStrategy.handleUndo(board,lastMove);

        }
        setGameState(GameState.ONGOING);
        setWinner(null);
    }


    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        //required details from user
        private Integer boardSize;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setBoardSize(Integer boardSize) {
            this.boardSize = boardSize;
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

        public void validate() {
            //bot should be 1,
            //all players hove different symbol,
            // players =size-1
            if(players.size()!=boardSize-1){
                throw new IllegalArgumentException("The players must be less than 1 of board size.");
            }

            if(boardSize==null || boardSize<=0){
                throw new IllegalArgumentException("Board size must be greater than zero");
            }

            int botCount=0;
            for(Player p: players){
                if(p.getType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount>1){
                throw new IllegalArgumentException("too many bot players");
            }


        }

        public Game build() {
            validate();
            return new Game(this);
        }
    }

}
