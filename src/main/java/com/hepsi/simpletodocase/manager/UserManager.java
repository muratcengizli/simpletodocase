package com.hepsi.simpletodocase.manager;

import com.hepsi.simpletodocase.dto.request.UserEditDTO;
import com.hepsi.simpletodocase.dto.request.UserRegisterDTO;
import com.hepsi.simpletodocase.dto.response.UserResponseModel;
import com.hepsi.simpletodocase.dto.response.ResponseBaseModel;
import com.hepsi.simpletodocase.model.Item;
import com.hepsi.simpletodocase.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserManager {
    /**
     * Converts UserRegisterDTO to User and save to db as a user with LOCAL register type
     * @param userRegisterDTO - userRegisterDTO
     * @return RegisterResponseModel
     */
    ResponseBaseModel<ResponseEntity<UserResponseModel>> save(UserRegisterDTO userRegisterDTO);
    /**
     * Converts UserEditDTO to lUser and update to db for a user
     * @param userId - userId
     * @param userEditDTO - userEditDTO
     * @return RegisterResponseModel
     */
    ResponseBaseModel<ResponseEntity<UserResponseModel>> update(String userId, UserEditDTO userEditDTO);
    /**
     * finds user in db and save user as deleted
     * @param userId - userId
     * @return String
     */
    ResponseBaseModel<ResponseEntity<String>> delete(String userId);
    /**
     * finds user in db and delete to the user
     * @param user - user
     * @return String
     */
    void delete(User user);
    /**
     * get active users as a ResponseModel
     * @return ResponseBaseModel<ResponseEntity<List<User>>>
     */
    ResponseBaseModel<ResponseEntity<List<User>>> getAll();
    /**
     * get active users
     * @return List<User>
     */
    List<User> getAlls();

    /**
     * get active items of specific user as a ResponseModel
     * @return ResponseBaseModel<ResponseEntity<List<Item>>>
     */
    ResponseBaseModel<ResponseEntity<List<Item>>> getAllItemsByUser(String userId);
    /**
     * get active items of specific user
     * @param userId - userId
     * @return List<Item>
     */
    List<Item> getAllItemByUser(String userId);

}
