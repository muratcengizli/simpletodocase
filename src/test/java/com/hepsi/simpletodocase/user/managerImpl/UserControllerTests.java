package com.hepsi.simpletodocase.user.managerImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hepsi.simpletodocase.controller.UserController;
import com.hepsi.simpletodocase.dto.request.UserEditDTO;
import com.hepsi.simpletodocase.dto.request.UserRegisterDTO;
import com.hepsi.simpletodocase.dto.response.ResponseBaseModel;
import com.hepsi.simpletodocase.dto.response.UserResponseModel;
import com.hepsi.simpletodocase.enums.ERole;
import com.hepsi.simpletodocase.enums.RegisterType;
import com.hepsi.simpletodocase.model.User;
import com.hepsi.simpletodocase.repository.ItemRepository;
import com.hepsi.simpletodocase.repository.UserRepository;
import com.hepsi.simpletodocase.util.Constants;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.time.Instant;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class UserControllerTests {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Order(1)
    public void addUserTest_Success() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmailAddress("murat@gmail.com");
        userRegisterDTO.setPassword("12345678");
        userRegisterDTO.setName("Murat");
        String jsonRequest = objectMapper.writeValueAsString(userRegisterDTO);
        MvcResult result = mockMvc.perform(
                                    post("/api/user/create").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                                    .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ResponseBaseModel<ResponseEntity<UserResponseModel>> response = objectMapper.readValue(resultContent, ResponseBaseModel.class);
        assertEquals(response.getMessages(), Constants.CREATED);
    }

    @Test
    @Order(2)
    public void getAllUserTest_Success() throws Exception {
        User user = setupUser();
        MvcResult result = mockMvc.perform(
                                    get("/api/user/getAll").contentType(MediaType.APPLICATION_JSON_VALUE))
                                    .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ResponseBaseModel<ResponseEntity<List<User>>> response = objectMapper.readValue(resultContent, ResponseBaseModel.class);
        assertEquals(response.getMessages(), Constants.USERS_LISTED);

    }

    @Test
    @Order(3)
    public void getAllItemsByUserTest_Success() throws Exception {
        User user = setupUser();
        MvcResult result = mockMvc.perform(
                        get("/api/user/getAllItems/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ResponseBaseModel<ResponseEntity<List<User>>> response = objectMapper.readValue(resultContent, ResponseBaseModel.class);
        assertEquals(response.getMessages(), Constants.ITEMS_LISTED);

    }

    public User setupUser()	{
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        User user = new User();
        user.setUserId("1");
        user.setEmailAddress("murat@gmail.com");
        user.setPassword("12345678");
        user.setName("Murat");
        user.setRole(ERole.PERSONAL_USER);
        user.setRegisterType(RegisterType.LOCAL);
        user.setIsDeleted(false);
        user.setDeletedDate(null);
        user.setUpdatedDate(null);
        user.setCreatedAt(Instant.now());
        return user;
    }
}
