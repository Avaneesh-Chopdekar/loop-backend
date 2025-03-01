package com.loop.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message extends BaseEntity {

    @Column(nullable = false)
    @NotBlank(message = "Message content cannot be blank")
    private String content;

    @Column(nullable = false)
    @NotBlank(message = "Sender cannot be blank")
    private String sender;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}
