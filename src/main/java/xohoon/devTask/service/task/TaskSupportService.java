package xohoon.devTask.service.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.repository.task.TaskSupportRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskSupportService {
    private final TaskSupportRepository taskSupportRepository;
}