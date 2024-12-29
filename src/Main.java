import TicTacToe.GameControllers.GameController;
import TicTacToe.Models.*;
import TicTacToe.Strategy.ColWinningStrategy;
import TicTacToe.Strategy.RowWinningStrategy;
import TicTacToe.Strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


        GameController gameController = new GameController();
//        to start the game required are
//        players, board size, winning strategy
        List<Player> players = new ArrayList<>();
        players.add(new Human(1,"Danish",new Symbol('X')));
        players.add(new Bot(2,"AI",BotDiffLevel.EASY,new Symbol('O')));

        int boardSize=3;
        List<WinningStrategy> winningStrategies = new ArrayList<>();
        System.out.println("Please choose your winning strategies (Type '0' when done):");
        System.out.println("1. Row Winning");
        System.out.println("2. Column Winning");

        while (true) {
            System.out.print("Enter the number of your strategy: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break; // Exit the loop if the user enters 0
            }

            switch (choice) {
                case 1:
                    System.out.println("Adding RowWinningStrategy!");
                    winningStrategies.add(new RowWinningStrategy());
                    break;
                case 2:
                    System.out.println("Adding ColWinningStrategy!");
                    winningStrategies.add(new ColWinningStrategy());
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 3.");
            }

        }
        if (winningStrategies.isEmpty()) {
            System.out.println("No strategies selected. Defaulting to RowWinningStrategy.");
            winningStrategies.add(new RowWinningStrategy());
        }


        Game game=gameController
                .startGame(3,players,winningStrategies);

        gameController.display(game); //display board
        while(gameController.getGameState(game).equals(GameState.ONGOING)){
            // make move is responsible for
            // take input, update game state , and update turn
            gameController.makeMove(game);
            gameController.display(game);
            System.out.println("Do you want to undo [Y/N]");
            String undo = scanner.nextLine();
            if(undo.equals("Y")){
                gameController.undoMove(game);
                System.out.println("Undo successful");
                gameController.display(game);
            }

        }
        if(gameController.getGameState(game).equals(GameState.SUCCESS)){
            System.out.println("Game has completed and winner is "+gameController.getWinner(game).getName());
        }
        if(gameController.getGameState(game).equals(GameState.DRAW)){
            System.out.println("Game has been draw ");
        }


    }
}