package xohoon.devTask.service.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.domain.entity.task.TaskDetail;
import xohoon.devTask.domain.entity.task.TaskSupport;
import xohoon.devTask.repository.task.TaskSupportRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskSupportService {
    private final TaskSupportRepository taskSupportRepository;

    @Transactional
    public void setTaskSupport(Member member, TaskDetail taskDetail) {
        TaskSupport taskSupport = new TaskSupport();
        taskSupport.setMembers(member);
        taskSupport.setTaskDetails(taskDetail);
        taskSupportRepository.save(taskSupport);
    }
}