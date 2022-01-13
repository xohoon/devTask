package xohoon.devTask.service.toy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.Toy.ToyDetail;
import xohoon.devTask.domain.entity.Toy.ToySupport;
import xohoon.devTask.repository.toy.ToySupportRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToySupportService {
    private final ToySupportRepository toySupportRepository;

    @Transactional
    public void setToySupport(Member member, ToyDetail toyDetail) {
        ToySupport toySupport = new ToySupport();
        toySupport.setMember(member);
        toySupport.setToyDetail(toyDetail);
        toySupportRepository.save(toySupport);
    }
}
