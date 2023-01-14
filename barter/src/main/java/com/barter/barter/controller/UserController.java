package com.barter.barter.controller;

import com.barter.barter.data.dto.UserDTO;
import com.barter.barter.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){this.userService = userService;}

    @PostMapping(value = "/signup")
    public UserDTO postUser(@RequestBody UserDTO userDTO){
        String id = userDTO.getId();
        String password = userDTO.getPassword();
        String name = userDTO.getName();
        String nickname = userDTO.getNickname();
        return userService.postUser(id, name, password, nickname);
    }

    @GetMapping(value = "/user/{id}")
    public UserDTO getUser(@PathVariable String id){return userService.getUser(id);}
}
