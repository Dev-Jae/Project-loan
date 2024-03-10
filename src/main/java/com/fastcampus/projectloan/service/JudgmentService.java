package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.dto.ApplicationDTO;
import com.fastcampus.projectloan.dto.ApplicationDTO.GrantAmount;
import com.fastcampus.projectloan.dto.JudgmentDTO.Request;
import com.fastcampus.projectloan.dto.JudgmentDTO.Response;
import com.fastcampus.projectloan.dto.ResponseDTO;

public interface JudgmentService {

    Response create(Request request);

    Response get(Long judgmentId);

    Response getJudgmentOfApplication(Long applicationId);

    Response update(Long judgmentId, Request request);

    void delete(Long judgmentId);

    GrantAmount grant(Long judgmentId);
}
