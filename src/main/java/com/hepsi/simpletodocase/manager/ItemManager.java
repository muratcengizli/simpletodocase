package com.hepsi.simpletodocase.manager;

import com.hepsi.simpletodocase.dto.request.ItemTodoDTO;
import com.hepsi.simpletodocase.dto.response.ItemResponseModel;
import com.hepsi.simpletodocase.dto.response.ResponseBaseModel;
import com.hepsi.simpletodocase.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

public interface ItemManager {
    /**
     * Converts ItemTodoDTO to an item and save to db as an item
     * @param itemTodoDTO - itemTodoDTO
     * @return RegisterResponseModel
     */
    ResponseEntity<ItemResponseModel> save(String userId, ItemTodoDTO itemTodoDTO);

    /**
     * Converts ItemTodoDTO to an item and update to db as an item
     * @param itemId - itemId
     * @param itemTodoDTO - itemTodoDTO
     * @return RegisterResponseModel
     */
    ResponseBaseModel<ResponseEntity<ItemResponseModel>> update(String itemId, ItemTodoDTO itemTodoDTO);
    /**
     * finds item in db and save item as deleted
     * @param itemId - itemId
     * @return String
     */
    ResponseBaseModel<ResponseEntity<String>> delete(String itemId);
    /**
     * finds item in db and delete to the item from db
     * @param item - item
     * @return String
     */
    void delete(Item item);
    /**
     * get active items
     * @return List<Item>
     */
    List<Item> getAll();

    List<Item> getAllItemsByUser(String userId);
}
