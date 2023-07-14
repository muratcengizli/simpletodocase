package com.hepsi.simpletodocase.manager;

import com.hepsi.simpletodocase.dto.request.UserRegisterDTO;
import com.hepsi.simpletodocase.dto.response.RegisterResponseModel;
import com.hepsi.simpletodocase.enums.ERole;
import com.hepsi.simpletodocase.enums.RegisterType;
import com.hepsi.simpletodocase.model.User;
import com.hepsi.simpletodocase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class AuthManagerImpl implements AuthManager {
    @Autowired
    private UserRepository userRepository;

    //private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public ResponseEntity<RegisterResponseModel> save(UserRegisterDTO userRegisterDTO) {

        User user = new User();
        user.setEmailAddress(userRegisterDTO.getEmailAddress());
        user.setName(userRegisterDTO.getName());
       // user.setPassword(bCryptPasswordEncoder.encode(userRegisterDTO.getPassword()));
        user.setPassword(userRegisterDTO.getPassword());
        user.setUuid(String.valueOf(UUID.randomUUID()));
        user.setRole(ERole.PERSONAL_USER);
        user.setRegisterType(RegisterType.LOCAL);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponseModel(user));
    }

}
