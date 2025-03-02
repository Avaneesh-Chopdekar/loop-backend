package com.loop.backend.service;

import com.loop.backend.entity.Message;
import com.loop.backend.entity.Room;
import com.loop.backend.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public ResponseEntity<?> createRoom(String roomId) {
        if (roomRepository.findByRoomId(roomId).isPresent()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "true");
            error.put("roomId", roomId);
            error.put("message", "Room ID already exists");
            return ResponseEntity.badRequest().body(error);
        }

        Room room = new Room();
        room.setRoomId(roomId);
        roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    public ResponseEntity<?> joinRoom(String roomId) {
        Room room = roomRepository.findByRoomId(roomId).orElseThrow(
                () -> new RuntimeException("Room not found")
        );
        return ResponseEntity.ok(room);
    }

    public ResponseEntity<?> getMessages(String roomId, int page, int size) {
        Room room = roomRepository.findByRoomId(roomId).orElseThrow(
                () -> new RuntimeException("Room not found")
        );

        List<Message> messages = room.getMessages();

        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        List<Message> paginatedMessages = messages.subList(start, end);

        return ResponseEntity.ok(room.getMessages());
    }
}
