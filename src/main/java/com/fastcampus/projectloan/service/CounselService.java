package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.dto.CounselDTO.Request;
import com.fastcampus.projectloan.dto.CounselDTO.Response;

public interface CounselService {

    Response create(Request request);
}
