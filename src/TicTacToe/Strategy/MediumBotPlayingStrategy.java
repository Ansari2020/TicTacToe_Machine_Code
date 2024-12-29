package TicTacToe.Strategy;

import TicTacToe.Models.Board;
import TicTacToe.Models.Cell;
import TicTacToe.Models.CellState;
import TicTacToe.Models.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MediumBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        List<Cell> emptyCells = new ArrayList<Cell>();

        for(List<Cell> row: board.getGrid()){
            for(Cell c: row){
                if(c.getCellState() == CellState.EMPTY){
                    emptyCells.add(c);
                }
            }
        }
        if(emptyCells.isEmpty()){
            throw new IllegalStateException("No more moves to play");

        }
        Random randomMoves = new Random();
        Cell selectedState=emptyCells.get(randomMoves.nextInt(emptyCells.size()));
        return new Move(new Cell(selectedState.getRow(), selectedState.getCol()),null);


    }


}
