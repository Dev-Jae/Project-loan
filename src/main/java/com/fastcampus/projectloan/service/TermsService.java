package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.dto.TermsDTO.Request;
import com.fastcampus.projectloan.dto.TermsDTO.Response;

public interface TermsService {

    Response create(Request request);
}
