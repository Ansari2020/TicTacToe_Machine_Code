package TicTacToe.Models;

import TicTacToe.Strategy.BotPlayingDiffFactory;
import TicTacToe.Strategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotDiffLevel botDiffLevel;

    public Bot( Integer id,String name, BotDiffLevel botDiffLevel, Symbol symbol) {
        super(id,name,PlayerType.BOT,symbol);
        this.botDiffLevel = botDiffLevel;
    }

    public BotDiffLevel getBotDiffLevel() {
        return botDiffLevel;
    }

    public void setBotDiffLevel(BotDiffLevel botDiffLevel) {
        this.botDiffLevel = botDiffLevel;
    }

    @Override
    public Move makeMove(Board board) {
        return BotPlayingDiffFactory.
                getBotPlayingStrategy(botDiffLevel).
                makeMove(board);
    }
}
