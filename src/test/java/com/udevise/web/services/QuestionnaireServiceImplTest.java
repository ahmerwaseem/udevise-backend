package com.udevise.web.services;

import com.udevise.web.domain.Question;
import com.udevise.web.domain.Questionnaire;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class QuestionnaireServiceImplTest {

  @Autowired
  QuestionnaireServiceImpl service;

  @Test
  @Ignore
  public void saveTest(){
    Questionnaire questionnaire = new Questionnaire();
    questionnaire.setDescription("test");
    List<Question> questionList = new ArrayList<>();
    service.saveQuestionnaire(questionnaire);
  }

}