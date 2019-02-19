package com.udevise.web.repositories;

import com.udevise.web.domain.Answer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends PagingAndSortingRepository<Answer,String> {
  Optional<List<Answer>> findAllByQuestionId(String id);
  Optional<List<Answer>> getAnswersByQuestionnaireId(String id);
}
