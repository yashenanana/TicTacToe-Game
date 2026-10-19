package com.einarbeitung.tictactoe.repository;

import com.einarbeitung.tictactoe.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends JpaRepository<Game,Integer> {

}
