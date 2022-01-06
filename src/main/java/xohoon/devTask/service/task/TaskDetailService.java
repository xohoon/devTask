package xohoon.devTask.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.domain.entity.task.TaskDetail;
import xohoon.devTask.repository.task.TaskDetailRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskDetailService {

    private final TaskDetailRepository taskDetailRepository;

    @Transactional
    public void saveTaskDetail(TaskDetail taskDetail, Task task) {
        taskDetail.setTask(task);
        taskDetailRepository.save(taskDetail);
    }

    public List<TaskDetail> getTaskDetails(List<Long> taskIds) {
        return taskDetailRepository.findAllByTask_id(taskIds);
    }
}
