package com.hepsi.simpletodocase.repository;

import com.hepsi.simpletodocase.model.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CouchbaseRepository<User, Long> {
    User findById(String id);
    User findByEmailAddress(String emailAddress);
    Boolean existsByEmailAddress(String emailAddress);
}
