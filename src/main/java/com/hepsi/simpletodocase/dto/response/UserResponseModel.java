package com.hepsi.simpletodocase.dto.response;

import com.hepsi.simpletodocase.enums.ERole;
import com.hepsi.simpletodocase.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseModel {
    private User user;
    private ERole eRole;

    public UserResponseModel(User user){
        this.user = user;
        this.eRole = ERole.PERSONAL_USER;
    }
}
