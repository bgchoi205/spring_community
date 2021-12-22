package com.cbg.exam.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER");

    private String value;

}
