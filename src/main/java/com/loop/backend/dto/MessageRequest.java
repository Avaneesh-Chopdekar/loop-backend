package com.loop.backend.dto;

import lombok.Data;


@Data
public class MessageRequest {

    private String content;
    private String sender;
    private String roomId;
}
