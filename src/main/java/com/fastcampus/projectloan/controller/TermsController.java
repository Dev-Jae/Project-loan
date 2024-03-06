package com.fastcampus.projectloan.controller;

import com.fastcampus.projectloan.dto.ResponseDTO;
import com.fastcampus.projectloan.dto.TermsDTO.Request;
import com.fastcampus.projectloan.dto.TermsDTO.Response;
import com.fastcampus.projectloan.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/terms")
public class TermsController extends AbstractController {

    private final TermsService termsService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request){
        return ok(termsService.create(request));
    }
}
