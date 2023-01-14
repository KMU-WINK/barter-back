package com.barter.barter.service;

import com.barter.barter.data.dto.UserDTO;
import com.barter.barter.data.dto.UserLoginDTO;
import com.barter.barter.data.entity.UserEntity;
import com.barter.barter.data.handler.UserHandler;
import com.barter.barter.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserHandler userHandler;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserHandler userHandler,
                       UserRepository userRepository){this.userHandler=userHandler;
        this.userRepository = userRepository;
    }

    public UserDTO postUser(String id, String password, String name, String nickname){
        UserEntity userEntity = userHandler.postUserEntity(id, password, name, nickname);

        UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getPassword(), userEntity.getName(), userEntity.getNickname());
        return userDTO;
    }

    public UserDTO getUser(String id){
        UserEntity userEntity = userHandler.getUserEntity(id);
        if(userEntity==null){
            return null;
        } else {
            UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getPassword(), userEntity.getName(), userEntity.getNickname());
            return userDTO;
        }
    }

    public void deleteUser(String id){
        userHandler.deleteUserEntity(id);
    }
}
