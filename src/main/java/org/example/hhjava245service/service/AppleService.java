package org.example.hhjava245service.service;

import lombok.RequiredArgsConstructor;
import org.example.hhjava245service.model.Apple;
import org.example.hhjava245service.model.AppleDTO;
import org.example.hhjava245service.repo.AppleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppleService {

    private final AppleRepo repo;

    public List<AppleDTO> getAllApples() {
        return repo.findAll().stream()
                .map(apple -> {
                    AppleDTO appleDTO = new AppleDTO(
                            apple.id(),
                            apple.name(),
                            apple.color(),
                            apple.retailPrice());
                    return appleDTO;
                })
                .toList();
    }

    public AppleDTO getById(String id) {
        Apple temp = repo.findById(id).orElseThrow();
        AppleDTO appleDTO = new AppleDTO(
                temp.id(),
                temp.name(),
                temp.color(),
                temp.retailPrice());
        return appleDTO;
    }

    public AppleDTO createApple(Apple apple) {
        Apple temp =  repo.save(apple);
        AppleDTO appleDTO = new AppleDTO(
                temp.id(),
                temp.name(),
                temp.color(),
                temp.retailPrice());
        return appleDTO;
    }

    public Apple updateApple(Apple apple, String id) {
        if (repo.existsById(id)) {
            repo.save(apple);
            return repo.findById(id).orElseThrow();
        }else {
            throw new RuntimeException("Apple not found");
        }

    }

    public void deleteApple(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }else {
            throw new RuntimeException("Apple not found");
        }
    }
}
