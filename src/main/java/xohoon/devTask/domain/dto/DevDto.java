package xohoon.devTask.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevDto {
    private Long id;

    private String title;
    private String useSkill;
    private String url_1;
    private String url_2;
    private String url_3;
}
