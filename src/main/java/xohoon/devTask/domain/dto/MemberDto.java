package xohoon.devTask.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private int age;
    private String role;
}
