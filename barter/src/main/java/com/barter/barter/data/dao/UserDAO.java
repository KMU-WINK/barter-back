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
        try {
            UserEntity userEntity = userRepository.findById(id).get();
            return userEntity;
        }catch (Exception e){
            return null;
        }
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
