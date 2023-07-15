package com.hepsi.simpletodocase.dto.request;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserEditDTO {

    @Email(
            message = "Email is not valid",
            regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String emailAddress;

    @Size(min = 8, max = 50, message = "password length must be between 8 and 50 characters long!")
    private String password;

    private String name;
}
