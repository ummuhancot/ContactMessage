package com.tpe.backendproject.initialwork.service.help;

import com.tpe.backendproject.initialwork.entity.ContactMessage;
import com.tpe.backendproject.initialwork.exception.ResourceNotFoundException;
import com.tpe.backendproject.initialwork.payload.messages.ErorMessages;
import com.tpe.backendproject.initialwork.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.image.RasterFormatException;
import java.util.List;
import java.util.logging.ErrorManager;

@Component
@RequiredArgsConstructor
public class MethodHelper {

    private final ContactMessageRepository contactMessageRepository;


    public ContactMessage checkMessageExistById(
            Long id) {
        return contactMessageRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(ErorMessages.NOT_FOUND_MESSAGE, id)));
    }

    public List<ContactMessage> checkMessageExistBySubject(
            String subject) {
        List<ContactMessage> contactMessages = contactMessageRepository.findBySubjectContainsIgnoreCase(subject);
        if (contactMessages.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ErorMessages.NOT_FOUND_MESSAGE_BY_SUBJECT, subject));
        }
        return contactMessages;
    }



}
