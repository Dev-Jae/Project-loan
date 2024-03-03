package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.domain.Counsel;
import com.fastcampus.projectloan.dto.CounselDTO.Request;
import com.fastcampus.projectloan.dto.CounselDTO.Response;
import com.fastcampus.projectloan.repository.CounselRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CounselServiceTest {

    @InjectMocks
    CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void 상담요청_왔을떄_새로운_상담에_대한_응답_리턴(){
        Counsel entity = Counsel.builder()
                .name("Todd")
                .cellPhone("010-0000-1234")
                .email("Todd@study.com")
                .memo("대출 받고 싶어요. 연락 부탁드립니다.")
                .zipCode("12345")
                .address("서울특별시 관악구 모든동")
                .addressDetail("1001호")
                .build();

        Request request = Request.builder()
                .name("Todd")
                .cellPhone("010-0000-1234")
                .email("Todd@study.com")
                .memo("대출 받고 싶어요. 연락 부탁드립니다.")
                .zipCode("12345")
                .address("서울특별시 관악구 모든동")
                .addressDetail("1001호")
                .build();
        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);

        Response actual = counselService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());

    }
}
