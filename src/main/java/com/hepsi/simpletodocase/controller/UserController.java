package com.hepsi.simpletodocase.controller;

import com.hepsi.simpletodocase.dto.request.UserEditDTO;
import com.hepsi.simpletodocase.dto.request.UserRegisterDTO;
import com.hepsi.simpletodocase.dto.response.ResponseBaseModel;
import com.hepsi.simpletodocase.dto.response.UserResponseModel;
import com.hepsi.simpletodocase.manager.UserManager;
import com.hepsi.simpletodocase.model.Item;
import com.hepsi.simpletodocase.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/create")
    public ResponseBaseModel<ResponseEntity<UserResponseModel>> createPersonal(@Valid @RequestBody UserRegisterDTO userRegisterDTO, BindingResult result) throws Exception {

        if (result != null && result.hasErrors() && result.getFieldError() != null) {
            try{
                throw new Exception(result.getFieldError().getDefaultMessage());
            } catch (Exception e) {
                throw new Exception("binding result error");
            }
        }

        return userManager.save(userRegisterDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseBaseModel<ResponseEntity<UserResponseModel>> update(@PathVariable("id") String userId,
                                                                       @Valid @RequestBody UserEditDTO userEditDTO, BindingResult result) throws Exception {

        if (result != null && result.hasErrors() && result.getFieldError() != null) {
            try{
                throw new Exception(result.getFieldError().getDefaultMessage());
            } catch (Exception e) {
                throw new Exception("binding result error");
            }
        }

        return userManager.update(userId, userEditDTO);
    }

    @PutMapping("/delete/{id}")
    public ResponseBaseModel<ResponseEntity<String>> delete(@PathVariable("id") String userId,
                                                            @Valid @RequestBody UserEditDTO userEditDTO, BindingResult result) throws Exception {

        if (result != null && result.hasErrors() && result.getFieldError() != null) {
            try{
                throw new Exception(result.getFieldError().getDefaultMessage());
            } catch (Exception e) {
                throw new Exception("binding result error");
            }
        }

        return userManager.passiveDelete(userId);
    }
    @GetMapping("/getAll")
    public ResponseBaseModel<ResponseEntity<List<User>>> getAll() {return userManager.getAll();}

    @GetMapping("/getAllItems/{id}")
    public ResponseBaseModel<ResponseEntity<List<Item>>> getAllItems(@PathVariable("id") String userId) throws Exception {
        return userManager.getAllItemsByUser(userId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") String userId, BindingResult result) throws Exception {

        if (result != null && result.hasErrors() && result.getFieldError() != null) {
            try{
                throw new Exception(result.getFieldError().getDefaultMessage());
            } catch (Exception e) {
                throw new Exception("binding result error");
            }
        }

        return userManager.delete(userId);
    }

}
