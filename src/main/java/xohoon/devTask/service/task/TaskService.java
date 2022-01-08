package xohoon.devTask.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.repository.task.TaskRepository;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public void saveTask(Task task, Company company) {
        task.setCompany(company);
        taskRepository.save(task);
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTaskDetails(Long id) {
        return taskRepository.findAllById(Collections.singleton(id));
    }
}
