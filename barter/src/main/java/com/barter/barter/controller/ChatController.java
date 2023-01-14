package com.barter.barter.controller;

import com.barter.barter.data.dto.ChatRoomDTO;
import com.barter.barter.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoomDTO createRoom(@RequestBody String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoomDTO> findAllRoom() {
        return chatService.findAllRoom();
    }
}
