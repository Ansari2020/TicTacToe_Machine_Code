package TicTacToe.Models;

public abstract class Player {

    private Integer id;
    private String name;
    private PlayerType type;
    private Symbol symbol;

    public Player( Integer id,String name, PlayerType type, Symbol symbol) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.symbol = symbol;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public abstract Move makeMove(Board board);
}
