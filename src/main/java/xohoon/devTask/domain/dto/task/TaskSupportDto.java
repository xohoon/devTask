package xohoon.devTask.domain.dto.task;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.task.TaskDetail;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskSupportDto {
    private Long id;
    private int support_status; // 요청 상태
    private int success_status; // 완료 상태

    private Long member_id;
    private Long td_id;
}
