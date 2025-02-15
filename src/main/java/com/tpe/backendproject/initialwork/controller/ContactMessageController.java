package com.tpe.backendproject.initialwork.controller;

import com.tpe.backendproject.initialwork.payload.request.ContactRequest;
import com.tpe.backendproject.initialwork.payload.response.ContactResponse;
import com.tpe.backendproject.initialwork.payload.response.ResponseMessage;
import com.tpe.backendproject.initialwork.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contactMessage")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping("/save")
    public ResponseEntity<ResponseMessage<ContactResponse>> saveMessage(@RequestBody @Valid ContactRequest contactRequest){
        return ResponseEntity.ok(contactMessageService.saveMessages(contactRequest));

    }










}
