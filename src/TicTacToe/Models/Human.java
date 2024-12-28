package TicTacToe.Models;

import javax.swing.*;
import java.util.Scanner;

public class Human extends Player {
   private int coins;
    private int level;
    private Scanner scanner;

    public Human(Integer id, String name, Symbol symbol) {
        super(id, name, PlayerType.HUMAN, symbol);
        this.coins = 0;
        this.level = 1;
        this.scanner = new Scanner(System.in);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public Move makeMove(Board board) {
        System.out.print("Enter row number for move: ");
        int row= scanner.nextInt();
        System.out.print("Enter column number for move: ");
        int col= scanner.nextInt();
        return new Move(new Cell(row,col),this);
    }
}
