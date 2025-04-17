package com.meetime.hubspot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String firstName;
    
    @NotBlank(message = "O sobrenome é obrigatório")
    private String lastName;
    
    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    private String email;
    
    private String phone;
    private String company;
    private String website;
} 