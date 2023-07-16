package com.hepsi.simpletodocase.repository;

import com.hepsi.simpletodocase.model.User;
import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CouchbaseRepository<User, String> {
    List<User> findByIsDeleted(Boolean status);

}
