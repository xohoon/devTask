package xohoon.devTask.domain.dto.task;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskDto {
    private Long id;
    private String task_title;
    private String task_dead_day; // 마감 날짜
    private int task_status; // 마감 형태
}
