package com.hepsi.simpletodocase.manager;

import com.hepsi.simpletodocase.dto.request.ItemTodoDTO;
import com.hepsi.simpletodocase.dto.response.ItemResponseModel;
import com.hepsi.simpletodocase.dto.response.ResponseBaseModel;
import com.hepsi.simpletodocase.model.Item;
import com.hepsi.simpletodocase.repository.ItemRepository;
import com.hepsi.simpletodocase.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ItemManagerImpl implements ItemManager {

    @Autowired
    private ItemRepository itemRepository;
    @Override
    @Transactional
    public ResponseEntity<ItemResponseModel> save(ItemTodoDTO itemTodoDTO) {
        Item item = new Item();
        item.setItemId(String.valueOf(UUID.randomUUID()));
        if(!itemTodoDTO.getDescription().equals(null))
            item.setDescription(itemTodoDTO.getDescription());

        item.setIsDone(false);
        item.setModifiedDate(null);
        item.setDeletedDate(null);
        item.setIsDeleted(false);
        item.setCreatedDate(Instant.now());
        itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ItemResponseModel(item));
    }
    @Override
    @Transactional
    public ResponseBaseModel<ResponseEntity<ItemResponseModel>> update(String itemId, ItemTodoDTO itemTodoDTO) {

        Item item = itemRepository.findById(itemId);
        if (item.equals(null))
            return new ResponseBaseModel<>(
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ItemResponseModel()), Constants.ITEM_NOT_FOUND);

        if(!itemTodoDTO.getDescription().isEmpty())
            item.setDescription(itemTodoDTO.getDescription());

        if (Objects.equals(Boolean.TRUE, Boolean.valueOf(itemTodoDTO.getIsDone())))
            item.setIsDone(false);
        else
            item.setIsDone(true);

        item.setModifiedDate(Instant.now());
        return new ResponseBaseModel<>(
                ResponseEntity.status(HttpStatus.OK).body(new ItemResponseModel(item)));
    }
    @Override
    @Transactional
    public ResponseBaseModel<ResponseEntity<String>> delete(String itemId) {
        Item item = itemRepository.findById(itemId);
        if (item.equals(null))
            return new ResponseBaseModel<>(
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.ITEM_NOT_FOUND));
        item.setIsDeleted(true);
        item.setDeletedDate(Instant.now());
        return new ResponseBaseModel<>(
                ResponseEntity.status(HttpStatus.OK).body(Constants.DELETED));
    }

    @Override
    public List<Item> getAll() {return itemRepository.findAll();}

}
