package com.hepsi.simpletodocase.manager;

import com.hepsi.simpletodocase.dto.request.UserRegisterDTO;
import com.hepsi.simpletodocase.dto.response.RegisterResponseModel;
import org.springframework.http.ResponseEntity;

public interface AuthManager {

    /**
     * Converts PersonalUserRegisterDTO to PersonalUser and save to db as a user with LOCAL register type
     * @param userRegisterDTO - userRegisterDTO
     * @return RegisterResponseModel
     */
    ResponseEntity<RegisterResponseModel> save(UserRegisterDTO userRegisterDTO);

}
