package com.loop.backend.controller;

import com.loop.backend.entity.Message;
import com.loop.backend.entity.Room;
import com.loop.backend.repository.RoomRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/room")
public class RoomController {

    private final RoomRepository roomRepository;

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody @NonNull String roomId) {
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

    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable @NonNull String roomId) {
        Room room = roomRepository.findByRoomId(roomId).orElseThrow(
                () -> new RuntimeException("Room not found")
        );
        return ResponseEntity.ok(room);
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getMessages(
            @PathVariable @NonNull String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ) {
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
