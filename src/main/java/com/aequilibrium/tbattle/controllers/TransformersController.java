package com.aequilibrium.tbattle.controllers;

import com.aequilibrium.tbattle.logic.BattleManager;
import com.aequilibrium.tbattle.model.BattleOutcome;
import com.aequilibrium.tbattle.model.Transformer;
import com.aequilibrium.tbattle.repos.TransformersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transformers", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransformersController {

    private final TransformersRepo repo;

    private final BattleManager battleManager;

    public TransformersController(@Autowired TransformersRepo repo,
                                  @Autowired  BattleManager battleManager) {
        this.repo = repo;
        this.battleManager = battleManager;
    }

    @GetMapping
    @ResponseBody
    public List<Transformer> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Transformer getById(@PathVariable Long id) {
        return repo.getOne(id);
    }

    @PostMapping
    public ResponseEntity<Transformer> create(@RequestBody Transformer transformer) {
        return ResponseEntity.ok(repo.saveAndFlush(transformer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transformer> updateById(@PathVariable Long id, @RequestBody Transformer transformer) {
        transformer.setId(id);
        return ResponseEntity.ok(repo.saveAndFlush(transformer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/battle")
    public ResponseEntity<BattleOutcome> battle(@RequestBody List<Long> battleSetup) {
        return ResponseEntity.ok(battleManager.battle(battleSetup));
    }
}
