package com.example.AngularTest.service;

import com.example.AngularTest.entity.Game;
import com.example.AngularTest.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameService {
    @Autowired
    GameRepository GameRepository;

    public List<Game> list() {
        return GameRepository.findAll();
    }

    public Optional<Game> getOne(int id) {
        return GameRepository.findById(id);
    }

    public Optional<Game> getByTitle(String title) {
        return GameRepository.findByTitle(title);
    }

    public void save(Game game) {
        GameRepository.save(game);
    }

    public void delete(int id) {
        GameRepository.deleteById(id);
    }

    public boolean existeById(int id) {
        return GameRepository.existsById(id);
    }

    public boolean existeByTitle(String title) {
        return GameRepository.existsByTitle(title);
    }
}
