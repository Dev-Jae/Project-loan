package com.fastcampus.projectloan.controller;

import com.fastcampus.projectloan.dto.CounselDTO;
import com.fastcampus.projectloan.dto.CounselDTO.Request;
import com.fastcampus.projectloan.dto.CounselDTO.Response;
import com.fastcampus.projectloan.dto.ResponseDTO;
import com.fastcampus.projectloan.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/counsels")
public class CounselController extends AbstractController{

    private final CounselService counselService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(counselService.create(request));

    }
}
