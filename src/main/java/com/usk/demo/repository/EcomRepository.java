package com.usk.demo.repository;

import com.usk.demo.entity.Delivary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcomRepository extends MongoRepository<Delivary,String>{


}
