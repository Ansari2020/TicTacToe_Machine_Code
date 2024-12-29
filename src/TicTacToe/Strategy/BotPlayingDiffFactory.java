package TicTacToe.Strategy;

import TicTacToe.Models.BotDiffLevel;

public class BotPlayingDiffFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDiffLevel botDiffLevel) {
        if(botDiffLevel==BotDiffLevel.EASY){
            return new EasyBotPlayingStrategy();
        }
        else if(botDiffLevel==BotDiffLevel.MEDIUM){
            return new MediumBotPlayingStrategy();
        }
        return null;

    }
}
