package TicTacToe.GameControllers;

import TicTacToe.Models.Game;
import TicTacToe.Models.GameState;
import TicTacToe.Models.Player;
import TicTacToe.Strategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(
            int size,
            List<Player> players,
            List<WinningStrategy> winningStrategies
    ){

            return Game.getBuilder().
                    setBoardSize(size).
                    setPlayers(players).
                    setWinningStrategies(winningStrategies)
                    .build();

    }


    public GameState getGameState(Game game) {
        return game.getGameState();
    }
    public void display(Game game) {
        game.displayBoard();

    }
    public void makeMove(Game game){
         game.makeMove();


    }

    public void undoMove(Game game){
        game.undo();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }
}
