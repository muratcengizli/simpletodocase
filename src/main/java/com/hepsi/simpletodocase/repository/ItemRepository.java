package com.hepsi.simpletodocase.repository;

import com.hepsi.simpletodocase.model.Item;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ItemRepository extends CouchbaseRepository<Item, String> {

    List<Item> findByIsDeleted(Boolean status);
    List<Item> findItemsByUserIdAndIsDeleted(String user_id, Boolean status);


}
