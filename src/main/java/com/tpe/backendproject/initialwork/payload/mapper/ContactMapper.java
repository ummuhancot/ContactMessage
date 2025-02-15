package com.tpe.backendproject.initialwork.payload.mapper;

import com.tpe.backendproject.initialwork.entity.ContactMessage;
import com.tpe.backendproject.initialwork.payload.request.ContactRequest;
import com.tpe.backendproject.initialwork.payload.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactMapper {


    public ContactResponse mapToRespose(ContactMessage contactMessage) {
        return ContactResponse.builder()
                .name(contactMessage.getName())
                .email(contactMessage.getEmail())
                .subject(contactMessage.getSubject())
                .message(contactMessage.getMessage())
                .createdAt(contactMessage.getCreatedAt())
                .build();
    }


    public ContactMessage mapToEntitiy(ContactRequest contactRequest){
        return ContactMessage.builder()
                .name(contactRequest.getName())
                .email(contactRequest.getEmail())
                .subject(contactRequest.getSubject())
                .message(contactRequest.getMessage())
                .build();

    }

    public List<ContactResponse> mapToResponseList(List<ContactMessage> allMessages){

        return allMessages.stream()
                .map(this::mapToRespose)
                .toList();
    }










}
