package TicTacToe.Strategy;

import TicTacToe.Models.Board;
import TicTacToe.Models.Move;
import TicTacToe.Models.Player;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy {

    private HashMap<Integer, HashMap<Character,Integer>> rowCounts=new HashMap<>();

//    for every row we will check counts with symbol
//    1: {'x': 0, 'O': 0}
//    2: {'x': 1, 'O': 1}

    @Override
    public boolean winner(Board board, Move move) {

        int row=move.getCell().getRow();
        Character sym=move.getPlayer().getSymbol().getCh();

        if(!rowCounts.containsKey(row)){
            rowCounts.put(row, new HashMap<>());
        }
        HashMap<Character,Integer> counts=rowCounts.get(row);
        if(!counts.containsKey(sym)){
            counts.put(sym,0);
        }
        counts.put(sym,counts.get(sym)+1);

        if(counts.get(sym)==board.getSize()){
            return true;

        }
        return false;


    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row=move.getCell().getRow();
        Character sym=move.getPlayer().getSymbol().getCh();
        rowCounts.get(row).put(sym,rowCounts.get(row).get(sym)-1);
    }
}
