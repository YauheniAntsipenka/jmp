package com.epam.jmp.jmpm7springboot.domain;

import lombok.Data;

/**
 * RegistrationRequest
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
 @Data
 public class RegistrationRequest {
     private String login;
     private String password;
 }
