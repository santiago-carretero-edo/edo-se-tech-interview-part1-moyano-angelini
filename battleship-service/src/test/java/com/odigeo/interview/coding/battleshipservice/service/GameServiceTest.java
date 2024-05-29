package com.odigeo.interview.coding.battleshipservice.service;

import com.odigeo.interview.coding.battleshipapi.contract.GameJoinCommand;
import com.odigeo.interview.coding.battleshipapi.contract.GameStartCommand;
import com.odigeo.interview.coding.battleshipservice.exception.GameJoinException;
import com.odigeo.interview.coding.battleshipservice.model.Game;
import com.odigeo.interview.coding.battleshipservice.repository.GameRepositoryImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class GameServiceTest {

    @Mock
    private GameRepositoryImpl gameRepository;
    @Mock
    private Game game;

    @InjectMocks
    private GameService gameService;

    @BeforeMethod
    public void init() {
        initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(gameRepository);
    }

    @Test
    public void testNewGameVsComputer() {
        GameStartCommand command = new GameStartCommand();
        command.setPlayerId("player1");
        Game newGame = gameService.newGame(command);
        assertNotNull(newGame);
        assertNotNull(newGame.getId());
        assertEquals(newGame.getPlayerOneId(), command.getPlayerId());
        verify(gameRepository, times(1)).saveOrUpdateGame(any(Game.class));
    }

    @Test
    public void testNewGameVsHumanPlayer() {
        GameStartCommand command = new GameStartCommand();
        command.setPlayerId("player1");
        Game newGame = gameService.newGame(command);
        assertNotNull(newGame);
        assertNotNull(newGame.getId());
        assertEquals(newGame.getPlayerOneId(), command.getPlayerId());
        verify(gameRepository, times(1)).saveOrUpdateGame(any(Game.class));
    }

    @Test
    public void testJoinGame() {
        when(game.getId()).thenReturn("12345");
        when(game.getPlayerTwoId()).thenReturn(null);
        when(gameRepository.getGame(any())).thenReturn(Optional.of(game));
        GameJoinCommand command = new GameJoinCommand();
        command.setPlayerId("player2");
        gameService.joinGame("12345", command);
        verify(gameRepository, times(1)).saveOrUpdateGame(game);
    }

    @Test(expectedExceptions = GameJoinException.class, expectedExceptionsMessageRegExp = "Another player is already playing this game")
    public void testJoinFullGame() {
        when(game.getId()).thenReturn("12345");
        when(game.getPlayerTwoId()).thenReturn("anotherPlayer");
        when(gameRepository.getGame(any())).thenReturn(Optional.of(game));
        GameJoinCommand command = new GameJoinCommand();
        command.setPlayerId("player2");
        gameService.joinGame("12345", command);
        verify(gameRepository, never()).saveOrUpdateGame(game);
    }

}
