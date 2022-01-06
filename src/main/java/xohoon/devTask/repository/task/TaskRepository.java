package xohoon.devTask.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.task.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findAllByCom_id(Long id);
}
