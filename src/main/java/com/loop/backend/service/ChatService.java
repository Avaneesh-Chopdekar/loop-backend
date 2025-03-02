package com.loop.backend.service;

import com.loop.backend.dto.MessageRequest;
import com.loop.backend.entity.Message;
import com.loop.backend.entity.Room;
import com.loop.backend.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final RoomRepository roomRepository;

    public Message sendMessage(String roomId, MessageRequest request) {
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
