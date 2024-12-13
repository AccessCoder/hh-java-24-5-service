package org.example.hhjava245service.controller;

import lombok.RequiredArgsConstructor;
import org.example.hhjava245service.model.Apple;
import org.example.hhjava245service.model.AppleDTO;
import org.example.hhjava245service.service.AppleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apple")
@RequiredArgsConstructor
public class AppleController {

    private final AppleService service;

    @GetMapping
    public List<AppleDTO> getAll() {
        return service.getAllApples();
    }

    @GetMapping("/{id}")
    public AppleDTO getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public AppleDTO createApple(@RequestBody Apple apple) {
        return service.createApple(apple);
    }

    @PutMapping("/{id}")
    public Apple updateApple(@RequestBody Apple apple, @PathVariable String id) {
        return service.updateApple(apple, id);
    }

    @DeleteMapping("/{id}")
    public void deleteApple(@PathVariable String id) {
         service.deleteApple(id);
    }

}
