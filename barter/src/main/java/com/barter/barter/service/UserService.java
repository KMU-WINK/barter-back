package com.barter.barter.service;

import com.barter.barter.data.dto.user.UserDTO;
import com.barter.barter.data.entity.UserEntity;
import com.barter.barter.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public UserDTO postUser(String id, String password, String name, String nickname, String img){
        UserEntity userEntity = UserEntity.builder()
                .user_id(id)
                .password(password)
                .name(name)
                .nickname(nickname)
                .img(img)
                .build();
        userRepository.save(userEntity);
        UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getUser_id(), userEntity.getPassword(), userEntity.getName(), userEntity.getNickname(), userEntity.getImg());
        return userDTO;
    }

    public UserDTO getUser(String id){
        UserEntity userEntity = userRepository.findByUser_id(id);
        if(userEntity==null){
            return null;
        } else {
            UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getUser_id(), userEntity.getPassword(), userEntity.getName(), userEntity.getNickname(), userEntity.getImg());
            return userDTO;
        }
    }

    public void deleteUser(String id){
        userRepository.deleteByUser_id(id);
    }

    public UserDTO updateUser(String id, String password, String nickname, String img){
        UserEntity userEntity = userRepository.findByUser_id(id);

        userEntity.setPassword(password);
        userEntity.setNickname(nickname);
        userEntity.setImg(img);
        userRepository.save(userEntity);
        return new UserDTO(userEntity.getId(), userEntity.getUser_id(), userEntity.getPassword(), userEntity.getName(), userEntity.getNickname(), userEntity.getImg());
    }
}
