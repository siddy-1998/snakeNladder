package org.game.service.config;

import org.game.constants.BoardConstants;
import org.game.constants.JumpConstants;
import org.game.model.GameConfig;
import org.game.model.Player;
import org.game.model.jump.Crocodile;
import org.game.model.jump.Jump;
import org.game.model.jump.Ladder;
import org.game.model.jump.Snake;
import org.game.model.stay.Mine;
import org.game.model.stay.Stay;
import org.game.service.validation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class GameConfigService {
    private final ValidationHelperService validationHelperService;
    private final ConfigLoader configLoader;

    public GameConfigService(ValidationHelperService validationHelperService, ConfigLoader configLoader) {
        this.validationHelperService = validationHelperService;
        this.configLoader = configLoader;
    }

    public GameConfig getConfig(String fileName) throws Exception {
        GameConfig config = new GameConfig();
        Map<String, Object> data = configLoader.loadConfig(fileName);

        setAndValidateBoardSize(data, config);
        setAndValidateSnakes(data, config);
        setAndValidateLadders(data, config);
        setAndValidateCrocodiles(data, config);
        setAndValidateMines(data, config);
        setAndValidateMovementStrategy(data, config);
        boolean manualOverride = isManualOverride(data, config);
        setAndValidatePlayers(manualOverride, data, config);
        setAndValidateDiceCount(data, config);

        return config;
    }

    private void setAndValidateDiceCount(Map<String, Object> data, GameConfig config) {

        int diceCount = (int) data.get("numOfDice");
        validationHelperService.validateDiceCount(diceCount);
        config.setDiceCount(diceCount);

        System.out.println("numOfDice: " + diceCount);
    }

    private void setAndValidatePlayers(boolean manualOverride, Map<String, Object> data, GameConfig config) {
        if (manualOverride) {
            // user will give inputs
            enterPlayersManually(config);
        }

        List<Map<String, Object>> playersFromInput = (List<Map<String, Object>>) data.get("players");
        System.out.println("Players:");
        List<Player> players = new ArrayList<>();
        for (Map<String, Object> player : playersFromInput) {
            String playerName = (String) player.get("name");
            int position = (int) player.get("position");
            if (!validationHelperService.validatePosition(position)) {
                throw new RuntimeException("Please give a valid position for player " + playerName + " b/w " + BoardConstants.BOARD_START +
                        " to " + (config.getBoardSize() * config.getBoardSize() - 1));
            }
            Player p = new Player(playerName, position);
            players.add(p);
            System.out.println("Name: " + playerName + ", Position: " + position);
        }
        config.setPlayers(players);
    }

    private void setAndValidateBoardSize(Map<String, Object> data, GameConfig config) {
        int boardSize = (int) data.get("boardSize");
        validationHelperService.setBoardSize(boardSize);
        config.setBoardSize(boardSize);

        System.out.println("Board Size: " + boardSize);
    }

    private void enterPlayersManually(GameConfig config) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of Players");
        int numOfPlayers = sc.nextInt();
        List<Player> players = new ArrayList<>();

        int currentPlayer = 1;
        while (currentPlayer <= numOfPlayers) {
            System.out.println("Please enter name of Player No. " + currentPlayer);
            String playerName = sc.next();
            System.out.println("Please enter position of Player No. " + currentPlayer);
            int position = sc.nextInt();
            if (!validationHelperService.validatePosition(position)) {
                System.out.println("Please add the player at correct position b/w " + BoardConstants.BOARD_START +
                        " to " + (config.getBoardSize() * config.getBoardSize() - 1));
                continue;
            }
            Player player = new Player(playerName, position);
            players.add(player);
            currentPlayer++;
        }
        config.setPlayers(players);
    }

    private boolean isManualOverride(Map<String, Object> data, GameConfig config) {
        boolean manualOverride = (boolean) data.get("manualOverride");
        config.setManualOverride(manualOverride);
        System.out.println("manualOverride: " + manualOverride);
        return manualOverride;
    }

    private void setAndValidateMovementStrategy(Map<String, Object> data, GameConfig config) {
        String movementStrategy = (String) data.get("movementStrategy");
        validationHelperService.validateMovementStrategy(movementStrategy);
        config.setMovementStrategy(movementStrategy);
        System.out.println("Movement Strategy: " + movementStrategy);
    }

    private void setAndValidateMines(Map<String, Object> data, GameConfig config) {
        List<Map<String, Integer>> minesFromInput = (List<Map<String, Integer>>) data.get("mines");
        System.out.println("Mines:");
        List<Stay> mines = new ArrayList<>();
        for (Map<String, Integer> mine : minesFromInput) {
            int position = mine.get("position");
            Mine mineStay = new Mine(2, position);
            validationHelperService.validateMines(position);
            mines.add(mineStay);
            System.out.println("Position: " + position);
        }
        config.setMines(mines);
    }

    private void setAndValidateCrocodiles(Map<String, Object> data, GameConfig config) throws Exception {
        Validator validator = new CrocodileValidator();
        List<Map<String, Integer>> crocodiles = (List<Map<String, Integer>>) data.get("crocodiles");
        System.out.println("Crocodiles:");
        List<Jump> crocs = new ArrayList<>();

        for (Map<String, Integer> crocodile : crocodiles) {
            int position = crocodile.get("position");
            Crocodile currentJump = new Crocodile(position, position - JumpConstants.CROCODILE_STEP_BACK);
            validator.validate(currentJump, validationHelperService);
            crocs.add(currentJump);
            System.out.println("Position: " + position);
        }
        config.setCrocodiles(crocs);
    }

    private void setAndValidateLadders(Map<String, Object> data, GameConfig config) throws Exception {
        Validator validator = new LadderValidator();
        List<Map<String, Integer>> laddersFromInput = (List<Map<String, Integer>>) data.get("ladders");
        System.out.println("Ladders:");
        List<Jump> ladders = new ArrayList<>();

        for (Map<String, Integer> ladder : laddersFromInput) {
            int bottom = ladder.get("bottom");
            int top = ladder.get("top");
            Ladder currentJump = new Ladder(bottom, top);
            validator.validate(currentJump, validationHelperService);
            ladders.add(currentJump);
            System.out.println("Bottom: " + bottom + ", Top: " + top);
        }
        config.setLadders(ladders);
    }

    private void setAndValidateSnakes(Map<String, Object> data, GameConfig config) throws Exception {
        Validator validator = new SnakeValidator();
        List<Map<String, Integer>> snakesFromInput = (List<Map<String, Integer>>) data.get("snakes");
        System.out.println("Snakes:");
        List<Jump> snakes = new ArrayList<>();
        for (Map<String, Integer> snake : snakesFromInput) {
            int head = snake.get("head");
            int tail = snake.get("tail");
            Snake currentJump = new Snake(head, tail);
            validator.validate(currentJump, validationHelperService);
             //validationHelperService.addSnakeHead(head);
            //        validationHelperService.addSnakeTail(tail);
            //        snakes.add(currentJump);
            snakes.add(currentJump);
            System.out.println("Head: " + head + ", Tail: " + tail);
        }
        config.setSnakes(snakes);
    }
}
