package xohoon.devTask.domain.dto.task;

import lombok.*;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.task.TaskDetail;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskSupportDto {
    private Long id;
    private Long member_id;
    private Long td_id;
}
