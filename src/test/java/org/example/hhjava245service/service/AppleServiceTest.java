package org.example.hhjava245service.service;


import org.example.hhjava245service.model.Apple;
import org.example.hhjava245service.model.AppleDTO;
import org.example.hhjava245service.repo.AppleRepo;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppleServiceTest {

    private final AppleRepo appleRepo = mock(AppleRepo.class);
    private final IdService idService= mock(IdService.class);

    @Test
    void getAllApples_shouldReturnEmptyList_whenCalledInitially() {
        //GIVEN
        List<AppleDTO> expected = Collections.emptyList();
        AppleService service = new AppleService(appleRepo, idService);
        //WHEN
        List<AppleDTO> actual = service.getAllApples();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getById_shouldReturnGala_whenCalledWithValidId() {
        //GIVEN
        Apple apple = new Apple("1", "Gala", "Red", 0.1, 0.2);
        AppleDTO expected = new AppleDTO(apple.id(), apple.name(), apple.color(), apple.retailPrice());
        AppleService service = new AppleService(appleRepo, idService);
        when(appleRepo.findById(apple.id())).thenReturn(Optional.of(apple));
        //WHEN
        AppleDTO actual = service.getById(apple.id());
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void updateApple_shouldReturnUpdatedGala_whenCalledWithValidData() {
        //GIVEN
        Apple apple = new Apple("1", "Gala", "Red", 0.1, 0.2);
        Apple expected = new Apple(
                apple.id(),
                apple.name(),
                apple.color(),
                apple.price(),
                apple.retailPrice());
        AppleService service = new AppleService(appleRepo, idService);
        when(appleRepo.existsById(apple.id())).thenReturn(true);
        when(appleRepo.findById(apple.id())).thenReturn(Optional.of(apple));
        //WHEN
        Apple actual = service.updateApple(apple, apple.id());
        //THEN
        assertEquals(expected, actual);
        verify(appleRepo).save(apple);
    }

    @Test
    void createApple_shouldReturnCreatedGala_whenCalledWithValidData() {
        //GIVEN
        AppleService service = new AppleService(appleRepo, idService);
        Apple apple = new Apple("1", "Gala", "Red", 0.1, 0.2);
        AppleDTO expected = new AppleDTO("1", apple.name(), apple.color(), apple.retailPrice());
        when(idService.generateId()).thenReturn("1");
        when(appleRepo.save(apple)).thenReturn(apple);
        //WHEN
        AppleDTO actual = service.createApple(apple);
        //THEN
        assertEquals(expected, actual);
        verify(appleRepo).save(apple);
    }
}