package xohoon.devTask.service.toy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.Toy.Toy;
import xohoon.devTask.repository.toy.ToyRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToyService {
    private final ToyRepository toyRepository;

    @Transactional
    public void saveToy(Toy toy, Member member) {
        toy.setMember(member);
        toyRepository.save(toy);
    }

    public List<Toy> getToyByMemberId(Long id) {
        return toyRepository.findAllByMember_id(id);
    }
}
