package com.odigeo.interview.coding.battleshipservice.service;

import com.odigeo.interview.coding.battleshipapi.contract.DeployShipsCommand;
import com.odigeo.interview.coding.battleshipapi.contract.GameFireCommand;
import com.odigeo.interview.coding.battleshipapi.contract.GameFireResponse;
import com.odigeo.interview.coding.battleshipapi.contract.GameJoinCommand;
import com.odigeo.interview.coding.battleshipapi.contract.GameStartCommand;
import com.odigeo.interview.coding.battleshipservice.exception.GameJoinException;
import com.odigeo.interview.coding.battleshipservice.exception.GameNotFoundException;
import com.odigeo.interview.coding.battleshipservice.model.Game;
import com.odigeo.interview.coding.battleshipservice.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.UUID;

@Singleton
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    @Inject
    private GameRepository repository;

    public Game newGame(GameStartCommand command) {
        Game game = new Game();
        game.setId(UUID.randomUUID().toString());
        game.setPlayerOneId(command.getPlayerId());
        game.setCreatedAt(Instant.now());
        game.setPlayerTurn(1);
        repository.saveOrUpdateGame(game);
        logger.info("New game created {}", game.getId());
        return game;
    }

    public void joinGame(String gameId, GameJoinCommand command) {
        Game game = repository.getGame(gameId).orElseThrow(() -> new GameNotFoundException(gameId));

        if (game.getPlayerTwoId() != null) {
            throw new GameJoinException("Another player is already playing this game");
        }

        game.setPlayerTwoId(command.getPlayerId());
        repository.saveOrUpdateGame(game);
    }

    public void deployShips(String gameId, DeployShipsCommand command) {
        Game game = repository.getGame(gameId).orElseThrow(() -> new GameNotFoundException(gameId));

        // Insert your code here

        repository.saveOrUpdateGame(game);
    }

    public GameFireResponse fire(String gameId, GameFireCommand command) {
        Game game = repository.getGame(gameId).orElseThrow(() -> new GameNotFoundException(gameId));

        GameFireResponse response = new GameFireResponse();

        // Insert your code here

        repository.saveOrUpdateGame(game);
        return response;
    }

}
