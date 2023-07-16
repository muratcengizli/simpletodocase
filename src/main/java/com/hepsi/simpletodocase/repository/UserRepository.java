package com.hepsi.simpletodocase.repository;

import com.hepsi.simpletodocase.model.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends CouchbaseRepository<User, String> {
    List<User> findByIsDeleted(Boolean status);

}
