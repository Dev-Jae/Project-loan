package com.fastcampus.projectloan.controller;

import com.fastcampus.projectloan.dto.ApplicationDTO;
import com.fastcampus.projectloan.dto.JudgmentDTO;
import com.fastcampus.projectloan.dto.JudgmentDTO.Request;
import com.fastcampus.projectloan.dto.JudgmentDTO.Response;
import com.fastcampus.projectloan.dto.ResponseDTO;
import com.fastcampus.projectloan.service.JudgmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.fastcampus.projectloan.dto.ApplicationDTO.*;
import static com.fastcampus.projectloan.dto.ResponseDTO.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/judgments")
public class JudgmentController {

    private final JudgmentService judgmentService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request){
        return ok(judgmentService.create(request));
    }

    @GetMapping("/{judgmentId}")
    public ResponseDTO<Response> get(@PathVariable Long judgmentId){
        return ok(judgmentService.get(judgmentId));
    }

    @GetMapping("/applications/{applicationId}")
    public ResponseDTO<Response> getJudgmentOfApplication(@PathVariable Long applicationId){
        return ok(judgmentService.getJudgmentOfApplication(applicationId));
    }

    @PutMapping("/{judgmentId}")
    public ResponseDTO<Response> update(@PathVariable Long judgmentId, @RequestBody Request request){
        return ok(judgmentService.update(judgmentId, request));
    }

    @DeleteMapping("/{judgmentId}")
    public ResponseDTO<Void> delete(@PathVariable Long judgmentId){
        judgmentService.delete(judgmentId);
        return ok();
    }

    @PatchMapping("/{judgmentId}/grants")
    public ResponseDTO<GrantAmount> grant(@PathVariable Long judgmentId){
        return ok(judgmentService.grant(judgmentId));
    }
}
