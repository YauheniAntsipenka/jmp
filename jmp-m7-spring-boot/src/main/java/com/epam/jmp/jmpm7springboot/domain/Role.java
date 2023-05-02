package com.epam.jmp.jmpm7springboot.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Role
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
@Entity
@Data
@Table(name = "role_table", schema = "public")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer id;

    @Column
    private String name;
}
