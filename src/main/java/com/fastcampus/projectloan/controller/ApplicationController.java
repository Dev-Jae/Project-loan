package com.fastcampus.projectloan.controller;

import com.fastcampus.projectloan.dto.ApplicationDTO.Request;
import com.fastcampus.projectloan.dto.ApplicationDTO.Response;
import com.fastcampus.projectloan.dto.ResponseDTO;
import com.fastcampus.projectloan.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/applications")
public class ApplicationController extends AbstractController{

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request){
        return ok(applicationService.create(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseDTO<Response> get(@PathVariable Long applicationId){
        return ok(applicationService.get(applicationId));
    }
}
