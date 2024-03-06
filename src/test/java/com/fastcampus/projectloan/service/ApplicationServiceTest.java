package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.domain.Application;
import com.fastcampus.projectloan.dto.ApplicationDTO.Request;
import com.fastcampus.projectloan.dto.ApplicationDTO.Response;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

    @Test
    void 어플리케이션_아이디가_존재하면_정보_리턴(){
        Long findId = 1L;

        Application entity = Application.builder()
                        .applicationId(1L)
                        .build();

        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.get(findId);

        assertThat(actual.getApplicationId()).isSameAs(findId);
    }

    @Test
    void 어플리케이션_아이디가_존재할_경우_업데이트(){
        Long findId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        Request request = Request.builder()
                .hopeAmount(BigDecimal.valueOf(5000000))
                .build();

        when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(entity);
        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.update(findId, request);

        assertThat(actual.getApplicationId()).isSameAs(findId);
        assertThat(actual.getHopeAmount()).isSameAs(request.getHopeAmount());
    }

    @Test
    void 어플리케이션_아이디가_존재할_경우_소프트_딜리트(){
        Long targetId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .build();

        when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(entity);
        when(applicationRepository.findById(targetId)).thenReturn(Optional.ofNullable(entity));

        applicationService.delete(targetId);

        assertThat(entity.getIsDeleted()).isSameAs(true);

    }
}
