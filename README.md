# Simple To-Do List 
This project is a backend project which creates an user and provides user to prepare item list.

# Documantation
This project has JavaDoc in project and has Swagger in terms of documentation. 

# Technologies
• Java 8 
• Spring Boot 
• Spring Data Couchbase 
• Couchbase 
• Swagger 
• Maven 
• Docker 
• JUnit and Mockito 

# Running Tests
Unit and Integration tests is written  by using Mockito and Junit.
Also manual tests were done by using Postman.

![postman](https://github.com/muratcengizli/simpletodocase/assets/72807094/6c8a960d-cf6b-4bed-9dd1-928a41463c52)

You can see an example below for Service anda Repository layers. 

    @Test
    @Order(3)
    void testGetUserDelete_Success() {
        User user = setup();
        userManager.delete(user);
        verify(userRepository, times(1)).delete(user);

    }
This test creates a user in a repo and delete it by using delete method of service layer.

Also you can see an example below for controller layer. 

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
    
This test builds a mockMvc and creates a registerDto. After it builds a mock perform and sends a request dreate adress, 
according to response it compares response status and expect status.

# Links
https://hub.docker.com/repository/docker/muratcengizli/simpletodocaseapp

# ScreenShots
![Swagger](https://github.com/muratcengizli/simpletodocase/assets/72807094/395f9487-2521-40f6-85b8-af74e7d3467e)
![Docker-Hub](https://github.com/muratcengizli/simpletodocase/assets/72807094/88684e1b-db5c-4c0e-b811-d8d5341d7ec0)
![postman2](https://github.com/muratcengizli/simpletodocase/assets/72807094/ca451260-0744-4ec9-9dd4-28b8537ebf0f)




