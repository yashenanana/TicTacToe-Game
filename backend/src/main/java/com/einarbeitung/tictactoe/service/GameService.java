package com.einarbeitung.tictactoe.service;

import com.einarbeitung.tictactoe.DTO.*;

import com.einarbeitung.tictactoe.model.Game;
import com.einarbeitung.tictactoe.model.LoadedGame;
import com.einarbeitung.tictactoe.repository.GameRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service layer that handles all logic related to gameplay.
 */
@Service
public class GameService {

    private final Logger logger = LoggerFactory.getLogger(GameService.class);

    private final GameRepo repo;

    public GameService(GameRepo repo) {
        this.repo = repo;
    }

    private static final int[][] WIN_COMBINATIONS = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };

    /**
     * Creates a new {@link Game}  instance and saves it to the database
     * @return new Game ID
     */
    public CreateGameResponse createGame(){
        ArrayList<String[]> history = new ArrayList<>();
        int currentMove = 0;
        String[] firstMove = new String[9];
        for(int i = 0; i < 9; i++){
            firstMove[i] = null;
        }
        history.add(firstMove);
        Game game = new Game(history,currentMove, null,  null);
        repo.save(game);
        logger.info("Game with id {} created", game.getId());
        return new CreateGameResponse(game.getId());
    }

    /**
     * Adds new move to existing {@link Game} . Requested move is validated and potential winner is calculated.
     * The game is then updated and saved in the database.
     * @param moveRequest {@link MoveRequest}
     * @param id unique game ID
     * @return {@link MoveResponse}
     */
    public MoveResponse addMove(MoveRequest moveRequest, int id){
        Game game = repo.getReferenceById(id);
        logger.info("Add move at position {} for game id {}",moveRequest.position(), id);
        if(game.isLoaded()){
            throw new IllegalArgumentException("Loaded games cannot be played. Please start a new game.");
        }
        // If the game has been previously jumped, the history is updated from the new move.
        if(game.isJumped()){
            game.setCurrentSquares(game.getHistory().get(game.getCurrentMove()));
            ArrayList<String[]> currentHistory = new ArrayList<>();
            for (int i = 0; i <= game.getCurrentMove(); i++) {
                String[] temp = duplicateArray(game.getHistory().get(i));
                currentHistory.add(temp);
            }
            game.setHistory(currentHistory);
            game.setJumped(false);
        }
        String[] newSquares = updateSquares(moveRequest.position(), game);
        ArrayList<String[]> newHistory = game.getHistory();
        newHistory.add(newSquares);
        int newCurrentMove = game.getHistory().size()-1;
        game.setHistory(newHistory);
        game.setCurrentSquares(newSquares);
        game.setCurrentMove(newCurrentMove);
        calculateWinningInfo(newSquares, game);
        repo.save(game);
        return new MoveResponse(game.getCurrentMove(), game.getWinner(), game.getWinningMoves(), game.getHistory());
    }

    /**
     * Helper function to validate new move and update board.
     * @param position Position of requested move on the board
     * @param game Referenced game instance
     * @return Array representing updated board with latest move
     */
    private String[] updateSquares(int position, Game game){
        String[] currentSquares = game.getCurrentSquares() ;
        if(game.getWinner()!=null){
            throw new IllegalArgumentException("Winner is already played. Please start a new game.");
        }
        if(!game.isAscending()){
            throw new IllegalArgumentException("Move not allowed in ascending mode");
        }
        if(currentSquares[position]!=null){
            throw new IllegalArgumentException("Square is already filled. Try a different move.");
        }
        boolean newXIsNext = game.getCurrentMove()%2==0;
        game.setXIsNext(newXIsNext);
        String[] nextSquares = duplicateArray(currentSquares);
        if(game.isXIsNext()){
            nextSquares[position] = "X";
        }else{
            nextSquares[position] = "O";
        }
        return nextSquares;
    }

    /**
     * Helper function to calculate winner and winning moves by comparing current board with WIN_COMBINATIONS.
     * @param squares Array representing current board
     * @param game Referenced {@link Game}  instance
     */
    private void calculateWinningInfo(String[] squares, Game game){
        for (int[] winCombination : WIN_COMBINATIONS) {
            String a = squares[winCombination[0]],  b = squares[winCombination[1]],c = squares[winCombination[2]];
            if (a != null && a.equals(b) && b.equals(c)) {
                game.setWinner(a);
                game.setWinningMoves(duplicateArray(winCombination));
                repo.save(game);
                return;
            } else {
                game.setWinningMoves(null);
                game.setWinner(null);
            }
        }
    }

    /**
     * Function to update isAscending variable of specific {@link Game}  instance.
     * @param id Unique game ID
     * @return {@link SetAscendingResponse}
     */
    public SetAscendingResponse setAscending(int id) {
        Game game =  repo.getReferenceById(id);
        logger.info("Set game {} to ascending:{}", id, !game.isAscending());
        game.setAscending(!game.isAscending());
        repo.save(game);
        return new SetAscendingResponse(game.isAscending());
    }

    /**
     * Function to execute timeskip functionality. Determines array to represent current board through move provided in jumpToRequest.
     * Winner is recalculated to rerender board.
     * @param jumpToRequest {@link JumpToRequest}
     * @param id Unique game ID
     * @return Instance of {@link LoadedGame} model containing updated board, winner, winningMoves and currentMove
     */
    public LoadedGame jumpTo(JumpToRequest jumpToRequest, int id) {
        logger.info("Jump to move {} for game id {}", jumpToRequest.move(), id);
        Game game =  repo.getReferenceById(id);
        int move = jumpToRequest.move();
        String[] newSquares;
        int jumpedMove;
        game.setCurrentMove(move);
        if(game.isAscending()){
            newSquares = game.getHistory().get(game.getCurrentMove());
            jumpedMove = game.getCurrentMove();
        }else{
            List<String[]> reversedHistory = reverseHistory(game.getHistory());
            newSquares = reversedHistory.get(game.getCurrentMove());
            jumpedMove = reversedHistory.size()-1-game.getCurrentMove();
        }
        calculateWinningInfo(newSquares, game); //winner recalculated to rerender board
        if(!game.isLoaded()){
            game.setJumped(true);
            repo.save(game);
        }
        return new LoadedGame(game.getId(), game.getWinner(), game.getWinningMoves(), jumpedMove, game.isAscending(), newSquares, game.getHistory());
    }

    //Hilfefunktionen
    public List<String[]> reverseHistory(List<String[]> givenCurrent) {
        ArrayList<String[]> reverseCurrent = new ArrayList<>(givenCurrent);
        Collections.reverse(reverseCurrent);
        return reverseCurrent;
    }

    private String[] duplicateArray(String[] array){
        String[] duplicate = new String[array.length];
        System.arraycopy(array, 0, duplicate, 0, array.length);
        return duplicate;
    }

    private int[] duplicateArray(int[] array){
        int[] duplicate = new int[array.length];
        System.arraycopy(array, 0, duplicate, 0, array.length);
        return duplicate;
    }
}
