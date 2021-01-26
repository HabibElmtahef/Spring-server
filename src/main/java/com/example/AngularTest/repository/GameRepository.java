package com.example.AngularTest.repository;

import com.example.AngularTest.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
   Optional<Game> findByTitle(String title);
   boolean existsByTitle(String title);
}
