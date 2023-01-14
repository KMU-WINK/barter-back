package com.barter.barter.data.handler;

import com.barter.barter.data.dao.UserDAO;
import com.barter.barter.data.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHandler {

    UserDAO userDAO;

    @Autowired
    public UserHandler(UserDAO userDAO){this.userDAO=userDAO;}

    public UserEntity postUserEntity(String id, String password, String name, String nickname){
        UserEntity userEntity = new UserEntity(id, password, name, nickname);
        return userDAO.saveUser(userEntity);
    }
    public UserEntity getUserEntity(String id){return userDAO.getUser(id);}
    public void deleteUserEntity(String id){userDAO.deleteUser(id);}
}
