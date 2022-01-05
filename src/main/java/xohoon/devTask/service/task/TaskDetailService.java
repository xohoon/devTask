package xohoon.devTask.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.repository.task.TaskDetailRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskDetailService {

    private final TaskDetailRepository taskDetailRepository;
}
