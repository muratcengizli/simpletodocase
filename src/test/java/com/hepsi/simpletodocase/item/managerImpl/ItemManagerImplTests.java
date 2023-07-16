package com.hepsi.simpletodocase.item.managerImpl;

import com.hepsi.simpletodocase.manager.ItemManager;
import com.hepsi.simpletodocase.model.Item;
import com.hepsi.simpletodocase.repository.ItemRepository;
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
public class ItemManagerImplTests {

    @Autowired
    private ItemManager itemManager;
    @MockBean
    private ItemRepository itemRepository;

    @Test
    @Order(1)
    void testGetUserSave_Success() {
        Item item = setup();
        String item_id = "1";
        String description = "Finish the assignment!";
        String user_id = "100";
        Boolean is_deleted = false;

        assertEquals(user_id, item.getUserId());
        assertEquals(item_id, item.getItemId());
        assertEquals(description, item.getDescription());
        assertEquals(is_deleted, item.getIsDeleted());
    }

    @Test
    @Order(2)
    void testGetItems_Success() {
        Item item = setup();
        Item item1 = item;
        item.setItemId("2");
        Mockito.when(itemRepository.save(item1)).thenReturn(item1);
        Mockito.when(itemManager.getAlls()).thenReturn(Stream.of(item, item1).collect(Collectors.toList()));
        assertEquals(2, itemManager.getAlls().size());

    }

    @Test
    @Order(3)
    void testGetUserDelete_Success() {
        Item item = setup();
        itemManager.delete(item);
        verify(itemRepository, times(1)).delete(item);

    }
    public Item setup()	{
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
