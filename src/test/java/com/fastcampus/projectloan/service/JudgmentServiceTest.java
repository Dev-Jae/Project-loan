package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.domain.Application;
import com.fastcampus.projectloan.domain.Judgment;
import com.fastcampus.projectloan.dto.ApplicationDTO;
import com.fastcampus.projectloan.dto.ApplicationDTO.GrantAmount;
import com.fastcampus.projectloan.dto.JudgmentDTO.Request;
import com.fastcampus.projectloan.dto.JudgmentDTO.Response;
import com.fastcampus.projectloan.repository.ApplicationRepository;
import com.fastcampus.projectloan.repository.JudgmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JudgmentServiceTest {

    @InjectMocks
    private JudgmentServiceImpl judgmentService;

    @Mock
    private JudgmentRepository judgmentRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void 심사요청_했을때_리턴(){
        Judgment judgment = Judgment.builder()
                .applicationId(1L)
                .name("Todd")
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        Request request = Request.builder()
                .applicationId(1L)
                .name("Todd")
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();


        // application find
        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(Application.builder().build()));
        // judgment save
        when(judgmentRepository.save(ArgumentMatchers.any(Judgment.class))).thenReturn(judgment);

        Response actual = judgmentService.create(request);

        assertThat(actual.getName()).isSameAs(judgment.getName());
        assertThat(actual.getApplicationId()).isSameAs(judgment.getApplicationId());
        assertThat(actual.getApprovalAmount()).isSameAs(judgment.getApprovalAmount());
    }

    @Test
    void 심사_아이디를_통헤_조회후_리턴(){
        Judgment entity = Judgment.builder()
                .judgmentId(1L)
                .build();

        when(judgmentRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));

        Response actual = judgmentService.get(1L);

        assertThat(actual.getJudgmentId()).isSameAs(1L);
    }

    @Test
    void 대출신청아이디로_심사_조회후_리턴(){
        Judgment judgmentEntity = Judgment.builder()
                .judgmentId(1L)
                .build();

        Application applicationEntity = Application.builder()
                .applicationId(1L)
                .build();

        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(applicationEntity));
        when(judgmentRepository.findByApplicationId(1L)).thenReturn(Optional.ofNullable(judgmentEntity));

        Response actual = judgmentService.getJudgmentOfApplication(1L);

        assertThat(actual.getJudgmentId()).isSameAs(1L);

    }

    @Test
    void 요청값으로_심사내용_수정후_리턴(){
        Judgment entity = Judgment.builder()
                .judgmentId(1L)
                .name("김아무개")
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        Request request = Request.builder()
                .name("추아무개")
                .approvalAmount(BigDecimal.valueOf(10000000))
                .build();

        when(judgmentRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(judgmentRepository.save(ArgumentMatchers.any(Judgment.class))).thenReturn(entity);

        Response actual = judgmentService.update(1L, request);

        assertThat(actual.getJudgmentId()).isSameAs(1L);
        assertThat(actual.getName()).isSameAs(request.getName());
        assertThat(actual.getApprovalAmount()).isSameAs(request.getApprovalAmount());
    }

    @Test
    void 심사아이디로_소프트딜리트(){
        Judgment entity = Judgment.builder()
                .judgmentId(1L)
                .build();

        when(judgmentRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(judgmentRepository.save(ArgumentMatchers.any(Judgment.class))).thenReturn(entity);

        judgmentService.delete(1L);

        assertThat(entity.getIsDeleted()).isTrue();
    }

    @Test
    void 대출신청심사_금액부여_기능(){
        Judgment judgmentEntity = Judgment.builder()
                .name("김아무개")
                .applicationId(1L)
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        Application applicationEntity = Application.builder()
                .applicationId(1L)
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();


        when(judgmentRepository.findById(1L)).thenReturn(Optional.ofNullable(judgmentEntity));
        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(applicationEntity));
        when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(applicationEntity);

        GrantAmount actual = judgmentService.grant(1L);

        assertThat(actual.getApplicationId()).isSameAs(1L);
        assertThat(actual.getApprovalAmount()).isSameAs(judgmentEntity.getApprovalAmount());
    }
}