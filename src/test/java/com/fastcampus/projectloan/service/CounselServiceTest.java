package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.domain.Counsel;
import com.fastcampus.projectloan.dto.CounselDTO.Request;
import com.fastcampus.projectloan.dto.CounselDTO.Response;
import com.fastcampus.projectloan.exception.BaseException;
import com.fastcampus.projectloan.exception.ResultType;
import com.fastcampus.projectloan.repository.CounselRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

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

    @Test
    void 상담아이디_존재하는_경우_상담_리턴(){
        Long findId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .build();

        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.get(findId);

        assertThat(actual.getCounselId()).isSameAs(findId);
    }

    @Test
    void 상담아이디_존재하지_않는_경우_리턴(){
        Long findId = 2L;

        when(counselRepository.findById(findId)).thenThrow(new BaseException(ResultType.SYSTEM_ERROR));

        Assertions.assertThrows(BaseException.class, () -> counselService.get(findId));
    }

    @Test
    void 상담_존재하면_입력값으로_저장_리턴(){
        Long findId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .name("Todd")
                .cellPhone("010-0000-1234")
                .email("Todd@study.com")
                .memo("대출 받고 싶어요. 연락 부탁드립니다.")
                .zipCode("12345")
                .address("서울특별시 관악구 모든동")
                .addressDetail("1001호")
                .build();

        Request request = Request.builder()
                .name("New Todd")
                .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);
        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.update(findId, request);

        assertThat(actual.getCounselId()).isSameAs(findId);
        assertThat(actual.getName()).isSameAs(request.getName());
    }
}
