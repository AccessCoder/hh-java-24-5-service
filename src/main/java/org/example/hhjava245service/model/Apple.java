package org.example.hhjava245service.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("Apples")
public record Apple(String id, String name, String color, double price, double retailPrice) {
}
