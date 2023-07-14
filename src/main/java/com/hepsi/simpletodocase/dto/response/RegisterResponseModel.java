package com.hepsi.simpletodocase.dto.response;

import com.hepsi.simpletodocase.enums.ERole;
import com.hepsi.simpletodocase.model.User;
import lombok.Data;

@Data
public class RegisterResponseModel {
    private User user;
    private ERole eRole;

    public RegisterResponseModel(User user){
        this.user = user;
        this.eRole = ERole.PERSONAL_USER;
    }
}
