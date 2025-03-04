package com.ms.email.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDTO {

    @NotBlank
    private String ownerRef;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
}
