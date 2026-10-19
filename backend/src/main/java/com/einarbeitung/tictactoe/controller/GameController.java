package com.einarbeitung.tictactoe.controller;

import com.einarbeitung.tictactoe.DTO.*;
import com.einarbeitung.tictactoe.exception.InvalidMove;
import com.einarbeitung.tictactoe.model.LoadedGame;
import com.einarbeitung.tictactoe.model.Stats;
import com.einarbeitung.tictactoe.service.GameService;
import com.einarbeitung.tictactoe.service.GameStatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 *The controller layer to receive client requests, call the {@link GameService} and {@link GameStatsService} layer and
 * return according HTTP response.
 */
@CrossOrigin(origins = "http://localhost:5174")
@RestController
public class GameController {
    private final GameService gameService;
    private final GameStatsService gameStatsService;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    public GameController(GameService gameService, GameStatsService gameStatsService){
        this.gameService = gameService;
        this.gameStatsService = gameStatsService;
    }

    @PostMapping("/new-game")
    public ResponseEntity<CreateGameResponse> newGame() {
        logger.info("New Game Request");
        try {
            return ResponseEntity.ok(gameService.createGame());
        } catch(Exception _) {
            logger.error("Error while creating Game Request");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("{id}/new-move")
    public ResponseEntity<MoveResponse> newMove(@RequestBody MoveRequest moveRequest, @PathVariable int id) throws InvalidMove {
        logger.info("New Move Request");
        try {
            return ResponseEntity.ok(gameService.addMove(moveRequest, id));
        } catch (RuntimeException e){
            throw new InvalidMove(e.getMessage());
        }
    }

    /**
     * Method to refresh history. The database is cleaned so only valid games are present and prepared so the correct boards are displayed in client-side.
     * @return a list of {@link Stats} of all valid games
     */
    @PostMapping("/refresh-history")
    public ResponseEntity<List<Stats>> refreshHistory() {
        gameStatsService.cleanDatabase();
        gameStatsService.prepareDatabase();
        return ResponseEntity.ok(gameStatsService.getAllValidGames());
    }

    @PatchMapping("{id}/set-ascending")
    public ResponseEntity<SetAscendingResponse> setNewAscending(@PathVariable int id) {
        logger.info("Set Ascending Request");
        try {
            return ResponseEntity.ok(gameService.setAscending(id));
        } catch(Exception _) {
            logger.error("Error while setting Ascending");
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("{id}/jump-to")
    public ResponseEntity<LoadedGame> jumpTo(@RequestBody JumpToRequest jumpToRequest, @PathVariable int id) {
        logger.info("Jump To Request");
        try {
            return ResponseEntity.ok(gameService.jumpTo(jumpToRequest, id));
        }catch (Exception _){
            logger.error("Error while jumping to");
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}/set-loaded-game")
    public ResponseEntity<LoadedGame> loadGame(@PathVariable int id) {
        logger.info("Load Game Request");
        try{
            return ResponseEntity.ok(gameStatsService.getGame(id));
        }catch (Exception _){
            logger.error("Error while loading Game");
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {
        logger.info("Delete Game Request");
        try {
            gameStatsService.deleteFromDb(id);
            return ResponseEntity.ok().build();
        }catch (Exception _){
            logger.error("Error while deleting Game");
            return ResponseEntity.badRequest().build();
        }
    }
}