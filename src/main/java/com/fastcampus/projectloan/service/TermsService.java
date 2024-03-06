package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.dto.TermsDTO.Request;
import com.fastcampus.projectloan.dto.TermsDTO.Response;

import java.util.List;

public interface TermsService {

    Response create(Request request);

    List<Response>  getAll();
}
