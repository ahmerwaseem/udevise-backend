package com.udevise.web.services;

import com.udevise.web.domain.Response;
import org.springframework.stereotype.Service;

@Service
public interface ResponseService {
  Response save(Response response);
}
