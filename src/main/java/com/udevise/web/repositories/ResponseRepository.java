package com.udevise.web.repositories;

import com.udevise.web.domain.Response;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseRepository extends MongoRepository<Response,String> {
  Optional<List<Response>> findAllByQuestionnaireId(String id);
}
