package xohoon.devTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.TaskSupport;

public interface TaskSupportRepository extends JpaRepository<TaskSupport, Long> {
}
