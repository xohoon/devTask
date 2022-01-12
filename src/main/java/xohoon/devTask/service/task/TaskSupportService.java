package xohoon.devTask.service.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.dto.task.TaskSupportDto;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.domain.entity.task.TaskDetail;
import xohoon.devTask.domain.entity.task.TaskSupport;
import xohoon.devTask.repository.task.TaskSupportRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskSupportService {

    private final TaskSupportRepository taskSupportRepository;
    private final EntityManager em;

    @Transactional
    public void setTaskSupport(Member member, TaskDetail taskDetail) {
        TaskSupport taskSupport = new TaskSupport();
        taskSupport.setMember(member);
        taskSupport.setTaskDetail(taskDetail);
        taskSupportRepository.save(taskSupport);
    }

    public List<TaskSupport> getSupportByMemberId(Long id) {
        return taskSupportRepository.findAllByMember_id(id);
    }

    @Transactional
    public void setStatus(Long id) {
        TaskSupport taskSupport = em.getReference(TaskSupport.class, id);
        taskSupport.setSupport_status(1);
        taskSupportRepository.save(taskSupport);
    }
}