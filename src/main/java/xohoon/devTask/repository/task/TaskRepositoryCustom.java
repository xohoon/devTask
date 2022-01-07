package xohoon.devTask.repository.task;

import xohoon.devTask.domain.entity.task.Task;

import java.util.List;

public interface TaskRepositoryCustom {
    List<Task> findTaskCustom();
}
