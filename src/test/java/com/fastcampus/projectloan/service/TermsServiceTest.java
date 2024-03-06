package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.domain.Terms;
import com.fastcampus.projectloan.dto.TermsDTO.Request;
import com.fastcampus.projectloan.dto.TermsDTO.Response;
import com.fastcampus.projectloan.repository.TermsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

class TermsServiceTest {
    @InjectMocks
    TermsServiceImpl termsService;

    @Mock
    private TermsRepository termsRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void 새로운_약관_정보_요청했을때_저장_후_리턴(){
        Terms entity = Terms.builder()
                .name("대출 이용 약관")
                .termsDetailUrl("https://abc_storage.acc/test")
                .build();

        Request request = Request.builder()
                .name("대출 이용 약관")
                .termsDetailUrl("https://abc_storage.acc/test")
                .build();

        when(termsRepository.save(ArgumentMatchers.any(Terms.class))).thenReturn(entity);

        Response actual = termsService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
        assertThat(actual.getTermsDetailUrl()).isSameAs(entity.getTermsDetailUrl());


    }

    @Test
    void 모든_약관을_리턴(){
        Terms entity1 = Terms.builder()
                .name("대출 이용 약관1")
                .termsDetailUrl("https://test_url1.com/test1")
                .build();
        Terms entity2 = Terms.builder()
                .name("대출 이용 약관2")
                .termsDetailUrl("https://test_url2.com/test2")
                .build();

        List<Terms> list = new ArrayList<>(Arrays.asList(entity1, entity2));

        when(termsRepository.findAll()).thenReturn(list);

        List<Response> actual = termsService.getAll();

        assertThat(actual.size()).isSameAs(list.size());
    }

}
