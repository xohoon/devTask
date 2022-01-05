package xohoon.devTask.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.task.TaskDetail;

public interface TaskDetailRepository extends JpaRepository<TaskDetail, Long> {
}
