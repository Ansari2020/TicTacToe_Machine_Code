package TicTacToe.Strategy;

import TicTacToe.Models.Board;
import TicTacToe.Models.Move;

public interface WinningStrategy {

    boolean winner(Board board, Move move);
    void handleUndo(Board board, Move move);

}
