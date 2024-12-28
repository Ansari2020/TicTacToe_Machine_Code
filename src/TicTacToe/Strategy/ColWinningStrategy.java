package TicTacToe.Strategy;

import TicTacToe.Models.Board;
import TicTacToe.Models.Move;

import java.util.HashMap;

public class ColWinningStrategy implements WinningStrategy{

    HashMap<Integer, HashMap<Character, Integer>> colCounts=new HashMap<>();


    @Override
    public boolean winner(Board board, Move move) {
        int col=move.getCell().getCol();
        char sym=move.getPlayer().getSymbol().getCh();
        if(!colCounts.containsKey(col)){
            colCounts.put(col, new HashMap<>());

        }
        HashMap<Character, Integer> counts=colCounts.get(col);
        if(!counts.containsKey(sym)){
            counts.put(sym, 0);
        }
        counts.put(sym, counts.get(sym) + 1);
        if(counts.get(sym)==board.getSize()){
            return true;
        }


        return false;

        //System.out.println("Col Winning Strategy");
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col=move.getCell().getCol();

        char sym=move.getPlayer().getSymbol().getCh();
        colCounts.get(col).put(sym, colCounts.get(col).get(sym)-1);

    }
}
