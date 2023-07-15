package com.hepsi.simpletodocase.dto.response;

import com.hepsi.simpletodocase.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseModel {

    private Item item;

}
