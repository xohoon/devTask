package xohoon.devTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
