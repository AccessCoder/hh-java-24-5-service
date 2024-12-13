package org.example.hhjava245service.repo;

import org.example.hhjava245service.model.Apple;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppleRepo extends MongoRepository<Apple, String> {
}
