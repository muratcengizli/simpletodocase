package com.hepsi.simpletodocase.item.managerImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hepsi.simpletodocase.controller.ItemController;
import com.hepsi.simpletodocase.controller.UserController;
import com.hepsi.simpletodocase.dto.request.ItemTodoDTO;
import com.hepsi.simpletodocase.dto.request.UserRegisterDTO;
import com.hepsi.simpletodocase.dto.response.ItemResponseModel;
import com.hepsi.simpletodocase.dto.response.ResponseBaseModel;
import com.hepsi.simpletodocase.dto.response.UserResponseModel;
import com.hepsi.simpletodocase.model.Item;
import com.hepsi.simpletodocase.model.User;
import com.hepsi.simpletodocase.repository.ItemRepository;
import com.hepsi.simpletodocase.util.Constants;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
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
public class ItemControllerTests {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @InjectMocks
    private ItemController itemController;

    @MockBean
    private ItemRepository itemRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Order(1)
    public void addItemTest_Success() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        ItemTodoDTO itemTodoDTO = new ItemTodoDTO();
        itemTodoDTO.setDescription("Finish the tests!");
        String jsonRequest = objectMapper.writeValueAsString(itemTodoDTO);
        MvcResult result = mockMvc.perform(
                        post("/api/item/create/100").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ResponseBaseModel<ResponseEntity<ItemResponseModel>> response = objectMapper.readValue(resultContent, ResponseBaseModel.class);
        assertEquals(response.getMessages(), Constants.CREATED);
    }

    @Test
    @Order(2)
    public void getAllUserTest_Success() throws Exception {
        Item item = setupItem();
        MvcResult result = mockMvc.perform(
                        get("/api/item/getAll").contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ResponseBaseModel<ResponseEntity<List<Item>>> response = objectMapper.readValue(resultContent, ResponseBaseModel.class);
        assertEquals(response.getMessages(), Constants.ITEMS_LISTED);

    }

    private Item setupItem() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Item item = new Item();
        item.setItemId("1");
        item.setDescription("Finish the assignment!");
        item.setIsDone(false);
        item.setCreatedDate(Instant.now());
        item.setModifiedDate(null);
        item.setIsDeleted(false);
        item.setDeletedDate(null);
        item.setUserId("100");
        return item;
    }
}
