package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.dto.ApplicationDTO.Request;
import com.fastcampus.projectloan.dto.ApplicationDTO.Response;

public interface ApplicationService {

    Response create(Request request);

    Response get(Long applicationId);

    Response update(Long applicationId, Request request);

    void delete(Long applicationId);
}
