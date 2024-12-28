package TicTacToe.Models;

public class Cell {
    private Integer row;
    private Integer col;
    private Symbol symbol;
    private CellState cellState;

    public Cell(Integer row, Integer col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
        this.symbol = null;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public void displayCell(){
        if(cellState==CellState.EMPTY){
            System.out.print("| - |");
        }
        else{
            System.out.print("| "+symbol.getCh()+" |");
        }
    }
}
