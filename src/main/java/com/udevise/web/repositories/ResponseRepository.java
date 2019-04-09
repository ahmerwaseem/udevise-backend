package com.udevise.web.repositories;

import com.udevise.web.domain.requests.ResponseRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseRepository extends MongoRepository<ResponseRequest,String> {
  Optional<List<ResponseRequest>> findAllByQuestionnaireId(String id);
}
