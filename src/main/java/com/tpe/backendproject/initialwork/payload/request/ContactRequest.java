package com.tpe.backendproject.initialwork.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ContactRequest {


    @NotNull(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Subject must not be empty")
    private String subject;

    @NotNull(message = "Message must not be empty")
    private String message;




}
