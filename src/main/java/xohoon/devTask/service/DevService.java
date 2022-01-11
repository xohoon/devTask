package xohoon.devTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.Dev;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.repository.DevRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DevService {

    private final DevRepository devRepository;

    @Transactional
    public void register(Dev dev, Member member) {
        dev.setMember(member);
        devRepository.save(dev);
    }

    public Dev getDevByMemberId(Long id) {
        return devRepository.findByMember_id(id);
    }

    public Dev getDevById(Long id) {
        return devRepository.findById(id).orElse(new Dev());
    }

    @Transactional
    public void deleteDev(Long id) {
        devRepository.deleteById(id);
    }
}
