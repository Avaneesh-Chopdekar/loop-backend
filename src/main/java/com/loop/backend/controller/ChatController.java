package com.loop.backend.controller;

import com.loop.backend.entity.Message;
import com.loop.backend.dto.MessageRequest;
import com.loop.backend.service.ChatService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(
            @DestinationVariable @NonNull String roomId,
            @RequestBody @NonNull MessageRequest request
    ) {
        return chatService.sendMessage(roomId, request);
    }
}
