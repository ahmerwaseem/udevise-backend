package com.udevise.web.services;

import com.udevise.web.domain.Response;
import com.udevise.web.repositories.ResponseRepository;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService {

  private ResponseRepository responseRepository;

  public ResponseServiceImpl(ResponseRepository responseRepository) {
    this.responseRepository = responseRepository;
  }

  @Override
  public Response save(Response response) {
    //validate if questionnaire id is same, validate question ids, validate answersallowed,
    //validate required, validate if userid required, add userid
    return responseRepository.save(response);
  }
}
