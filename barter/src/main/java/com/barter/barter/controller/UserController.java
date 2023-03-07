package com.barter.barter.controller;

import com.barter.barter.data.dto.ErrorDTO;
import com.barter.barter.data.dto.user.UserDTO;
import com.barter.barter.data.dto.user.UserLoginDTO;
import com.barter.barter.data.dto.user.UserPostDTO;
import com.barter.barter.data.dto.user.UserUpdateDTO;
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
    public ResponseEntity postUser(@RequestBody UserPostDTO userPostDTO) {
        String id = userPostDTO.getId();
        String password = userPostDTO.getPassword();
        String name = userPostDTO.getName();
        String nickname = userPostDTO.getNickname();
        String img = userPostDTO.getImg();
        if(userService.getUser(id) == null){
            return new ResponseEntity<UserDTO>(userService.postUser(id, password, name, nickname, img), HttpStatus.OK);
        }else{
            return new ResponseEntity<ErrorDTO>(new ErrorDTO("DUPLICATE ID", "이미 있는 계정입니다."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id){
        UserDTO userDTO = userService.getUser(id);
        if(userDTO != null){
            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updatePassword(@PathVariable String id, @RequestBody UserUpdateDTO userUpdateDTO){
        try{
            userService.updateUser(id, userUpdateDTO.getPassword(), userUpdateDTO.getNickname(), userUpdateDTO.getImg());
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserLoginDTO loginDTO){
        String id = loginDTO.getId();
        String password = loginDTO.getPassword();
        UserDTO userDTO = userService.getUser(id);
        if(userDTO==null){return ResponseEntity.notFound().build();}
        if (userDTO.getPassword().equals(password)){
            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
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
