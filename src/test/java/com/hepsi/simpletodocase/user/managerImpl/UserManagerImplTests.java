package com.hepsi.simpletodocase.user.managerImpl;

import com.hepsi.simpletodocase.enums.ERole;
import com.hepsi.simpletodocase.enums.RegisterType;
import com.hepsi.simpletodocase.manager.UserManager;
import com.hepsi.simpletodocase.model.Item;
import com.hepsi.simpletodocase.model.User;
import com.hepsi.simpletodocase.repository.ItemRepository;
import com.hepsi.simpletodocase.repository.UserRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.Instant;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserManagerImplTests {

    @Autowired
    private UserManager userManager;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ItemRepository itemRepository;

    @Test
    @Order(1)
    void testGetUserSave_Success() {
        User user = setup();
        String user_id = "1";
        String email_Adress = "murat@gmail.com";
        String name = "Murat";
        Boolean is_deleted = false;

        assertEquals(user_id, user.getUserId());
        assertEquals(email_Adress, user.getEmailAddress());
        assertEquals(name, user.getName());
        assertEquals(is_deleted, user.getIsDeleted());
    }

    @Test
    @Order(2)
    void testGetUsers_Success() {
            User user = setup();
            User user1 = user;
            user1.setUserId("2");
            Mockito.when(userRepository.save(user1)).thenReturn(user1);
            Mockito.when(userManager.getAlls()).thenReturn(Stream.of(user, user1).collect(Collectors.toList()));
            assertEquals(2, userManager.getAlls().size());

    }

    @Test
    @Order(3)
    void testGetUserDelete_Success() {
        User user = setup();
        userManager.delete(user);
        verify(userRepository, times(1)).delete(user);

    }

    @Test
    @Order(4)
    void testGetItemsByUser_Success() {
        User user = setup();
        Item item = setup1();
        Item item1 = item;
        item1.setItemId("2");
        Mockito.when(itemRepository.save(item1)).thenReturn(item1);
        Mockito.when(userManager.getAllItemByUser(user.getUserId())).thenReturn(Stream.of(item, item1).collect(Collectors.toList()));
        assertEquals(2, userManager.getAllItemByUser(user.getUserId()).size());

    }

    public User setup()	{
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
        Mockito.when(userRepository.save(user)).thenReturn(user);
        return user;
    }

    public Item setup1()	{
        Item item = new Item();
        item.setItemId("1");
        item.setDescription("Finish the assignment!");
        item.setIsDone(false);
        item.setCreatedDate(Instant.now());
        item.setModifiedDate(null);
        item.setIsDeleted(false);
        item.setDeletedDate(null);
        item.setUserId("100");
        Mockito.when(itemRepository.save(item)).thenReturn(item);
        return item;
    }

}
