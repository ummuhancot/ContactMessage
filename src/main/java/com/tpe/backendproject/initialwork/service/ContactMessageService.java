package com.tpe.backendproject.initialwork.service;

import com.tpe.backendproject.initialwork.payload.mapper.ContactMapper;
import com.tpe.backendproject.initialwork.payload.request.ContactRequest;
import com.tpe.backendproject.initialwork.payload.response.ContactResponse;
import com.tpe.backendproject.initialwork.payload.response.ResponseMessage;
import com.tpe.backendproject.initialwork.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;


@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactMapper contactMapper;


    public ResponseMessage<ContactResponse> saveMessages(@Valid ContactRequest contactRequest){

    }
}
