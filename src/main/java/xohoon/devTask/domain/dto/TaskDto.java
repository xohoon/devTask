package xohoon.devTask.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String subject;
    private int task_day;
    private String detail_content;
    // common
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long lastModifiedMemberId;
}
