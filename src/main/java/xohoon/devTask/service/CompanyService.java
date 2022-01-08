package xohoon.devTask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.domain.entity.task.Task;
import xohoon.devTask.repository.CompanyRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public void register(Company company, Member member) {
        company.setMember(member);
        companyRepository.save(company);
    }

    public Company getCompany(Long id) {
        return companyRepository.findByMember_id(id);
    }

    public Company getCompanyDetail(Long id) {
        return companyRepository.findById(id).orElse(new Company());
    }

    @Transactional
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
