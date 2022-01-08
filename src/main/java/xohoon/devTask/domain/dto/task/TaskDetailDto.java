package xohoon.devTask.domain.dto.task;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskDetailDto {
    private Long id;

    private String task_subject; // main title
    private String task_part; // 전문분야
    private String task_part_personnel; // 분야 모집 인원
    private String tasking_day; // 진행 기간
    private String task_need_skill; // 과제진행 필요한 기술
    private String task_btn_id; // 버튼 id
}
