package Strategies.botPlayingStrategies;

import Models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel level){
        return new EasyBotPlayingStrategy();
    }
}
