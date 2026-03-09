package com.khandekar.journalApp.repository;

import com.khandekar.journalApp.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findUserByUsername(String username);
}
