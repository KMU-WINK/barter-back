package com.barter.barter.controller;

import com.barter.barter.data.dto.UserDTO;
import com.barter.barter.data.dto.UserLoginDTO;
import com.barter.barter.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    @GetMapping(value = "/user/{id}")
//    public UserDTO getUser(@PathVariable String id){
//        return userService.getUser(id);
//    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id){
        UserDTO userDTO = userService.getUser(id);
        if(userDTO != null){
            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/user/login")
    public ResponseEntity<UserLoginDTO> loginUser(@RequestBody UserLoginDTO loginDTO){
        String id = loginDTO.getId();
        String password = loginDTO.getPassword();
        UserDTO userDTO = userService.getUser(id);
        if(userDTO==null){return ResponseEntity.notFound().build();}
        if (userDTO.getPassword().equals(password)){
            UserLoginDTO userLoginDTO = new UserLoginDTO(userDTO.getId(), userDTO.getPassword());
            return new ResponseEntity<UserLoginDTO>(userLoginDTO, HttpStatus.OK);
        }
        else {return ResponseEntity.notFound().build();}
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
