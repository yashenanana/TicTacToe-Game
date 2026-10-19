package com.einarbeitung.tictactoe.service;

import com.einarbeitung.tictactoe.model.Game;
import com.einarbeitung.tictactoe.model.LoadedGame;
import com.einarbeitung.tictactoe.model.Stats;
import com.einarbeitung.tictactoe.repository.GameRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service layer that handles all logic related to game history.
 */
@Service
public class GameStatsService {
    private final GameRepo repo;
    private final Logger logger = LoggerFactory.getLogger(GameStatsService.class);

    public GameStatsService(GameRepo repo) {
        this.repo = repo;
    }

    /**
     * Function deletes games with no move history from the database.
     */
    public void cleanDatabase() {
        List<Game> allGames = repo.findAll();
        for(Game game : allGames){
            if(game.getCurrentMove()==0) {
                repo.delete(game);
            }
        }
        logger.info("Database cleaned");
    }

    /**
     * Function sets isAscending and currentMove for all games to true and the latest move in its history.
     */
    public void prepareDatabase(){
        List<Game> allGames = repo.findAll();
        for(Game game : allGames){
            game.setLoaded(true);
            if(!game.isAscending()){
                game.setAscending(true);
            }
            game.setCurrentMove(game.getHistory().size()-1);
            repo.save(game);
        }
        logger.info("Database prepared");
    }

    /**
     * @return List of {@link Stats} of all games in database.
     */
    public List<Stats> getAllValidGames(){
        List<Game> allGames = repo.findAll();
        List<Stats> validGameStats = new ArrayList<>();
        for(Game game : allGames){
                validGameStats.add(new Stats(game.getId(), game.getWinner(), game.getWinningMoves(), game.getCurrentMove()));
         }
         logger.info("All valid games retrieved");
         return validGameStats;
    }

    /**
     * @param id Unique game ID
     * @return Specific {@link LoadedGame} instance matching the game ID in request
     */
    public LoadedGame getGame(int id){
        Game matchingGame = repo.findById(id).orElseThrow();
        logger.info("Game with id {} loaded", id);
        return new LoadedGame(matchingGame.getId(), matchingGame.getWinner(), matchingGame.getWinningMoves(),  matchingGame.getCurrentMove(), matchingGame.isAscending(), matchingGame.getCurrentSquares(), matchingGame.getHistory());
    }

    /**
     * Function to delete specific instance of {@link Game} from database.
     * @param id Unique game ID
     */
    public void deleteFromDb(int id){
        repo.delete(repo.getReferenceById(id));
        logger.info("Game with id {} deleted", id);
    }
}
