package xohoon.devTask.service.toy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.Toy.Toy;
import xohoon.devTask.domain.entity.Toy.ToyDetail;
import xohoon.devTask.domain.entity.task.TaskDetail;
import xohoon.devTask.repository.toy.ToyDetailRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToyDetailService {
    private final ToyDetailRepository toyDetailRepository;

    @Transactional
    public void saveToyDetail(ToyDetail toyDetail, Toy toy) {
        toyDetail.setToy(toy);
        toyDetailRepository.save(toyDetail);
    }

    public ToyDetail getToyDetailById(Long id) {
        return toyDetailRepository.findById(id).orElse(new ToyDetail());
    }
}
