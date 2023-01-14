package com.barter.barter.data.dao;

import com.barter.barter.data.dto.UserLoginDTO;
import com.barter.barter.data.entity.UserEntity;
import com.barter.barter.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDAO {
    UserRepository userRepository;

    @Autowired
    public UserDAO(UserRepository userRepository){this.userRepository = userRepository;}

    public UserEntity saveUser(UserEntity userEntity){
        userRepository.save(userEntity);
        return userEntity;
    }

    public UserEntity getUser(String id){
        UserEntity userEntity = userRepository.findById(id).get();
        return userEntity;
    }

//    public UserLoginDTO loginUser(String id, String password){
//        UserEntity userEntity = userRepository.findById(id).get();
//        if(userEntity.getPassword().equals(password)) {
//            UserLoginDTO userLoginDTO = new UserLoginDTO(userEntity.getId(), userEntity.getPassword());
//            return userLoginDTO;
//        } else {
//
//        }
//    }
}
