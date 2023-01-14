package com.barter.barter.controller;

import com.barter.barter.data.dto.ErrorDTO;
import com.barter.barter.data.dto.UserDTO;
import com.barter.barter.data.dto.UserLoginDTO;
import com.barter.barter.data.dto.UserPostDTO;
import com.barter.barter.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){this.userService = userService;}

    @PostMapping(value = "/signup")
    public ResponseEntity postUser(@RequestPart(value = "user-data") UserPostDTO userPostDTO, @RequestPart(value = "file-data", required = false) MultipartFile image) throws IOException {
        String id = userPostDTO.getId();
        String password = userPostDTO.getPassword();
        String name = userPostDTO.getName();
        String nickname = userPostDTO.getNickname();
        if(userService.getUser(id) == null){
            if(image == null){
                return new ResponseEntity<UserDTO>(userService.postUser(id, name, password, nickname, image), HttpStatus.OK);
            }else {
                if (!Objects.requireNonNull(image.getContentType()).contains("image")) {
                    return ResponseEntity.badRequest().build();
                } else {
                    return new ResponseEntity<UserDTO>(userService.postUser(id, name, password, nickname, image), HttpStatus.OK);
                }
            }
        }else{
            return new ResponseEntity<ErrorDTO>(new ErrorDTO("DUPLICATE ID", "이미 있는 계정입니다."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id){
        UserDTO userDTO = userService.getUser(id);
        if(userDTO != null){
            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity updatePassword(@PathVariable String id, @RequestBody String password){
        try{
            userService.updateUser(id, password);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/login")
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
