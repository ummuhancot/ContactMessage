package com.tpe.backendproject.initialwork.service;

import com.tpe.backendproject.initialwork.entity.ContactMessage;
import com.tpe.backendproject.initialwork.exception.ResourceNotFoundException;
import com.tpe.backendproject.initialwork.payload.mapper.ContactMapper;
import com.tpe.backendproject.initialwork.payload.messages.ErorMessages;
import com.tpe.backendproject.initialwork.payload.messages.SuccessMassages;
import com.tpe.backendproject.initialwork.payload.request.ContactRequest;
import com.tpe.backendproject.initialwork.payload.response.ContactResponse;
import com.tpe.backendproject.initialwork.payload.response.ResponseMessage;
import com.tpe.backendproject.initialwork.repository.ContactMessageRepository;
import com.tpe.backendproject.initialwork.service.help.MethodHelper;
import com.tpe.backendproject.initialwork.service.help.PegableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactMapper contactMapper;
    private final PegableHelper pageableHelper;
    private final MethodHelper methodHelper;


    public ResponseMessage<ContactResponse> saveMessages(@Valid ContactRequest contactRequest){

        ContactMessage saveMessage = contactMapper.mapToEntitiy(contactRequest);

        contactMessageRepository.save(saveMessage);

        return ResponseMessage.<ContactResponse>builder()
                .message(SuccessMassages.MESSAGE_CREATE)
                .responseBody(contactMapper.mapToRespose(saveMessage))
                .httpStatus(HttpStatus.CREATED)
                .build();

    }


    public ResponseMessage<List<ContactResponse>> getAllMessages() {

        List<ContactMessage> allMessages = contactMessageRepository.findAll();

        if (allMessages.isEmpty()){
            throw new ResourceNotFoundException(ErorMessages.NOT_FOUND_MESSAGE);
        }
        return ResponseMessage.<List<ContactResponse>>builder()
                .message(SuccessMassages.ALL_MESSAGES_FETCHED)
                .responseBody(contactMapper.mapToResponseList(allMessages))
                .build();

    }


    public Page<ContactResponse> getMessagesByPage(int page, int size, String sort, String type) {

        if (contactMessageRepository.findAll(pageableHelper.getPageable(page, size, sort, type))
                .isEmpty()) {
            throw new ResourceNotFoundException(ErorMessages.NOT_FOUND_MESSAGE);
        }
        return contactMessageRepository.findAll(pageableHelper.getPageable(page, size, sort, type))
                .map(contactMapper::mapToRespose);
    }

    public ResponseMessage<List<ContactResponse>> getMessageByEmail(
            String email) {
        List<ContactMessage> messagesByEmail = contactMessageRepository.findByEmail(email);
        if (messagesByEmail.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ErorMessages.NOT_FOUND_MESSAGE_BY_EMAIL, email));
        }
        return ResponseMessage.<List<ContactResponse>>builder()
                .message(String.format(SuccessMassages.MESSAGES_FETCHED_BY_EMAIL, email))
                .responseBody(contactMapper.mapToResponseList(messagesByEmail))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public ResponseMessage<List<ContactResponse>> getMessagesByCreationDateBetween(
            String startDate,
            String endDate) {
        LocalDateTime startDateTime = LocalDate.parse(startDate)
                .atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(endDate)
                .atStartOfDay();

        List<ContactMessage> messagesByCreationDateBetween = contactMessageRepository.findByCreatedAtBetween(startDateTime, endDateTime);
        if (messagesByCreationDateBetween.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ErorMessages.NOT_FOUND_MESSAGE_BETWEEN_DATES, startDate, endDate));
        }
        return ResponseMessage.<List<ContactResponse>>builder()
                .message(String.format(SuccessMassages.MESSAGE_FOUND_BETWEEN_DATES, startDate, endDate))
                .responseBody(contactMapper.mapToResponseList(messagesByCreationDateBetween))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public ResponseMessage<List<ContactResponse>> getMessagesByCreationTimeBetween(
            String startTime,
            String endTime) {

        List<ContactMessage> messagesByCreationTimeBetween = contactMessageRepository.findByCreatedAtTimeBetween(startTime, endTime);

        if (messagesByCreationTimeBetween.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ErorMessages.NOT_FOUND_MESSAGE_BETWEEN_TIMES, startTime, endTime));
        }
        return ResponseMessage.<List<ContactResponse>>builder()
                .message(String.format(SuccessMassages.MESSAGE_FOUND_BETWEEN_HOURS, startTime, endTime))
                .responseBody(contactMapper.mapToResponseList(messagesByCreationTimeBetween))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public String deleteMessageById(
            Long messageId) {
        methodHelper.checkMessageExistById(messageId);
        contactMessageRepository.deleteById(messageId);
        return String.format(SuccessMassages.MESSAGE_DELETE, messageId);
    }

    public ResponseMessage<ContactResponse> updateMessageById(
            @Valid ContactRequest contactRequest,
            Long messageId) {
        ContactMessage messageFromDb = methodHelper.checkMessageExistById(messageId);
        ContactMessage updatedMessage = contactMapper.mapToEntitiy(contactRequest);
        if (!messageFromDb.getName()
                .equals(updatedMessage.getName())) {
            messageFromDb.setName(updatedMessage.getName());
        }
        if (!messageFromDb.getEmail()
                .equals(updatedMessage.getEmail())) {
            messageFromDb.setEmail(updatedMessage.getEmail());
        }

        if (!messageFromDb.getSubject()
                .equals(updatedMessage.getSubject())) {
            messageFromDb.setSubject(updatedMessage.getSubject());
        }

        if (!messageFromDb.getMessage()
                .equals(updatedMessage.getMessage())) {
            messageFromDb.setMessage(updatedMessage.getMessage());
        }
        contactMessageRepository.save(messageFromDb);
        return ResponseMessage.<ContactResponse>builder()
                .message(SuccessMassages.MESSAGE_UPDATE)
                .responseBody(contactMapper.mapToRespose(messageFromDb))
                .httpStatus(HttpStatus.OK)
                .build();

    }

    public ResponseMessage<List<ContactResponse>> searchMessageBySubject(
            String subject) {
        List<ContactMessage> messagesBySubject = methodHelper.checkMessageExistBySubject(subject);
        return ResponseMessage.<List<ContactResponse>>builder()
                .message(String.format(SuccessMassages.MESSAGE_FOUND_BY_SUBJECT, subject))
                .responseBody(contactMapper.mapToResponseList(messagesBySubject))
                .httpStatus(HttpStatus.OK)
                .build();
    }






}
