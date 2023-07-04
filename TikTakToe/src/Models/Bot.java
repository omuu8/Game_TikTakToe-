package Models;

import Strategies.botPlayingStrategies.BotPlayingStrategy;
import Strategies.botPlayingStrategies.BotPlayingStrategyFactory;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Long id, String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel){
        super(id,name,symbol,PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);
    }
    @Override
    public Move makeMove(Board board) {
        // for bot move it depends on the strategy
        // so for diff strategy we will make use of factory design pattern
        Move move = botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }


    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
