package xohoon.devTask.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xohoon.devTask.domain.entity.task.TaskDetail;

import java.util.Collection;
import java.util.List;

public interface TaskDetailRepository extends JpaRepository<TaskDetail, Long> {
    List<TaskDetail> findAllByTask_id(Long id);
}
