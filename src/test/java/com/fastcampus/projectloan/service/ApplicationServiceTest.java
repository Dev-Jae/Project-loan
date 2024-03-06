package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.domain.Application;
import com.fastcampus.projectloan.dto.ApplicationDTO;
import com.fastcampus.projectloan.dto.ApplicationDTO.Request;
import com.fastcampus.projectloan.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

import static com.fastcampus.projectloan.dto.ApplicationDTO.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void 새로운_대출신청_요청오면_신청된값_리턴(){
        Application entity = Application.builder()
                .name("Todd")
                .cellPhone("010-1111-1111")
                .email("todd@loan.com")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        Request request = Request.builder()
                .name("Todd")
                .cellPhone("010-1111-1111")
                .email("todd@loan.com")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(entity);

        Response actual = applicationService.create(request);

        assertThat(actual.getHopeAmount()).isSameAs(entity.getHopeAmount());
        assertThat(actual.getName()).isSameAs(entity.getName());

    }
}
