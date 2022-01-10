package xohoon.devTask.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import xohoon.devTask.domain.entity.task.TaskSupport;

import java.util.List;

public interface TaskSupportRepository extends JpaRepository<TaskSupport, Long> {
}
