package com.hepsi.simpletodocase.controller;

import com.hepsi.simpletodocase.dto.request.ItemTodoDTO;
import com.hepsi.simpletodocase.dto.response.ItemResponseModel;
import com.hepsi.simpletodocase.dto.response.ResponseBaseModel;
import com.hepsi.simpletodocase.manager.ItemManager;
import com.hepsi.simpletodocase.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemManager itemManager;

    public ItemController(ItemManager itemManager) {this.itemManager = itemManager;}

    @PostMapping("/create/{id}")
    public ResponseBaseModel<ResponseEntity<ItemResponseModel>> create(@PathVariable("id") String userId,
                                                                       @Valid @RequestBody ItemTodoDTO itemTodoDTO, BindingResult result) throws Exception {

        if (result != null && result.hasErrors() && result.getFieldError() != null) {
            try{
                throw new Exception(result.getFieldError().getDefaultMessage());
            } catch (Exception e) {
                throw new Exception("binding result error");
            }
        }

        return itemManager.save(userId,itemTodoDTO);

    }

    @PutMapping("/update/{id}")
    public ResponseBaseModel<ResponseEntity<ItemResponseModel>> update(@PathVariable("id") String itemId,
                                                                       @Valid @RequestBody ItemTodoDTO itemTodoDTO, BindingResult result) throws Exception {

        if (result != null && result.hasErrors() && result.getFieldError() != null) {
            try{
                throw new Exception(result.getFieldError().getDefaultMessage());
            } catch (Exception e) {
                throw new Exception("binding result error");
            }
        }

        return itemManager.update(itemId, itemTodoDTO);
    }
    @PutMapping("/passive-delete/{id}")
    public ResponseBaseModel<ResponseEntity<String>> passiveDelete(@PathVariable("id") String itemId,
                                                            @Valid @RequestBody ItemTodoDTO itemTodoDTO, BindingResult result) throws Exception {


        if (result != null && result.hasErrors() && result.getFieldError() != null) {
            try{
                throw new Exception(result.getFieldError().getDefaultMessage());
            } catch (Exception e) {
                throw new Exception("binding result error");
            }
        }

        return itemManager.passiveDelete(itemId);
    }
    @GetMapping("/getAll")
    public ResponseBaseModel<ResponseEntity<List<Item>>> getAll() {return itemManager.getAll();}

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") String itemId, BindingResult result) throws Exception {


        if (result != null && result.hasErrors() && result.getFieldError() != null) {
            try{
                throw new Exception(result.getFieldError().getDefaultMessage());
            } catch (Exception e) {
                throw new Exception("binding result error");
            }
        }

        return itemManager.delete(itemId);
    }
}
