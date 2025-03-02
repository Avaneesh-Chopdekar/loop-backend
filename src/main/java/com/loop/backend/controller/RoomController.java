package com.loop.backend.controller;

import com.loop.backend.service.RoomService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequiredArgsConstructor
@Controller
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody @NonNull String roomId) {
        return roomService.createRoom(roomId);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable @NonNull String roomId) {
        return roomService.joinRoom(roomId);
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getMessages(
            @PathVariable @NonNull String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ) {
        return roomService.getMessages(roomId, page, size);
    }
}
