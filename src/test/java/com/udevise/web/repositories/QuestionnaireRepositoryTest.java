package com.udevise.web.repositories;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class QuestionnaireRepositoryTest {

  @Autowired
  QuestionnaireRepository repository;
  @Autowired
  QuestionRepository questionRepository;


  @Test
  @Ignore
  public void test(){
//    Questionnaire questionnaire = new Questionnaire();
//    questionnaire.setDescription("test");
//    Question question = new Question();
//    question.setType(QuestionType.MULTIPLE_CHOICE);
//    List<String> list = new ArrayList<>();
//    list.add("option1");
//    question.setQuestions("dbref test");
//    question.setAnswersAllowed(list);
//    question = questionRepository.save(question);
//    List<Question> questionList = new ArrayList<>();
//    questionList.add(question);
//    questionnaire.setQuestions(questionList);
//    Questionnaire q1 = repository.save(questionnaire);
    repository.findById("5c5e58d8d797290a149e3c62");
    System.out.println();
  }

}