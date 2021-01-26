package com.example.AngularTest.controller;

import com.example.AngularTest.dto.Mensaje;
import com.example.AngularTest.entity.Game;
import com.example.AngularTest.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")

public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<List<Game>> list() {
        List<Game> list = gameService.list();
        return new ResponseEntity (list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Game> getById(@PathVariable("id") int id) {
        if(!gameService.existeById(id)) return new ResponseEntity(new Mensaje("Invalid Game"), HttpStatus.OK);
        Game res = gameService.getOne(id).get();
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/detail/{title}")
    public ResponseEntity<Game> getById(@PathVariable("title") String title) {
        if(!gameService.existeByTitle(title)) return new ResponseEntity(new Mensaje("Invalid Game"), HttpStatus.OK);
        Game res = gameService.getByTitle(title).get();
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping("/add_game")
    public ResponseEntity<?> create(@RequestBody Game game) {
        if(game.getTitle().isEmpty() || game.getDescription().isEmpty() || game.getImage().isEmpty() || game.getTrailer().isEmpty() || game.getRating() <= 0 || game.getRating() > 5 )
        return new ResponseEntity(new Mensaje("please All The Fields"), HttpStatus.OK);
         gameService.save(game);
         return new ResponseEntity(game, HttpStatus.OK);
    }

    @DeleteMapping("/game/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!gameService.existeById(id))
            return new ResponseEntity(new Mensaje("Game Invalid"), HttpStatus.NOT_FOUND);
        gameService.delete(id);
        return new ResponseEntity(new Mensaje("Game Deleted"), HttpStatus.OK);
    }

    @PutMapping("/game/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Game game) {
        Game gameId = gameService.getOne(id).get();
        gameId.setId(game.getId());
        gameId.setTitle(game.getTitle().isEmpty() ? gameId.getTitle() : game.getTitle());
        gameId.setDescription(game.getDescription().isEmpty() ? gameId.getDescription() : game.getDescription());
        gameId.setImage(game.getImage().isEmpty() ? gameId.getImage() : game.getImage());
        gameId.setTrailer(game.getTrailer().isEmpty() ? gameId.getTrailer() : game.getTrailer());
        gameId.setRating(game.getRating());

        gameService.save(gameId);
        return new ResponseEntity(new Mensaje("Game Updated"), HttpStatus.OK);
    }

}
