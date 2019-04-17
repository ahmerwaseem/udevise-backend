package com.udevise.web.repositories;

import com.udevise.web.domain.model.Questionnaire;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionnaireRepository extends PagingAndSortingRepository<Questionnaire,String> {
  Optional<Questionnaire> findByIdAndCreatorId(String id, String creatorId);
  Optional<List<Questionnaire>> findByCreatorId(String id);
  Optional<Questionnaire> getQuestionnairesById(String id);
  Questionnaire save(Questionnaire questionnaire);
}
