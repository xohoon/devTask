package xohoon.devTask.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xohoon.devTask.domain.entity.task.TaskDetail;

import java.util.Collection;
import java.util.List;

public interface TaskDetailRepository extends JpaRepository<TaskDetail, Long> {
    @Query("select td from TaskDetail td where td.td_id = :td_id")
    TaskDetail findByTd_id(@Param("td_id") Long td_id);
}
