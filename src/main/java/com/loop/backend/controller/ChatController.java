package com.loop.backend.controller;

import com.loop.backend.entity.Message;
import com.loop.backend.entity.Room;
import com.loop.backend.dto.MessageRequest;
import com.loop.backend.repository.RoomRepository;
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

    private final RoomRepository roomRepository;

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(
            @DestinationVariable @NonNull String roomId,
            @RequestBody @NonNull MessageRequest request
    ) {
        Room room = roomRepository.findByRoomId(roomId).orElseThrow(
                () -> new RuntimeException("Room not found")
        );
        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        room.getMessages().add(message);
        roomRepository.save(room);
        return message;
    }
}
