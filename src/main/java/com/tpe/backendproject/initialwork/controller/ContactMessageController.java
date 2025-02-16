package com.tpe.backendproject.initialwork.controller;

import com.tpe.backendproject.initialwork.payload.request.ContactRequest;
import com.tpe.backendproject.initialwork.payload.response.ContactResponse;
import com.tpe.backendproject.initialwork.payload.response.ResponseMessage;
import com.tpe.backendproject.initialwork.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contactMessage")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping("/save")
    public ResponseEntity<ResponseMessage<ContactResponse>> saveMessage(@RequestBody @Valid ContactRequest contactRequest){
        return ResponseEntity.ok(contactMessageService.saveMessages(contactRequest));

    }


    @GetMapping("/getAllMessages")
    public ResponseEntity<ResponseMessage<List<ContactResponse>>> saveMessaage(){
        return ResponseEntity.ok(contactMessageService.getAllMessages());
    }

    @GetMapping("/getMessagesByPage")
    public ResponseEntity<Page<ContactResponse>> getMessagesByPage(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort",defaultValue = "createdAt") String sort,
            @RequestParam(value = "type",defaultValue = "desc") String type){
        return ResponseEntity.ok(contactMessageService.getMessagesByPage(page,size,sort,type));
    }

    @GetMapping("/searchMessageBySubject")
    public ResponseEntity<ResponseMessage<List<ContactResponse>>> searchMessageBySubject(
            @RequestParam(value = "subject") String subject) {
        return ResponseEntity.ok(contactMessageService.searchMessageBySubject(subject));
    }

    @GetMapping("/getMessageByEmail")
    public ResponseEntity<ResponseMessage<List<ContactResponse>>> getMessageByEmail(
            @RequestParam(value = "email") String email) {
        return ResponseEntity.ok(contactMessageService.getMessageByEmail(email));
    }


    @GetMapping("/getMessagesByCreationDateBetween")
    public ResponseEntity<ResponseMessage<List<ContactResponse>>> getMessagesByCreationDateBetween(
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate") String endDate) {
        return ResponseEntity.ok(contactMessageService.getMessagesByCreationDateBetween(startDate, endDate));
    }

    @GetMapping("getMessagesByCreationHourBetween")
    public ResponseEntity<ResponseMessage<List<ContactResponse>>> getMessagesByCreationHourBetween(
            @RequestParam(value = "startHour") String startHour,
            @RequestParam(value = "endHour") String endHour) {
        return ResponseEntity.ok(contactMessageService.getMessagesByCreationTimeBetween(startHour, endHour));
    }

    @DeleteMapping("/deleteMessageById/{messageId}")
    public ResponseEntity<String> deleteMessageById(
            @PathVariable Long messageId) {
        return ResponseEntity.ok(contactMessageService.deleteMessageById(messageId));
    }

    @DeleteMapping("/deleteMessageById")
    public ResponseEntity<String> deleteMessageByIdPath(
            @RequestParam Long messageId) {
        return ResponseEntity.ok(contactMessageService.deleteMessageById(messageId));
    }

    @PutMapping("/updateMessageById/{messageId}")
    public ResponseEntity<ResponseMessage<ContactResponse>> updateMessageById(
            @RequestBody @Valid ContactRequest contactRequest,
            @PathVariable Long messageId) {
        return ResponseEntity.ok(contactMessageService.updateMessageById(contactRequest, messageId));
    }





}
