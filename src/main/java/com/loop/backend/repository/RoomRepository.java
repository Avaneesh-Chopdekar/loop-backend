package com.loop.backend.repository;

import com.loop.backend.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

    Optional<Room> findByRoomId(String roomId);
}
