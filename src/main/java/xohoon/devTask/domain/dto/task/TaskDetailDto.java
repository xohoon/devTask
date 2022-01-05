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
public class TaskDetailDto {
    private Long id;

    private String task_part; // 전문분야
    private String task_part_personnel; // 분야 모집 인원
    private String tasking_day; // 진행 기간
    private String task_need_skill; // 과제진행 필요한 기술

    // common
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long lastModifiedMemberId;
}
