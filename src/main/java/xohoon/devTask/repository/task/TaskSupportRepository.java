package xohoon.devTask.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.task.TaskSupport;

public interface TaskSupportRepository extends JpaRepository<TaskSupport, Long> {
}
