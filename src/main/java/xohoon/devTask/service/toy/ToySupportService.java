package xohoon.devTask.service.toy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.Toy.ToyDetail;
import xohoon.devTask.domain.entity.Toy.ToySupport;
import xohoon.devTask.repository.toy.ToySupportRepository;

import javax.persistence.EntityManager;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToySupportService {

    private final ToySupportRepository toySupportRepository;
    private final EntityManager em;

    @Transactional
    public void setToySupport(Member member, ToyDetail toyDetail) {
        ToySupport toySupport = new ToySupport();
        toySupport.setMember(member);
        toySupport.setToyDetail(toyDetail);
        toySupportRepository.save(toySupport);
    }

    @Transactional
    public void setStatus(Long id) {
        ToySupport toySupport = em.getReference(ToySupport.class, id);
        toySupport.setSupport_status(1);
        toySupportRepository.save(toySupport);
    }
}
