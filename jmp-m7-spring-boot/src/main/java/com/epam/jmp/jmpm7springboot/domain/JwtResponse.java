package com.epam.jmp.jmpm7springboot.domain;

import java.io.Serializable;

/**
 * JwtResponse
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
