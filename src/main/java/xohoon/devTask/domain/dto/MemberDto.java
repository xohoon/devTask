package xohoon.devTask.domain.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private int age;
    private String role;
}
