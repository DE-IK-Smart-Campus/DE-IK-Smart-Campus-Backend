package hu.unideb.smartcampus.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import hu.unideb.smartcampus.persistence.mongo.entity.UserLocationEntity;

@Repository
public interface UserLocationRepository extends MongoRepository<UserLocationEntity, String>{

}
