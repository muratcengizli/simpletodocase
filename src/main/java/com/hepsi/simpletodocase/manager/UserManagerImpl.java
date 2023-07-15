package com.hepsi.simpletodocase.manager;

import com.hepsi.simpletodocase.dto.request.UserEditDTO;
import com.hepsi.simpletodocase.dto.request.UserRegisterDTO;
import com.hepsi.simpletodocase.dto.response.UserResponseModel;
import com.hepsi.simpletodocase.dto.response.ResponseBaseModel;
import com.hepsi.simpletodocase.enums.ERole;
import com.hepsi.simpletodocase.enums.RegisterType;
import com.hepsi.simpletodocase.model.User;
import com.hepsi.simpletodocase.repository.UserRepository;
import com.hepsi.simpletodocase.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UserManagerImpl implements UserManager {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<UserResponseModel> save(UserRegisterDTO userRegisterDTO) {

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
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseModel(user));
    }
    @Override
    @Transactional
    public ResponseBaseModel<ResponseEntity<UserResponseModel>> update(String userId, UserEditDTO userEditDTO) {

        User user = userRepository.findByEmailAddress(userId);
        if (user.equals(null))
            return new ResponseBaseModel<>(
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponseModel()), Constants.USER_NOT_FOUND);

        if(!userEditDTO.getEmailAddress().isEmpty())
            user.setEmailAddress(userEditDTO.getName());

        if(!userEditDTO.getPassword().isEmpty())
            user.setPassword(userEditDTO.getPassword());

        if(!userEditDTO.getName().isEmpty())
            user.setName(userEditDTO.getName());

        user.setUpdatedDate(Instant.now());
        return new ResponseBaseModel<>(
                ResponseEntity.status(HttpStatus.OK).body(new UserResponseModel(user)));
    }
    @Override
    @Transactional
    public ResponseBaseModel<ResponseEntity<String>> delete(String userId) {

        User user = userRepository.findById(userId);
        if (user.equals(null))
            return new ResponseBaseModel<>(
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.USER_NOT_FOUND));

        user.setIsDeleted(true);
        user.setDeletedDate(Instant.now());
        return new ResponseBaseModel<>(
                ResponseEntity.status(HttpStatus.OK).body(Constants.DELETED));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


}
