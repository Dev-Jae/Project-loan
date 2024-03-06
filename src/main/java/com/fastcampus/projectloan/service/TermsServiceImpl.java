package com.fastcampus.projectloan.service;

import com.fastcampus.projectloan.domain.Terms;
import com.fastcampus.projectloan.dto.TermsDTO.Request;
import com.fastcampus.projectloan.dto.TermsDTO.Response;
import com.fastcampus.projectloan.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService{

    private final TermsRepository termsRepository;

    private final ModelMapper modelMapper;

    @Override
    public Response create(Request request) {

        Terms terms = modelMapper.map(request, Terms.class);
        Terms created = termsRepository.save(terms);

        return modelMapper.map(created, Response.class);
    }
}
