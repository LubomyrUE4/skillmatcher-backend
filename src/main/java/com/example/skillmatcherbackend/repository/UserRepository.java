package com.example.skillmatcherbackend.repository;

import com.example.skillmatcherbackend.model.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDocument, String> {
    UserDocument findByEmail(String email);
}