package com.hepsi.simpletodocase.manager;

import com.hepsi.simpletodocase.dto.request.UserEditDTO;
import com.hepsi.simpletodocase.dto.request.UserRegisterDTO;
import com.hepsi.simpletodocase.dto.response.UserResponseModel;
import com.hepsi.simpletodocase.dto.response.ResponseBaseModel;
import com.hepsi.simpletodocase.enums.ERole;
import com.hepsi.simpletodocase.enums.RegisterType;
import com.hepsi.simpletodocase.model.Item;
import com.hepsi.simpletodocase.model.User;
import com.hepsi.simpletodocase.repository.UserRepository;
import com.hepsi.simpletodocase.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.time.Instant;
import java.util.*;

@Service
public class UserManagerImpl implements UserManager {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemManager itemManager;

    @Override
    @Transactional
    public ResponseBaseModel<ResponseEntity<UserResponseModel>> save(UserRegisterDTO userRegisterDTO) {

        User user = new User();
        user.setEmailAddress(userRegisterDTO.getEmailAddress());
        user.setName(userRegisterDTO.getName());
        user.setPassword(userRegisterDTO.getPassword());
        user.setUserId(String.valueOf(UUID.randomUUID()));
        user.setRole(ERole.PERSONAL_USER);
        user.setRegisterType(RegisterType.LOCAL);
        user.setUpdatedDate(null);
        user.setDeletedDate(null);
        user.setIsDeleted(false);
        user.setCreatedAt(Instant.now());
        try{
            userRepository.save(user);
            return new ResponseBaseModel<>(
                    ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseModel(user)), Constants.CREATED);

        }catch(Exception e)  {
            return new ResponseBaseModel<>(
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponseModel()), Constants.NOT_CREATED);
        }
    }
    @Override
    @Transactional
    public ResponseBaseModel<ResponseEntity<UserResponseModel>> update(String userId, UserEditDTO userEditDTO) {
        try{
            User user = userRepository.findById(userId).get();
            if (ObjectUtils.isEmpty(user))
                return new ResponseBaseModel<>(
                        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponseModel()), Constants.USER_NOT_FOUND);

            if(!userEditDTO.getEmailAddress().isEmpty())
                user.setEmailAddress(userEditDTO.getName());

            if(!userEditDTO.getPassword().isEmpty())
                user.setPassword(userEditDTO.getPassword());

            if(!userEditDTO.getName().isEmpty())
                user.setName(userEditDTO.getName());

            user.setUpdatedDate(Instant.now());
            userRepository.save(user);
            return new ResponseBaseModel<>(
                    ResponseEntity.status(HttpStatus.OK).body(new UserResponseModel(user)));

        }catch(Exception e){
            return new ResponseBaseModel<>(
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponseModel()), Constants.NOT_UPDATED);
        }
    }



    @Override
    @Transactional
    public ResponseBaseModel<ResponseEntity<String>> passiveDelete(String userId) {

        try{
            User user = userRepository.findById(userId).get();
            if (ObjectUtils.isEmpty(user))
                return new ResponseBaseModel<>(
                        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.USER_NOT_FOUND));

            user.setIsDeleted(true);
            user.setDeletedDate(Instant.now());
            userRepository.save(user);
            return new ResponseBaseModel<>(
                    ResponseEntity.status(HttpStatus.OK).body(""), Constants.DELETED);

        }catch(Exception e){
            return new ResponseBaseModel<>(
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(""), Constants.NOT_DELETED);
        }
    }

    @Override
    public void delete(User user) {userRepository.delete(user);}

    @Override
    public String delete(String userId) {
        delete(userRepository.findById(userId).get());
        return Constants.DELETED;
    }

    @Override
    public ResponseBaseModel<ResponseEntity<List<User>>> getAll() {
        return new ResponseBaseModel<>(
                ResponseEntity.status(HttpStatus.OK).body(userRepository.findByIsDeleted(false)), Constants.USERS_LISTED);
    }

    @Override
    public List<User> getAlls() {
        return userRepository.findByIsDeleted(false);
    }

    @Override
    public ResponseBaseModel<ResponseEntity<List<Item>>> getAllItemsByUser(String userId) {
        return itemManager.getAllItemsByUser(userId);}

    @Override
    public List<Item> getAllItemByUser(String userId) {
        return itemManager.getAllItemByUser(userId);
    }

}
