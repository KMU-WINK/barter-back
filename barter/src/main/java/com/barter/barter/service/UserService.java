package com.barter.barter.service;

import com.barter.barter.data.dto.UserDTO;
import com.barter.barter.data.dto.UserLoginDTO;
import com.barter.barter.data.entity.UserEntity;
import com.barter.barter.data.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserHandler userHandler;

    @Autowired
    public UserService(UserHandler userHandler){this.userHandler=userHandler;}

    public UserDTO postUser(String id, String password, String name, String nickname){
        UserEntity userEntity = userHandler.postUserEntity(id, password, name, nickname);

        UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getPassword(), userEntity.getName(), userEntity.getNickname());
        return userDTO;
    }

    public UserDTO getUser(String id){
        UserEntity userEntity = userHandler.getUserEntity(id);

        UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getPassword(), userEntity.getName(), userEntity.getNickname());
        return userDTO;
    }

//    public UserLoginDTO loginUser(String id, String password){
//        u
//    }
}
