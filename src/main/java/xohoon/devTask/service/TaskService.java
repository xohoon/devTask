package xohoon.devTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.Task;
import xohoon.devTask.repository.TaskRepository;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public void registerTask(Task task, Member member) {
        task.setMember(member);
        taskRepository.save(task);
    }

    public List<Task> getTaskList(Long id) {
        return taskRepository.findAllByMember_id(id);
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).orElse(new Task());
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getUserTaskList() {
        return taskRepository.findAll();
    }
}
