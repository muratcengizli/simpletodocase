package com.hepsi.simpletodocase.controller;

import com.hepsi.simpletodocase.dto.request.UserRegisterDTO;
import com.hepsi.simpletodocase.dto.response.RegisterResponseModel;
import com.hepsi.simpletodocase.manager.AuthManager;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthManager authManager;

    public AuthController(AuthManager authManager) {
        this.authManager = authManager;
    }

    @PostMapping("/create")
    public ResponseEntity<RegisterResponseModel> createPersonal(@RequestBody UserRegisterDTO userRegisterDTO, BindingResult result) throws Exception {

        if (result != null && result.hasErrors() && result.getFieldError() != null) {
            try{
                throw new Exception(result.getFieldError().getDefaultMessage());
            } catch (Exception e) {
                throw new Exception("binding result error");
            }
        }

        return authManager.save(userRegisterDTO);
    }
}
