package com.loop.backend.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "rooms")
public class Room extends BaseEntity { // Inherits id, createdAt, updatedAt

    @NotBlank(message = "Room ID cannot be blank")
    private String roomId;

    private List<Message> messages = new ArrayList<>();
}