package xohoon.devTask.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xohoon.devTask.domain.entity.task.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.task_title = :test")
    List<Task> findAllByTask_title(@Param("test") String test);
}
