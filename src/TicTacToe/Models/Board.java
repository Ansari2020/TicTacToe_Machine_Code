package TicTacToe.Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Integer  size;
    private List<List<Cell>> grid;

    public Board(Integer size ) {
        this.size = size;
        grid = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            grid.add(new ArrayList<>());
            for(int j = 0; j < size; j++) {
                grid.get(i).add(new Cell(i,j));
            }
        }
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    public void display() {
        for(List<Cell> row : grid) {
            for(Cell cell : row) {
                cell.displayCell();
            }
            System.out.println();
        }
    }
}