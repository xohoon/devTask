package xohoon.devTask.domain.dto.task;

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
    private String task_subject;
    private String task_dead_day; // 마감 날짜
    private int tasking_status; // 마감 형태

    // common
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long lastModifiedMemberId;
}
