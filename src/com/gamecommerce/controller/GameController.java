package com.gamecommerce.controller;

import com.gamecommerce.model.Game;
import com.gamecommerce.service.GameService;

import java.util.List;

public class GameController {

    private GameService gameService;

    public GameController() {
        this.gameService = new GameService();
    }

    public List<Game> fetchGames() {
        return gameService.getAllGames();
    }
}
