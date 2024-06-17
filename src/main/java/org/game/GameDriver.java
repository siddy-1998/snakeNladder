package org.game;

import org.game.model.GameConfig;
import org.game.service.Game;
import org.game.service.config.ConfigLoader;
import org.game.service.config.GameConfigService;
import org.game.service.validation.ValidationHelperService;

import java.io.IOException;

public class GameDriver {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            String fileName = "game-config.yml";
            ConfigLoader configLoader = new ConfigLoader();
            ValidationHelperService validationHelperService = new ValidationHelperService();
            GameConfigService gameConfigService = new GameConfigService(validationHelperService, configLoader);

            GameConfig config = gameConfigService.getConfig(fileName);
            Game game = new Game(config);
            game.startGame();


        } catch (IOException e) {
            throw new RuntimeException(new Exception("Config File Not Found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}