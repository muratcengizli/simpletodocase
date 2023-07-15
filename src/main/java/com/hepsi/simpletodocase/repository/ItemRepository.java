package com.hepsi.simpletodocase.repository;

import com.hepsi.simpletodocase.model.Item;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CouchbaseRepository<Item, Long> {

    Item findById(String itemId);
}
