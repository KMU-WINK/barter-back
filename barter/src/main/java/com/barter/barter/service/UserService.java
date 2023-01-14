package com.barter.barter.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.barter.barter.S3key;
import com.barter.barter.config.S3Config;
import com.barter.barter.data.dto.UserDTO;
import com.barter.barter.data.dto.UserLoginDTO;
import com.barter.barter.data.dto.UserPostDTO;
import com.barter.barter.data.entity.UserEntity;
import com.barter.barter.data.handler.UserHandler;
import com.barter.barter.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Component
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserHandler userHandler;

//    @Value("cloud.aws.s3.bucket")
    private final String bucketName = (new S3key()).bucket;

    @Autowired
    public UserService(UserHandler userHandler,
                       UserRepository userRepository){
        this.userHandler=userHandler;
        this.userRepository = userRepository;
    }
    public UserPostDTO postUser(String id, String password, String name, String nickname){
        UserEntity userEntity = userHandler.postUserEntity(id, name, password, nickname, null);
        UserPostDTO userPostDTO = new UserPostDTO(userEntity.getId(), userEntity.getPassword(), userEntity.getName(), userEntity.getNickname());
        return userPostDTO;
    }

    public String updateImage(String id, MultipartFile image) throws IOException {
        S3Config s3Config = new S3Config();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(image.getContentType());
        objectMetadata.setContentLength(image.getSize());
        s3Config.amazonS3().putObject(new PutObjectRequest(bucketName, id, image.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        String url = String.valueOf(s3Config.amazonS3().getUrl(bucketName, id));

        UserEntity userEntity = userHandler.getUserEntity(id);

        userEntity.setImg(url);
        userRepository.save(userEntity);
        return url;
    }

    public UserDTO getUser(String id){
        UserEntity userEntity = userHandler.getUserEntity(id);
        if(userEntity==null){
            return null;
        } else {
            UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getPassword(), userEntity.getName(), userEntity.getNickname(), userEntity.getImg());
            return userDTO;
        }
    }

    public void deleteUser(String id){
        userHandler.deleteUserEntity(id);
    }

    public void updateUser(String id, String password){
        UserEntity userEntity = userHandler.getUserEntity(id);

        userEntity.setPassword(password);
        userRepository.save(userEntity);
    }
}
