package com.hepsi.simpletodocase.manager;

import com.hepsi.simpletodocase.dto.request.UserEditDTO;
import com.hepsi.simpletodocase.dto.request.UserRegisterDTO;
import com.hepsi.simpletodocase.dto.response.UserResponseModel;
import com.hepsi.simpletodocase.dto.response.ResponseBaseModel;
import com.hepsi.simpletodocase.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserManager {
    /**
     * Converts UserRegisterDTO to User and save to db as a user with LOCAL register type
     * @param userRegisterDTO - userRegisterDTO
     * @return RegisterResponseModel
     */
    ResponseEntity<UserResponseModel> save(UserRegisterDTO userRegisterDTO);
    /**
     * Converts UserEditDTO to lUser and update to db for a user
     * @param userId - userId
     * @param userEditDTO - userEditDTO
     * @return RegisterResponseModel
     */
    ResponseBaseModel<ResponseEntity<UserResponseModel>> update(String userId, UserEditDTO userEditDTO);
    /**
     * finds user in db and delete to the user
     * @param userId - userId
     * @return String
     */
    ResponseBaseModel<ResponseEntity<String>> delete(String userId);
    /**
     * get active users
     * @return List<User>
     */
    List<User> getAll();

}
