package xohoon.devTask.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xohoon.devTask.domain.entity.task.TaskDetail;

import java.util.Collection;
import java.util.List;

public interface TaskDetailRepository extends JpaRepository<TaskDetail, Long> {

    @Query("select td from TaskDetail td where td.task in :taskIds")
    List<TaskDetail> findAllByTaskTd_id(@Param("taskIds")List<Long> taskIds);
}
